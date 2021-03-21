package com.hngg.jianshi.widget;

import android.os.Looper;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Date: 2021/2/23
 * Timer: 15:37
 * Author: nedhuo
 * Description:
 */
public class ViewClickObservable extends Observable<Object> {
    private View view;

    public static final Object EVENT = new Object();

    public ViewClickObservable(View view) {
        view = view;
    }

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        MyListener myListener = new MyListener(view, observer);
        observer.onSubscribe(myListener);
        view.setOnClickListener(myListener);
    }


    private class MyListener implements View.OnClickListener, Disposable {
        private final Observer<? super Object> mObserver;
        private final View mView;
        //Atomic就是原子性的意思，即能够保证在高并发的情况下只有一个线程能够访问这个属性值。
        //我们使用 AtomicBoolean 高效并发处理 “只初始化一次” 的功能要求
        private final AtomicBoolean isDisposable = new AtomicBoolean();

        public MyListener(View view, Observer<? super Object> observer) {
            mView = view;
            mObserver = observer;
        }

        @Override
        public void onClick(View v) {
            if (!isDisposed()) {
                mObserver.onNext(EVENT);
            }
        }

        @Override
        public void dispose() {
            //判断是否被取消
            if (isDisposable.compareAndSet(false, true)) {
                //判断是否在UI线程
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    mView.setOnClickListener(null);
                } else {
                    AndroidSchedulers.mainThread().scheduleDirect(() -> {
                        mView.setOnClickListener(null);
                    });
                }
            }
        }

        @Override
        public boolean isDisposed() {
            return isDisposable.get();
        }
    }
}
