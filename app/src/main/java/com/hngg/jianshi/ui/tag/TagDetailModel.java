package com.hngg.jianshi.ui.tag;

import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.taginfo.TagInfoBean;
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
                .getTagDetailBean(tagId).compose(mHttpUtil.applySchedulers());
    }
}
