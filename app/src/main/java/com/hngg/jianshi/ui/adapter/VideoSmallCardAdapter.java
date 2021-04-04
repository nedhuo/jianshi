package com.hngg.jianshi.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.video.VideoDetailActivity;
import com.hngg.jianshi.ui.viewholder.VideoSmallCardViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2021/2/14
 * Timer: 17:29
 * Author: nedhuo
 * Description:
 */
public class VideoSmallCardAdapter extends RecyclerView.Adapter {

    private final Activity mCtx;

    private List<ItemList> mItemList;

    public VideoSmallCardAdapter(Activity ctx) {
        mItemList = new ArrayList<>();
        mCtx = ctx;
    }

    public void setData(List<ItemList> itemList) {
        mItemList.addAll(itemList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx)
                .inflate(R.layout.item_video_smallcard, parent, false);
        return new VideoSmallCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Data videoData = mItemList.get(position).getData();
        VideoSmallCardViewHolder viewHolder = (VideoSmallCardViewHolder) holder;
        GlideUtil.loadImage(holder.itemView,videoData.getCover().getFeed(),viewHolder.iv_videoImage);

        viewHolder.tv_videoTitle.setText(videoData.getTitle());
        viewHolder.tv_videoCategory.setText(videoData.getCategory());
        viewHolder.tv_VideoDuration.setText(CommonUtil.intToTime(videoData.getDuration()));

        viewHolder.ll_smallCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.VIDEO_BEAN, videoData);
                intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
