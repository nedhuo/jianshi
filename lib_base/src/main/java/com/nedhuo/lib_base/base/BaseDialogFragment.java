package com.nedhuo.lib_base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import com.nedhuo.lib_common.wight.LoadingDialog;

/**
 *
 */
public abstract class BaseDialogFragment<P extends IPresenter, VDB extends ViewDataBinding>
        extends DialogFragment implements IView{

    protected P mPresenter;
    private LoadingDialog mLoadingDialog;
    private VDB mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // ARouter.getInstance().inject(this);
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            initArgs(getArguments());
        }

        initView();
        initListener();
        initData();
    }

    public void initArgs(Bundle arguments) {

    }


    protected abstract void initView();

    protected void initListener() {

    }


    protected abstract void initData();


    protected abstract int getLayoutId();

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onDestroyView() {
        if (mBinding != null) {
            mBinding.unbind();
        }
        super.onDestroyView();
    }

    public void showLoading(String content) {
        if (!isAdded() || getActivity() == null) {
            return;
        }
        if (getActivity() instanceof BaseMvpActivity) {
            ((BaseMvpActivity) getActivity()).showLoading(content);
        }
    }

    public void disLoading() {
        if (getActivity() instanceof BaseMvpActivity) {
            ((BaseMvpActivity) getActivity()).disLoading();
        }
    }
}
