package com.hngg.jianshi.ui.tag;

import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class TagDetailPresenter extends BasePresenter<TagDetailContract.Model, TagDetailContract.View> {
    @Inject
    public TagDetailPresenter(TagDetailContract.Model model, TagDetailContract.View view) {
        super(model, view);
        mModel = (TagDetailModel) model;
        mRootView = (TagDetailActivity) view;
    }
}
