package com.hngg.jianshi.ui.me.collection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseAdapter;
import com.hngg.jianshi.data.database.bean.CollectionInfo;
import com.hngg.jianshi.ui.viewholder.VideoInfoViewHolder;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class CollectionAdapter extends BaseAdapter {
    private final CollectionActivity mCtx;
    private final List<CollectionInfo> mDataList;


    public CollectionAdapter(CollectionActivity collectionActivity) {
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
            CollectionInfo collectionInfo = mDataList.get(position);
            VideoInfoViewHolder holder = (VideoInfoViewHolder) viewHolder;
            GlideUtil.loadImage(mCtx, collectionInfo.getPoster(), holder.ivContent);
            holder.tvVideoTitle.setText(collectionInfo.getVideoName());
        }
    }

    @Override
    public int getItemCount() {
        LogUtil.i(TAG, "数据长度:" + mDataList.size());
        return mDataList.size();
    }

    public void setData(List<CollectionInfo> collectionInfos, boolean isUpdate) {
        if (isUpdate) {
            mDataList.clear();
        }
        mDataList.addAll(collectionInfos);
        notifyDataSetChanged();
    }
}
