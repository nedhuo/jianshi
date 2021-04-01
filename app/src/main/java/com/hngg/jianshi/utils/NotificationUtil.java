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

import static android.provider.Settings.EXTRA_APP_PACKAGE;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class NotificationUtil {
    private RemoteViews mRemoteView = null
    private NotificationManagerCompat mNotificationManagerCompat;
    private NotificationManager mNotifiManager = null
    private NotificationChannel mNotifiChannel = null
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
                mNotifiChannel = NotificationChannel(channelId, channelName, importance);
//                mNotifiChannel.canBypassDnd();//是否绕过请勿打扰模式
                mNotifiChannel.enableLights(false)//是否在桌面icon右上角展示小红点
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
                mNotifiChannel.setShowBadge(true)

            }
        return mNotifiChannel !!
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
     * 创建通知
     * 使用自定义布局时，注意不要设置内容属性
     */
    private NotificationCompat.Builder createNotification(
            Context context,
            FileInfo fileInfo:
    ) {

        Intent intent = Intent(context, MyFileActivity:: class.java);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getService(context, 0, intent, 0);

        mRemoteView = new RemoteViews(context.getPackageName(), R.layout.notify_download);
        //下面几行代码操作最为致命
//        remoteView.setProgressBar(R.id.progressView, 100, 0, false)
        mRemoteView.setTextViewText(R.id.tv_title, fileInfo.name);
//        remoteView.setTextViewText(R.id.tv_size, "0%:")

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context.getApplicationContext());

        //8.0之后需要加上这一句
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(channelId);
        }

        //标题
        notificationBuilder
                //.setContentTitle(fileInfo.name)
                //内容
                //   .setContentText("0%")
                //设置发送的时间
                .setWhen(System.currentTimeMillis())
                //设置小图标（通知栏没有下拉的图标）
                .setSmallIcon(R.mipmap.app_icon)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                //设置右侧大图标
                //          .setLargeIcon(GlideUtils.bitmapByUrl(context, fileInfo.url))
                //设置布局
                .setCustomContentView(mRemoteView)
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

        //主要是这句

        //.setProgress(100, 0, false)
        return notificationBuilder
    }


    /**
     * 发送通知
     *
     * @param id      确认唯一下载Item的标识
     * @param context 环境
     */
    void sendNotification(Context context, FileInfo fileInfo) {
        if (checkId(fileInfo.id.toInt())) {
            Log.i("NotificationUtils", "通知栏ID已存在")
            return
        }

        if (!EasyPermissionUtils.checkNotificationPermission(context)) {
            /*无权限，查询是否已请求*/
            val isApply = SharedPrefsUtils.getBoolean(context, mIsApplyNotification_sp, false)
            SharedPrefsUtils.putBoolean(context, mIsApplyNotification_sp, true)
            /*已请求则不再请求*/
            if (isApply) return
                    /*TODO 添加申请弹框 ，确定进入设置*/
                    //         EasyPermissionUtils.obtainNotificationPermission(context)

                    jumpSetting(context)
            Toast.makeText(context, "当前无通知权限", Toast.LENGTH_SHORT).show()
            Log.i("NotificationUtils", "当前通知无权限")
            return
        }

        No manager = obtainManager(context)
        val notification = createNotification(context, fileInfo).build()
        manager.notify(fileInfo.id.toInt(), notification)
        mNotifications[fileInfo.id.toInt()] = notification
    }


    /**
     * 同一条下载的downId应该一致
     * 更新进度
     */
    fun updateNotifi(downId:Int, progress:Int, speed:String):Boolean

    {
        if (mNotifiManager == null) {
            return false
        }

        val notification = mNotifications[downId]
        if (notification != null) {
            notification.bigContentView = mRemoteView
            notification.contentView
                    .setProgressBar(R.id.progressView, 100, progress, false)
            notification.contentView.setTextViewText(R.id.tv_speed, speed)
            notification.contentView.setTextViewText(R.id.tv_size, "${progress}%")

            mNotifiManager !!.notify(downId, notification)
            return true
        } else {
            return false
        }
    }


    /**
     * 检测ID是否存在
     *
     * @return ·true  ID已存在
     * false ID不存在
     */
    private boolean checkId(int id) {
        for (key in mNotifications.keys()) {
            if (id == key) {
                return true;
            }
        }
        return false;
    }

    /**
     * 跳转设置页面
     */
    public void jumpSetting(Context context) {
        try {
            // 根据isOpened结果，判断是否需要提醒用户跳转AppInfo页面，去打开App通知权限
            Intent intent = Intent();
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS;
            //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
            intent.putExtra(EXTRA_APP_PACKAGE, context.getPackageName());
            intent.putExtra(EXTRA_CHANNEL_ID, context.getApplicationInfo().uid);

            //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);

            // 小米6 -MIUI9.6-8.0.0系统，是个特例，通知设置界面只能控制"允许使用通知圆点"——然而这个玩意并没有卵用，
            // 我想对雷布斯说：I'm not ok!!!
            //  if ("MI 6".equals(Build.MODEL)) {
            //      intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            //      Uri uri = Uri.fromParts("package", getPackageName(), null);
            //      intent.setData(uri);
            //      // intent.setAction("com.android.settings/.SubSettings");
            //  }
            context.startActivity(intent)
        } catch (e:Exception){
            e.printStackTrace();
            // 出现异常则跳转到应用设置界面：锤子坚果3——OC105 API25
            val intent = Intent()

            //下面这种方案是直接跳转到当前应用的设置界面。
            //https://blog.csdn.net/ysy950803/article/details/71910806
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", context.packageName, null)
            intent.data = uri
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    /**
     * 判断通知权限
     */
    private Boolean checkNotifiPermission(Context context) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        boolean isOpened = manager.areNotificationsEnabled()

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
