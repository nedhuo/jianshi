package com.hngg.jianshi.ui.user;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.userinfo.UserInfoBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_DynamicBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_HomeBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_WorksBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class UserInfoModel extends BaseModel implements UserInfoContract.Model {

    private final KaiYanHttpUtil mHttpUtil;

    @Inject
    public UserInfoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        mHttpUtil = new KaiYanHttpUtil();
    }

    UserInfoBean loadMore() {
        return null;
    }

    Observable<UserInfoBean> refresh() {
        return mHttpUtil.getService(ApiInterface.class)
                .getUserInfo()
                .compose(mHttpUtil.applySchedulers());
    }

    ObservableSource<UserInfo_HomeBean> onRefreshHomeData(String s) {
        return  mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_Home()
                .compose(mHttpUtil.applySchedulers());
    }

    ObservableSource<UserInfo_WorksBean> onRefreshWorksData(String s) {
        return  mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_Works()
                .compose(mHttpUtil.applySchedulers());
    }

    public ObservableSource<UserInfo_DynamicBean> onRefreshDynamicData(String s) {
        return  mHttpUtil.getService(ApiInterface.class)
                .getUserInfo_Dynamics()
                .compose(mHttpUtil.applySchedulers());
    }
}
