package com.hngg.jianshi.ui.me.download.downloading;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.task.DownloadTask;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.data.datebase.VideoTaskInfo;
import com.hngg.jianshi.data.datebase.VideoTaskInfoDao;
import com.hngg.jianshi.data.datebase.VideoTaskState;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
class VideoDownloadAdapter extends RecyclerView.Adapter<VideoItemViewHolder> {

    private final List<VideoTaskInfo> mList;
    private final FragmentActivity mCtx;

    VideoDownloadAdapter(FragmentActivity activity, List<VideoTaskInfo> videoTaskInfoList, List<DownloadEntity> allNotCompleteTask) {
        mCtx = activity;
        mList = videoTaskInfoList;
    }

    @NonNull
    @Override
    public VideoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx)
                .inflate(R.layout.item_download_video, parent, false);
        return new VideoItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull VideoItemViewHolder holder,
                                 int position, @NonNull List<Object> payloads) {
        if (!payloads.isEmpty()) {
            DownloadTask downloadTask = (DownloadTask) payloads.get(0);
            updateProgress(holder, downloadTask);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull VideoItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 局部更新
     */
    private void updateProgress(VideoItemViewHolder holder, DownloadTask downloadTask) {

    }


    public void updateState(DownloadTask taskItem) {
        int position = indexVideoTask(taskItem.getKey());
        if (position == -1) {
            updateDataFromDb(taskItem);
            return;
        }

        VideoTaskInfo videoTaskInfo = mList.get(position);
        switch (taskItem.getState()) {
            case DownloadEntity.STATE_COMPLETE:
                /*1. 进度更新  2. 通知数据库  3. 移除*/

            case DownloadEntity.STATE_RUNNING:
            case DownloadEntity.STATE_STOP:
            case DownloadEntity.STATE_WAIT:
                notifyItemChanged(position, taskItem);
                break;
            case DownloadEntity.STATE_CANCEL:
            case DownloadEntity.STATE_FAIL:
                break;
        }
    }

    /**
     * 下载完成
     */
    private void onDownloadComplete(DownloadTask taskItem) {

    }

    private void updateDataFromDb(DownloadTask taskItem) {
        List<VideoTaskInfo> videoTaskInfoList = DbManager.getInstance(mCtx)
                .getVideoTaskDao().queryBuilder()
                .where(VideoTaskInfoDao.Properties.TaskState.notEq(VideoTaskState.SUCCESS))
                .list();
        mList.clear();
        mList.addAll(videoTaskInfoList);
        int i = indexVideoTask(taskItem.getKey());
        if (i == -1) {
            /*TODO 在Aria中删除数据，当前不知道taskId*/
        }
    }

    private int indexVideoTask(String downloadUrl) {
        for (VideoTaskInfo videoTaskInfo : mList) {
            if (videoTaskInfo.getUrl().equals(downloadUrl))
                return mList.indexOf(videoTaskInfo);
        }
        /*TODO 通知更新数据*/
        return -1;
    }
}

class VideoItemViewHolder extends RecyclerView.ViewHolder {

    VideoItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
}