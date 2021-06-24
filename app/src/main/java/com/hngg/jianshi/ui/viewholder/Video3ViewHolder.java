package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Video3ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_content)
    public ImageView ivContent;
    @BindView(R.id.iv_headImage)
    public ImageView ivHeadImage;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_personDesc)
    public TextView tvPersonDesc;


    public Video3ViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
