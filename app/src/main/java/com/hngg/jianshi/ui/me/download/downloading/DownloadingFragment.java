package com.hngg.jianshi.ui.me.download.downloading;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.task.DownloadTask;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadingFragment extends BaseFragment<DownloadingPresenter>
        implements DownloadingContract.View {

    @BindView(R.id.rv_downloading)
    RecyclerView rvDownloading;
    private VideoDownloadAdapter mAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerDownloadingComponent
//                .builder()
//                .appComponent(appComponent)
//                .downloadingModule(new DownloadingModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_downloading, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            LogUtil.i(TAG, "mPresenter为null");
            return;
        }
        Aria.download(this).register();
        List<DownloadEntity> allNotCompleteTask = Aria.download(this).getAllNotCompleteTask();
        List<VideoTaskInfo> infoList = DbManager.getInstance(mContext)
                .getVideoTaskDao().queryNotFinished();
        mAdapter = new VideoDownloadAdapter(getActivity(), infoList, allNotCompleteTask);
        rvDownloading.setLayoutManager( new LinearLayoutManager(mContext));
        rvDownloading.setAdapter(mAdapter);
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Aria.download(this).unRegister();
    }

    /**
     * 下载监听
     */

    @Download.onPre
    void onPre(DownloadTask taskItem) {
        LogUtil.i(TAG, "onPre");
        mAdapter.updateState(taskItem);
    }

    @Download.onTaskRunning
    void onTaskRunning(DownloadTask taskItem) {
        LogUtil.i(TAG, "onTaskRunning");
        mAdapter.updateState(taskItem);
    }

    @Download.onTaskComplete
    void onTaskComplete(DownloadTask taskItem) {
        LogUtil.i(TAG, "taskComplete");
        //在这里处理任务完成的状态
        mAdapter.updateState(taskItem);
    }

    @Download.onTaskStart
    void onTaskStart(DownloadTask taskItem) {
        LogUtil.i(TAG, "onTaskStart");
        mAdapter.updateState(taskItem);
    }

    @Download.onTaskStop
    void onTaskStop(DownloadTask taskItem) {
        LogUtil.i(TAG, "onTaskStop");
        mAdapter.updateState(taskItem);
    }

    @Download.onTaskCancel
    void onTaskCancel(DownloadTask taskItem) {
        LogUtil.i(TAG, "onTaskCancel");
        mAdapter.updateState(taskItem);
    }

    @Download.onTaskFail
    void onTaskFail(DownloadTask taskItem) {
        LogUtil.i(TAG, "onTaskFail");
        mAdapter.updateState(taskItem);
    }


}
