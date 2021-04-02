package com.hngg.jianshi.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.hngg.jianshi.R;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class NotificationUtil {
    private RemoteViews mRemoteView = null;
    private NotificationManagerCompat mNotificationManagerCompat;
    private NotificationManager mNotifiManager = null;
    private NotificationChannel mNotifiChannel = null;
    private String channelId = "niceVideo";
    private String channelName = "download";
    private int importance = NotificationManager.IMPORTANCE_DEFAULT;
    private ConcurrentHashMap<Integer, Notification> mNotifications;
    private String mIsApplyNotification_sp = "isApplyNotification";

    /**
     * 创建通知渠道
     */

    private NotificationChannel obtainChannel() {
        if (mNotifiChannel == null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //创建通知渠道，重要程度为NotificationManager.IMPORTANCE_HIGH 紧急
                mNotifiChannel = new NotificationChannel(channelId, channelName, importance);
//                mNotifiChannel.canBypassDnd();//是否绕过请勿打扰模式
                mNotifiChannel.enableLights(false);//是否在桌面icon右上角展示小红点
//                mNotifiChannel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏显示通知
//                mNotifiChannel.setLightColor(Color.RED);//闪关灯的灯光颜色
//                mNotifiChannel.canShowBadge();//桌面launcher的消息角标
                mNotifiChannel.enableVibration(false);//是否允许震动
//                mNotifiChannel.getAudioAttributes();//获取系统通知响铃声音的配置
//                mNotifiChannel.getGroup();//获取通知取到组
//                mNotifiChannel.setBypassDnd(true);//设置可绕过 请勿打扰模式
                mNotifiChannel.setSound(null, null);
//                //channel.setVibrationPattern(new long[]{100, 100, 200});//设置震动模式
//                mNotifiChannel.shouldShowLights();//是否会有灯光
                //是否在久按桌面图标时显示此渠道的通知
                mNotifiChannel.setShowBadge(true);

            }
        return mNotifiChannel;
    }

    /**
     * 获取NotificationManager,没有则新建
     */
    private NotificationManager obtainManager(Context context) {
        //创建通知
        if (mNotifiManager == null) {
            mNotifiManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        //通知加入通道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotifiManager.createNotificationChannel(obtainChannel());
        }
        return mNotifiManager;
    }


    /**
     * 判断通知权限
     */
    private Boolean checkNotifiPermission(Context context) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        boolean isOpened = manager.areNotificationsEnabled();

        if (isOpened) {
            Log.i(
                    "DownloadNotification",
                    "通知权限已经被打开" +
                            "\n手机型号:" + android.os.Build.MODEL +
                            "\nSDK版本:" + android.os.Build.VERSION.SDK +
                            "\n系统版本:" + android.os.Build.VERSION.RELEASE +
                            "\n软件包名:" + context.getPackageName()
            );
            return true;
        } else {
            return false;
        }
    }

}
