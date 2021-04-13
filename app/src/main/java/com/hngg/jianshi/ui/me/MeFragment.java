package com.hngg.jianshi.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hngg.jianshi.R;

import com.hngg.jianshi.component.DaggerMeComponent;
import com.hngg.jianshi.ui.me.download.DownloadActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;

/**
 * Date: 2020/11/19
 * Timer: 16:17
 * Author: nedhuo
 * Description:
 */
public class MeFragment extends BaseFragment<MePresenter> implements MeContract.View, View.OnClickListener {


    @BindView(R.id.ll_myDownload)
    LinearLayout ll_myDownload;
    @BindView(R.id.ll_myCollection)
    LinearLayout ll_myCollection;
    @BindView(R.id.ll_history)
    LinearLayout ll_history;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMeComponent
                .builder()
                .appComponent(appComponent)
                .meModule(new MeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {
        ll_myDownload.setOnClickListener(this);
        ll_history.setOnClickListener(this);
        ll_myCollection.setOnClickListener(this);
    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_myDownload:
                Intent intent = new Intent(this.getActivity(), DownloadActivity.class);
                startActivity(intent);
                break;
        }
    }
}
