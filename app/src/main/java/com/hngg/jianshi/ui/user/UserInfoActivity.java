package com.hngg.jianshi.ui.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerUserInfoComponent;
import com.hngg.jianshi.data.bean.userinfo.UserInfoBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_First_Bean;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.jianshi.widget.AppBarStateChangeListener;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import net.lucode.hackware.magicindicator.MagicIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

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

    public void setPgcData(UserInfoBean.PgcInfoBean pgcInfo) {
        GlideUtil.loadImage(this, pgcInfo.getCover(), ivTopImage);
        GlideUtil.loadCircleImage(this, pgcInfo.getIcon(), ivHeadImage);
        tvTitle.setText(pgcInfo.getName());
        tvPersonDesc.setText(pgcInfo.getDescription());
//        tvWorksCount.setText(pgcInfo.getVideoCount());
//        tvFollowCount.setText(pgcInfo.getFollowCount());
//        tvFansCount.setText(pgcInfo.getCollectCount());
    }

    public void setFirstPageData(UserInfo_First_Bean firstBean) {

    }

    public void setTabPageData(String title, Object tabBean) {

    }
}
