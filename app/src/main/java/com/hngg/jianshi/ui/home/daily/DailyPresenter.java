package com.hngg.jianshi.ui.home.daily;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.data.bean.home.VideoBeanForClient;
import com.hngg.jianshi.ui.viewholder.VideoViewHolder;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 19:36
 * Author: nedhuo
 * Description:
 */
public class DailyPresenter extends BasePresenter<DailyContract.Model, DailyContract.View> {
    private DailyFragment mRootView;
    private DailyModel mModel;
    public RecyclerView.Adapter mAdapter;

    @Inject
    DailyPresenter(DailyContract.Model model, DailyContract.View rootView) {
        super(model, rootView);
        mRootView = (DailyFragment) rootView;
        mModel = (DailyModel) model;
    }

    public void initView() {
        mAdapter = new RecyclerView.Adapter<ViewHolder>() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                switch (viewType) {
                    case DataType.VIDEO_ID:
                        View view = LayoutInflater.from(mRootView.getContext()).
                                inflate(R.layout.item_daily_video, parent, false);
                        return new VideoViewHolder(view);
                    case DataType.TEXT_HEADER_ID:

                    case DataType.TEXT_FOOTER_ID:

                    case DataType.VIDEO_COLLECTION_FOLLOW_ID:

                    case DataType.VIDEO_COLLECTION_COVER_ID:

                    default:
                        TextView textView = new TextView(parent.getContext());
                        textView.setHeight(200);
                        textView.setText("aaaa");
                        return new RecyclerView.ViewHolder(textView) {
                        };
                }

            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
                ItemList itemList = mModel.getItemDatas().get(position);
                if (viewHolder instanceof VideoViewHolder) {
                    VideoBeanForClient videoData = (VideoBeanForClient) itemList.getData();
                    VideoViewHolder holder = (VideoViewHolder) viewHolder;

                    Glide.with(holder.itemView)
                            .load(videoData.getAuthor().getIcon())
                            .circleCrop()
                            .into(holder.mIv_icon);
                    Glide.with(holder.itemView)
                            .load(videoData.getCover().getFeed())
                            .centerCrop()
                            .into(holder.mIv_content);
                    holder.mTv_title.setText(videoData.getTitle());
                    holder.mTv_desc.setText(videoData.getDescription());
                }
            }

            @Override
            public int getItemCount() {
               // Log.i(TAG, "ItemDatas.size():" + mModel.getItemDatas().size());
                return 0;
            }

            @Override
            public int getItemViewType(int position) {
                ItemList itemList = mModel.getItemDatas().get(position);

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
        };

        mRootView.initRecyclerView(mAdapter);

    }
}

class DataType {
    final static String VIDEO = "video";
    final static String TEXT_HEADER = "textHeader";
    final static String TEXT_FOOTER = "textFooter";
    final static String VIDEO_COLLECTION_FOLLOW = "videoCollectionOfFollow";
    final static String VIDEO_COLLECTION_COVER = "videoCollectionForCover";

    final static int VIDEO_ID = 1;
    final static int TEXT_HEADER_ID = 2;
    final static int TEXT_FOOTER_ID = 3;
    final static int VIDEO_COLLECTION_FOLLOW_ID = 4;
    final static int VIDEO_COLLECTION_COVER_ID = 5;
}
