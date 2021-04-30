package com.hngg.jianshi.ui.tag;

import com.hngg.jianshi.data.bean.taginfo.TagInfoBean;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class TagDetailPresenter extends BasePresenter<TagDetailContract.Model, TagDetailContract.View> {
    @Inject
    public TagDetailPresenter(TagDetailContract.Model model, TagDetailContract.View view) {
        super(model, view);
        mModel = (TagDetailModel) model;
        mRootView = (TagDetailActivity) view;
    }

    public void initData(long tagId) {
        mModel.onRefresh(tagId).subscribe(new BaseObserver<TagInfoBean>() {
            @Override
            protected void onSuccess(TagInfoBean o) {
                mRootView.setTabInfo(o.getTabInfo());
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
