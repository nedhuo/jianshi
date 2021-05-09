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
import com.hngg.jianshi.ui.me.collection.CollectionActivity;
import com.hngg.jianshi.ui.me.download.DownloadActivity;
import com.hngg.jianshi.ui.me.history.HistoryActivity;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.ll_playInfo)
    LinearLayout ll_playInfo;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
    }

    /**
     * onActivityCreate调用
     */
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ll_myDownload.setOnClickListener(this);
        ll_history.setOnClickListener(this);
        ll_myCollection.setOnClickListener(this);
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_myDownload:
                LogUtil.i(TAG, "ll_myDownload");
                intent = new Intent(this.getActivity(), DownloadActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_myCollection:
                intent = new Intent(this.getActivity(), CollectionActivity.class);
                startActivity(intent);

                break;
            case R.id.ll_history:
                intent = new Intent(this.getActivity(), HistoryActivity.class);
                startActivity(intent);
                LogUtil.i(TAG, "ll_history");

                break;

        }
    }
}
