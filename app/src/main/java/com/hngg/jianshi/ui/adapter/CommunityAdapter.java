package com.hngg.jianshi.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.data.bean.recommend.Content;
import com.hngg.jianshi.ui.community.CommunityVideoActivity;
import com.hngg.jianshi.ui.community.UgcPictureActivity;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2021/2/20
 * Timer: 9:54
 * Author: nedhuo
 * Description:
 */
public class CommunityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Activity mCtx;
    private List<ItemList> mItemList;

    public CommunityAdapter(Activity ctx) {
        mCtx = ctx;
        mItemList = new ArrayList<>();
    }

    /**
     * CommunityData中，第一条数据与第二条数据虽然Type都一样，但里面的数据是不一样的
     * 第一条数据是两个图片
     * 第二条数据是一个BannerView Data
     * 把这两条数据提取出来，从第三条数据开始加载recyclerView
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case DataType.UGC_PICTURE_ID:
            case DataType.VIDEO_CARD_ID:
                view = LayoutInflater.from(mCtx)
                        .inflate(R.layout.item_community_video, parent, false);
                return new FollowCardViewHolder(view);
        }
        return new RecyclerView.ViewHolder(new View(mCtx)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FollowCardViewHolder) {
            FollowCardViewHolder viewHolder = (FollowCardViewHolder) holder;
            Content content = mItemList.get(position).getData().getContent();
            Data data = content.getData();
            if (DataType.UGC_PICTURE_CARD.equals(content.getType())) {
                GlideUtil.loadImage(viewHolder.iv_content,data.getUrl(),viewHolder.iv_content);
                GlideUtil.loadCircleImage(viewHolder.iv_headImage,
                        data.getOwner().getAvatar(),
                        viewHolder.iv_headImage);
                viewHolder.tv_name.setText(data.getOwner().getNickname());
                viewHolder.tv_desc.setText(data.getDescription());
                viewHolder.iv_flag.setImageResource(R.drawable.ic_storage);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mCtx, UgcPictureActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constant.PICTURE_BEAN, data);
                        intent.putExtra(Constant.PICTURE_BUNDLE, bundle);
                        mCtx.startActivity(intent);
                    }
                });
            } else if (DataType.VIDEO_CARD.equals(content.getType())) {
                Glide.with((viewHolder).iv_content)
                        .load(data.getCover().getFeed())
                        .into(viewHolder.iv_content);
                Glide.with((viewHolder).iv_headImage)
                        .load(data.getOwner().getAvatar())
                        .centerCrop()
                        .circleCrop()
                        .into(viewHolder.iv_headImage);
                viewHolder.tv_name.setText(data.getOwner().getNickname());
                if (data.getDescription().trim().equals("")) {
                    viewHolder.tv_desc.setVisibility(View.GONE);
                } else {
                    viewHolder.tv_desc.setVisibility(View.VISIBLE);
                    viewHolder.tv_desc.setText(data.getDescription());
                }

                viewHolder.iv_flag.setImageResource(R.drawable.video_play);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mCtx, CommunityVideoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constant.VIDEO_BEAN, data);
                        intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                        mCtx.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItemList.size() > 0) {
            Content content = mItemList.get(position).getData().getContent();
            switch (content.getType()) {
                case DataType.UGC_PICTURE_CARD:
                    return DataType.UGC_PICTURE_ID;
                case DataType.VIDEO_CARD:
                    return DataType.VIDEO_CARD_ID;
                default:
            }
        }
        return -1;
    }

    public void setData(List<ItemList> data, boolean isUpdate) {
        if (isUpdate) mItemList.clear();
        mItemList.addAll(data);
        notifyDataSetChanged();
    }


    private class DataType {
        final static String UGC_PICTURE_CARD = "ugcPicture";
        final static String VIDEO_CARD = "video";

        final static int UGC_PICTURE_ID = 100;
        final static int VIDEO_CARD_ID = 200;

    }


    private class FollowCardViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_headImage;
        private final ImageView iv_content;
        private final TextView tv_desc;
        private final TextView tv_name;
        private final ImageView iv_flag;

        public FollowCardViewHolder(View view) {
            super(view);

            iv_content = view.findViewById(R.id.iv_content);
            iv_flag = view.findViewById(R.id.iv_flag);
            tv_desc = view.findViewById(R.id.tv_desc);
            tv_name = view.findViewById(R.id.tv_title);
            iv_headImage = view.findViewById(R.id.iv_headImage);
        }
    }


}
