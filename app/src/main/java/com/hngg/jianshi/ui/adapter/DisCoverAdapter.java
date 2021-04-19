package com.hngg.jianshi.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.viewholder.BannerViewHolder;
import com.hngg.jianshi.ui.viewholder.TextHeaderViewHolder;
import com.hngg.jianshi.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2021/2/22
 * Timer: 15:57
 * Author: nedhuo
 * Description:
 */
public class DisCoverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Activity mCtx;
    private List<ItemList> mItemList;
    private final String TAG = "DisCoverAdapter";

    public DisCoverAdapter(Activity ctx) {
        mCtx = ctx;
        mItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case DataType.BRIEF_CARD_ID:
                view = LayoutInflater.from(mCtx)
                        .inflate(R.layout.item_brief, parent, false);
                return new BriefItemViewHolder(view);
            case DataType.COLUMN_CARD_ID:
                return new RecyclerView.ViewHolder(new View(mCtx)) {
                };
            case DataType.HORIZONTAL_SCROLL_ID:
                view = LayoutInflater.from(mCtx)
                        .inflate(R.layout.item_bannerview, parent, false);
                return new BannerViewHolder(view);
            case DataType.TEXT_CARD_ID:
                view = LayoutInflater.from(mCtx)
                        .inflate(R.layout.item_header, parent, false);
                return new TextHeaderViewHolder(view);
            default:
        }
        return new RecyclerView.ViewHolder(new View(mCtx)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            List<ItemList> itemList = mItemList.get(position).getData().getItemList();
            viewHolder.banner.setAdapter(new BannerViewAdapter(itemList, TAG));

        } else if (holder instanceof TextHeaderViewHolder) {
            TextHeaderViewHolder viewHolder = (TextHeaderViewHolder) holder;
            viewHolder.mTvHeaderTime.setText(mItemList.get(position).getData().getText());
        } else if (holder instanceof BriefItemViewHolder) {
            BriefItemViewHolder viewHolder = (BriefItemViewHolder) holder;
            Data data = mItemList.get(position).getData();
            GlideUtil.loadImage(mCtx,data.getIcon(),viewHolder.iv_image);
            viewHolder.tv_title.setText(data.getTitle());
            viewHolder.tv_desc.setText(data.getDescription());
            holder.itemView.setOnClickListener(v -> {

            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        String type = mItemList.get(position).getType();
        switch (type) {
            case DataType.BRIEF_CARD:
                return DataType.BRIEF_CARD_ID;
            case DataType.HORIZONTAL_SCROLL_CARD:
                return DataType.HORIZONTAL_SCROLL_ID;
            case DataType.TEXT_CARD:
                return DataType.TEXT_CARD_ID;
            case DataType.COLUMN_CARD_LIST:
                return DataType.COLUMN_CARD_ID;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void setData(List<ItemList> data, boolean isUpdate) {
        if (isUpdate) mItemList.clear();
        mItemList = data;
    }


    private class DataType {
        final static String HORIZONTAL_SCROLL_CARD = "horizontalScrollCard";
        final static String TEXT_CARD = "textCard";
        final static String BRIEF_CARD = "briefCard";
        final static String COLUMN_CARD_LIST = "columnCardList";


        final static int HORIZONTAL_SCROLL_ID = 100;
        final static int TEXT_CARD_ID = 200;
        final static int BRIEF_CARD_ID = 300;
        final static int COLUMN_CARD_ID = 400;
    }

    private class BriefItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_image;
        private final TextView tv_title;
        private final TextView tv_desc;

        public BriefItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
        }
    }


}
