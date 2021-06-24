package com.hngg.jianshi.ui.viewholder;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2020/11/24
 * Timer: 17:17
 * Author: nedhuo
 * Description:
 */
public class VideoViewHolder extends RecyclerView.ViewHolder {
    public ImageView mIv_content;
    public ImageView mIv_icon;
    public TextView mTv_title;
    public TextView mTv_desc;
    public TextView mTv_duration;

    public ConstraintLayout cardVideo;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        mIv_content = itemView.findViewById(R.id.iv_dailyItem_content);
        mIv_icon = itemView.findViewById(R.id.iv_dailyItem_head);
        mTv_title = itemView.findViewById(R.id.tv_dailyItem_title);
        mTv_desc = itemView.findViewById(R.id.tv_dailyItem_desc);
        mTv_duration = itemView.findViewById(R.id.tv_dailyItem_duration);
        cardVideo = itemView.findViewById(R.id.cardVideo);
    }
}
