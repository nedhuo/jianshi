package com.hngg.jianshi.ui.user;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.userinfo.UserInfoBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_Dynamic_Bean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_First_Bean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_Works_Bean;
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

    ObservableSource<UserInfo_First_Bean> onRefreshFirstData(String s) {
        return null;
    }

    ObservableSource<UserInfo_Works_Bean> onRefreshWorksData(String s) {
        return null;
    }

    public ObservableSource<UserInfo_Dynamic_Bean> onRefreshDynamicData(String s) {

        return null;
    }
}
