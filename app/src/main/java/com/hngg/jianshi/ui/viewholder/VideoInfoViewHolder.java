package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的页面 Collection History两个页面Item
 */
public class VideoInfoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_content)
    public ImageView ivContent;
    @BindView(R.id.tv_videoTitle)
    public TextView tvVideoTitle;
    @BindView(R.id.tv_time)
    public TextView tvTime;
    public VideoInfoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
