package com.hngg.jianshi.ui.calender;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hngg.jianshi.R;
import com.hngg.jianshi.utils.StatusBarUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

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
        StatusBarUtil.setFontColor(getWindow(), getColor(R.color.color_statusBar_font));
    }

}
