package com.hngg.jianshi.ui.search;

import com.hngg.jianshi.base.BaseActivity;
import com.hngg.jianshi.base.BasePresenter;
import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.SearchBean;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;

public class SearchPresenter extends BasePresenter<BaseActivity> {
    private KaiYanHttpUtil mHttpUtil;
    private String mNextPageUrl;

    public SearchPresenter(BaseActivity t) {
        super(t);
        mHttpUtil = new KaiYanHttpUtil();
    }

    public void onRefresh(String query) {
        mHttpUtil.getService(ApiInterface.class)
                .getQueryData("\"" + query + "\"")
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<SearchBean>() {
                    @Override
                    protected void onSuccess(SearchBean o) {
                        mNextPageUrl = o.getNextPageUrl();
                        mRootView.setData(o.getItemList(), true);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        mRootView.setData(null, true);
                        LogUtil.e(TAG, e.getMessage());
                    }
                });
    }

    public void onLoadMore() {
        if (mNextPageUrl == null || mNextPageUrl.equals("")) {
            mRootView.setData(null, false);
            return;
        }
        mHttpUtil.getService(ApiInterface.class)
                .getQueryNextData(mNextPageUrl)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<SearchBean>() {
                    @Override
                    protected void onSuccess(SearchBean o) {
                        mNextPageUrl = o.getNextPageUrl();
                        mRootView.setData(o.getItemList(), false);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        mRootView.setData(null, false);
                        LogUtil.e(TAG, e.getMessage());
                    }
                });
    }
}
