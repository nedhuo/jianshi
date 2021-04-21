package com.hngg.jianshi.ui.user;

import com.hngg.jianshi.data.bean.userinfo.UserInfoBean;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

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
    }


    public void initData() {
        mModel.refresh().subscribe(new BaseObserver<UserInfoBean>() {
            @Override
            protected void onSuccess(UserInfoBean o) {
                LogUtil.i(TAG,"AAAAAA");
                mRootView.setPgcData(o.getPgcInfo());
            }

            @Override
            public void onFail(Throwable e) {
                LogUtil.e(TAG,e.getMessage());
                e.printStackTrace();
            }
        });


//                .flatMap((Function<UserInfoBean, ObservableSource<UserInfo_First_Bean>>) userInfo -> {
//                    mRootView.setPgcData(userInfo.getPgcInfo());
//                    List<UserInfoBean.TabInfoBean.TabListBean> tabInfoList =
//                            userInfo.getTabInfo().getTabList();
//                    mTitleList = new ArrayList<>();
//                    mUrlList = new ArrayList<>();
//                    for (UserInfoBean.TabInfoBean.TabListBean tabInfo : tabInfoList) {
//                        mTitleList.add(tabInfo.getName());
//                        mUrlList.add(tabInfo.getApiUrl());
//                    }
//                    if (mUrlList.size() < 1) {
//                        LogUtil.e(TAG, "UserInfo页面Fragment数据<1");
//                        return null;
//                    }
//                    return mModel.onRefreshFirstData(mUrlList.get(0));
//                }).flatMap((Function<UserInfo_First_Bean, ObservableSource<UserInfo_Works_Bean>>) firstBean -> {
//            mRootView.setTabPageData(mTitleList.get(0), firstBean);
//            if (mUrlList.size() < 2) {
//                LogUtil.e(TAG, "UserInfo页面Fragment数据<2");
//                return null;
//            }
//            return mModel.onRefreshWorksData(mUrlList.get(1));
//        }).flatMap((Function<UserInfo_Works_Bean, ObservableSource<UserInfo_Dynamic_Bean>>) worksBean -> {
//            mRootView.setTabPageData(mTitleList.get(1), worksBean);
//            if (mUrlList.size() < 3) {
//                LogUtil.e(TAG, "UserInfo页面Fragment数据<3");
//                return null;
//            }
//            return mModel.onRefreshDynamicData(mUrlList.get(2));
//        }).subscribe(new BaseObserver<UserInfo_Dynamic_Bean>() {
//            @Override
//            protected void onSuccess(UserInfo_Dynamic_Bean dynamicBean) {
//                mRootView.setTabPageData(mTitleList.get(2), dynamicBean);
//            }
//
//            @Override
//            public void onFail(Throwable e) {
//
//            }
//        });
//                .subscribe(new BaseObserver<UserInfoBean>() {
//
//
//                    @Override
//                    protected void onSuccess(UserInfoBean userInfo) {
//                        mRootView.setPgcData(userInfo.getPgcInfo());
//                        List<UserInfoBean.TabInfoBean.TabListBean> tabInfoList =
//                                userInfo.getTabInfo().getTabList();
//                        mTabList = new HashMap<>();
//                        for (UserInfoBean.TabInfoBean.TabListBean tabInfo : tabInfoList) {
//                            mTabList.put(tabInfo.getName(), tabInfo.getApiUrl());
//                        }
//                    }
//
//                    @Override
//                    public void onFail(Throwable e) {
//                        mRootView.showErrorPage();
//                    }
//                });
    }
}
