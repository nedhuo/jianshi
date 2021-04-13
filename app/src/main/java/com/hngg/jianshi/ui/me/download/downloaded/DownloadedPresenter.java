package com.hngg.jianshi.ui.me.download.downloaded;

import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.mvp.IPresenter;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadedPresenter
        extends BasePresenter<DownloadedContract.Model, DownloadedContract.View> {
    private DownloadedFragment mRootView;

    @Inject
    public DownloadedPresenter(DownloadedContract.Model model, DownloadedContract.View rootView) {
        super(model, rootView);
        mRootView = (DownloadedFragment) rootView;
        mModel = (DownloadedModel) model;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
