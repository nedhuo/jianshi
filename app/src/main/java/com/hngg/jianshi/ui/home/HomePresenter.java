package com.hngg.jianshi.ui.home;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import com.hngg.jianshi.R;
import com.hngg.jianshi.ui.home.daily.DailyFragment;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 16:28
 * Author: nedhuo
 * Description:
 */
public class HomePresenter extends BasePresenter {
    private HomeFragment mRootView;

    @Inject
    HomePresenter(HomeFragment rootView) {
        mRootView = rootView;
    }

    /**
     * TabLayout
     * ViewPager
     */
    public void initView() {
        List<String> titles = obtainTitles();
        mRootView.initTabLayout();

        List<Fragment> fragments = obtainPagerList();
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(
                mRootView.getChildFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        };
//        ViewPager.SimpleOnPageChangeListener listener =
//                new ViewPager.SimpleOnPageChangeListener() {
//                    @Override
//                    public void onPageScrolled(int position, float positionOffset,
//                                               int positionOffsetPixels) {
//                        mRootView.setCurrentItem(position);
//                    }
//                };
        mRootView.initViewPager(adapter);

//        FragmentStateAdapter pagerAdapter = new FragmentStateAdapter(mRootView) {
//            @Override
//            public int getItemCount() {
//                return fragments.size();
//            }
//
//            @NonNull
//            @Override
//            public Fragment createFragment(int position) {
//                return fragments.get(position);
//            }
//        };
//        ViewPager2.OnPageChangeCallback callback = new ViewPager2.OnPageChangeCallback(){
//            @Override
//            public void onPageSelected(int position) {
//                mRootView.setCurrentItem(position);
//                Log.i(TAG,""+ position);
//            }
//        };
//        mRootView.setPagerAdapter(pagerAdapter,callback);

    }

    //TabList Item
    private List<String> obtainTitles() {
        List<String> titles = new ArrayList<>();
        titles.add(mRootView.getString(R.string.daily));
        titles.add(mRootView.getString(R.string.recommend));
        return titles;
    }

    //Fragment Item
    private List<Fragment> obtainPagerList() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new DailyFragment());
        fragments.add(new DailyFragment());
        return fragments;
    }
}
