package com.hngg.jianshi.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseAdapter;
import com.hngg.jianshi.data.DataType;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.video.VideoDetailActivity;
import com.hngg.jianshi.ui.viewholder.Video3ViewHolder;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class RankingAdapter extends BaseAdapter<RecyclerView.ViewHolder> {
    private final Activity mCtx;
    private final List<ItemList> mDataList;

    public RankingAdapter(Activity activity) {
        mCtx = activity;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        String type = mDataList.get(position).getType();
        LogUtil.i(TAG, type);
        if (type.equals(DataType.VIDEO_CARD)) {
            return DataType.VIDEO_CARD_ID;
        }
        return DataType.OTHER_ID;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.VIDEO_CARD_ID) {
            View inflate = LayoutInflater.from(mCtx).inflate(R.layout.item_video3, parent, false);
            return new Video3ViewHolder(inflate);
        } else {
            TextView textView = new TextView(mCtx);
            textView.setVisibility(View.GONE);
            return new RecyclerView.ViewHolder(textView) {
            };
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof Video3ViewHolder) {
            Video3ViewHolder holder = (Video3ViewHolder) viewHolder;
            Data data = mDataList.get(position).getData();
            LogUtil.i(TAG, data.toString());
            GlideUtil.loadImage(mCtx, data.getCover().getFeed(), holder.ivContent);
            if (data.getAuthor() != null) {
                GlideUtil.loadCircleImage(mCtx, data.getAuthor().getIcon(), holder.ivHeadImage);
                holder.tvPersonDesc.setText(data.getAuthor().getName());
            } else if (data.getProvider() != null) {
                GlideUtil.loadCircleImage(mCtx, data.getProvider().getIcon(), holder.ivHeadImage);
                holder.tvPersonDesc.setText(data.getProvider().getName());
            }
            holder.tvTitle.setText(data.getTitle());


            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.VIDEO_BEAN, data);
                Intent intent = new Intent(mCtx, VideoDetailActivity.class);
                intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                mCtx.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setData(List<ItemList> data, boolean isUpdate) {
        if (isUpdate) {
            mDataList.clear();
        }
        mDataList.addAll(data);
        notifyDataSetChanged();
    }
}
