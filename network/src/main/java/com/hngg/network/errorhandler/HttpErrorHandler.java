package com.hngg.network.errorhandler;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Date: 2020/11/23
 * Timer: 22:10
 * Author: nedhuo
 * Description:
 */
public class HttpErrorHandler<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        return Observable.error(ExceptionHandle.handleException(throwable));
    }
}
