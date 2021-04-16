package com.hngg.jianshi.ui.me.download.downloading;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.task.DownloadTask;
import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.data.datebase.VideoTask;
import com.hngg.jianshi.data.datebase.VideoTaskDao;
import com.hngg.jianshi.data.datebase.VideoTaskState;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
class VideoDownloadAdapter extends RecyclerView.Adapter<VideoItemViewHolder> {

    private final List<VideoTask> mList;
    private final FragmentActivity mCtx;

    VideoDownloadAdapter(FragmentActivity activity, List<VideoTask> videoTaskList, List<DownloadEntity> allNotCompleteTask) {
        mCtx = activity;
        mList = videoTaskList;
    }

    @NonNull
    @Override
    public VideoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateState(DownloadTask taskItem) {
        int i = indexVideoTask(taskItem.getDownloadUrl());
        if (i == -1) {
            updateDataFromDb(taskItem);
        }
        switch (taskItem.getState()) {
            case DownloadEntity.STATE_COMPLETE:
                break;
        }
    }

    private void updateDataFromDb(DownloadTask taskItem) {
        List<VideoTask> videoTaskList = DbManager.getInstance(mCtx)
                .getVideoTaskDao().queryBuilder()
                .where(VideoTaskDao.Properties.TaskState.notEq(VideoTaskState.SUCCESS))
                .list();
        mList.clear();
        mList.addAll(videoTaskList);
        int i = indexVideoTask(taskItem.getDownloadUrl());
        if (i == -1) {
            /*TODO 在Aria中删除数据，当前不知道taskId*/
        }
    }

    private int indexVideoTask(String downloadUrl) {
        for (VideoTask videoTask : mList) {
            if (videoTask.getUrl().equals(downloadUrl))
                return mList.indexOf(videoTask);
        }
        /*TODO 通知更新数据*/
        return -1;
    }
}

class VideoItemViewHolder extends RecyclerView.ViewHolder {

    public VideoItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}