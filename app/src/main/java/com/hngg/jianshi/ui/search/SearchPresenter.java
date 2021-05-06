package com.hngg.jianshi.ui.search;

import com.hngg.jianshi.base.BasePresenter;
import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.SearchBean;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;

public class SearchPresenter<T> extends BasePresenter<T> {
    private KaiYanHttpUtil mHttpUtil;
    private String mNextPageUrl;

    public SearchPresenter(T t) {
        super(t);
        mHttpUtil = new KaiYanHttpUtil();
    }

    public void onRefresh(String query) {
        mHttpUtil.getService(ApiInterface.class)
                .getQueryData(10, query)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<SearchBean>() {
                    @Override
                    protected void onSuccess(SearchBean o) {
                        mNextPageUrl = o.getNextPageUrl();
                    }

                    @Override
                    public void onFail(Throwable e) {
                        LogUtil.e(TAG, e.getMessage());
                    }
                });
    }

    public void onLoadMore() {
        if (mNextPageUrl == null || mNextPageUrl.equals("")) {
            return;
        }
    }
}
