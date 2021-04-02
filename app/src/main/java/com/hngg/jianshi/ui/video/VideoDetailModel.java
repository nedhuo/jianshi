package com.hngg.jianshi.ui.video;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.home.RelationVideoBean;
import com.hngg.jianshi.data.bean.reply.ReplyRootBean;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Date: 2021/2/16
 * Timer: 18:56
 * Author: nedhuo
 * Description: Model 必须实现 Contract 的 Model 接口, 并且继承 BaseModel,
 * 然后通过 IRepositoryManager 拿到需要的 Service 和 Cache, 为 Presenter 提供需要的数据 (是否使用缓存请自行选择)
 * <p>
 * 使用的是插件的IRepositoryManager
 */
public class VideoDetailModel implements VideoDetailContract.Model {

    private final KaiYanHttpUtil httpUtil;

    @Inject
    public VideoDetailModel() {
        httpUtil = new KaiYanHttpUtil();
    }


    public Observable<ReplyRootBean> getVideoReply(long id) {
        return httpUtil.getService(ApiInterface.class)
                .getVideoReply(id)
                .compose(httpUtil.applySchedulers());
    }

    public Observable<RelationVideoBean> getRelationVideo(long id) {

        return httpUtil.getService(ApiInterface.class)
                .getRelationVideo(String.valueOf(id))
                .compose(httpUtil.applySchedulers());
    }

    @Override
    public void onDestroy() {

    }
}
