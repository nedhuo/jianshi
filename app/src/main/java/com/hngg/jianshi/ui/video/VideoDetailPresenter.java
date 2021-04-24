package com.hngg.jianshi.ui.video;

import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.arialyy.aria.core.Aria;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.RelationVideoBean;
import com.hngg.jianshi.data.bean.reply.ReplyRootBean;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;
import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.service.DownloadService;
import com.hngg.jianshi.ui.adapter.RelationVideoAdapter;
import com.hngg.jianshi.ui.adapter.VideoReplyAdapter;
import com.hngg.jianshi.utils.DownloadUtil;
import com.hngg.jianshi.utils.LogUtil;
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
        if (true) {
            /*从数据库移除*/
            return false;
        } else {
            /*添加到数据库*/
            return true;
        }
    }


    /**
     * 2. 查询是否存在该任务
     * 3. 开始下载
     */
    public void onDownloadVideo(Data videoData) {
        boolean isDownloaded = DbManager.getInstance(mRootView).getVideoTaskDao()
                .queryIsDownloaded(videoData.getPlayUrl());
        if (isDownloaded) {
            Toast.makeText(mRootView, "当前视频已下载", Toast.LENGTH_SHORT).show();
            return;
        }

        /*开启Service*/
        Intent intent = new Intent(mRootView, DownloadService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mRootView.startForegroundService(intent);
        } else {
            mRootView.startService(intent);
        }

        /*封装下载数据对象*/

        VideoTaskInfo taskInfo = DownloadUtil.createVideoTaskInfo(mRootView, videoData);
        long taskId = Aria.download(mRootView)
                .load(taskInfo.getUrl())
                .setFilePath(taskInfo.getFilePath() + "/" + taskInfo.getVideoName() + ".mp4")
                .create();
        if (taskId != -1) {
            taskInfo.setTaskId(taskId);
            DbManager.getInstance(mRootView).getVideoTaskDao().add(taskInfo);
            Toast.makeText(mRootView, "开始下载...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mRootView, "下载失败", Toast.LENGTH_SHORT).show();
        }
    }


    public void onShare(String raw) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");//设置分享的格式
//        if (mRootView.getPackageName() != null)
//            intent.setPackage(mRootView.getPackageName());
        intent.putExtra(Intent.EXTRA_TEXT, raw);
        //需要使用Intent.createChooser，否则会出现别样的应用选择框，您可以试试
        intent = Intent.createChooser(intent, "Share To");
        try {
            mRootView.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG,"sysShareText Exception:" + e.getMessage());
        }

    }
}
