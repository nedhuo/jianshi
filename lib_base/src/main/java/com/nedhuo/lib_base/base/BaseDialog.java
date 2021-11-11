package com.nedhuo.lib_base.base;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.nedhuo.lib_base.R;

public abstract class BaseDialog<VDB extends ViewDataBinding> extends Dialog {

    protected VDB mBinding;

    public BaseDialog(@NonNull Context context) {
        this(context, R.style.BaseDialogStyle);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mBinding = DataBindingUtil.bind(LayoutInflater.from(context).inflate(getLayoutId(), null, false));
        if (mBinding != null) {
            setContentView(mBinding.getRoot());
        }
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        initView();
        initData();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();
}
