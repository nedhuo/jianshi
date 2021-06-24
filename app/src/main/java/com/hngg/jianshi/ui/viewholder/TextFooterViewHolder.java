package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2021/2/14
 * Timer: 17:04
 * Author: nedhuo
 * Description:
 */
public class TextFooterViewHolder extends RecyclerView.ViewHolder {
    public TextView mTvFindPast;

    public TextFooterViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvFindPast = itemView.findViewById(R.id.tv_findPast);
    }
}
