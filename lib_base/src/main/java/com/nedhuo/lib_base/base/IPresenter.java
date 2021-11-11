package com.nedhuo.lib_base.base;

public interface IPresenter {

    /**
     * View销毁时负责解绑 防止内存泄漏
     * */
    void unBindView();


    /**
     * 取消所有请求
     * */
    void cancelRequest();
}
