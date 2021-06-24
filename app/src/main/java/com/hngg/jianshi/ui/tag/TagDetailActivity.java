package com.hngg.jianshi.ui.tag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerTagDetailComponent;
import com.hngg.jianshi.data.RandomData;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.data.bean.taginfo.TagInfoBean;
import com.hngg.jianshi.ui.tag.dynamic.TagDetail_DynamicFragment;
import com.hngg.jianshi.ui.tag.video.TagDetail_VideoFragment;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.jianshi.utils.StatusBarUtil;
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
 * @Data:
 */
public class TagDetailActivity extends BaseActivity<TagDetailPresenter>
        implements TagDetailContract.View {

    @BindView(R.id.magicIndicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.vp_tagDetail)
    ViewPager mViewPager;
    @BindView(R.id.ib_back)
    ImageButton mIbBack;
    @BindView(R.id.iv_topImage)
    ImageView mTopImage;
    @BindView(R.id.tv_tagDesc)
    TextView mTvDesc;
    @BindView(R.id.tv_tagTitle)
    TextView mTvTitle;
    @BindView(R.id.ib_share)
    ImageButton ibShare;
    @BindView(R.id.collapsingLayout)
    CollapsingToolbarLayout collapsingLayout;
    private TagDetail_VideoFragment mVideoFragment;
    private TagDetail_DynamicFragment mDynamicFragment;
    private long mTagId;
    private String mTagTitle;
    private String mTagDesc;
    private List<String> mTitleData;
    private List<String> mUrlData;
    //title,data
    private List<Fragment> mFragments;
    private Handler mHandler = new Handler(msg -> {
        if (mPresenter != null) {
            switch (msg.what) {
                case 101:
                    //Video页面更新
                    mPresenter.onRefresh(mTagId, 0);
                    break;
                case 102:
                    //Video页面加载更多
                    mPresenter.onLoadMore(0);
                    break;
                case 201:
                    //Dynamic页面更新
                    mPresenter.onRefresh(mTagId, 1);
                    break;
                case 202:
                    //Dynamic页面加载更多
                    mPresenter.onLoadMore(1);
                    break;
            }

        }
        return false;
    });


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTagDetailComponent
                .builder()
                .appComponent(appComponent)
                .tagDetailModule(new TagDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_tagdetail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        StatusBarUtil.setTransparent(this);
        ButterKnife.bind(this);

        mIbBack.setOnClickListener(v -> onBackPressed());

        if (mPresenter == null) {
            LogUtil.i(TAG, "mPresenter为null");
            return;
        }
        /*接收数据*/
        Bundle bundleExtra = getIntent().getBundleExtra(Constant.TAGDETAIL_BUNDLE);
        if (bundleExtra != null) {
            mTagId = bundleExtra.getLong(Constant.TAGDETAIL_BEAN);
            mTagTitle = bundleExtra.getString(Constant.TAGDETAIL_TITLE);
            mTagDesc = bundleExtra.getString(Constant.TAGDETAIL_DESC);
            if (mTagId == -1) {
                LogUtil.e(TAG, "接收数据为null");
                return;
            }
        } else {
            LogUtil.e(TAG, "接收数据为null");
            return;
        }
        mTitleData = new ArrayList<>();
        mUrlData = new ArrayList<>();
        mFragments = new ArrayList<>();
        mFragments.add(null);
        mFragments.add(null);

        mPresenter.initData(mTagId);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void setTabInfo(TagInfoBean.TabInfo tabInfo) {
        LogUtil.i(TAG, tabInfo.toString());
        List<TagInfoBean.TabList> tabList = tabInfo.getTabList();
        for (TagInfoBean.TabList tab : tabList) {
            mTitleData.add(tab.getName());
            mUrlData.add(tab.getApiUrl());
        }
        LogUtil.i(TAG, mUrlData.get(0));
        if (mPresenter != null) {
            mPresenter.onRefresh(mTagId, 0);
            mPresenter.onRefresh(mTagId, 1);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setTagInfo(TagInfoBean.TagInfo tagInfo) {
        if (tagInfo == null) {
            LogUtil.e(TAG, "tagInfo数据为null");
            String bg = RandomData.obtainUserInfoBg();
            StatusBarUtil.setFontColorByImage(this, bg);
            GlideUtil.loadImage(this, bg, mTopImage);
            if (mTagTitle != null && !mTagTitle.equals("")) {
                mTvTitle.setText("#" + mTagTitle);
            }
            if (mTagDesc != null && !mTagDesc.equals("")) {
                mTvDesc.setText(mTagDesc);
            }
            return;
        }
        StatusBarUtil.setFontColorByImage(this, tagInfo.getBgPicture());
        GlideUtil.loadImage(this, tagInfo.getBgPicture(), mTopImage);
        LogUtil.i(TAG, tagInfo.toString());
        mTvTitle.setText("#" + tagInfo.getName());
        mTvDesc.setText(tagInfo.getDescription());
    }

    public void setFragmentData(List<ItemList> itemList, int position, boolean isUpdate) {
        if (position == 0) {
            if (mVideoFragment == null) {
                if (itemList == null || itemList.size() == 0) {
                    //加载随机数据
                    mPresenter.onRefresh(RandomData.obtainTagDetailVideosData());
                    return;
                }
                mVideoFragment = new TagDetail_VideoFragment();
                mVideoFragment.setData(itemList, isUpdate);
                //设置Handler
                mVideoFragment.setHandler(mHandler);
                mFragments.set(0, mVideoFragment);

                if (mFragments.get(0) != null && mFragments.get(1) != null) {
                    initFragmentPage();
                }
            } else {
                mVideoFragment.setData(itemList, isUpdate);
            }
        } else if (position == 1) {
            if (mDynamicFragment == null) {
                mDynamicFragment = new TagDetail_DynamicFragment();
                mDynamicFragment.setData(itemList, isUpdate);
                mDynamicFragment.setHandler(mHandler);
                mFragments.set(1, mDynamicFragment);

                if (mFragments.get(0) != null && mFragments.get(1) != null) {
                    initFragmentPage();
                }
            } else {
                mDynamicFragment.setData(itemList, isUpdate);
            }
        }


    }

    private void initFragmentPage() {

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleData.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView titleView =
                        new ColorTransitionPagerTitleView(context);
                titleView.setNormalColor(Color.GRAY);
                titleView.setSelectedColor(Color.BLACK);
                titleView.setText(mTitleData.get(index));
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
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });


        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
