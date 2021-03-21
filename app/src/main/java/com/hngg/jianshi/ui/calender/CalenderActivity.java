package com.hngg.jianshi.ui.calender;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hngg.jianshi.widget.MyRxView;

import com.hngg.network.Observer.BaseObserver;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Date: 2021/2/23
 * Timer: 20:14
 * Author: nedhuo
 * Description:
 */
public class CalenderActivity extends BaseActivity {
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

}
