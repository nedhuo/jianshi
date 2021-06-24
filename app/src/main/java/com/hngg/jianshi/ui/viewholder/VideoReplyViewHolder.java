package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2021/2/17
 * Timer: 17:09
 * Author: nedhuo
 * Description:
 */
public class VideoReplyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_headImage)
    public ImageView ivHeadImage;
    @BindView(R.id.tv_name)
    public TextView tvName;
    @BindView(R.id.tv_reply)
    public TextView tvReply;
    @BindView(R.id.tv_publishTime)
    public TextView tvPublishTime;

    public VideoReplyViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
