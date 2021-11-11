package com.nedhuo.lib_base.base;

public class BasePresenter<V extends IView> implements IPresenter {


    @Override
    public void unBindView() {
        cancelRequest();
    }

    @Override
    public void cancelRequest() {
        //TODO 关闭所有网络请求
    }
}
