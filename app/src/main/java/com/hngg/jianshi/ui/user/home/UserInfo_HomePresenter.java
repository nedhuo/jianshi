package com.hngg.jianshi.ui.user.home;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_HomeBean;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

public class UserInfo_HomePresenter extends BasePresenter {

    private UserInfo_HomeFragment mRootView;
    private final KaiYanHttpUtil mHttpUtil;

    @Inject
    UserInfo_HomePresenter(UserInfo_HomeFragment rootView) {
        mRootView = rootView;
        mHttpUtil = new KaiYanHttpUtil();
    }

    void onRefresh(String url) {
        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_Home(url)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_HomeBean>() {
                               @Override
                               protected void onSuccess(UserInfo_HomeBean o) {

                               }

                               @Override
                               public void onFail(Throwable e) {

                               }
                           }
                );
    }

    void onLoadMore(String url){
        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_NextHome(url)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_HomeBean>() {
                               @Override
                               protected void onSuccess(UserInfo_HomeBean o) {

                               }

                               @Override
                               public void onFail(Throwable e) {

                               }
                           }
                );
    }
}
