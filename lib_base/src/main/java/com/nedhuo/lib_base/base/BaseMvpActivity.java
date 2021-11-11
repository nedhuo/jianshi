package com.nedhuo.lib_base.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.nedhuo.lib_common.wight.LoadingDialog;

public abstract class BaseMvpActivity<P extends IPresenter, VDB extends ViewDataBinding>
        extends AppCompatActivity implements IView {

    protected P mPresenter;
    private LoadingDialog mLoadingDialog;
    private VDB mBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mBinding.setLifecycleOwner(this);
//        ARouter.getInstance().inject(this);
//        BarUtils.setStatusBarLightMode(this, isLightMode());
//        BarUtils.transparentStatusBar(this);
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected void initView() {
        mPresenter = bindPresenter();
    }

    protected abstract P bindPresenter();

    protected void initData() {
    }

    public void disLoadings() {
        disLoading();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unBindView();
        }
        super.onDestroy();
    }

    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void showLoading(String content) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void disLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
}