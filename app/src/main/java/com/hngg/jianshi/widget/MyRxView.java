package com.hngg.jianshi.widget;

import android.view.View;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;

/**
 * Date: 2021/2/23
 * Timer: 15:35
 * Author: nedhuo
 * Description:
 */
public class MyRxView {
    public static Observable<Object> clicks(View view){
        return new ViewClickObservable(view);
    }


}
