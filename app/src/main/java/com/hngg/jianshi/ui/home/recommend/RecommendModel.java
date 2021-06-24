package com.hngg.jianshi.ui.home.recommend;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.recommend.RecommendRootBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Date: 2020/11/19
 * Timer: 17:00
 * Author: nedhuo
 * Description: 暂时RepositoryManager并未完善，使用的是自定义的HttpUtil
 */
public class RecommendModel extends BaseModel implements RecommendContract.Model {

    private final KaiYanHttpUtil httpUtil;

    @Inject
    public RecommendModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        httpUtil = new KaiYanHttpUtil();
    }

    public Observable<RecommendRootBean> getRecommendData() {
        return httpUtil.getService(ApiInterface.class)
                .getRecommendData()
                .compose(httpUtil.applySchedulers())
                .compose(httpUtil.exceptionTransformer());
    }

    public Observable<RecommendRootBean> getRecommendNextPage(String urlPath) {
        return httpUtil.getService(ApiInterface.class)
                .getRecommendNextPage(urlPath)
                .compose(httpUtil.applySchedulers())
                .compose(httpUtil.exceptionTransformer());
    }
}
