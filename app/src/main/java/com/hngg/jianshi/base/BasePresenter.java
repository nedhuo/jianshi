package com.hngg.jianshi.base;

import java.lang.ref.WeakReference;

public class BasePresenter<T> {
    protected final String TAG = this.getClass().getSimpleName();
    protected final T mRootView;

    public BasePresenter(T t) {
        /*若引用解决内存泄漏问题*/
        WeakReference<T> weakReference = new WeakReference<>(t);
        mRootView = weakReference.get();
    }


}
