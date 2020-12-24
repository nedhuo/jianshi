package com.hngg.jianshi.ui.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2020/11/24
 * Timer: 17:20
 * Author: nedhuo
 * Description:
 */
public class TextHeaderViewHolder extends RecyclerView.ViewHolder {
    public TextHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
