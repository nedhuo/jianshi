package com.hngg.jianshi.ui.me.download.downloaded;

import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadedPresenter
        extends BasePresenter {
    private DownloadedFragment mRootView;
    private DownloadedModel mModel;

    @Inject
    DownloadedPresenter(DownloadedContract.Model model, DownloadedContract.View rootView) {
//        super(model, rootView);
        mRootView = (DownloadedFragment) rootView;
        mModel = (DownloadedModel) model;
    }


}
