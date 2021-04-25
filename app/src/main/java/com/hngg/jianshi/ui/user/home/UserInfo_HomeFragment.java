package com.hngg.jianshi.ui.user.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerUserInfo_HomeComponent;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.widget.RVWrapperWidget;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UserInfo_HomeFragment extends BaseFragment<UserInfo_HomePresenter> {

    @BindView(R.id.rv_userInf_home)
    RecyclerView mRecyclerView;
    @BindView(R.id.classicsHeader)
    ClassicsHeader mClassicsHeader;
    @BindView(R.id.classicsFooter)
    ClassicsFooter mClassicsFooter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private List<ItemList> mDataList = new ArrayList<>();
    private String mDataUrl;
    private UserInfo_HomeAdapter mAdapter;
    private RVWrapperWidget<UserInfo_HomeAdapter> mAdapterWrapper;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerUserInfo_HomeComponent.builder().appComponent(appComponent)
                .userInfo_HomeModule(new UserInfo_HomeModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userinfo_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            String[] stringArray;
            stringArray = getArguments().getStringArray(Constant.USERINFO_HOME_BEAN);
            assert stringArray != null;
            mDataUrl = stringArray[1];
        }


        mRefreshLayout.setRefreshHeader(mClassicsHeader);
        mRefreshLayout.setRefreshFooter(mClassicsFooter);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            if (mPresenter != null && mDataUrl != null) {
                mPresenter.onRefresh(mDataUrl, refreshlayout);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            assert mPresenter != null;
            mPresenter.onLoadMore(refreshlayout);
        });
        mRefreshLayout.autoRefresh();

        mAdapter = new UserInfo_HomeAdapter(getActivity());
        mAdapterWrapper = new RVWrapperWidget<>(mAdapter, getActivity());
        mAdapterWrapper.addHeaderView(new View(getActivity()));
        mAdapterWrapper.addHeaderView(new View(getActivity()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapterWrapper);

    }

    @Override
    public void setData(@Nullable Object data) {
    }

    public void setRvData(List<ItemList> itemList, boolean isUpdate) {
        if (isUpdate){
            ArrayList<ItemList> list = new ArrayList<>();
            list.add(itemList.remove(0));
            list.add(itemList.remove(0));
            mAdapterWrapper.setData(list,true);
        }
        mAdapter.setData(itemList, isUpdate);
    }
}
