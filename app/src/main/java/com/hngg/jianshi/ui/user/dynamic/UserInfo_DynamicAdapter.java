package com.hngg.jianshi.ui.user.dynamic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.DataType;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.viewholder.DynamicInfoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
class UserInfo_DynamicAdapter extends RecyclerView.Adapter {
    private final FragmentActivity mCtx;
    private final List<ItemList> mDataList;

    public UserInfo_DynamicAdapter(FragmentActivity activity) {
        mCtx = activity;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        ItemList itemList = mDataList.get(position);
        if (itemList.getType().equals(DataType.DYNAMIC_INFO_CARD)) {
            return DataType.DYNAMIC_INFO_CARD_ID;
        }
        return DataType.OTHER_ID;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.DYNAMIC_INFO_CARD_ID) {
            View inflate = LayoutInflater.from(mCtx)
                    .inflate(R.layout.item_dynamic_info, parent, false);
            return new DynamicInfoViewHolder(inflate);
        } else {
            TextView textView = new TextView(mCtx);
            return new RecyclerView.ViewHolder(textView) {
            };
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

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
