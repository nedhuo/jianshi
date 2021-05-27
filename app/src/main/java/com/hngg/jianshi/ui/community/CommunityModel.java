package com.hngg.jianshi.ui.community;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.community.CommunityRootBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Date: 2021/2/20
 * Timer: 9:02
 * Author: nedhuo
 * Description:
 * 并没有重写框架自带的RepositoryManager,使用的是自己写了一个网络框架
 */
public class CommunityModel extends BaseModel implements CommunityContract.Model {

    private final KaiYanHttpUtil mHttpUtil;

    @Inject
    public CommunityModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        mHttpUtil = new KaiYanHttpUtil();
    }

    public Observable<CommunityRootBean> getCommunityData() {
        return mHttpUtil.getService(ApiInterface.class)
                .getCommunityData()
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer());
    }

    public Observable<CommunityRootBean> getCommunityNextData(String nextUrl) {
        return mHttpUtil.getService(ApiInterface.class)
                .getCommunityNextPage(nextUrl)
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer());
    }

}
