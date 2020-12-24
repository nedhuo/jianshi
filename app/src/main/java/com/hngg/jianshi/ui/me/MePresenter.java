package com.hngg.jianshi.ui.me;

import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 16:28
 * Author: nedhuo
 * Description:
 */
public class MePresenter extends BasePresenter<MeContract.Model, MeContract.View> {
    private MeFragment mRootView;
    private MeModel mModel;

    @Inject
    MePresenter(MeContract.Model model, MeContract.View rootView) {
        super(model, rootView);
        mRootView = (MeFragment) rootView;
        mModel = (MeModel) model;
    }
}
