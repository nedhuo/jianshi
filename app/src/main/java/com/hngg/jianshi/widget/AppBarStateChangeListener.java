package com.hngg.jianshi.widget;

import android.view.ViewGroup;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.hngg.jianshi.utils.LogUtil;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    private final String TAG = "AppBarStateChangeListener";
    private final int mHeight;

    public AppBarStateChangeListener(CoordinatorLayout coordinator) {
        ViewGroup.LayoutParams layoutParams = coordinator.getLayoutParams();
        mHeight = layoutParams.height;
        LogUtil.i(TAG, mHeight + "高度");
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int totalOffset = 870;
        float precent = Math.abs(verticalOffset) / totalOffset;
        float alpha = Math.round(precent * 255);
        setToolBarAlpha(alpha);
        LogUtil.i(TAG, "verticalOffset" + verticalOffset);
    }


    public abstract void setToolBarAlpha(float alpha);
}
