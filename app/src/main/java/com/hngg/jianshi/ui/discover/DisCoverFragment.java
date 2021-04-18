package com.hngg.jianshi.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.adapter.DisCoverAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Date: 2020/11/19
 * Timer: 16:45
 * Author: nedhuo
 * Description:
 */
public class DisCoverFragment extends BaseFragment<DisCoverPresenter>
        implements DisCoverContract.View {

    @Inject
    DisCoverPresenter mPresenter;
    @BindView(R.id.classicsHeader)
    ClassicsHeader mClassicsHeader;
    @BindView(R.id.rv_discover)
    RecyclerView mRvDiscover;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private DisCoverAdapter mAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerDisCoverComponent
//                .builder()
//                .appComponent(appComponent)
//                .disCoverModule(new DisCoverModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //init 刷新控件
        mRefreshLayout.setRefreshHeader(mClassicsHeader);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            assert mPresenter != null;
            mPresenter.getCommunityData(true);
        });


        mPresenter.initRecyclerView();
        mPresenter.getCommunityData(true);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    public void setData(List<ItemList> data, boolean isUpdate) {
        mAdapter.setData(data, isUpdate);
        mAdapter.notifyDataSetChanged();
        if (isUpdate && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    /**
     * mPresenter通知View 没有更多数据了
     */
    public void notifyNoData() {
        mRefreshLayout.setNoMoreData(true);
    }

    public void initRecyclerView(DisCoverAdapter adapter, RecyclerView.LayoutManager layoutManager) {
        mAdapter = adapter;
        //添加头部View

        mRvDiscover.setLayoutManager(layoutManager);
        mRvDiscover.setAdapter(adapter);
    }
}
