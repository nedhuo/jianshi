package com.hngg.jianshi.ui.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;


/**
 * Date: 2021/2/18
 * Timer: 18:26
 * Author: nedhuo
 * Description:
 */
public class BannerViewAdapter extends BannerAdapter<ItemList,
        BannerViewAdapter.BannerItemViewHolder> {
    private final String mTag;

    public BannerViewAdapter(List<ItemList> datas, String tag) {
        super(datas);
        mTag = tag;
    }

    @Override
    public BannerItemViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setBackgroundResource(R.drawable.bg_card_round);
        return new BannerItemViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerItemViewHolder holder, ItemList data, int position, int size) {
        if (mTag.equals(DataType.RECOMMEND)) {
            String imageUrl = mDatas.get(position).getData().getContent().getData().getCover().getFeed();
            Glide.with(holder.imageView)
                    .load(imageUrl)
                    .centerCrop()
                    .into(holder.imageView);
        } else if (mTag.equals(DataType.DISCOVER)) {
            Data bannerData = mDatas.get(position).getData();
            Glide.with(holder.imageView)
                    .load(bannerData.getImage())
                    .centerCrop()
                    .into(holder.imageView);
        }

    }

    class BannerItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerItemViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }

    private class DataType {
        final static String DISCOVER = "DisCoverAdapter";
        final static String RECOMMEND = "RecommendAdapter";
    }
}
