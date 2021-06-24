package com.hngg.jianshi.ui.me.download;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.arialyy.aria.core.Aria;
import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseActivity;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.ui.me.download.downloaded.DownloadedFragment;
import com.hngg.jianshi.ui.me.download.downloading.DownloadingFragment;
import com.hngg.jianshi.utils.StatusBarUtil;

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
import butterknife.ButterKnife;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadActivity extends BaseActivity {

    @BindView(R.id.indicator_download)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp_download)
    ViewPager mViewPager;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_delete)
    TextView tvDelete;


    private boolean mIsDeleteState = false;
    private DownloadingFragment mDownloadingFragment;
    private DownloadedFragment mDownloadedFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setFontColor(getWindow(), getColor(R.color.color_statusBar_font));
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

        initData();
    }


    public void initData() {
        ibBack.setOnClickListener(v -> onBackPressed());

        CommonNavigator commonNavigator = new CommonNavigator(this);
        String[] stringArray = getResources().getStringArray(R.array.download_page);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return stringArray.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView titleView =
                        new ColorTransitionPagerTitleView(context);
                titleView.setNormalColor(Color.GRAY);
                titleView.setSelectedColor(Color.BLACK);
                titleView.setText(stringArray[index]);

                titleView.setTextSize(14F);
                //   titleView.setTypeface(Typeface.DEFAULT_BOLD);

                titleView.setOnClickListener(view ->
                        mViewPager.setCurrentItem(index));
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);

        List<Fragment> fragments = new ArrayList<>();
        mDownloadingFragment = new DownloadingFragment();
        mDownloadedFragment = new DownloadedFragment();
        fragments.add(mDownloadingFragment);
        fragments.add(mDownloadedFragment);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        ViewPagerHelper.bind(magicIndicator, mViewPager);

        tvDelete.setOnClickListener(v -> {
            //点击切换状态
            mIsDeleteState = !mIsDeleteState;
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem == 0) {
                if (mDownloadingFragment != null) {
                    mDownloadingFragment.setDeleteEditState(mIsDeleteState);
                }
            } else {
                if (mDownloadedFragment != null) {
                    mDownloadedFragment.setDeleteEditState(mIsDeleteState);
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        Aria.download(this).unRegister();
        DbManager.cancel();
        super.onDestroy();
    }

}
