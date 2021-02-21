package com.hngg.jianshi.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerCommunityComponent;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.adapter.CommunityAdapter;
import com.hngg.jianshi.ui.adapter.RecommendAdapter;
import com.hngg.jianshi.ui.adapter.RecyclerViewWrapper;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Date: 2021/2/20
 * Timer: 9:01
 * Author: nedhuo
 * Description:
 */
public class CommunityFragment extends BaseFragment<CommunityPresenter>
        implements CommunityContract.View {
    @BindView(R.id.classicsHeader)
    ClassicsHeader mClassicsHeader;
    @BindView(R.id.rv_community)
    RecyclerView mRv_community;
    @BindView(R.id.classicsFooter)
    ClassicsFooter mClassicsFooter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private RecyclerViewWrapper mAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommunityComponent
                .builder()
                .appComponent(appComponent)
                .communityModule(new CommunityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //init 刷新控件
        mRefreshLayout.setRefreshHeader(mClassicsHeader);
        mRefreshLayout.setRefreshFooter(mClassicsFooter);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            assert mPresenter != null;
            mPresenter.getCommunityData(true);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            assert mPresenter != null;
            mPresenter.getCommunityData(false);
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
        } else if ( mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadMore();
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

    public void initRecyclerView(StaggeredGridLayoutManager layoutManager, RecyclerViewWrapper adapter) {
        mAdapter = adapter;
        //添加头部View
        View headerView1 = LayoutInflater.from(this.getContext())
                .inflate(R.layout.item_community_content, null, false);
        View headerView2 = LayoutInflater.from(this.getContext())
                .inflate(R.layout.item_community_banner, null, false);
        mAdapter.addHeaderView(headerView1);
        mAdapter.addHeaderView(headerView2);
        mRv_community.setLayoutManager(layoutManager);
        mRv_community.setAdapter(adapter);
    }
}
