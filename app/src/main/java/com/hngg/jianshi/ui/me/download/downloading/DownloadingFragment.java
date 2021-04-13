package com.hngg.jianshi.ui.me.download.downloading;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.task.DownloadTask;
import com.hngg.jianshi.R;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadingFragment extends BaseFragment<DownloadingPresenter>
        implements DownloadingContract.View {
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_downloading, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    /**
     * 下载监听
     */
    @Download.onTaskRunning
    protected void onTaskRunning(DownloadTask task) {
        LogUtil.i(TAG, "onTaskRunning");
    }

    @Download.onTaskComplete
    protected void onTaskComplete(DownloadTask task) {
        LogUtil.i(TAG, "taskComplete");
        //在这里处理任务完成的状态
    }

    @Download.onTaskStart
    protected void onTaskStart(DownloadTask taskItem) {
        LogUtil.i(TAG, "onTaskStart");
    }

    @Download.onTaskStop
    protected void onTaskStop(DownloadTask taskItem) {
        LogUtil.i(TAG, "onTaskStop");
    }

    @Download.onTaskCancel
    protected void onTaskCancel(DownloadTask taskItem) {
        LogUtil.i(TAG, "onTaskCancel");
    }

    @Download.onTaskFail
    protected void onTaskFail(DownloadTask taskItem) {
        LogUtil.i(TAG, "onTaskFail");
    }


}
