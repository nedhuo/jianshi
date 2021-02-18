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
    public ImageView iv_videoImage;
    public TextView tv_VideoDuration;
    public TextView tv_videoTitle;
    public TextView tv_videoCategory;
    public LinearLayout ll_smallCard;

    public VideoSmallCardViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_videoImage = itemView.findViewById(R.id.iv_videoList_image);
        tv_VideoDuration = itemView.findViewById(R.id.tv_videoList_duration);
        tv_videoTitle = itemView.findViewById(R.id.tv_videoList_title);
        tv_videoCategory = itemView.findViewById(R.id.tv_videoList_category);
        ll_smallCard = itemView.findViewById(R.id.ll_smallCard);
    }
}
