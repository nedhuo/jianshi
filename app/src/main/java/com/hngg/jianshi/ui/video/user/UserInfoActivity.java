package com.hngg.jianshi.ui.video.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data: 该页面数据https
 */
public class UserInfoActivity extends BaseActivity<UserInfoPresenter>
        implements UserInfoContract.View {

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

    @Override
    public void showMessage(@NonNull String message) {

    }
}
