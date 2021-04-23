package com.hngg.jianshi.ui.user.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerUserInfo_DynamicComponent;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

public class UserInfo_DynamicFragment extends BaseFragment<UserInfo_DynamicPresenter> {
    private List<ItemList> mDataList = new ArrayList<>();


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerUserInfo_DynamicComponent.builder().appComponent(appComponent)
                .userInfo_DynamicModule(new UserInfo_DynamicModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userinfo_dynamic, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {
        if (data!=null){
            try {
                mDataList.addAll((List<ItemList>) data);
                LogUtil.i(TAG, mDataList.size() + "");
            } catch (Exception e) {
                //TODO 请求数据
            }
        }
    }
}
