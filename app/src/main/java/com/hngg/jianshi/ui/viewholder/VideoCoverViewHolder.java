package com.hngg.jianshi.ui.viewholder;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2021/2/14
 * Timer: 17:32
 * Author: nedhuo
 * Description:
 */
public class VideoCoverViewHolder extends RecyclerView.ViewHolder {
    public RecyclerView mRecyclerView;
    private Fragment mCtx;

    public VideoCoverViewHolder(View itemView, Fragment ctx) {
        super(itemView);
        mRecyclerView = itemView.findViewById(R.id.rv_videoList);
        mCtx = ctx;
        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mCtx.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
