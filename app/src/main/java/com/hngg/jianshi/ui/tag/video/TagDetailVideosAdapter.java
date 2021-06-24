package com.hngg.jianshi.ui.tag.video;

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
import com.hngg.jianshi.data.bean.home.Header;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.viewholder.Video2ViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class TagDetailVideosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final FragmentActivity mCtx;
    private List<ItemList> mDataList;
    private static final String TAG = "TagDetailVideosAdapter";

    TagDetailVideosAdapter(FragmentActivity activity, List<ItemList> dataList) {
        mCtx = activity;
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        try {
            mDataList.addAll(dataList);
        } catch (Exception e) {
            LogUtil.e(TAG, "mDataList为null");
        }
    }

    @Override
    public int getItemViewType(int position) {
        String type = mDataList.get(position).getType();
        if (type.equals(DataType.FOLLOW_CARD)) {
            return DataType.FOLLOW_CARD_ID;
        } else if (type.equals(DataType.PICTURE_FOLLOW_CARD)) {
            return DataType.PICTURE_FOLLOW_ID;
        } else {
            LogUtil.i(TAG, "TagDetailFragment漏掉的类型" + type);
            return DataType.OTHER_ID;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.FOLLOW_CARD_ID) {
            View inflate = LayoutInflater.from(mCtx).inflate(R.layout.item_video2_card, parent, false);
            return new Video2ViewHolder(inflate);
        } else if (viewType == DataType.PICTURE_FOLLOW_ID) {
            //TODO 带实现
            TextView textView = new TextView(mCtx);
            return new RecyclerView.ViewHolder(textView) {
            };
        } else {
            TextView textView = new TextView(mCtx);
            return new RecyclerView.ViewHolder(textView) {
            };
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof Video2ViewHolder) {
            Video2ViewHolder holder = (Video2ViewHolder) viewHolder;
            Data data = mDataList.get(position).getData();
            Header header = data.getHeader();
            Data data1 = data.getContent().getData();

            GlideUtil.loadCircleImage(mCtx, header.getIcon(), holder.ivHead);
            holder.tvAuthor.setText(header.getTitle());
            holder.tvAuthorDesc.setText(header.getDescription());
            holder.tvDuration.setText(CommonUtil.intToTime(data1.getDuration()));
            GlideUtil.loadImage(mCtx, data1.getCover().getFeed(), holder.ivContent);
            holder.tvDesc.setText(data1.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setData(List<ItemList> data) {
        mDataList.clear();
        mDataList.addAll(data);
        notifyDataSetChanged();
    }


}
