package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2021/2/17
 * Timer: 17:26
 * Author: nedhuo
 * Description:
 */
public class ReplyHeaderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTitle;

    public ReplyHeaderViewHolder(View view) {
        super(view);
        tvTitle = itemView.findViewById(R.id.tv_title);
    }
}
