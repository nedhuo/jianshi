package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2021/2/14
 * Timer: 17:51
 * Author: nedhuo
 * Description:
 */
public class VideoSmallCardViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_videoList_image)
    public ImageView iv_videoImage;
    @BindView(R.id.tv_videoList_duration)
    public TextView tv_VideoDuration;
    @BindView(R.id.tv_videoList_title)
    public TextView tv_videoTitle;
    @BindView(R.id.tv_videoList_category)
    public TextView tv_videoCategory;
    @BindView(R.id.ll_smallCard)
    public LinearLayout ll_smallCard;

    public VideoSmallCardViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
