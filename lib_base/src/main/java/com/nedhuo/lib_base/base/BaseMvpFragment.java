package com.nedhuo.lib_base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseMvpFragment<P extends IPresenter, VDB extends ViewDataBinding>
        extends Fragment implements IView{
    protected VDB mBinding;
    protected P mPresenter;

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

    @Override
    public void onDestroyView() {
        if (mBinding != null) {
            mBinding.unbind();
        }
        super.onDestroyView();
    }

    protected abstract int getLayoutId();


    protected abstract P bindPresenter();

    protected void initView() {
        mPresenter = bindPresenter();
    }

    protected void initListener() {
    }

    protected abstract void initData();


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
