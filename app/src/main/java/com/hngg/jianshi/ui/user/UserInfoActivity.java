package com.hngg.jianshi.ui.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerUserInfoComponent;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.data.bean.userinfo.UserInfoBean;
import com.hngg.jianshi.ui.user.dynamic.UserInfo_DynamicFragment;
import com.hngg.jianshi.ui.user.home.UserInfo_HomeFragment;
import com.hngg.jianshi.ui.user.works.UserInfo_WorksFragment;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.jianshi.widget.AppBarStateChangeListener;
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
import butterknife.ButterKnife;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data: 该页面数据https
 */
public class UserInfoActivity extends BaseActivity<UserInfoPresenter>
        implements UserInfoContract.View {

    @BindView(R.id.iv_topImage)
    ImageView ivTopImage;
    @BindView(R.id.iv_headImage)
    ImageView ivHeadImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_personDesc)
    TextView tvPersonDesc;
    @BindView(R.id.tv_worksCount)
    TextView tvWorksCount;
    @BindView(R.id.tv_followCount)
    TextView tvFollowCount;
    @BindView(R.id.tv_fansCount)
    TextView tvFansCount;
    @BindView(R.id.collapsingLayout)
    CollapsingToolbarLayout collapsingLayout;
    @BindView(R.id.place_holder)
    View placeHolder;
    @BindView(R.id.magicIndicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.vp_userInfo)
    ViewPager mViewPager;
    private List<String> mTitleList;
    private List<List<ItemList>> mDataList;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUserInfoComponent
                .builder()
                .appComponent(appComponent)
                .userInfoModule(new UserInfoModule(this))
                .build()
                .inject(this);
    }


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_userinfo;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            LogUtil.e(TAG, "mPresenter为null");
            return;
        }
        mTitleList = new ArrayList<>();
        mDataList = new ArrayList<>();
        mPresenter.initData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener(coordinator) {
            @Override
            public void setToolBarAlpha(float alpha) {
                toolBar.setAlpha(alpha);
                LogUtil.d(TAG, "ALPHA更新");
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    /**
     * 网络数据加载异常
     */
    public void showErrorPage() {

    }

    @SuppressLint("SetTextI18n")
    public void setPgcData(UserInfoBean.PgcInfoBean pgcInfo) {
        GlideUtil.loadImage(this, pgcInfo.getCover(), ivTopImage);
        GlideUtil.loadCircleImage(this, pgcInfo.getIcon(), ivHeadImage);
        tvTitle.setText(pgcInfo.getName());
        tvPersonDesc.setText(pgcInfo.getDescription());
        tvWorksCount.setText("" + pgcInfo.getVideoCount());
        tvFollowCount.setText("" + pgcInfo.getFollowCount());
        tvFansCount.setText("" + pgcInfo.getCollectCount());
    }


    public void setTabPageData(String title, List<ItemList> itemLists) {
        mTitleList.add(title);
        mDataList.add(itemLists);
        if (mTitleList.size() == 3 & mDataList.size() == 3) {
            loadFragments();
        }
    }

    private void loadFragments() {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment());
        fragments.add(new Fragment());
        fragments.add(new Fragment());
        ArrayList<String> titleList = new ArrayList<>(mTitleList);

        int index = 0;
        for (String title : titleList) {
            if (title.equals("首页")) {
                UserInfo_HomeFragment userInfo_homeFragment = new UserInfo_HomeFragment();
                userInfo_homeFragment.setData(mDataList.get(index));
                mTitleList.set(0, title);
                fragments.set(0, userInfo_homeFragment);
            } else if (title.equals("作品")) {
                UserInfo_WorksFragment userInfo_worksFragment = new UserInfo_WorksFragment();
                userInfo_worksFragment.setData(mDataList.get(index));
                mTitleList.set(1, title);
                fragments.set(1, userInfo_worksFragment);
            } else if (title.equals("动态")) {
                UserInfo_DynamicFragment userInfo_dynamicFragment = new UserInfo_DynamicFragment();
                userInfo_dynamicFragment.setData(mDataList.get(index));
                mTitleList.set(2, title);
                fragments.set(2, userInfo_dynamicFragment);
            }
            index++;
        }
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

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView titleView =
                        new ColorTransitionPagerTitleView(context);
                titleView.setNormalColor(Color.GRAY);
                titleView.setSelectedColor(Color.BLACK);
                titleView.setText(mTitleList.get(index));
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

        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
}
