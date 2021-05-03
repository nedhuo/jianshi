package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.lzy.ninegrid.NineGridView;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowPictureViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_head)
    public ImageView ivHead;
    @BindView(R.id.tv_author)
    public TextView tvAuthor;
    @BindView(R.id.tv_authorDesc)
    public TextView tvAuthorDesc;
    @BindView(R.id.tv_desc)
    public TextView tvDesc;
    @BindView(R.id.flowLayout_tag)
    public TagFlowLayout flowLayoutTag;
    @BindView(R.id.nineGridView)
    public NineGridView nineGridView;

    public FollowPictureViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
