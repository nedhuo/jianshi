package com.hngg.jianshi.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.hngg.jianshi.R;
//import com.hngg.jianshi.component.DaggerHomeComponent;

import com.hngg.jianshi.component.DaggerHomeComponent;
import com.hngg.jianshi.ui.TourRecommendationActivity;
import com.hngg.jianshi.ui.search.SearchActivity;
import com.hngg.jianshi.widget.CustomViewPager;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Date: 2020/11/19
 * Timer: 16:17
 * Author: nedhuo
 * Description:
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IView {

    @BindView(R.id.tl_home)
    TabLayout mTabLayout;
    @BindView(R.id.vp_home)
    CustomViewPager mViewPager;

    @Inject
    HomePresenter mPresenter;
    @BindView(R.id.iv_rili)
    ImageView ivRili;
    @BindView(R.id.iv_search)
    ImageView ivSearch;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "initData执行");

        ivRili.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, TourRecommendationActivity.class);
            startActivity(intent);
        });
        ivSearch.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, SearchActivity.class);
            startActivity(intent);
        });

        mPresenter.initView();
    }

    /**
     * 这个方法需要调用才能执行
     */
    @Override
    public void setData(@Nullable Object data) {
        Log.i(TAG, "setData执行");
    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    void initViewPager(FragmentPagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
    }

    /**
     * ViewPager移除其他监听，可以添加 OnPageChangeListener
     * 这条代码会移除TabLayout添加的Tab,调用ViewPagerAdapter的getPageTitle()方法
     * 源码详见TabLayout中populateFromPagerAdapter（）方法
     */
    void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
}
