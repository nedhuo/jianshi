package com.hngg.jianshi.ui.recommend;

import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 16:18
 * Author: nedhuo
 * Description:
 */
public class RecommendPresent extends BasePresenter<RecommendContract.Model, RecommendContract.View> {
    @Inject
    public RecommendPresent(RecommendContract.Model model, RecommendContract.View rootView) {
        super(model, rootView);
    }
}
