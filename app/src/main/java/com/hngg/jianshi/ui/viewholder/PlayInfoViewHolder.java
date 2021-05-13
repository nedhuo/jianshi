package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayInfoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_content)
    public ImageView ivContent;
    @BindView(R.id.cb_selected)
    public CheckBox cbSelected;
    @BindView(R.id.tv_duration)
    public TextView tvDuration;
    @BindView(R.id.progressBar)
    public ProgressBar progressBar;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_playTime)
    public TextView tvPlayTime;

    public PlayInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
