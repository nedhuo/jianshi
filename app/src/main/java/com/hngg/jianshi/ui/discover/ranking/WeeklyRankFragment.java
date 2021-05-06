package com.hngg.jianshi.ui.discover.ranking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseFragment;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeeklyRankFragment extends BaseFragment {
    @BindView(R.id.classicsHeader)
    ClassicsHeader mClassicsHeader;
    @BindView(R.id.rv_rank)
    RecyclerView rvRanking;
    @BindView(R.id.classicsFooter)
    ClassicsFooter mClassicsFooter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private RankingAdapter mAdapter;
    private DailyRankPresenter mPresenter;
    private List<ItemList> data;
    private static final String DAILY_STRATEGY = "monthly";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rank_daily, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        mPresenter = new DailyRankPresenter(this);
        initView();
        mPresenter.onRefresh(DAILY_STRATEGY);
    }

    private void initView() {
        mRefreshLayout.setRefreshHeader(mClassicsHeader);
        mRefreshLayout.setRefreshFooter(mClassicsFooter);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            mPresenter.onRefresh(DAILY_STRATEGY);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {

        });
        mAdapter = new RankingAdapter(getActivity());
        rvRanking.setAdapter(mAdapter);
        rvRanking.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void setData(List<ItemList> data, boolean isUpdate) {
        if (data == null) {
            Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            if (isUpdate) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishRefreshWithNoMoreData();
            }
            return;
        }
        mAdapter.setData(data, isUpdate);

        if (isUpdate) {
            mRefreshLayout.finishLoadMore();
        } else {
            mRefreshLayout.finishRefresh();
        }
    }
}
