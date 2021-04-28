package com.hngg.jianshi.ui.user;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.userinfo.UserInfoBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

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


    Observable<UserInfoBean> refresh(long id, String userType) {
        return mHttpUtil.getService(ApiInterface.class)
                .getUserInfo(id, userType)
                .compose(mHttpUtil.applySchedulers());
    }

//    ObservableSource<UserInfo_HomeBean> onRefreshHomeData(String s) {
//        return  mHttpUtil.getService(ApiInterface.class)
//                .getUserInfo_Home(s)
//                .compose(mHttpUtil.applySchedulers());
//    }
//
//    ObservableSource<UserInfo_WorksBean> onRefreshWorksData(String s) {
//        return  mHttpUtil.getService(ApiInterface.class)
//                .getUserInfo_Works()
//                .compose(mHttpUtil.applySchedulers());
//    }
//
//    public ObservableSource<UserInfo_DynamicBean> onRefreshDynamicData(String s) {
//        return  mHttpUtil.getService(ApiInterface.class)
//                .getUserInfo_Dynamics(s)
//                .compose(mHttpUtil.applySchedulers());
//    }
}
