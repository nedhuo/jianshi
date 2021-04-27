package com.hngg.jianshi.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.DataType;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.viewholder.BannerViewHolder;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class RVWrapperWidget<T extends RecyclerView.Adapter> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_HEADER_ID = 100;
    private static final int BASE_FOOTER_ID = 200;
    private final T mAdapter;
    private final Activity mCtx;
    //SparseArray(稀疏数组)，4.4以上，SparseArrayCompat是为了兼容低版本的存在
    // 在Android内部用来替代HashMap<Integer,E>
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();
    private List<ItemList> mDataList;
    private String TAG = "RVWrapperWidget";

    public RVWrapperWidget(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, Activity ctx) {
        mAdapter = (T) adapter;
        mCtx = ctx;
        mDataList = new ArrayList<>();
    }

    /**
     * 注意 : 装饰类的ViewType与被装饰类的ViewType是统一的，不要使用一样的ViewType
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.VIDEO_COLLECTION_HORIZONTAL_SCROLL_ID) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.item_community_banner, parent, false);
            return new BannerViewHolder(view);
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isHeaderView(position)) {
            if (holder instanceof BannerViewHolder) {
                setBannerViewData((BannerViewHolder) holder, mDataList.get(position));
            }
        } else {
            mAdapter.onBindViewHolder(holder, position - getHeadersCount());
        }
    }

    private void setBannerViewData(BannerViewHolder holder, ItemList itemList) {
        List<ItemList> dataList = itemList.getData().getItemList();
        UserInfo_HomeBannerAdapter adapter = new UserInfo_HomeBannerAdapter(dataList);
        holder.banner.setAdapter(adapter);
        holder.banner.setIndicator(new CircleIndicator(mCtx));
        holder.banner.setBannerGalleryEffect(10, 10,5,1);
    }


    @Override
    public int getItemCount() {
        return getHeadersCount() + getFooterCount() + mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position) && mDataList.size() > 0) {
            ItemList itemList = mDataList.get(position);
            if (itemList.getType().equals(DataType.VIDEO_COLLECTION_HORIZONTAL_SCROLL_CARD)) {
                return DataType.VIDEO_COLLECTION_HORIZONTAL_SCROLL_ID;
            } else if (itemList.getType().equals(DataType.TEXT_HEADER)) {
                return -1;
            } else {
                return -1;
            }
        }
        return mAdapter.getItemViewType(position - getHeadersCount());
    }


    private int getHeadersCount() {
        return mHeaderViews.size();
    }

    private int getFooterCount() {
        return mFooterViews.size();
    }


    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_HEADER_ID, view);
    }

    public void addFooterView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_FOOTER_ID, view);
    }

    /**
     * 判断当前View是否是HeaderView
     */
    private boolean isHeaderView(int position) {
        if (position < getHeadersCount())
            return true;
        return false;
    }

    /**
     * 判断当前View是否是FooterView
     * <p>
     * 如果当前position >= HeaderViewCount + ItemCount ,即为FooterView
     */
    private boolean isFooterView(int position) {
        if (position >= getHeadersCount() + mAdapter.getItemCount())
            return true;
        return false;
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mAdapter.onViewAttachedToWindow(holder);
    }

    public void setData(List<ItemList> data, boolean isUpdate) {
        if (isUpdate) {
            mDataList.clear();
            mDataList.addAll(data);
        }
        notifyDataSetChanged();
    }


    class UserInfo_HomeBannerAdapter extends BannerAdapter<ItemList,
            UserInfo_HomeBannerAdapter.BannerItemViewHolder> {


        public UserInfo_HomeBannerAdapter(List<ItemList> datas) {
            super(datas);
        }

        @Override
        public BannerItemViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.item_banner_video, parent, false);
            return new UserInfo_HomeBannerAdapter.BannerItemViewHolder(view);
        }

        @Override
        public void onBindView(BannerItemViewHolder holder, ItemList data, int position, int size) {
            Data bannerData = mDatas.get(position).getData();
            LogUtil.i(TAG, "bannerData.getCover().getFeed()" + bannerData.getCover().getFeed());
            GlideUtil.loadImage(mCtx, bannerData.getCover().getFeed(), holder.ivImage);
            holder.tvTitle.setText(bannerData.getTitle());
        }

        class BannerItemViewHolder extends RecyclerView.ViewHolder {
            final ImageView ivImage;
            final TextView tvTitle;

            public BannerItemViewHolder(@NonNull View view) {
                super(view);
                ivImage = view.findViewById(R.id.iv_image);
                tvTitle = view.findViewById(R.id.tv_author);
            }
        }
    }
}

