package com.hngg.jianshi.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.webview.WebViewActivity;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;
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
    private static final String TAG = "BannerViewAdapter";
    private final String mTag;
    private final Activity mCtx;

    public BannerViewAdapter(List<ItemList> datas, Activity context, String tag) {
        super(datas);
        mTag = tag;
        mCtx=context;
    }

    @Override
    public BannerItemViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setBackgroundResource(R.drawable.shape_card_white);
        return new BannerItemViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerItemViewHolder holder, ItemList itemList, int position, int size) {
        if (mTag.equals(DataType.RECOMMEND)) {
            Data data = mDatas.get(position).getData().getContent().getData();
            String imageUrl = data.getCover().getFeed();
            GlideUtil.loadRectangleImage(holder.imageView, imageUrl, holder.imageView, 20);
            holder.itemView.setOnClickListener(v -> {
                LogUtil.i(TAG, "" + data.getWebUrl().getRaw());
                Intent intent = new Intent(mCtx, WebViewActivity.class);
                intent.putExtra(Constant.WEBVIEW_URL, data.getWebUrl().getRaw());
                mCtx.startActivity(intent);
            });
        } else if (mTag.equals(DataType.DISCOVER)) {
            Data bannerData = mDatas.get(position).getData();
            GlideUtil.loadImage(holder.imageView, bannerData.getImage(), holder.imageView);
            GlideUtil.loadImage(holder.imageView, bannerData.getImage(), holder.imageView);
            holder.itemView.setOnClickListener(v -> {
                LogUtil.i(TAG, "" + bannerData.getWebUrl().getRaw());
                Intent intent = new Intent(mCtx, WebViewActivity.class);
                intent.putExtra(Constant.WEBVIEW_URL, bannerData.getWebUrl().getRaw());
                mCtx.startActivity(intent);
            });
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
