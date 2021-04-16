package com.hngg.jianshi.ui.video;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.arialyy.aria.core.Aria;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.RelationVideoBean;
import com.hngg.jianshi.data.bean.reply.ReplyRootBean;
import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.data.datebase.VideoTaskInfo;
import com.hngg.jianshi.data.datebase.VideoTaskInfoDao;
import com.hngg.jianshi.data.datebase.VideoTaskState;
import com.hngg.jianshi.service.DownloadService;
import com.hngg.jianshi.ui.adapter.RelationVideoAdapter;
import com.hngg.jianshi.ui.adapter.VideoReplyAdapter;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.FileUtil;
import com.hngg.jianshi.utils.NotificationUtil;
import com.hngg.jianshi.utils.SpUtil;
import com.jess.arms.mvp.BasePresenter;

import java.util.List;

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
    private NotificationUtil mNotificationUtil;

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


        //   mNotificationUtil.onDownloadSuccess(videoTask);
        return false;
    }

    /**
     * 1. 展示通知
     * 2. 查询是否存在该任务
     * 3. 开始下载
     */
    public void onDownloadVideo(Data videoData) {
        Intent intent = new Intent(mRootView, DownloadService.class);
        mRootView.startService(intent);

        VideoTaskInfoDao videoTaskDao = DbManager.getInstance(mRootView).getVideoTaskDao();
        List<VideoTaskInfo> list = videoTaskDao
                .queryBuilder()
                .where(VideoTaskInfoDao.Properties.VideoId.eq(videoData.getId()))
                .list();
        if (list.size() > 0) {
            Log.i(TAG, "当前查询到的数据" + list.get(0).getVideoName());
            Toast.makeText(mRootView, "当前视频已经被下载", Toast.LENGTH_SHORT).show();
            return;
        }

        /*封装下载数据对象*/
        VideoTaskInfo videoTaskInfo = createVideoTaskInfo(videoTaskDao, videoData);
        long taskId = Aria.download(mRootView)
                .load(videoTaskInfo.getUrl())
                .setFilePath(videoTaskInfo.getFilePath() + videoTaskInfo.getVideoName() + ".mp4")
                .create();
        if (taskId == -1) {
            videoTaskDao.delete(videoTaskInfo);
            Toast.makeText(mRootView, "下载失败", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mRootView, "开始下载...", Toast.LENGTH_SHORT).show();
        }

        /*显示通知*/
//        if (mNotificationUtil == null)
//            mNotificationUtil = NotificationUtil.getInstance(mRootView);
//        mNotificationUtil.showNotification(videoTaskInfo);


    }

    private VideoTaskInfo createVideoTaskInfo(VideoTaskInfoDao videoTaskDao, Data videoData) {
        int uniqueId = CommonUtil.generatorUniqueID(mRootView);
        if (uniqueId == 0) {
            /*校验*/
            uniqueId = checkUniqueId(videoTaskDao);
        }

        VideoTaskInfo videoTaskInfo = new VideoTaskInfo();
        videoTaskInfo.setCreateTime(System.currentTimeMillis());
        videoTaskInfo.setDownId(uniqueId);
        videoTaskInfo.setPoster(videoData.getAuthor().getIcon());
        videoTaskInfo.setFilePath(FileUtil.getDownloadPath());
        videoTaskInfo.setTaskState(VideoTaskState.OTHER);
        videoTaskInfo.setUrl(videoData.getPlayUrl());
        videoTaskInfo.setVideoId(videoData.getId());
        videoTaskInfo.setVideoName(videoData.getTitle());

        long insert = videoTaskDao.insertOrReplace(videoTaskInfo);
        Log.i(TAG, "下载数据插入数据库ID" + insert);
        Log.i(TAG, "当前生成ID" + uniqueId);
        return videoTaskInfo;
    }

    /**
     * 当生成int 类型ID为0时，查询数据库进行判断
     * <p>
     * 判断并计算返回真正的downId
     *
     * @param videoTaskDao
     */
    private int checkUniqueId(VideoTaskInfoDao videoTaskDao) {
        List<VideoTaskInfo> videoTaskInfos = videoTaskDao.queryBuilder().list();
        if (videoTaskInfos.size() == 0)
            return 0;
        else {
            Log.i(TAG, "SP文件已破坏，重新计算当前downId");
            /*获取真实唯一ID*/
            List<VideoTaskInfo> list = videoTaskDao.queryBuilder()
                    .orderAsc(VideoTaskInfoDao.Properties.DownId).list();
            int downId = list.get(list.size() - 1).getDownId();
            Log.i(TAG, "重新查找会DownId为" + downId + 1);
            SpUtil.putInt(mRootView, Constant.UNIQUE_ID, downId + 2);
            return downId + 1;
        }

    }


    public void onShare() {

    }
}
