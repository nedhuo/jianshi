package com.hngg.jianshi.ui.discover;



import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.discover.DisCoverRootBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Date: 2020/11/19
 * Timer: 16:49
 * Author: nedhuo
 * Description:
 */
public class DisCoverModel extends BaseModel implements DisCoverContract.Model {

    private final KaiYanHttpUtil mHttpUtil;

    @Inject
    public DisCoverModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        mHttpUtil = new KaiYanHttpUtil();
    }

    public Observable<DisCoverRootBean> getDisCoverData() {
        return mHttpUtil.getService(ApiInterface.class)
                .getDisCoverData()
                .compose(mHttpUtil.applySchedulers())
                .compose(mHttpUtil.exceptionTransformer());
    }
}
