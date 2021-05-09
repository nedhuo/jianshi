package com.hngg.jianshi.ui.me.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseAdapter;
import com.hngg.jianshi.data.bean.home.Author;
import com.hngg.jianshi.data.bean.home.Cover;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.database.bean.HistoryInfo;
import com.hngg.jianshi.ui.video.VideoDetailActivity;
import com.hngg.jianshi.ui.viewholder.VideoInfoViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private final HistoryActivity mCtx;
    private final List<HistoryInfo> mDataList;


    public HistoryAdapter(HistoryActivity collectionActivity) {
        mCtx = collectionActivity;
        mDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_videoinfo, parent, false);
        return new VideoInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof VideoInfoViewHolder) {
            HistoryInfo historyInfo = mDataList.get(position);
            VideoInfoViewHolder holder = (VideoInfoViewHolder) viewHolder;
            GlideUtil.loadImage(mCtx, historyInfo.getPoster(), holder.ivContent);
            holder.tvVideoTitle.setText(historyInfo.getVideoName());
            holder.tvTime.setVisibility(View.VISIBLE);
            holder.tvTime.setText(CommonUtil.longToDate(historyInfo.getCreateTime()));
            LogUtil.i(TAG, historyInfo.getCreateTime() + "");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, VideoDetailActivity.class);
                    Bundle bundle = new Bundle();

                    Data data = new Data();
                    data.setId(historyInfo.getVideoId());
                    data.setTitle(historyInfo.getVideoName());
                    data.setDescription(historyInfo.getVideoDesc());
                    data.setPlayUrl(historyInfo.getUrl());

                    Cover cover = new Cover();
                    cover.setFeed(historyInfo.getPoster());

                    Author author = new Author();
                    author.setIcon(historyInfo.getAuthorIcon());
                    author.setId(historyInfo.getAuthorId());
                    author.setName(historyInfo.getAuthorName());
                    author.setDescription(historyInfo.getAuthorDesc());

                    data.setAuthor(author);
                    data.setCover(cover);
                    bundle.putSerializable(Constant.VIDEO_BEAN, data);
                    intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                    mCtx.startActivity(intent);
                }
//                     tvTitle.setText(mVideoData.getTitle());
//                String tag = mVideoData.getAuthor().getName() + " / " + mVideoData.getCategory();
//        tvTag.setText(tag);
//        tvDesc.setText(mVideoData.getDescription());
//
//
//                //Author
//        GlideUtil.loadCircleImage(this, mVideoData.getAuthor().getIcon(), ivHeadImage);
//        tvName.setText(mVideoData.getAuthor().getName());
//        tvPersonDesc.setText(mVideoData.getAuthor().getDescription());
            });
        }
    }

    @Override
    public int getItemCount() {
        LogUtil.i(TAG, "数据长度:" + mDataList.size());
        return mDataList.size();
    }

    public void setData(List<HistoryInfo> historyInfos, boolean isUpdate) {
        if (isUpdate) {
            mDataList.clear();
        }
        mDataList.addAll(historyInfos);
        notifyDataSetChanged();
    }
}