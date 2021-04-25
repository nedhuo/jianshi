package com.hngg.jianshi.ui.user.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.DataType;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.video.VideoDetailActivity;
import com.hngg.jianshi.ui.viewholder.VideoViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

class UserInfo_HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "UserInfo_HomeAdapter";
    private final List<ItemList> mDataList;
    private final FragmentActivity mCtx;

    UserInfo_HomeAdapter(FragmentActivity activity) {
        mCtx = activity;
        mDataList = new ArrayList<>();
    }


    @Override
    public int getItemViewType(int position) {
        if (mDataList.size() > 0) {
            ItemList itemList = mDataList.get(position);
            if (itemList.getType().equals(DataType.VIDEO_COLLECTION_HORIZONTAL_SCROLL_CARD)) {
                return DataType.VIDEO_COLLECTION_HORIZONTAL_SCROLL_ID;
            } else if (itemList.getType().equals(DataType.VIDEO_CARD)) {
                return DataType.VIDEO_CARD_ID;
            } else if (itemList.getType().equals(DataType.TEXT_HEADER)) {
                //todo 待实现
                return DataType.TEXT_HEADER_ID;
            } else if (itemList.getType().equals(DataType.TEXT_FOOTER)) {
                return DataType.TEXT_FOOTER_ID;
            } else if (itemList.getType().equals(DataType.VIDEO_COLLECTION_BRIEF)) {
                return DataType.VIDEO_COLLECTION_BRIEF_ID;
            } else if (itemList.getType().equals(DataType.BRIEF_CARD)) {
                return DataType.BRIEF_CARD_ID;
            } else {
                LogUtil.i(TAG, "mAdapter存在超出管理范围的数据存在");
                return DataType.OTHER_ID;
            }
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.VIDEO_CARD_ID) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.item_video_card, parent, false);
            return new VideoViewHolder(view);
        } else if (viewType == DataType.VIDEO_COLLECTION_HORIZONTAL_SCROLL_ID) {
            TextView textView = new TextView(mCtx);
            return new RecyclerView.ViewHolder(textView) {
            };
        } else {
            TextView textView = new TextView(mCtx);
            return new RecyclerView.ViewHolder(textView) {
            };
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Data data = mDataList.get(position).getData();
        if (viewHolder instanceof VideoViewHolder) {
            VideoViewHolder holder = (VideoViewHolder) viewHolder;
            GlideUtil.loadCircleImage(mCtx, data.getAuthor().getIcon(), holder.mIv_icon);
            GlideUtil.loadImage(mCtx, data.getCover().getFeed(), holder.mIv_content);

            holder.mTv_title.setText(data.getTitle());
            holder.mTv_desc.setText(data.getDescription());
            holder.mTv_duration.setText(CommonUtil.intToTime(data.getDuration()));
            holder.cardVideo.setOnClickListener(v -> {
                Intent intent = new Intent(mCtx, VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.VIDEO_BEAN, data);
                intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                mCtx.startActivity(intent);
            });
        }


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setData(List<ItemList> itemList, boolean isUpdate) {
        if (isUpdate) {
            mDataList.clear();
        }
        mDataList.addAll(itemList);
        notifyDataSetChanged();
    }
}
