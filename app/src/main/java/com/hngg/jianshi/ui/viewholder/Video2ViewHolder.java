package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.ButterKnife;

public class Video2ViewHolder extends RecyclerView.ViewHolder {
    public final ImageView ivHead;
    public final TagFlowLayout tagFlowLayout;
    public final TextView tvDuration;
    public final TextView tvDesc;
    public final TextView tvAuthorDesc;
    public final ImageView ivContent;
    public final TextView tvAuthor;

    public Video2ViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ivHead = itemView.findViewById(R.id.iv_head);
        ivContent = itemView.findViewById(R.id.iv_content);
        tvAuthorDesc = itemView.findViewById(R.id.tv_authorDesc);
        tvAuthor = itemView.findViewById(R.id.tv_author);
        tvDesc = itemView.findViewById(R.id.tv_desc);
        tvDuration = itemView.findViewById(R.id.tv_duration);
        tagFlowLayout = itemView.findViewById(R.id.flowLayout_tag);
    }
}
