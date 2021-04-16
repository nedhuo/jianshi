package com.hngg.jianshi.ui.home;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hngg.jianshi.R;
import com.hngg.jianshi.ui.home.daily.DailyFragment;
import com.hngg.jianshi.ui.home.recommend.RecommendFragment;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

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
        FragmentPagerAdapter adapter =
                new FragmentPagerAdapter(
                        mRootView.getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
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

        mRootView.initViewPager(adapter);
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
        fragments.add(new RecommendFragment());
        return fragments;
    }
}
