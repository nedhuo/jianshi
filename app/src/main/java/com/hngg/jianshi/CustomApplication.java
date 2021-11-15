package com.hngg.jianshi;

import com.hngg.jianshi.ui.MainActivity;
import com.hngg.jianshi.ui.crash.CaocConfig;
import com.hngg.jianshi.utils.Utils;
import com.hngg.jianshi.widget.GlideImageLoader;
import com.jess.arms.base.BaseApplication;
import com.lzy.ninegrid.NineGridView;
import com.squareup.leakcanary.LeakCanary;

/**
 * Date: 2020/11/23
 * Timer: 13:32
 * Author: nedhuo
 * Description:
 */
public class CustomApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //  Aria.download(this).register();
        NineGridView.setImageLoader(new GlideImageLoader());
        Utils.init(this);
        com.hngg.network.utils.Utils.init(this);
        com.blankj.utilcode.util.Utils.init(this);
        //内存泄漏检测
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
        //初始化全局异常崩溃
        initCrash();

    }

    private void initCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(MainActivity.class) //重新启动后的activity
//                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }

    /**
     * 这个方法会通知目前的内存占用级别，可以处理内存回收
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
