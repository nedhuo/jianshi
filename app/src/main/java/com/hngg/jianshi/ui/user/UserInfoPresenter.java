package com.hngg.jianshi.ui.user;

import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class UserInfoPresenter extends BasePresenter<UserInfoContract.Model, UserInfoContract.View> {

    @Inject
    UserInfoPresenter(UserInfoContract.Model model, UserInfoContract.View rootView) {
        super(model, rootView);
        mRootView = (UserInfoActivity) rootView;
        mModel = (UserInfoModel) model;
    }
}