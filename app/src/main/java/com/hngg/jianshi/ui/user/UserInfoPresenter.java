package com.hngg.jianshi.ui.user;

import com.hngg.jianshi.data.bean.userinfo.UserInfoBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_DynamicBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_HomeBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_WorksBean;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class UserInfoPresenter extends BasePresenter<UserInfoContract.Model, UserInfoContract.View> {
    private UserInfoModel mModel;
    private UserInfoActivity mRootView;
    private List<String> mTitleList;
    private List<String> mUrlList;

    @Inject
    UserInfoPresenter(UserInfoContract.Model model, UserInfoContract.View rootView) {
        super(model, rootView);
        mRootView = (UserInfoActivity) rootView;
        mModel = (UserInfoModel) model;
        mTitleList = new ArrayList<>();
        mUrlList = new ArrayList<>();
    }


    public void initData() {
        mModel.refresh()
                .subscribe(new BaseObserver<UserInfoBean>() {
                               @Override
                               protected void onSuccess(UserInfoBean o) {
                                   LogUtil.i(TAG, "AAAAAA");
                                   mRootView.setPgcData(o.getPgcInfo());
                                   List<UserInfoBean.TabInfoBean.TabListBean> tabInfoList = o.getTabInfo().getTabList();
                                   for (UserInfoBean.TabInfoBean.TabListBean tabInfo : tabInfoList) {
                                       mTitleList.add(tabInfo.getName());
                                       mUrlList.add(tabInfo.getApiUrl());
                                   }
                                   initFragmentData();
                               }

                               @Override
                               public void onFail(Throwable e) {
                                   LogUtil.e(TAG, e.getMessage());
                                   e.printStackTrace();
                               }
                           }
                );
    }

    private void initFragmentData() {
        mModel.onRefreshHomeData(mUrlList.get(0)).subscribe(new BaseObserver<UserInfo_HomeBean>() {
            @Override
            protected void onSuccess(UserInfo_HomeBean o) {
                mRootView.setTabPageData(mTitleList.get(0), o.getItemList());
            }

            @Override
            public void onFail(Throwable e) {
                e.printStackTrace();
            }
        });

        mModel.onRefreshWorksData(mUrlList.get(1)).subscribe(new BaseObserver<UserInfo_WorksBean>() {
            @Override
            protected void onSuccess(UserInfo_WorksBean o) {
                mRootView.setTabPageData(mTitleList.get(1), o.getItemList());
            }

            @Override
            public void onFail(Throwable e) {
                e.printStackTrace();
            }
        });

        mModel.onRefreshDynamicData(mUrlList.get(2)).subscribe(new BaseObserver<UserInfo_DynamicBean>() {
            @Override
            protected void onSuccess(UserInfo_DynamicBean o) {
                mRootView.setTabPageData(mTitleList.get(2), o.getItemList());
            }

            @Override
            public void onFail(Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
