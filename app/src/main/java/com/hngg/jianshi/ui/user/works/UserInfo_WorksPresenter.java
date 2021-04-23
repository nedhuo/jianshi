package com.hngg.jianshi.ui.user.works;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_DynamicBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_WorksBean;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

public class UserInfo_WorksPresenter extends BasePresenter {
    private UserInfo_WorksFragment mRootView;
    private final KaiYanHttpUtil mHttpUtil;

    @Inject
    UserInfo_WorksPresenter(UserInfo_WorksFragment rootView) {
        mRootView = rootView;
        mHttpUtil = new KaiYanHttpUtil();
    }

    void onRefresh(String url) {
        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_Works()
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_WorksBean>() {
                               @Override
                               protected void onSuccess(UserInfo_WorksBean o) {

                               }

                               @Override
                               public void onFail(Throwable e) {

                               }
                           }
                );
    }

    void onLoadMore(String url){
        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_NextWorks(url)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_DynamicBean>() {
                    @Override
                    protected void onSuccess(UserInfo_DynamicBean o) {

                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }
}
