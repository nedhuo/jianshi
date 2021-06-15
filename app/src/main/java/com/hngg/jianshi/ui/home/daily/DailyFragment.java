package com.hngg.jianshi.ui.home.daily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerDailyComponent;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.adapter.VideoCardAdapter;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Date: 2020/11/19
 * Timer: 19:35
 * Author: nedhuo
 * Description:
 */
public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {

    @BindView(R.id.rv_daily)
    RecyclerView mRecyclerView;
    @BindView(R.id.classicsHeader)
    ClassicsHeader mClassicsHeader;
    @BindView(R.id.classicsFooter)
    ClassicsFooter mClassicsFooter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private VideoCardAdapter mAdapter;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerDailyComponent
                .builder()
                .appComponent(appComponent)
                .dailyModule(new DailyModule(this))
                .build()
                .inject(this);
    }


    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            LogUtil.e(TAG, "mPresenter为null");
            return;
        }

        //init 刷新控件
        mRefreshLayout.setRefreshHeader(mClassicsHeader);
        mRefreshLayout.setRefreshFooter(mClassicsFooter);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            mPresenter.onRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            mPresenter.onLoadMore();
        });

        //init RecyclerView 与 数据
        mAdapter = new VideoCardAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mPresenter.onRefresh();
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
    public void setData(List<ItemList> data, boolean isUpdate, boolean noMoreData) {
        if (data != null) {
            if (isUpdate) {
                mAdapter.setData(data, true);
                mRefreshLayout.finishRefresh(0, true, noMoreData);
            } else {
                mAdapter.setData(data, false);
                mRefreshLayout.finishLoadMore(0, true, noMoreData);
            }
        } else {
            if (isUpdate) {
                mRefreshLayout.finishRefresh(0, false, noMoreData);
            } else {
                mRefreshLayout.finishLoadMore(0, false, noMoreData);
            }
        }
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
