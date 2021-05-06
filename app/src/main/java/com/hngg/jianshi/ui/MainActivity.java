package com.hngg.jianshi.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerMainComponent;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.jianshi.utils.PermissionUtil;
import com.hngg.jianshi.utils.StatusBarUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 一般让 Activity 或 Fragment 实现 Contract 中定义的 View 接口, 供 Presenter 调用对应方法响应 UI,
 * BaseActivity 默认注入 Presenter, 如想使用 Presenter, 必须将范型指定为 Presenter 的实现类
 * (虽然框架只可以指定一个范型, 但是可以自行生成并持有多个 Presenter, 达到复用的目的, 如何复用 Presenter?),
 * 还需要实现 setupActivityComponent 来提供 Presenter 需要的 Component 和 Module (如这个页面逻辑简单,
 * 并不需要 Presenter, 那就不要指定范型, 也不要实现 setupActivityComponent 方法)
 * <p>
 * BaseActivity
 * 1. 提供了Presenter的对象
 * 2. 绑定了ButterKnife
 * ......
 */
public class MainActivity extends BaseActivity<MainPresenter>
        implements EasyPermissions.PermissionCallbacks {

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
        StatusBarUtil.setFontColor(getWindow(), getColor(R.color.color_statusBar_font));

        if (mPresenter == null) {
            LogUtil.i(TAG, "mPresenter为null");
            return;
        }
        assert mPresenter != null;
        mPresenter.initBottomBar();
        mPresenter.initViewPager();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new PermissionUtil().checkPermissions(this);
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      //  EasyPermissions
       //         .onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }


    //权限允许
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {

        }
    }

}