package com.hngg.jianshi.ui.user.home;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_HomeBean;
import com.hngg.network.KaiYanApi;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import javax.inject.Inject;

public class UserInfo_HomePresenter extends BasePresenter {

    private UserInfo_HomeFragment mRootView;
    private final KaiYanHttpUtil mHttpUtil;
    private UserInfo_HomeAdapter mAdapter;
    private String mNextUrl;

    @Inject
    UserInfo_HomePresenter(UserInfo_HomeFragment rootView) {
        mRootView = rootView;
        mHttpUtil = new KaiYanHttpUtil();
    }

    void initRecyclerView() {

    }

    void onRefresh(String url, RefreshLayout refreshlayout) {
        String homeUrl=url;
        if (url.contains(KaiYanApi.baseHttpUrl)){
             homeUrl = homeUrl.replace(KaiYanApi.baseHttpUrl, "");
        }
        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_Home(homeUrl)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_HomeBean>() {
                               @Override
                               protected void onSuccess(UserInfo_HomeBean o) {
                                   mRootView.setRvData(o.getItemList(), true);
                                   mNextUrl = o.getNextPageUrl();
                                   refreshlayout.finishRefresh();
                               }

                               @Override
                               public void onFail(Throwable e) {
                                   refreshlayout.finishRefresh(false);
                               }
                           }
                );
    }

    void onLoadMore(RefreshLayout refreshLayout) {
        if (mNextUrl == null) {
            refreshLayout.setNoMoreData(true);
            return;
        }

        String homeUrl=mNextUrl;
        if (mNextUrl.contains(KaiYanApi.baseHttpUrl)){
            homeUrl = homeUrl.replace(KaiYanApi.baseHttpUrl, "");
        }
        mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_NextHome(homeUrl)
                .compose(mHttpUtil.applySchedulers())
                .subscribe(new BaseObserver<UserInfo_HomeBean>() {
                               @Override
                               protected void onSuccess(UserInfo_HomeBean o) {
                                  mRootView.setRvData(o.getItemList(), false);
                                   mNextUrl = o.getNextPageUrl();
                                   refreshLayout.finishRefresh();
                               }

                               @Override
                               public void onFail(Throwable e) {
                                   refreshLayout.finishLoadMore(false);
                               }
                           }
                );
    }
}
