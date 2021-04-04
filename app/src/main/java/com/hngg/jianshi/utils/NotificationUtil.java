package com.hngg.jianshi.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.arialyy.aria.core.task.DownloadTask;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.data.datebase.VideoTask;
import com.hngg.jianshi.data.datebase.VideoTaskDao;
import com.hngg.jianshi.ui.MainActivity;

import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.core.app.NotificationCompat.VISIBILITY_SECRET;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data: 需求：下载时候始终保持一个通知不被杀死
 * <p>
 * 建立两个通知map,其中一个赋予ongoing属性，只存一个通知
 * 另一个可以存储多个通知，但是不设置ongoing属性
 * <p>
 * 当具有ongoing属性的下载完成之后，也就是判断ongoing属性的list无数据之后
 * 另一个list有数据，从另一个list删除索引为0的通知，让这个通知重新新建，添加到ongoing属性list中
 * <p>
 * 如果有办法直接将其具有不死属性，也可以直接设置
 */
public class NotificationUtil {
    private static NotificationUtil mNotificationUtil;
    private final ContextWrapper mCtx;
    private final String mChannelName;
    private final String mChannelId;
    private NotificationManager mNotificationManager;
    private NotificationChannel mNotificationChannel;

    private int importance = NotificationManager.IMPORTANCE_DEFAULT;
    private ConcurrentHashMap<Integer, NotificationCompat.Builder> mOrdinaryMap; //普通通知
    private ConcurrentHashMap<Integer, NotificationCompat.Builder> mKeepAliveMap; //保活通知
    private ConcurrentHashMap<Integer, Notification> mSuccessMap;  //下载成功通知
    private String mIsApplyNotification_sp = "isApplyNotification";
    private static final String TAG = "NotificationUtil";


    public static NotificationUtil getInstance(ContextWrapper context) {
        if (mNotificationUtil == null) {
            synchronized (NotificationUtil.class) {
                if (mNotificationUtil == null)
                    mNotificationUtil = new NotificationUtil(context);
            }
        }
        return mNotificationUtil;
    }

    private NotificationUtil(ContextWrapper context) {
        mCtx = context;
        mChannelName = "download";
        mChannelId = "niceVideo";
        mNotificationChannel = obtainChannel();
        mNotificationManager = obtainManager();
        mOrdinaryMap = new ConcurrentHashMap<>();
        mKeepAliveMap = new ConcurrentHashMap<>();
        mSuccessMap = new ConcurrentHashMap<>();
    }

    private NotificationChannel obtainChannel() {
        if (mNotificationChannel == null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //创建通知渠道，重要程度为NotificationManager.IMPORTANCE_HIGH 紧急

                mNotificationChannel = new NotificationChannel(mChannelId, mChannelName, importance);
                mNotificationChannel.enableLights(false);//是否在桌面icon右上角展示小红点
                mNotificationChannel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏显示通知
                mNotificationChannel.enableVibration(false);//是否允许震动
                mNotificationChannel.setSound(null, null);
                mNotificationChannel.setShowBadge(true);

            }
        return mNotificationChannel;
    }

