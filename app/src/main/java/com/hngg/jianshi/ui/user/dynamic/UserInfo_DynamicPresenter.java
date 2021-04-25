package com.hngg.jianshi.ui.user.dynamic;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_DynamicBean;
import com.hngg.network.KaiYanApi;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import javax.inject.Inject;

public class UserInfo_DynamicPresenter extends BasePresenter {
    private UserInfo_DynamicFragment mRootView;
    private final KaiYanHttpUtil mHttpUtil;
    private String mNextUrl;

    @Inject
    UserInfo_DynamicPresenter(UserInfo_DynamicFragment rootView) {
        mRootView = rootView;
        mHttpUtil = new KaiYanHttpUtil();
    }


    void onRefresh(String url, RefreshLayout refreshLayout) {
        String dynamicUrl = url;
        if (url.contains(KaiYanApi.baseHttpUrl)) {
            dynamicUrl = dynamicUrl.replace(KaiYanApi.baseHttpUrl, "");
        }
        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_Dynamics(dynamicUrl)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_DynamicBean>() {
                               @Override
                               protected void onSuccess(UserInfo_DynamicBean o) {
                                   refreshLayout.finishRefresh();
                                   mRootView.setRvData(o.getItemList(), true);
                                   mNextUrl = o.getNextPageUrl();
                               }

                               @Override
                               public void onFail(Throwable e) {
                                   refreshLayout.finishRefresh(false);
                               }
                           }
                );
    }

    void onLoadMore(RefreshLayout refreshLayout) {
        if (mNextUrl == null) {
            refreshLayout.setNoMoreData(true);
            return;
        }

        String dynamicUrl = mNextUrl;
        if (mNextUrl.contains(KaiYanApi.baseHttpUrl)) {
            dynamicUrl = dynamicUrl.replace(KaiYanApi.baseHttpUrl, "");
        }

        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_NextDynamics(dynamicUrl)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_DynamicBean>() {
                               @Override
                               protected void onSuccess(UserInfo_DynamicBean o) {
                                   refreshLayout.finishLoadMore();
                                   mRootView.setRvData(o.getItemList(), false);
                                   mNextUrl = o.getNextPageUrl();
                               }

                               @Override
                               public void onFail(Throwable e) {
                                   refreshLayout.finishLoadMore(false);
                               }
                           }
                );
    }
}
