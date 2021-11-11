package com.nedhuo.lib_base.base;

import android.content.Context;

import java.lang.ref.Reference;

public class BasePresenter<V extends IView> implements IPresenter {
    protected CompositeDisposable mDisposables = new CompositeDisposable();
    protected RetrofitClient api = RetrofitClient.getInstance();
    protected Reference<V> MvpRef;
    protected Context mContext;

    @Override
    public void unBindView() {
        cancelRequest();
    }

    /**
     * 当页面销毁时，应该对正在发起的请求进行处理
     * */
    @Override
    public void cancelRequest() {
        //TODO 关闭所有网络请求

    }
}
