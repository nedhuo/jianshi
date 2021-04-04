package com.hngg.jianshi.ui.me.download;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
import com.hngg.jianshi.component.DaggerDownloadComponent;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadActivity extends BaseActivity<DownloadPresenter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Aria.download(this).register();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerDownloadComponent.builder()
                .appComponent(appComponent)
                .downloadModule(new DownloadModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }


    @Download.onTaskRunning
    protected void taskRunning(DownloadTask task) {
        Log.i(TAG, "taskRunning");
    }

    @Download.onTaskComplete
    protected void taskComplete(DownloadTask task) {
        Log.i(TAG, "taskComplete");
        //在这里处理任务完成的状态
    }
}
