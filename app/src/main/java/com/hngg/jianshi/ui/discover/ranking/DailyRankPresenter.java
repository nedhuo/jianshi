package com.hngg.jianshi.ui.discover.ranking;

import com.hngg.jianshi.base.BaseFragment;
import com.hngg.jianshi.base.BasePresenter;
import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.discover.RankingBean;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;

public class DailyRankPresenter extends BasePresenter<BaseFragment> {
    private KaiYanHttpUtil mHttpUtil;
    private String mNextPageUrl;

    DailyRankPresenter(BaseFragment fragment) {
        super(fragment);

        mHttpUtil = new KaiYanHttpUtil();
    }


    public void onRefresh(String strategy) {
        mHttpUtil.getService(ApiInterface.class)
                .getRanking(strategy, 10)
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer())
                .subscribe(new BaseObserver<RankingBean>() {
                    @Override
                    protected void onSuccess(RankingBean o) {
                        mRootView.setData(o.getItemList(), true);
                        mNextPageUrl = o.getNextPageUrl();
                    }

                    @Override
                    public void onFail(Throwable e) {
                        LogUtil.e(TAG, e.getMessage());
                        mRootView.setData(null, true);
                    }
                });
    }

    public void onLoadMore(String strategy) {
        if (mNextPageUrl == null || mNextPageUrl.equals("")) {
            mRootView.setData(null, false);
            return;
        }
        mHttpUtil.getService(ApiInterface.class)
                .getRanking(strategy, 10)
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer())
                .subscribe(new BaseObserver<RankingBean>() {
                    @Override
                    protected void onSuccess(RankingBean o) {
                        mRootView.setData(o.getItemList(), true);
                        mNextPageUrl = o.getNextPageUrl();
                    }

                    @Override
                    public void onFail(Throwable e) {
                        mRootView.setData(null, true);
                    }
                });
    }
}
