package com.hngg.jianshi.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2020/11/24
 * Timer: 17:20
 * Author: nedhuo
 * Description:
 */
public class TextHeaderViewHolder extends RecyclerView.ViewHolder {
    public TextView mTvHeaderTime;

    public TextHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvHeaderTime = itemView.findViewById(R.id.tv_header_time);
    }
}
