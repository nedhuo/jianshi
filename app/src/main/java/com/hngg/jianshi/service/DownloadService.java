package com.hngg.jianshi.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
import com.hngg.jianshi.utils.NotificationUtil;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadService extends Service {
    private NotificationUtil mNotificationUtil;
    private static final String TAG = "DownloadService";

    public DownloadService() {
        mNotificationUtil = NotificationUtil.getInstance(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Aria.download(this).register();
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





    @Download.onTaskRunning
    public void onTaskRunning(DownloadTask taskItem) {
        Log.i(TAG, "更新通知");
        mNotificationUtil.updateNotification(taskItem);
    }


    @Download.onTaskComplete
    public void onTaskComplete(DownloadTask taskItem) {
      //  mNotificationUtil.onDownloadSuccess(taskItem);
    }

    @Download.onTaskFail
    public void onTaskFail(DownloadTask taskItem) {
    }


    private void updateNotification(DownloadTask taskItem) {

    }
}
