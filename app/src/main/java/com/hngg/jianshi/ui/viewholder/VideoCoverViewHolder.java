package com.hngg.jianshi.ui.viewholder;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

/**
 * Date: 2021/2/14
 * Timer: 17:32
 * Author: nedhuo
 * Description:
 */
public class VideoCoverViewHolder extends RecyclerView.ViewHolder {
    public RecyclerView mRecyclerView;
    private Context mCtx;

    public VideoCoverViewHolder(View itemView, Context ctx) {
        super(itemView);
        mRecyclerView = itemView.findViewById(R.id.rv_videoList);
        mCtx = ctx;
        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mCtx);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
