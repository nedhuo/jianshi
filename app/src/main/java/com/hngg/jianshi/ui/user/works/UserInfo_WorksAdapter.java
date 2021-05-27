package com.hngg.jianshi.ui.user.works;

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
import com.hngg.jianshi.data.bean.home.Tags;
import com.hngg.jianshi.ui.tag.TagDetailActivity;
import com.hngg.jianshi.ui.video.VideoDetailActivity;
import com.hngg.jianshi.ui.viewholder.Video2ViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
class UserInfo_WorksAdapter extends RecyclerView.Adapter {
    private final FragmentActivity mCtx;
    private final List<ItemList> mDataList;

    public UserInfo_WorksAdapter(FragmentActivity activity) {
        mCtx = activity;
        mDataList = new ArrayList<>();
    }


    @Override
    public int getItemViewType(int position) {
        ItemList itemList = mDataList.get(position);
        if (itemList.getType().equals(DataType.VIDEO_CARD)) {
            return DataType.VIDEO_CARD_ID;
        } else {
            return DataType.OTHER_ID;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.VIDEO_CARD_ID) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.item_video2_card, parent, false);
            return new Video2ViewHolder(view);
        } else {
            TextView textView = new TextView(mCtx);
            return new RecyclerView.ViewHolder(textView) {
            };
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Data data = mDataList.get(position).getData();
        if (viewHolder instanceof Video2ViewHolder) {
            Video2ViewHolder holder = (Video2ViewHolder) viewHolder;
            GlideUtil.loadCircleImage(mCtx, data.getAuthor().getIcon(), holder.ivHead);
            GlideUtil.loadImage(mCtx, data.getCover().getFeed(), holder.ivContent);

            holder.tvAuthor.setText(data.getAuthor().getName());
            holder.tvAuthorDesc.setText(data.getAuthor().getDescription());
            holder.tvDesc.setText(data.getDescription());
            holder.tvDuration.setText(CommonUtil.intToTime(data.getDuration()));
            holder.tagFlowLayout.setAdapter(new TagAdapter<Tags>(data.getTags()) {
                @Override
                public View getView(FlowLayout parent, int position, Tags tags) {
                    TextView textView = (TextView) LayoutInflater.from(mCtx).inflate(R.layout.item_tag, parent, false);
//                    TextView textView = new TextView(mCtx);
//                    parent.addView(textView);
//                    textView.setTextColor(mCtx.getColor(R.color.colorBlue));
                    textView.setText(tags.getName());
//                    ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
//                    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
//                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//                    textView.setLayoutParams(layoutParams);
                    return textView;
                }
            });
            holder.tagFlowLayout.setOnTagClickListener((view, position1, parent) -> {
                Intent intent = new Intent(mCtx, TagDetailActivity.class);
                Bundle bundle = new Bundle();
                Tags tags = data.getTags().get(position1);
                bundle.putString(Constant.TAGDETAIL_TITLE, tags.getName());
                bundle.putString(Constant.TAGDETAIL_DESC, tags.getDesc());
                bundle.putLong(Constant.TAGDETAIL_BEAN, tags.getId());//id
                intent.putExtra(Constant.TAGDETAIL_BUNDLE, bundle);
                mCtx.startActivity(intent);
                return true;
            });
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mCtx, VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.VIDEO_BEAN, data);
                intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                mCtx.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setData(List<ItemList> itemList, boolean isUpdate) {
        if (isUpdate) {
            mDataList.clear();
        }
        mDataList.addAll(itemList);
        notifyDataSetChanged();
    }
}