package com.hngg.jianshi.ui.home.daily;

import com.blankj.utilcode.util.ToastUtils;
import com.hngg.jianshi.data.bean.home.DailyRootBean;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 19:36
 * Author: nedhuo
 * Description:
 */
public class DailyPresenter extends BasePresenter<DailyContract.Model, DailyContract.View> {
    private DailyFragment mRootView;
    private DailyModel mModel;
    private String mNextUrl;

    @Inject
    DailyPresenter(DailyContract.Model model, DailyContract.View rootView) {
        super(model, rootView);
        mRootView = (DailyFragment) rootView;
        mModel = (DailyModel) model;
    }




    public void onRefresh() {
        mModel.refresh().subscribe(new BaseObserver<DailyRootBean>() {
            @Override
            protected void onSuccess(DailyRootBean dailyRootBean) {
                mNextUrl = dailyRootBean.getNextPageUrl();
                mRootView.setData(dailyRootBean.getItemList(), true, false);
            }

            @Override
            public void onFail(Throwable e) {
                mRootView.setData(null, true, false);
                LogUtil.e(TAG, Objects.requireNonNull(e.getMessage()));
                ToastUtils.showShort(e.getMessage());
            }
        });
    }

    public void onLoadMore() {
        if (mNextUrl != null && !mNextUrl.equals("")) {
            mModel.loadMore(mNextUrl)
                    .subscribe(new BaseObserver<DailyRootBean>() {
                        @Override
                        protected void onSuccess(DailyRootBean dailyRootBean) {
                            mNextUrl = dailyRootBean.getNextPageUrl();
                            mRootView.setData(dailyRootBean.getItemList(), false, false);
                        }

                        @Override
                        public void onFail(Throwable e) {
                            mRootView.setData(null, false, false);
                            LogUtil.e(TAG, Objects.requireNonNull(e.getMessage()));
                            ToastUtils.showShort(e.getMessage());
                        }
                    });
        } else {
            mRootView.setData(null, false, true);
        }
    }

}


