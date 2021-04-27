package com.hngg.jianshi.ui.me.download.downloaded;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
class DownloadedAdapter extends RecyclerView.Adapter<DownloadedAdapter.VideoItemViewHolder> {
    private final List<VideoTaskInfo> mDataList;
    private final Context mCtx;


    public DownloadedAdapter(Context context, List<VideoTaskInfo> taskInfoList) {
        mCtx = context;
        mDataList = taskInfoList;
    }

    @NonNull
    @Override
    public VideoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx)
                .inflate(R.layout.item_downloaded_video, parent, false);
        return new VideoItemViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VideoItemViewHolder holder, int position) {
        VideoTaskInfo taskInfo = mDataList.get(position);
        GlideUtil.loadImage(mCtx, taskInfo.getPoster(), holder.ivImage);
        holder.tvTitle.setText(taskInfo.getVideoName());
        holder.tvAuthor.setText(CommonUtil.sizeTranform(taskInfo.getFileSize()));
    }

    class VideoItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox_chose)
        CheckBox checkboxChose;
        @BindView(R.id.tv_author)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvAuthor;
        @BindView(R.id.iv_menu)
        ImageView ivMenu;
        @BindView(R.id.iv_image)
        ImageView ivImage;

        public VideoItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

