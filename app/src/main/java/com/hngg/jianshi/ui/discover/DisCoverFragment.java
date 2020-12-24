package com.hngg.jianshi.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerDisCoverComponent;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 16:45
 * Author: nedhuo
 * Description:
 */
public class DisCoverFragment extends BaseFragment<DisCoverPresenter>
        implements DisCoverContract.View {

    @Inject
     DisCoverPresenter mPresenter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerDisCoverComponent
                .builder()
                .appComponent(appComponent)
                .disCoverModule(new DisCoverModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container,false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
