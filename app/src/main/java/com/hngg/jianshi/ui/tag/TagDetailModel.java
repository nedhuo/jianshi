package com.hngg.jianshi.ui.tag;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.taginfo.TagInfoBean;
import com.hngg.jianshi.data.bean.taginfo.TagInfoDynamicBean;
import com.hngg.jianshi.data.bean.taginfo.TagInfoVideosBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
class TagDetailModel extends BaseModel implements TagDetailContract.Model {

    private final KaiYanHttpUtil mHttpUtil;

    @Inject
    public TagDetailModel(IRepositoryManager repositoryManager) {

        super(repositoryManager);
        mHttpUtil = new KaiYanHttpUtil();
    }

    @Override
    public Observable<TagInfoBean> onRefresh(long tagId) {
        return mHttpUtil.getService(ApiInterface.class)
                .getTagDetailBean(tagId)
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer());
    }

    public Observable<TagInfoVideosBean> onRefreshVideos(long tagId) {
        return mHttpUtil.getService(ApiInterface.class)
                .getTagDetail_recommend(tagId)
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer());
    }

    public Observable<TagInfoDynamicBean> onRefreshDynamic(long tagId) {
        return mHttpUtil.getService(ApiInterface.class)
                .getTagDetail_dynamic(tagId)
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer());
    }

    public Observable<TagInfoVideosBean> onLoadMoreVideos(String videoNextUrl) {
        return mHttpUtil.getService(ApiInterface.class)
                .getTagDetail_nextVideos(videoNextUrl)
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer());
    }

    public Observable<TagInfoDynamicBean> onLoadMoreDynamic(String dynamicNextUrl) {
        return mHttpUtil.getService(ApiInterface.class)
                .getTagDetail_nextDynamic(dynamicNextUrl)
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer());
    }

    public Observable<TagInfoVideosBean> onRefresh(String url) {
        return mHttpUtil.getService(ApiInterface.class)
                .getTagDetail_nextVideos(url)
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer());
    }
}
