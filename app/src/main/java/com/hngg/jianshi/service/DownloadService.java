package com.hngg.jianshi.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.task.DownloadTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.data.datebase.VideoTask;
import com.hngg.jianshi.utils.NotificationUtil;
import com.jess.arms.base.delegate.IFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadService extends Service {
    private static DownloadService mDownloadService;
    private NotificationUtil mNotificationUtil;
    private static final String TAG = "DownloadService";
    private List<DownloadEntity> mTaskList;
    private List<VideoTask> mVideoList;

    @Override
    public void onCreate() {
        super.onCreate();
        Aria.download(this).register();
        mNotificationUtil = NotificationUtil.getInstance(this);
        mTaskList = new ArrayList<>();
        mVideoList = new ArrayList<>();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mTaskList = Aria.download(this).getTaskList();
//        mVideoList = DbManager.getInstance(this).getVideoTaskDao().queryBuilder().list();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Download.onTaskStart
    public void onTaskStart(DownloadTask taskItem) {
        Log.i(TAG, "开始下载");
        int position = indexVideoTask(taskItem);
        if (position == -1) {
            Log.i(TAG, "查询更新数据");


        }
    }


    @Download.onTaskRunning
    public void onTaskRunning(DownloadTask taskItem) {
        Log.i(TAG, "更新通知");
        int position = indexVideoTask(taskItem);
        if (position != -1) {
            mNotificationUtil.updateNotification(taskItem, mVideoList.get(position));
        } else {
            Log.e(TAG, "索引VideoTask定位异常");
            updateVideoTaskData();
            //updateVideoTaskData();
            Log.i(TAG,"VideoList数据"+mVideoList.size());

        }
    }


    @Download.onTaskComplete
    public void onTaskComplete(DownloadTask taskItem) {
        Log.i(TAG, "下载完成");
        int position = indexVideoTask(taskItem);
        if (position != -1) {
            mNotificationUtil.onDownloadSuccess(taskItem, mVideoList.get(position));
        }
    }


    @Download.onTaskFail
    public void onTaskFail(DownloadTask taskItem) {
        Log.i(TAG, "下载失败");
    }

    @Download.onTaskCancel
    public void onTaskCancel(DownloadTask taskItem) {
        Log.i(TAG, "下载取消");
    }


    private int indexVideoTask(DownloadTask taskItem) {

        for (VideoTask videoTask : mVideoList) {
            if (videoTask.getUrl().equals(taskItem.getKey())) {
                return mVideoList.indexOf(videoTask);
            }
        }
        return -1;
    }

    private void updateVideoTaskData() {
        List<VideoTask> list = DbManager.getInstance(this).getVideoTaskDao().queryBuilder().list();
        mVideoList.clear();
        mVideoList.addAll(list);
    }
}