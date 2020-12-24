package com.hngg.jianshi.ui.discover;

import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 16:46
 * Author: nedhuo
 * Description:
 */
public class DisCoverPresenter extends BasePresenter<DisCoverContract.Model, DisCoverContract.View> {
    private DisCoverFragment mRootView;

    @Inject
    DisCoverPresenter(DisCoverContract.Model model, DisCoverContract.View rootView) {
        super(model, rootView);
        mRootView = (DisCoverFragment) rootView;
    }
}
