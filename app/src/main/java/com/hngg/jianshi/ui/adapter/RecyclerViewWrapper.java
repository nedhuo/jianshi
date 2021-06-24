package com.hngg.jianshi.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.viewholder.BannerViewHolder;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2021/2/20
 * Timer: 16:45
 * Author: nedhuo
 * Description:对Adapter的一个包装类 ，为了给RecyclerView添加HeaderView或者FooterView
 * 实现原理：用Adapter对RecyclerView原本的Adapter实现一封封装，将HeaderView,与FooterView
 * 封装进去,然后用position区分，是HeaderView,或者FooterView的话，则由包装类Adapter处理，
 * 如果不是的话，直接交给内层Adapter处理
 * <p>
 * 这个封装类是CommunityFragment定制的，不通用
 */
public class RecyclerViewWrapper<T extends RecyclerView.Adapter> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_HEADER_ID = 100;
    private static final int BASE_FOOTER_ID = 200;
    private final T mAdapter;
    private final Activity mCtx;
    private static final String TAG = "RecyclerViewWrapper";
    //SparseArray(稀疏数组)，4.4以上，SparseArrayCompat是为了兼容低版本的存在
    // 在Android内部用来替代HashMap<Integer,E>
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();
    private List<ItemList> mItemList;

    public RecyclerViewWrapper(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, Activity ctx) {
        mAdapter = (T) adapter;
        mCtx = ctx;
        mItemList = new ArrayList<>();
    }

    /**
     * 注意 : 装饰类的ViewType与被装饰类的ViewType是统一的，不要使用一样的ViewType
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.ITEM_COLLECTION_ID) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.item_community_content, parent,
                    false);
            return new CommunityContentViewHolder(view);
        } else if (viewType == DataType.HORIZONTAL_SCROLL_ID) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.item_community_banner, parent,
                    false);
            return new BannerViewHolder(view);
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isHeaderView(position)) {
            if (holder instanceof CommunityContentViewHolder) {
                //
                CommunityContentViewHolder viewHolder = (CommunityContentViewHolder) holder;
                List<ItemList> itemList = mItemList.get(position).getData().getItemList();

                GlideUtil.loadRectangleImage(mCtx,
                        itemList.get(0).getData().getBgPicture(),
                        viewHolder.iv_content1, 5);
                viewHolder.tv_title1.setText(itemList.get(0).getData().getTitle());
                viewHolder.tv_desc1.setText(itemList.get(0).getData().getSubTitle());
                LogUtil.i(TAG, itemList.get(0).getData().toString());
                GlideUtil.loadRectangleImage(mCtx,
                        itemList.get(1).getData().getBgPicture(),
                        viewHolder.iv_content2, 5);

                viewHolder.tv_title2.setText(itemList.get(1).getData().getTitle());
                viewHolder.tv_desc2.setText(itemList.get(1).getData().getSubTitle());
            } else if (holder instanceof BannerViewHolder) {
                BannerViewHolder viewHolder = (BannerViewHolder) holder;
                List<ItemList> itemList = mItemList.get(position).getData().getItemList();
                BannerViewAdapter adapter = new BannerViewAdapter(itemList);
                viewHolder.banner.setAdapter(adapter);
                viewHolder.banner.setIndicator(new CircleIndicator(mCtx));
                //添加魅族效果
//                viewHolder.banner.setBannerGalleryMZ(20);
//                viewHolder.banner.addPageTransformer(new AlphaPageTransformer());
            }
        } else {
            mAdapter.onBindViewHolder(holder, position - getHeadersCount());
        }
    }


    @Override
    public int getItemCount() {
        return getHeadersCount() + getFooterCount() + mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position) && mItemList.size() > 0) {
            String dataType = mItemList.get(position).getData().getDataType();
            if (dataType.equals(DataType.ITEM_COLLECTION)) {
                return DataType.ITEM_COLLECTION_ID;
            } else if (dataType.equals(DataType.HORIZONTAL_SCROLL_CARD)) {
                return DataType.HORIZONTAL_SCROLL_ID;
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
        int position = holder.getLayoutPosition();
        if (isHeaderView(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    public void setData(List<ItemList> data, boolean isUpdate) {
        if (isUpdate) {
            mItemList.clear();
            mItemList.addAll(data);
        }
        notifyDataSetChanged();
    }


    private class DataType {
        final static String HORIZONTAL_SCROLL_CARD = "HorizontalScrollCard";
        final static String ITEM_COLLECTION = "ItemCollection";

        final static int HORIZONTAL_SCROLL_ID = 1;
        final static int ITEM_COLLECTION_ID = 2;
    }


    private class BannerViewAdapter extends BannerAdapter<ItemList, BannerViewAdapter.BannerItemViewHolder> {
        BannerViewAdapter(List<ItemList> datas) {
            super(datas);
        }

        @Override
        public BannerItemViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new BannerItemViewHolder(imageView);
        }

        @Override
        public void onBindView(BannerItemViewHolder holder, ItemList data, int position, int size) {
            String imageUrl = data.getData().getImage();
            LogUtil.i(TAG, data.getData().toString());
            GlideUtil.loadImage(holder.imageView, imageUrl, holder.imageView);
        }

        class BannerItemViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            BannerItemViewHolder(@NonNull ImageView view) {
                super(view);
                this.imageView = view;
            }
        }
    }

}

class CommunityContentViewHolder extends RecyclerView.ViewHolder {

    public final ImageView iv_content1;
    public final ImageView iv_content2;
    public final TextView tv_title1;
    public final TextView tv_title2;
    public final TextView tv_desc1;
    public final TextView tv_desc2;

    CommunityContentViewHolder(@NonNull View itemView) {
        super(itemView);

        iv_content1 = itemView.findViewById(R.id.iv_content1);
        iv_content2 = itemView.findViewById(R.id.iv_content2);

        tv_title1 = itemView.findViewById(R.id.tv_title1);
        tv_title2 = itemView.findViewById(R.id.tv_title2);

        tv_desc1 = itemView.findViewById(R.id.tv_desc1);
        tv_desc2 = itemView.findViewById(R.id.tv_desc2);
    }
}