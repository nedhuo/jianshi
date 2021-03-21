package com.hngg.jianshi.ui.video;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.hngg.jianshi.data.bean.home.RelationVideoBean;
import com.hngg.jianshi.data.bean.reply.JsonRootBean;
import com.hngg.jianshi.ui.adapter.RelationVideoAdapter;
import com.hngg.jianshi.ui.adapter.VideoReplyAdapter;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Date: 2021/2/16
 * Timer: 18:54
 * Author: nedhuo
 * Description:
 */
public class VideoDetailPresenter {
    VideoDetailActivity mRootView;
    VideoDetailModel mModel;

    @Inject
    public VideoDetailPresenter(VideoDetailModel model,
                                VideoDetailActivity rootView) {
        mRootView = rootView;
        mModel = model;
    }


    /**
     * Presenter持有Activity的对象，Activity也持有Presenter的对象
     * 需要手动去释放，防止引起内存泄漏
     */


    public void initRecyclerView() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mRootView,
                LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        RelationVideoAdapter relationVideoAdapter = new RelationVideoAdapter(mRootView);
        mRootView.initRelationVideoRV(layoutManager1, relationVideoAdapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mRootView) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        VideoReplyAdapter videoReplyAdapter = new VideoReplyAdapter(mRootView);
        mRootView.initVideoReplyRV(layoutManager2, videoReplyAdapter);
    }

    public Observable<RelationVideoBean> obtainRelationVideo(long id) {
        return mModel.getRelationVideo(id);
    }

    /**
     * TODO 视频评论 是有下一页的 思考 如何处理
     */
    public Observable<JsonRootBean> obtainVideoReply(long id) {
        return mModel.getVideoReply(id);
    }


    /**
     * TODO 处理内存泄漏问题
     */
    public void onDestroy() {
        if (mModel != null)
            mModel.onDestroy();
        this.mModel = null;
        this.mRootView = null;
    }
}
