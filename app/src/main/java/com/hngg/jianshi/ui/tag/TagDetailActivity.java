package com.hngg.jianshi.ui.tag;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerTagDetailComponent;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class TagDetailActivity extends BaseActivity<TagDetailPresenter>
        implements TagDetailContract.View {
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTagDetailComponent.builder().appComponent(appComponent)
                .tagDetailModule(new TagDetailModule(this)).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_tagdetail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
