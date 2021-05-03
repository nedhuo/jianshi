package com.hngg.jianshi.ui.tag.dynamic;

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
import com.hngg.jianshi.ui.community.UgcPictureActivity;
import com.hngg.jianshi.ui.viewholder.FollowPictureViewHolder;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridViewAdapter;

import java.util.ArrayList;
import java.util.List;

class TagDetailDynamicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemList> mDataList;
    private final FragmentActivity mCtx;
    private static final String TAG = "TagDetailDynamicAdapter";


    public TagDetailDynamicAdapter(FragmentActivity activity, List<ItemList> dataList) {
        mCtx = activity;
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.addAll(dataList);

    }

    @Override
    public int getItemViewType(int position) {
        String type = mDataList.get(position).getType();
        if (type.equals(DataType.PICTURE_FOLLOW_CARD)) {
            return DataType.PICTURE_FOLLOW_ID;
        } else {
            LogUtil.i(TAG, "TagDetailFragment漏掉的类型" + type);
            return DataType.OTHER_ID;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.PICTURE_FOLLOW_ID) {
            View inflate = LayoutInflater.from(mCtx).inflate(R.layout.item_pgc_picture, parent, false);
            return new FollowPictureViewHolder(inflate);
        } else {
            TextView textView = new TextView(mCtx);
            return new RecyclerView.ViewHolder(textView) {
            };
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof FollowPictureViewHolder) {
            FollowPictureViewHolder holder = (FollowPictureViewHolder) viewHolder;
            Data data = mDataList.get(position).getData().getContent().getData();
            GlideUtil.loadCircleImage(mCtx, data.getOwner().getAvatar(), holder.ivHead);
            holder.tvAuthor.setText(data.getOwner().getNickname());
            holder.tvAuthorDesc.setText(data.getOwner().getDescription());
            holder.tvDesc.setText(data.getDescription());
            List<ImageInfo> imageInfos = obtainNineImageData(data.getUrls());
            holder.nineGridView.setAdapter(new NineGridViewAdapter(mCtx, imageInfos) {
            });

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mCtx, UgcPictureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.PICTURE_BEAN, data);
                intent.putExtra(Constant.PICTURE_BUNDLE, bundle);
                mCtx.startActivity(intent);
            });
        }
    }

    private List<ImageInfo> obtainNineImageData(List<String> urls) {
        ArrayList<ImageInfo> imageInfos = new ArrayList<>();
        for (String url : urls) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setBigImageUrl(url);
            imageInfo.setThumbnailUrl(url);
            imageInfos.add(imageInfo);
        }
        return imageInfos;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setData(List<ItemList> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }
}
