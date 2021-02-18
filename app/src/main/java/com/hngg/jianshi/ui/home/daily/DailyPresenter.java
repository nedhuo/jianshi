package com.hngg.jianshi.ui.home.daily;

import android.util.Log;

import com.hngg.jianshi.data.bean.home.DailyRootBean;
import com.hngg.jianshi.ui.adapter.VideoCardAdapter;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Date: 2020/11/19
 * Timer: 19:36
 * Author: nedhuo
 * Description:
 */
public class DailyPresenter extends BasePresenter<DailyContract.Model, DailyContract.View> {
    private DailyFragment mRootView;
    private DailyModel mModel;
    private VideoCardAdapter mAdapter;
    private String mNextUrl;

    @Inject
    DailyPresenter(DailyContract.Model model, DailyContract.View rootView) {
        super(model, rootView);
        mRootView = (DailyFragment) rootView;
        mModel = (DailyModel) model;
    }

    public void initView() {
        //先设置Adapter
        mAdapter = new VideoCardAdapter(mRootView);
        mRootView.initRecyclerView(mAdapter);

        //请求数据
        onRefresh(null);
    }

    public void onLoadMore(RefreshLayout refreshlayout) {
        if (mNextUrl != "" && mNextUrl != null) {
            mModel.loadMore(mNextUrl).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<DailyRootBean>() {
                        @Override
                        protected void onSuccess(DailyRootBean dailyRootBean) {
                            mNextUrl = dailyRootBean.getNextPageUrl();
                            mAdapter.setData(dailyRootBean.getItemList());
                            mAdapter.notifyDataSetChanged();

                            refreshlayout.finishLoadMore();
                        }

                        @Override
                        public void onFail(Throwable e) {
                            Timber.e(e);
                        }
                    });
        } else {
            refreshlayout.setNoMoreData(true);
        }

    }

    public void onRefresh(RefreshLayout refreshlayout) {
        mModel.refresh().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<DailyRootBean>() {
                    @Override
                    protected void onSuccess(DailyRootBean dailyRootBean) {
                        mNextUrl = dailyRootBean.getNextPageUrl();
                        mAdapter.removeData();
                        mAdapter.setData(dailyRootBean.getItemList());
                        mAdapter.notifyDataSetChanged();
                        if (refreshlayout != null) {
                            refreshlayout.finishRefresh();
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }
                });

    }

}


