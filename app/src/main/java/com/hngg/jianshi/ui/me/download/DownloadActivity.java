package com.hngg.jianshi.ui.me.download;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
import com.hngg.jianshi.R;

import com.hngg.jianshi.component.DaggerDownloadComponent;
import com.hngg.jianshi.ui.me.download.downloaded.DownloadedFragment;
import com.hngg.jianshi.ui.me.download.downloading.DownloadingFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadActivity extends BaseActivity<DownloadPresenter> {

    @BindView(R.id.indicator_download)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp_download)
    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Aria.download(this).register();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerDownloadComponent
                .builder()
                .appComponent(appComponent)
                .downloadModule(new DownloadModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_download;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        CommonNavigator commonNavigator = new CommonNavigator(this);
        String[] stringArray = getResources().getStringArray(R.array.download_page);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return stringArray.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView =
                        new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(stringArray[index]);
                colorTransitionPagerTitleView.setOnClickListener(view ->
                        mViewPager.setCurrentItem(index));
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);

        List<Fragment> fragments;
        fragments = new ArrayList<Fragment>();
        fragments.add(new DownloadingFragment());
        fragments.add(new DownloadedFragment());

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return false;
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }


    @Download.onTaskRunning
    protected void onTaskRunning(DownloadTask task) {
        Log.i(TAG, "taskRunning");
    }

    @Download.onTaskComplete
    protected void onTaskComplete(DownloadTask task) {
        Log.i(TAG, "taskComplete");
        //在这里处理任务完成的状态
    }

    @Download.onTaskStart
    protected void onTaskStart(DownloadTask taskItem) {

    }

    @Download.onTaskStop
    protected void onTaskStop(DownloadTask taskItem) {
    }

    @Download.onTaskCancel
    protected void onTaskCancel(DownloadTask taskItem) {
    }

    @Download.onTaskFail
    protected void onTaskFail(DownloadTask taskItem) {
    }


}
