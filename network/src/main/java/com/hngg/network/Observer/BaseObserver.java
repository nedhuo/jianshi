package com.hngg.network.Observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Date: 2020/11/23
 * Timer: 20:23
 * Author: nedhuo
 * Description:
 * 对RxJava observer进行一层封装，让其只显示两个方法
 */
public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T o) {
        onSuccess(o);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T o);

    public void onFail(Throwable e) {

    }
}
