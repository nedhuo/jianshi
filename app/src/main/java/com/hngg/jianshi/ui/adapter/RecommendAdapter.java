package com.hngg.jianshi.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.video.VideoDetailActivity;
import com.hngg.jianshi.ui.viewholder.BannerViewHolder;
import com.hngg.jianshi.ui.viewholder.TextHeaderViewHolder;
import com.hngg.jianshi.ui.viewholder.VideoSmallCardViewHolder;
import com.hngg.jianshi.ui.viewholder.VideoViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.Constant;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2021/2/18
 * Timer: 16:25
 * Author: nedhuo
 * Description:
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemList> mItemList;
    private final Activity mCtx;
    private final String TAG = "RecommendAdapter";

    public RecommendAdapter(Activity ctx) {
        mCtx = ctx;
        mItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case DataType.FOLLOW_CARD_ID:
                view = LayoutInflater.from(mCtx).
                        inflate(R.layout.item_video_card, parent, false);
                return new VideoViewHolder(view);
            case DataType.SQUARE_CARD_COLLECTION_ID:
                view = LayoutInflater.from(mCtx).
                        inflate(R.layout.item_bannerview, parent, false);
                return new BannerViewHolder(view);
            case DataType.TEXT_CARD_ID:
                view = LayoutInflater.from(mCtx).
                        inflate(R.layout.item_header, parent, false);
                return new TextHeaderViewHolder(view);
            case DataType.VIDEO_SMALL_CARD_ID:
                view = LayoutInflater.from(mCtx)
                        .inflate(R.layout.item_video_smallcard, parent, false);
                return new VideoSmallCardViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Data data = mItemList.get(position).getData();
        if (holder instanceof TextHeaderViewHolder) {
            ((TextHeaderViewHolder) holder).mTvHeaderTime.setText(data.getText());
        } else if (holder instanceof VideoSmallCardViewHolder) {
            VideoSmallCardViewHolder videoHolder = (VideoSmallCardViewHolder) holder;
            Glide.with(mCtx)
                    .load(data.getCover().getFeed())
                    .centerCrop()
                    .into(videoHolder.iv_videoImage);
            videoHolder.tv_videoTitle.setText(data.getTitle());
            videoHolder.tv_videoCategory.setText("#" + data.getCategory());
            videoHolder.tv_VideoDuration.setText(CommonUtil.intToTime(data.getDuration()));

            videoHolder.ll_smallCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, VideoDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.VIDEO_BEAN, data);
                    intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                    mCtx.startActivity(intent);
                }
            });
        } else if (holder instanceof BannerViewHolder) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            BannerViewAdapter adapter = new BannerViewAdapter(data.getItemList(), TAG);
            viewHolder.banner.setAdapter(adapter);
            viewHolder.banner.setIndicator(new CircleIndicator(mCtx));
            //添加画廊效果
            viewHolder.banner.setBannerGalleryMZ(20);
            //(可以和其他PageTransformer组合使用，比如AlphaPageTransformer，注意但和其他带有缩放的PageTransformer会显示冲突)
            //添加透明效果(画廊配合透明效果更棒)
            //viewHolder.banner.addPageTransformer(new AlphaPageTransformer());

        } else if (holder instanceof VideoViewHolder) {
            VideoViewHolder viewHolder = (VideoViewHolder) holder;
            Data videoBean = data.getContent().getData();

            Glide.with(mCtx)
                    .load(videoBean.getAuthor().getIcon())
                    .circleCrop()
                    .into(viewHolder.mIv_icon);

            Glide.with(mCtx)
                    .load(videoBean.getCover().getFeed())
                    .centerCrop()
                    .into(viewHolder.mIv_content);

            viewHolder.mTv_title.setText(videoBean.getTitle());
            viewHolder.mTv_desc.setText(videoBean.getDescription());
            viewHolder.mTv_duration.setText(CommonUtil.intToTime(videoBean.getDuration()));
            viewHolder.cardVideo.setOnClickListener(v -> {
                Intent intent = new Intent(mCtx, VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.VIDEO_BEAN, videoBean);
                intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                mCtx.startActivity(intent);
            });
        } else {

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
            case DataType.VIDEO_SMALL_CARD:
                return DataType.VIDEO_SMALL_CARD_ID;
            case DataType.FOLLOW_CARD:
                return DataType.FOLLOW_CARD_ID;
            case DataType.TEXT_CARD:
                return DataType.TEXT_CARD_ID;
            case DataType.SQUARE_CARD_COLLECTION:
                return DataType.SQUARE_CARD_COLLECTION_ID;
        }
        return super.getItemViewType(position);
    }

    public void setData(List<ItemList> itemLists, boolean isUpdate) {
        if (isUpdate) {
            mItemList.clear();
        }
        mItemList.addAll(itemLists);
    }

    class DataType {
        final static String TEXT_CARD = "textCard";
        final static String VIDEO_SMALL_CARD = "videoSmallCard";
        final static String SQUARE_CARD_COLLECTION = "squareCardCollection";
        final static String FOLLOW_CARD = "followCard";

        final static int TEXT_CARD_ID = 1;
        final static int VIDEO_SMALL_CARD_ID = 2;
        final static int SQUARE_CARD_COLLECTION_ID = 3;
        final static int FOLLOW_CARD_ID = 4;
    }
}
