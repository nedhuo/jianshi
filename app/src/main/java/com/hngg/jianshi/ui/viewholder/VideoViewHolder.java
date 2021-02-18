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
    @BindView(R.id.iv_dailyItem_content)
    public ImageView mIv_content;
    @BindView(R.id.iv_dailyItem_head)
    public ImageView mIv_icon;
    @BindView(R.id.tv_dailyItem_title)
    public TextView mTv_title;
    @BindView(R.id.tv_dailyItem_desc)
    public  TextView mTv_desc;
    @BindView(R.id.tv_dailyItem_duration)
    public TextView mTv_duration;
    @BindView(R.id.cardVideo)
    public ConstraintLayout cardVideo;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
