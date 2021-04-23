package com.hngg.jianshi.ui.user.dynamic;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_DynamicBean;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

public class UserInfo_DynamicPresenter extends BasePresenter {
    private UserInfo_DynamicFragment mRootView;
    private final KaiYanHttpUtil mHttpUtil;

    @Inject
    UserInfo_DynamicPresenter(UserInfo_DynamicFragment rootView) {
        mRootView = rootView;
        mHttpUtil = new KaiYanHttpUtil();
    }


    void onRefresh(String url) {
        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_Dynamics(url)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_DynamicBean>() {
                               @Override
                               protected void onSuccess(UserInfo_DynamicBean o) {

                               }

                               @Override
                               public void onFail(Throwable e) {

                               }
                           }
                );
    }

    void onLoadMore(String url){
        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_NextDynamics(url)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_DynamicBean>() {
                               @Override
                               protected void onSuccess(UserInfo_DynamicBean o) {

                               }

                               @Override
                               public void onFail(Throwable e) {

                               }
                           }
                );
    }
}
