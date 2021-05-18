package com.hngg.jianshi.ui.home.recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerRecommendComponent;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.adapter.RecommendAdapter;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2020/11/19
 * Timer: 16:17
 * Author: nedhuo
 * Description:
 */
public class RecommendFragment extends BaseFragment<RecommendPresent>
        implements RecommendContract.View {
    @BindView(R.id.classicsHeader)
    ClassicsHeader mClassicsHeader;
    @BindView(R.id.rv_recommend)
    RecyclerView mRv_recommend;
    @BindView(R.id.classicsFooter)
    ClassicsFooter mClassicsFooter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private final String TAG = "RecommendFragment";
    private RecommendAdapter mAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerRecommendComponent
                .builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            LogUtil.i(TAG, "mPresenter为null");
            return;
        }
        //init 刷新控件
        mRefreshLayout.setRefreshHeader(mClassicsHeader);
        mRefreshLayout.setRefreshFooter(mClassicsFooter);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            assert mPresenter != null;
            mPresenter.obtainRecommendData(true);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            assert mPresenter != null;
            mPresenter.obtainRecommendData(false);
        });

        //init RecyclerView 与 数据
        mPresenter.initRecyclerView();
        mPresenter.obtainRecommendData(true);
    }


    @Override
    public void setData(@Nullable Object data) {
    }

    /**
     * @param isUpdate true 表示首页数据，数据集合清空在添加数据
     *                 false 表示非首页数据，添加到列表
     *                 <p>
     *                 flag
     */
    public void setData(List<ItemList> data, boolean isUpdate) {
        mAdapter.setData(data, isUpdate);
        mAdapter.notifyDataSetChanged();
        if (mRefreshLayout == null) {
            LogUtil.e(TAG, "mRefreshLayout为null");
            return;
        }
        if (isUpdate && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        } else if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    public void initRecyclerView(LinearLayoutManager layoutManager, RecommendAdapter adapter) {
        mAdapter = adapter;
        mRv_recommend.setLayoutManager(layoutManager);
        mRv_recommend.setAdapter(adapter);
    }

    public void notifyNoData() {
        mRefreshLayout.setNoMoreData(true);
    }
}
