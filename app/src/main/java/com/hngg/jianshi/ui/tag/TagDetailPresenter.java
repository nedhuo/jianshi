package com.hngg.jianshi.ui.tag;

import com.hngg.jianshi.data.bean.taginfo.TagInfoBean;
import com.hngg.jianshi.data.bean.taginfo.TagInfoDynamicBean;
import com.hngg.jianshi.data.bean.taginfo.TagInfoVideosBean;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class TagDetailPresenter extends BasePresenter<TagDetailContract.Model, TagDetailContract.View> {
    private TagDetailModel mModel;
    private TagDetailActivity mRootView;
    private String mVideosNextUrl;
    private String mDynamicNextUrl;

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
                LogUtil.i(TAG, o.toString());
                mRootView.setTabInfo(o.getTabInfo());
                mRootView.setTagInfo(o.getTagInfo());
            }

            @Override
            public void onFail(Throwable e) {
                LogUtil.e(TAG, e.getMessage());
            }
        });
    }

    /**
     * @param position 确定是哪个fragment的请求数据
     */
    public void onRefresh(long tagId, int position) {
        if (position == 0) {
            mModel.onRefreshVideos(tagId).subscribe(new BaseObserver<TagInfoVideosBean>() {
                @Override
                protected void onSuccess(TagInfoVideosBean o) {
                    mVideosNextUrl = o.getNextPageUrl();
                    mRootView.setFragmentData(o.getItemList(), position, true);
                }

                @Override
                public void onFail(Throwable e) {
                    mRootView.setFragmentData(null, position, true);
                    LogUtil.e(TAG, e.getMessage());
                }
            });
        } else if (position == 1) {
            mModel.onRefreshDynamic(tagId).subscribe(new BaseObserver<TagInfoDynamicBean>() {

                @Override
                protected void onSuccess(TagInfoDynamicBean o) {
                    mDynamicNextUrl = o.getNextPageUrl();
                    mRootView.setFragmentData(o.getItemList(), position, true);
                }

                @Override
                public void onFail(Throwable e) {
                    mRootView.setFragmentData(null, position, true);
                    LogUtil.e(TAG, e.getMessage());
                }
            });
        } else {
            mRootView.setFragmentData(null, position, true);
        }
    }


    public void onLoadMore(int position) {
        if (position == 0) {
            if (mVideosNextUrl != null && !mVideosNextUrl.equals("")) {
                mModel.onLoadMoreVideos(mVideosNextUrl).subscribe(new BaseObserver<TagInfoVideosBean>() {
                    @Override
                    protected void onSuccess(TagInfoVideosBean o) {
                        mDynamicNextUrl = o.getNextPageUrl();
                        mRootView.setFragmentData(o.getItemList(), position, false);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        mRootView.setFragmentData(null, position, false);
                        LogUtil.e(TAG, e.getMessage());
                    }
                });
            } else {
                mRootView.setFragmentData(null, position, false);
            }
        } else if (position == 1) {
            if (mDynamicNextUrl != null && !mDynamicNextUrl.equals("")) {
                mModel.onLoadMoreDynamic(mDynamicNextUrl).subscribe(new BaseObserver<TagInfoDynamicBean>() {
                    @Override
                    protected void onSuccess(TagInfoDynamicBean o) {
                        mDynamicNextUrl = o.getNextPageUrl();
                        mRootView.setFragmentData(o.getItemList(), position, false);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        mRootView.setFragmentData(null, position, false);
                        LogUtil.e(TAG, e.getMessage());
                    }
                });
            } else {
                mRootView.setFragmentData(null, position, false);
            }
        }
    }


    /**
     * 处理无数据情况
     * */
    public void onRefresh(String url) {
        mModel.onRefresh(url).subscribe(new BaseObserver<TagInfoVideosBean>() {
            @Override
            protected void onSuccess(TagInfoVideosBean o) {
                mRootView.setFragmentData(o.getItemList(), 0, true);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