    private NotificationManager obtainManager() {
        //创建通知
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager)
                    mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        //通知加入通道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //   NotificationChannel channel = obtainChannel();
            mNotificationManager.createNotificationChannel(mNotificationChannel);
        }
        return mNotificationManager;
    }


    public void showNotification(VideoTask videoTask) {
        Notification successBuild = mSuccessMap.get(videoTask.getDownId());
        NotificationCompat.Builder ordinaryBuild = mOrdinaryMap.get(videoTask.getDownId());
        NotificationCompat.Builder keepAliveBuild = mKeepAliveMap.get(videoTask.getDownId());
        if (successBuild != null || ordinaryBuild != null || keepAliveBuild != null) {
            Log.i(TAG, "当前通知已存在");
            return;
        }

        if (mKeepAliveMap.size() == 0) {
            Log.i(TAG, "创建保活通知");
            createKeepAliveNotification(videoTask);
        } else {
            Log.i(TAG, "创建普通通知");
            createOrdinaryNotification(videoTask);
        }
    }


    /**
     * 判断通知权限
     */
    private Boolean checkNotifiPermission() {
        NotificationManagerCompat manager = NotificationManagerCompat.from(mCtx);
        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        boolean isOpened = manager.areNotificationsEnabled();

        if (isOpened) {
            Log.i(
                    "DownloadNotification",
                    "通知权限已经被打开" +
                            "\n手机型号:" + android.os.Build.MODEL +
                            "\nSDK版本:" + android.os.Build.VERSION.SDK +
                            "\n系统版本:" + android.os.Build.VERSION.RELEASE +
                            "\n软件包名:" + mCtx.getPackageName()
            );
            return true;
        } else {
            return false;
        }
    }


    /**
     * @param videoTask 下载任务
     *                  <p>
     *                  处理逻辑：下载成功，判断是否是KeepAliveNotification
     *                  是 -> 移除当前下载通知，从ordianry获取一个通知放入keepAliveNotification中（移除，新建）
     *                  否 -> 切换Notification显示，为下载完成状态
     */
    public void onDownloadSuccess(VideoTask videoTask) {
        int downId = videoTask.getDownId();
        Notification keepAliveNotification = mKeepAliveMap.get(downId).build();
        if (keepAliveNotification != null) {
            mNotificationManager.cancel(downId);
            mKeepAliveMap.remove(downId);
            //TODO 可优化

            if (mKeepAliveMap.size() == 0) {
                if (mOrdinaryMap.size() > 0) {
                    /*需要mOrdinaryMap中排除下载成功状态*/
                    Enumeration<Integer> keys = mOrdinaryMap.keys();
                    int element = keys.nextElement();
                    /*通过element数据库取元素*/
//                    List<VideoTask> videoTaskList = DbManager.getInstance(mCtx)
//                            .getVideoTaskDao()
//                            .queryBuilder()
//                            .where(VideoTaskDao.Properties.DownId.eq(element))
//                            .list();
//                    if (videoTaskList.size() == 1) {
//                        VideoTask videoTask_db = videoTaskList.get(0);
//                        createKeepAliveNotification(videoTask_db);
//                    } else {
//                        Log.e(TAG, "数据库根据DownId查询异常,Notification可能显示异常");
//                    }
                    NotificationCompat.Builder builder = mOrdinaryMap.get(element);
                    if (builder != null) {
                        createKeepAliveNotification(element, builder);
                        mOrdinaryMap.remove(element);
                    }


                }
            }
        } else {
            Notification notification = mOrdinaryMap.get(downId).build();
            if (notification != null) {
                /*切换Map*/
                Enumeration<Integer> keys = mOrdinaryMap.keys();
                Integer key = keys.nextElement();
                Notification value = mOrdinaryMap.get(key).build();
                if (value != null) {
                    mSuccessMap.put(key, value);
                    mOrdinaryMap.remove(key);
                    /*TODO 切换下载成功布局*/

                }
            }
        }
    }

    /**
     * 创建保活通知
     */
    private void createKeepAliveNotification(VideoTask videoTask) {
        if (mKeepAliveMap.size() > 0) {
            createOrdinaryNotification(videoTask);
            return;
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(mCtx.getApplicationContext(), mChannelId);
        RemoteViews remoteViews = new RemoteViews(mCtx.getPackageName(), R.layout.notify_download);
        //TODO 添加view数据
        remoteViews.setTextViewText(R.id.tv_title, "测试测试");

        Intent intent = new Intent(mCtx, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(mCtx, 0, intent, 0);

        builder.setWhen(System.currentTimeMillis())
                //设置小图标（通知栏没有下拉的图标）
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setOngoing(true)  //进行中通知
                .setAutoCancel(false)
                .setCustomContentView(remoteViews)
                .setContentIntent(pendingIntent);

        mKeepAliveMap.put(videoTask.getDownId(), builder);
        Notification build = builder.build();
        mNotificationManager.notify(videoTask.getDownId(), build);
    }

    private void createKeepAliveNotification(int key, NotificationCompat.Builder builder) {
        builder.setOngoing(true);
        if (mKeepAliveMap.size() == 0) {
            mKeepAliveMap.put(key, builder);
        }
    }

    /**
     * 创建普通通知
     */
    private void createOrdinaryNotification(VideoTask videoTask) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(mCtx.getApplicationContext(), mChannelId);
        RemoteViews remoteViews = new RemoteViews(mCtx.getPackageName(), R.layout.notify_download);
        //TODO 添加view数据
        remoteViews.setTextViewText(R.id.tv_title, "测试测试");

        Intent intent = new Intent(mCtx, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(mCtx, 0, intent, 0);

        builder.setWhen(System.currentTimeMillis())
                //设置小图标（通知栏没有下拉的图标）
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                //   .setOngoing(true)  //进行中通知
                .setAutoCancel(true)
                .setCustomContentView(remoteViews)
                .setContentIntent(pendingIntent);

        mOrdinaryMap.put(videoTask.getDownId(), builder);
        Notification build = builder.build();
        mNotificationManager.notify(videoTask.getDownId(), build);
    }

    private void createOrdinaryNotification(int key, NotificationCompat.Builder builder) {
        builder.setOngoing(false);
        if (mOrdinaryMap.get(key) == null)
            mOrdinaryMap.put(key, builder);
    }


    /**
     * 由Service调用
     * */
    public void updateNotification(DownloadTask taskItem) {
        String url = taskItem.getKey();
        List<VideoTask> list = DbManager.getInstance(mCtx).getVideoTaskDao().queryBuilder()
                .where(VideoTaskDao.Properties.Url.eq(url)).list();
        if (list.size() == 0) {
            Toast.makeText(mCtx, "当前更新下载数据不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        VideoTask videoTask = list.get(0);
        int downId = videoTask.getDownId();
        if (mKeepAliveMap.get(downId) != null) {
            Notification build = mKeepAliveMap.get(downId).build();
            String downloadSize = taskItem.getConvertCurrentProgress()
                    + "/" + taskItem.getFileSize();

            RemoteViews remoteViews = build.bigContentView;
            remoteViews.setTextViewText(R.id.tv_speed, taskItem.getConvertSpeed());
            remoteViews.setTextViewText(R.id.tv_size, downloadSize);
            remoteViews.setProgressBar(R.id.progressView, 100,
                    taskItem.getPercent(), false);

            mNotificationManager.notify(downId, build);

        } else if (mOrdinaryMap.get(downId) != null) {
            Notification build = mOrdinaryMap.get(downId).build();

            String downloadSize = taskItem.getConvertCurrentProgress()
                    + "/" + taskItem.getFileSize();

            RemoteViews remoteViews = build.bigContentView;
            remoteViews.setTextViewText(R.id.tv_speed, taskItem.getConvertSpeed());
            remoteViews.setTextViewText(R.id.tv_size, downloadSize);
            remoteViews.setProgressBar(R.id.progressView, 100,
                    taskItem.getPercent(), false);

            mNotificationManager.notify(downId, build);
        } else {
            Log.i(TAG, "当前通知不存在，正在新建...");
            showNotification(videoTask);
        }
    }
}
