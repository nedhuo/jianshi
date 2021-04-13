package com.hngg.jianshi.ui.me.download.downloading;

import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.mvp.IPresenter;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
class DownloadingPresenter
        extends BasePresenter<DownloadingContract.Model, DownloadingContract.View> {

    private final DownloadingFragment mRootView;
    private final DownloadingModel mModel;

    @Inject
    public DownloadingPresenter(DownloadingContract.Model model, DownloadingContract.View rootView) {
        super(model, rootView);
        mRootView = (DownloadingFragment) rootView;
        mModel = (DownloadingModel) model;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
