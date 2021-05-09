package com.hngg.jianshi.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseActivity;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.discover.ranking.RankingAdapter;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.LogUtil;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends BaseActivity {
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.classicsHeader)
    ClassicsHeader mClassicsHeader;
    @BindView(R.id.rv_searchResult)
    RecyclerView rvSearchResult;
    @BindView(R.id.classicsFooter)
    ClassicsFooter mClassicsFooter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private SearchPresenter mPresenter;
    private String mSearchField;
    private RankingAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        mPresenter = new SearchPresenter(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(Constant.SEARCH_BUNDLE);
            if (bundle != null) {
                //    mVideoData = (Data) bundle.getSerializable(Constant.SEARCH_VIDEO_DATA);
                mSearchField = bundle.getString(Constant.SEARCH_FIELD);
            }
        }
    }

    @Override
    protected void initView() {
        mRefreshLayout.setRefreshHeader(mClassicsHeader);
        mRefreshLayout.setRefreshFooter(mClassicsFooter);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            if (mSearchField != null && !mSearchField.equals("")) {
                mPresenter.onRefresh(mSearchField);
            } else {
                mRefreshLayout.finishRefresh();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            assert mPresenter != null;
            mPresenter.onLoadMore();
        });
        ibBack.setOnClickListener(v -> onBackPressed());

        rvSearchResult.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RankingAdapter(this);
        rvSearchResult.setAdapter(mAdapter);

        mPresenter.onRefresh(mSearchField);
    }

    @Override
    public void setData(List<ItemList> itemList, boolean isUpdate) {
        LogUtil.i(TAG, "SHUJU");
        if (itemList == null || itemList.size() == 0) {
            mRefreshLayout.finishRefreshWithNoMoreData();
            mRefreshLayout.finishLoadMoreWithNoMoreData();
            return;
        } else {
            mRefreshLayout.finishLoadMore();
            mRefreshLayout.finishRefresh();
        }
        mAdapter.setData(itemList, isUpdate);
    }
}
