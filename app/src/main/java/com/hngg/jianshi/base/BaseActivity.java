package com.hngg.jianshi.base;

import androidx.appcompat.app.AppCompatActivity;

import com.hngg.jianshi.data.bean.home.ItemList;

import java.util.List;

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

    protected void initView() {
    }

    public void setData(List<ItemList> itemList, boolean isUpdate) {

    }

}
