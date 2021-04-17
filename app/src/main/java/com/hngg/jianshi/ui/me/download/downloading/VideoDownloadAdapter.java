package com.hngg.jianshi.ui.me.download.downloading;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.task.DownloadTask;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.data.datebase.VideoTaskInfo;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
class VideoDownloadAdapter extends RecyclerView.Adapter<VideoItemViewHolder> {

    private final List<VideoTaskInfo> mList;
    private final FragmentActivity mCtx;
    private final String TAG = "VideoDownloadAdapter";
    private final List<DownloadEntity> mAriaList;
    private final List<DownloadTask> mAriaTaskList;

    VideoDownloadAdapter(FragmentActivity activity,
                         List<VideoTaskInfo> videoTaskInfoList,
                         List<DownloadEntity> allNotCompleteTask) {
        mCtx = activity;
        mList = videoTaskInfoList;
        mAriaList = allNotCompleteTask;
        mAriaTaskList = new ArrayList<>();
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
        } else {
            onBindViewHolder(holder, position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull VideoItemViewHolder holder, int position) {
        setVideoData(holder, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    /**
     * 整体更新
     */
    private void setVideoData(VideoItemViewHolder holder, int position) {
        VideoTaskInfo taskInfo = mList.get(position);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CommonUtil.sizeTranform(taskInfo.getDownloadSize()));
        stringBuilder.append("/");
        stringBuilder.append(CommonUtil.sizeTranform(taskInfo.getFileSize()));
        String size = stringBuilder.toString();

        GlideUtil.loadImage(mCtx, taskInfo.getPoster(), holder.ivImage);
        holder.progressView.setProgress(taskInfo.getPercent());
        holder.tvSpeed.setText(CommonUtil.sizeTranform(taskInfo.getSpeed()));
        holder.tvTitle.setText(taskInfo.getVideoName());
        holder.tvSize.setText(size);
    }

    /**
     * 局部更新
     */
    private void updateProgress(VideoItemViewHolder holder, DownloadTask taskItem) {
        holder.progressView.setProgress(taskItem.getPercent());
        holder.tvSpeed.setText(taskItem.getConvertSpeed());
        holder.tvTitle.setText(taskItem.getTaskName());
        holder.tvSize.setText(taskItem.getConvertCurrentProgress());

        int position = indexVideoTask(taskItem.getKey());
        if (position == -1) {
            LogUtil.e(TAG, "更新进度异常");
            return;
        }
        switch (taskItem.getState()) {
            case DownloadEntity.STATE_COMPLETE:
                DbManager.getInstance(mCtx).getVideoTaskDao()
                        .updateVideoTaskInfo(mList.get(position));
                mList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mList.size() - position);
                return;
            case DownloadEntity.STATE_RUNNING:
                holder.btnStateChange.setBackgroundResource(R.drawable.ic_video_pause);
                break;
            case DownloadEntity.STATE_STOP:
                holder.tvSpeed.setText(mCtx.getResources().getString(R.string.state_pause));
                holder.btnStateChange.setBackgroundResource(R.drawable.ic_video_play);
                break;
            case DownloadEntity.STATE_WAIT:
                holder.tvSpeed.setText(mCtx.getResources().getString(R.string.state_waiting));
                holder.btnStateChange.setBackgroundResource(R.drawable.ic_video_pause);
                break;
            case DownloadEntity.STATE_PRE:
                holder.tvSpeed.setText(mCtx.getResources().getString(R.string.state_pre));
                holder.btnStateChange.setBackgroundResource(R.drawable.ic_video_pause);
                break;
        }
    }


    void updateState(DownloadTask taskItem) {
        int position = indexVideoTask(taskItem.getKey());
        if (position == -1) {
            updateDataFromDb(taskItem);
            return;
        }
        VideoTaskInfo videoTaskInfo = mList.get(position);
        /*更新当前集合信息*/
        updateVideoInfo(videoTaskInfo, taskItem);

        switch (taskItem.getState()) {
            case DownloadEntity.STATE_COMPLETE:
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
     * 将当前下载信息同步更新到VideoTaskInfo集合
     * 暂时只需要更新一个状态
     */
    private void updateVideoInfo(VideoTaskInfo taskInfo, DownloadTask taskItem) {
        taskInfo.setTaskState(taskItem.getState());
        taskInfo.setDownloadSize(taskItem.getCurrentProgress());
        taskInfo.setFileSize(taskItem.getFileSize());
        taskInfo.setPercent(taskItem.getPercent());
        taskItem.getSpeed();
    }

    /**
     * 下载完成
     */
    private void onDownloadComplete(DownloadTask taskItem) {

    }

    private void updateDataFromDb(DownloadTask taskItem) {
        List<VideoTaskInfo> list = DbManager.getInstance(mCtx).getVideoTaskDao().queryNotFinished();
        if (indexVideoTask(taskItem.getKey(), list) != -1) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        } else {
            /*TODO 在Aria中删除数据，当前不知道taskId*/
            LogUtil.i(TAG, "当前视频下载回调数据需要删除");
        }
    }

    private int indexAriaTask(String url) {
        for (DownloadEntity taskItem : mAriaList) {
            if (taskItem.getKey().equals(url))
                return mAriaList.indexOf(taskItem);
        }
        return -1;
    }

    private int indexVideoTask(String downloadUrl) {
        for (VideoTaskInfo videoTaskInfo : mList) {
            if (videoTaskInfo.getUrl().equals(downloadUrl))
                return mList.indexOf(videoTaskInfo);
        }
        return -1;
    }

    private int indexVideoTask(String downloadUrl, List<VideoTaskInfo> list) {
        for (VideoTaskInfo videoTaskInfo : list) {
            if (videoTaskInfo.getUrl().equals(downloadUrl))
                return mList.indexOf(videoTaskInfo);
        }
        return -1;
    }
}

class VideoItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.checkbox_chose)
    CheckBox checkboxChose;
    @BindView(R.id.tv_image)
    ImageView ivImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.progressView)
    ProgressBar progressView;
    @BindView(R.id.tv_speed)
    TextView tvSpeed;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.btn_stateChange)
    ImageView btnStateChange;
    @BindView(R.id.btn_menu)
    ImageView btnMenu;
    @BindView(R.id.ll_btn)
    LinearLayout llBtn;

    VideoItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
}