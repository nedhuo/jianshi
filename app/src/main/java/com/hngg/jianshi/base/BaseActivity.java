package com.hngg.jianshi.base;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onStart() {
        super.onStart();
        initData();
        initView();
    }

    protected void initData() {

    }

    protected void initView(){}
}
