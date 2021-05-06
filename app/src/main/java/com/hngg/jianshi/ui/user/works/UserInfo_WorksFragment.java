package com.hngg.jianshi.ui.user.works;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerUserInfo_WorksComponent;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UserInfo_WorksFragment extends BaseFragment<UserInfo_WorksPresenter> {
    @BindView(R.id.rv_userInf_works)
    RecyclerView mRecyclerView;
    @BindView(R.id.classicsHeader)
    ClassicsHeader mClassicsHeader;
    @BindView(R.id.classicsFooter)
    ClassicsFooter mClassicsFooter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private List<ItemList> mDataList = new ArrayList<>();
    private UserInfo_WorksAdapter mAdapter;
    private String mDataUrl;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerUserInfo_WorksComponent
                .builder()
                .appComponent(appComponent)
                .userInfo_WorksModule(new UserInfo_WorksModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userinfo_works, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            String[] stringArray;
            stringArray = getArguments().getStringArray(Constant.USERINFO_WORKS_BEAN);
            if (stringArray != null) {
                mDataUrl = stringArray[1];
            } else {
                LogUtil.i(TAG, "mDataUrl is null");
                return;
            }
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

        mAdapter = new UserInfo_WorksAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    public void setRvData(List<ItemList> itemList, boolean isUpdate) {
        mAdapter.setData(itemList, isUpdate);
    }
}
