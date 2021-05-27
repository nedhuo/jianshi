package com.hngg.jianshi.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerCommunityComponent;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.adapter.CommunityAdapter;
import com.hngg.jianshi.ui.adapter.RecyclerViewWrapper;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private RecyclerViewWrapper<CommunityAdapter> mAdapterWrapper;
    private CommunityAdapter mCommunityAdapter;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);
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
            mPresenter.getCommunityData();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            mPresenter.onLoadMore();
        });

        initRecyclerView();

        mPresenter.getCommunityData();
    }

    @Override
    public void setData(@Nullable Object data) {
    }


    /**
     * 设置RecyclerView数据
     */
    public void setData(List<ItemList> data, boolean isUpdate, boolean noMoreData) {
        if (data != null) {
            if (isUpdate) {
                List<ItemList> wrapperData = new ArrayList<>();
                wrapperData.add(data.remove(0));
                wrapperData.add(data.remove(0));
                mAdapterWrapper.setData(wrapperData, true);
                mCommunityAdapter.setData(data, true);
                mRefreshLayout.finishRefresh(0, true, noMoreData);
            } else {
                mCommunityAdapter.setData(data, false);
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

    /**
     * mPresenter通知View 没有更多数据了
     */
    public void notifyNoData() {
        mRefreshLayout.setNoMoreData(true);
    }

    public void initRecyclerView() {
        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mCommunityAdapter = new CommunityAdapter(getActivity());
        mAdapterWrapper = new RecyclerViewWrapper<>(mCommunityAdapter, getActivity());

        //添加头部View
        View headerView1 = LayoutInflater.from(this.getContext())
                .inflate(R.layout.item_community_content, null, false);
        View headerView2 = LayoutInflater.from(this.getContext())
                .inflate(R.layout.item_community_banner, null, false);
        mAdapterWrapper.addHeaderView(headerView1);
        mAdapterWrapper.addHeaderView(headerView2);
        mRv_community.setLayoutManager(manager);
        mRv_community.setAdapter(mAdapterWrapper);

        /*不能使用new TabLayout.Tab */
//        TabLayout.Tab tab = mTabLayout.newTab();
//        tab.setText(getResources().getString(R.string.community));
//        mTabLayout.addTab(tab);
//        mTabLayout.setInlineLabel(false);
    }
}
