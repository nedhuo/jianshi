package com.hngg.jianshi.ui.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerDisCoverComponent;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.adapter.DisCoverAdapter;
import com.hngg.jianshi.ui.discover.ranking.RankingActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.ib_ranking)
    ImageButton ibRanking;

    private DisCoverAdapter mAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerDisCoverComponent
                .builder()
                .appComponent(appComponent)
                .disCoverModule(new DisCoverModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {
            //init 刷新控件
            mRefreshLayout.setRefreshHeader(mClassicsHeader);
            mRefreshLayout.setOnRefreshListener(refreshlayout -> {
                mPresenter.getCommunityData();
            });

            initRecyclerView();
            mPresenter.getCommunityData();
        }
        ibRanking.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, RankingActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void setData(@Nullable Object data) {

    }

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


    public void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mAdapter = new DisCoverAdapter(getActivity());
        mRvDiscover.setLayoutManager(layoutManager);
        mRvDiscover.setAdapter(mAdapter);
    }


    @Override
    public void onDestroy() {
        if (mAdapter != null)
            mAdapter.onDestory();
        mAdapter = null;
        super.onDestroy();
    }
}
