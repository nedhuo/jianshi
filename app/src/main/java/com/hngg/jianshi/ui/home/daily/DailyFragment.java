package com.hngg.jianshi.ui.home.daily;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import com.hngg.jianshi.component.DaggerDailyComponent;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;

/**
 * Date: 2020/11/19
 * Timer: 19:35
 * Author: nedhuo
 * Description:
 */
public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {

    @BindView(R.id.rv_daily)
    RecyclerView mRecyclerView;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerDailyComponent
                .builder()
                .appComponent(appComponent)
                .dailyModule(new DailyModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        assert mPresenter != null;
        mPresenter.initView();
    }

    @Override
    public void setData(@Nullable Object data) {
        Log.i(TAG, "setData执行");
    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @Override
    public void initRecyclerView(RecyclerView.Adapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }
}
