package com.hngg.jianshi.ui.video;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.RelationVideoBean;
import com.hngg.jianshi.data.bean.reply.ReplyRootBean;
import com.hngg.jianshi.ui.adapter.RelationVideoAdapter;
import com.hngg.jianshi.ui.adapter.VideoReplyAdapter;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Date: 2021/2/16
 * Timer: 18:54
 * Author: nedhuo
 * Description:
 */
public class VideoDetailPresenter extends BasePresenter {
    private VideoDetailActivity mRootView;
    private VideoDetailModel mModel;

    @Inject
    VideoDetailPresenter(VideoDetailModel model,
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
    public Observable<ReplyRootBean> obtainVideoReply(long id) {
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

    /**
     * @return true  表示未收藏，已经将其收藏
     * false 表示已收藏，已取消收藏，或者收藏失败
     */
    boolean checkAndCollection(Data videoData) {
        if (checkIsCollection(videoData)) {
            /*从数据库移除*/
            return false;
        } else {
            /*添加到数据库*/
            return true;
        }
    }

    /**
     * 检查收藏数据库该视频是否存在
     *
     * @return true    表示已经收藏
     * false   表示未收藏
     */
    boolean checkIsCollection(Data videoData) {
        return false;
    }
}
