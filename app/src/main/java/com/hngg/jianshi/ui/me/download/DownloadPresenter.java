package com.hngg.jianshi.ui.me.download;

import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadPresenter extends BasePresenter {
    DownloadActivity mRootView;

    @Inject
    public DownloadPresenter(DownloadActivity rootView) {
        mRootView = rootView;
    }
}
