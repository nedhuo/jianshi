package com.hngg.jianshi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Date: 2020/11/21
 * Timer: 22:52
 * Author: nedhuo
 * Description:
 */
public class CustomViewPager extends ViewPager {
    protected final String TAG = getClass().getSimpleName();
    private int mLastx;
    private int mLasty;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE: {
//                //当子类View不能滑动时
//                int deltaX = x - mLastx;
//                int deltaY = y = mLasty;
//                Log.i(TAG, "deltaX" + deltaX + "deltaY" + deltaY);
//                Log.i(TAG, "X" + x + "Y" + y);
//                Log.i(TAG, "mLastx" + mLastx + "mLasty" + mLasty);
//                if (父容器需要拦截的事件) {
//                    parent.requestDisallowInterceptTouchEvent(false); //父布局需要要拦截此事件
//                }
//                break;
//            }
//
//        }
//        mLastx = x;
//        mLasty = y;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        Log.i(TAG, "onOverScrolled scrollX" + scrollX + "scrollY" + scrollY);
        Log.i(TAG, "onOverScrolled clampedX" + clampedX + "clampedY" + clampedY);
        if (clampedX) {
            Log.i(TAG, "onOverScrolled clampedX");
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.i(TAG, "overScrollBy deltaX" + deltaX + "deltaY" + deltaY);
        Log.i(TAG, "overScrollBy scrollX" + scrollX + "scrollY" + scrollY);
        Log.i(TAG, "overScrollBy scrollRangeX" + scrollRangeX + "scrollRangeY" + scrollRangeY);
        Log.i(TAG, "overScrollBy maxOverScrollX" + maxOverScrollX + "maxOverScrollY" + maxOverScrollY);
        Log.i(TAG, "overScrollBy isTouchEvent" + isTouchEvent);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }


}
