package com.hngg.jianshi.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.video.VideoDetailActivity;
import com.hngg.jianshi.ui.viewholder.TextFooterViewHolder;
import com.hngg.jianshi.ui.viewholder.TextHeaderViewHolder;
import com.hngg.jianshi.ui.viewholder.VideoCoverViewHolder;
import com.hngg.jianshi.ui.viewholder.VideoViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2021/2/13
 * Timer: 16:03
 * Author: nedhuo
 * Description: 大图片列表Adapter
 */
public class VideoCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = "CardAdapter";
    private final Fragment mCtx;
    private List<ItemList> mItemList;


    public VideoCardAdapter(Fragment ctx) {
        mItemList = new ArrayList<>();
        mCtx = ctx;
    }

    public void removeData() {
        mItemList.clear();
    }

    public void setData(List<ItemList> itemList) {
        mItemList.addAll(itemList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case DataType.VIDEO_ID:
                view = LayoutInflater.from(mCtx.getActivity()).
                        inflate(R.layout.item_video_card, parent, false);
                return new VideoViewHolder(view);
            case DataType.TEXT_HEADER_ID:
                view = LayoutInflater.from(mCtx.getActivity()).
                        inflate(R.layout.item_header, parent, false);
                return new TextHeaderViewHolder(view);
            case DataType.TEXT_FOOTER_ID:
                view = LayoutInflater.from(mCtx.getActivity()).
                        inflate(R.layout.item_footer, parent, false);
                return new TextFooterViewHolder(view);
            case DataType.VIDEO_COLLECTION_FOLLOW_ID:

            case DataType.VIDEO_COLLECTION_COVER_ID:
                view = LayoutInflater.from(mCtx.getActivity()).
                        inflate(R.layout.item_collection_smallcard, parent, false);
                return new VideoCoverViewHolder(view, mCtx);
            default:
                TextView textView = new TextView(parent.getContext());
                textView.setHeight(0);
                return new RecyclerView.ViewHolder(textView) {
                };

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Data data = mItemList.get(position).getData();
        if (viewHolder instanceof VideoViewHolder) {
            VideoViewHolder holder = (VideoViewHolder) viewHolder;
            try {
                Glide.with(holder.itemView)
                        .load(data.getAuthor().getIcon())
                        .circleCrop()
                        .into(holder.mIv_icon);
            } catch (Exception e) {
                Log.i(TAG, "===============");
                Log.i(TAG, data.getTitle());

                Glide.with(holder.itemView)
                        .load("http://img.kaiyanapp.com/ebf307197b634f30b2fa4eb867e908c1.jpeg?" +
                                "imageMogr2/quality/60/format/jpg")
                        .circleCrop()
                        .into(holder.mIv_icon);
            }
            Glide.with(holder.itemView)
                    .load(data.getCover().getFeed())
                    .centerCrop()
                    .into(holder.mIv_content);
            holder.mTv_title.setText(data.getTitle());
            holder.mTv_desc.setText(data.getDescription());
            holder.mTv_duration.setText(CommonUtil.intToTime(data.getDuration()));
            holder.cardVideo.setOnClickListener(v -> {
                Intent intent = new Intent(mCtx.getActivity(), VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.VIDEO_BEAN, data);
                intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                mCtx.getActivity().startActivity(intent);

            });
        } else if (viewHolder instanceof TextFooterViewHolder) {

            TextFooterViewHolder holder = (TextFooterViewHolder) viewHolder;
            holder.mTvFindPast.setText(data.getText());
            //http://baobab.kaiyanapp.com/api/v2/feed?issueIndex=1
            //eyepetizer://feed?issueIndex=1
        } else if (viewHolder instanceof TextHeaderViewHolder) {

            TextHeaderViewHolder holder = (TextHeaderViewHolder) viewHolder;
            holder.mTvHeaderTime.setText(data.getText());
        } else if (viewHolder instanceof VideoCoverViewHolder) {
            //videoSmallCard
            VideoCoverViewHolder holder = (VideoCoverViewHolder) viewHolder;
            VideoSmallCardAdapter adapter = new VideoSmallCardAdapter(mCtx.getActivity());
            holder.mRecyclerView.setAdapter(adapter);
            adapter.setData(data.getItemList());
            adapter.notifyDataSetChanged();
        }

    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ItemList itemList = mItemList.get(position);

        switch (itemList.getType()) {
            case DataType.VIDEO:
                Log.i(TAG, DataType.VIDEO);
                return DataType.VIDEO_ID;

            case DataType.TEXT_HEADER:
                return DataType.TEXT_HEADER_ID;

            case DataType.TEXT_FOOTER:
                return DataType.TEXT_FOOTER_ID;

            case DataType.VIDEO_COLLECTION_FOLLOW:
                return DataType.VIDEO_COLLECTION_FOLLOW_ID;

            case DataType.VIDEO_COLLECTION_COVER:
                return DataType.VIDEO_COLLECTION_COVER_ID;

            default:
                break;
        }
        return super.getItemViewType(position);
    }
}

class DataType {
    final static String VIDEO = "video";
    final static String TEXT_HEADER = "textHeader";
    final static String TEXT_FOOTER = "textFooter";
    final static String VIDEO_COLLECTION_FOLLOW = "videoCollectionOfFollow";
    final static String VIDEO_COLLECTION_COVER = "videoCollectionWithCover";

    final static int VIDEO_ID = 1;
    final static int TEXT_HEADER_ID = 2;
    final static int TEXT_FOOTER_ID = 3;
    final static int VIDEO_COLLECTION_FOLLOW_ID = 4;
    final static int VIDEO_COLLECTION_COVER_ID = 5;
}