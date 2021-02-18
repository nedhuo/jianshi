package com.hngg.jianshi.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import android.view.MotionEvent;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hngg.jianshi.R;


import com.hngg.jianshi.component.DaggerMainComponent;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;


import butterknife.BindView;

/**
 * 一般让 Activity 或 Fragment 实现 Contract 中定义的 View 接口, 供 Presenter 调用对应方法响应 UI,
 * BaseActivity 默认注入 Presenter, 如想使用 Presenter, 必须将范型指定为 Presenter 的实现类
 * (虽然框架只可以指定一个范型, 但是可以自行生成并持有多个 Presenter, 达到复用的目的, 如何复用 Presenter?),
 * 还需要实现 setupActivityComponent 来提供 Presenter 需要的 Component 和 Module (如这个页面逻辑简单,
 * 并不需要 Presenter, 那就不要指定范型, 也不要实现 setupActivityComponent 方法)
 *
 * BaseActivity
 * 1. 提供了Presenter的对象
 * 2. 绑定了ButterKnife
 * ......
 */
public class MainActivity extends BaseActivity<MainPresenter> {

    @BindView(R.id.vp_main)
    ViewPager mViewPager;

    @BindView(R.id.bnv_main)
    BottomNavigationView mBottomBar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    /**
     * onCreate方法内调用
     */
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        assert mPresenter != null;
        mPresenter.initBottomBar();
        mPresenter.initViewPager();
    }

    /**
     * ViewPager2
     * FragmentStateAdapter adapter, ViewPager2.OnPageChangeCallback callback
     */
    public void initViewPager(FragmentPagerAdapter adapter, ViewPager.OnPageChangeListener callback) {
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(callback);
        mViewPager.setCurrentItem(0);

//        mViewPager.setAdapter(adapter);
//        mViewPager.registerOnPageChangeCallback(callback);
//        mViewPager.dispatchTouchEvent();
//        mViewPager.onInterceptTouchEvent();
//        mViewPager.onTouchEvent();
    }

    public void initBottomView(BottomNavigationView.OnNavigationItemSelectedListener listener) {
        mBottomBar.setOnNavigationItemSelectedListener(listener);
    }

    //ViewPager滚动监听
    public void setCurrentItem(int position) {
        Log.i(TAG, "position:" + position);
        Log.i(TAG, "mViewPager.getCurrentItem() :" + mViewPager.getCurrentItem());

        if (mViewPager.getCurrentItem() != position)
            mViewPager.setCurrentItem(position);

        mBottomBar.getMenu().getItem(0).setChecked(false);
        mBottomBar.getMenu().getItem(1).setChecked(false);
        mBottomBar.getMenu().getItem(2).setChecked(false);
        mBottomBar.getMenu().getItem(3).setChecked(false);
        mBottomBar.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }
}

