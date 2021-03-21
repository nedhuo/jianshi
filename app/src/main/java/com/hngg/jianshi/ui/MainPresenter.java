package com.hngg.jianshi.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hngg.jianshi.R;
import com.hngg.jianshi.ui.community.CommunityFragment;
import com.hngg.jianshi.ui.discover.DisCoverFragment;
import com.hngg.jianshi.ui.home.HomeFragment;
import com.hngg.jianshi.ui.me.MeFragment;
import com.hngg.jianshi.ui.home.recommend.RecommendFragment;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Date: 2020/11/18
 * Timer: 18:58
 * Author: nedhuo
 * Description:
 * Presenter 在 MVP 中的大部分作用是实现业务逻辑代码, 从 Model 层获取数据,
 * 在调用 View 层显示数据, 首先必须实现 BasePresenter, 并指定 View 和 Model 的范型,
 * 注意一定要指定 Contract 中定义的接口, Presenter 需要的 View 和 Model,
 *
 * 都使用 Dagger2 来注入, 这样即解藕又方便测试
 * <p>
 * TODO 内存泄漏问题
 */

public class MainPresenter extends BasePresenter {

    private MainActivity mRootView;

    @Inject
    MainPresenter(MainActivity rootView) {
        mRootView = rootView;
    }

    public void initViewPager() {
        List<Fragment> fragments = obtainPager();
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(
                mRootView.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        };
        ViewPager.OnPageChangeListener listener = new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mRootView.setCurrentItem(position);
            }
        };
        mRootView.initViewPager(pagerAdapter,listener);

    }

    public void initBottomBar() {
        BottomNavigationView.OnNavigationItemSelectedListener listener =
                menuItem -> {
                    //ViewPager页面切换
                    //BottomBar颜色大小切换
                    int id = -1;
                    switch (menuItem.getItemId()) {
                        case R.id.item_bottom_home:
                            id = 0;
                            break;
                        case R.id.item_bottom_recommend:
                            id = 1;
                            break;
                        case R.id.item_bottom_discover:
                            id = 2;
                            break;
                        case R.id.item_bottom_me:
                            id = 3;
                            break;
                    }
                    mRootView.setCurrentItem(id);
                    return true;
                };
        mRootView.initBottomView(listener);
    }

    private List<Fragment> obtainPager() {
        List<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new HomeFragment());
        arrayList.add(new CommunityFragment());
        arrayList.add(new DisCoverFragment());
        arrayList.add(new MeFragment());
        return arrayList;
    }
}
