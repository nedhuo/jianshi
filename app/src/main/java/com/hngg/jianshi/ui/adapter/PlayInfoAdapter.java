package com.hngg.jianshi.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseAdapter;
import com.hngg.jianshi.data.database.bean.PlayInfo;
import com.hngg.jianshi.ui.VideoPlayerActivity;
import com.hngg.jianshi.ui.me.playinfo.PlayInfoActivity;
import com.hngg.jianshi.ui.viewholder.PlayInfoViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class PlayInfoAdapter extends BaseAdapter {
    private final PlayInfoActivity mCtx;

    private List<PlayInfo> mDataList;
    public boolean mIsDeleteState = false;

    public PlayInfoAdapter(PlayInfoActivity playInfoActivity) {
        mCtx = playInfoActivity;
        mDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mCtx).inflate(R.layout.item_playinfo, parent, false);
        return new PlayInfoViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof PlayInfoViewHolder) {
            PlayInfo playInfo = mDataList.get(position);
            ((PlayInfoViewHolder) holder).tvTitle.setText(playInfo.getVideoName());
            ((PlayInfoViewHolder) holder).tvDuration.setText(CommonUtil.intToTime(playInfo.getDuration()));
            ((PlayInfoViewHolder) holder).tvPlayTime
                    .setText("继续播放：" + CommonUtil.intToTime((int) (playInfo.getSeekTime() / 1000)));
            GlideUtil.loadImage(mCtx, playInfo.getPoster(), ((PlayInfoViewHolder) holder).ivContent);
            if (playInfo.getDuration() != 0) {
                //seekTime 单位 ms  duration 单位 s
                int progress = (int) (playInfo.getSeekTime() / 10 / playInfo.getDuration());
                ((PlayInfoViewHolder) holder).progressBar.setProgress(progress);
            }

            if (mIsDeleteState) {
                //删除编辑状态
                ((PlayInfoViewHolder) holder).cbSelected.setVisibility(View.VISIBLE);
            } else {
                ((PlayInfoViewHolder) holder).cbSelected.setVisibility(View.GONE);
            }

            holder.itemView.setOnLongClickListener(v -> {
                if (!mIsDeleteState) {
                    mCtx.onDeleteListChange(playInfo, true);
                }
                return true;
            });

            holder.itemView.setOnClickListener(v -> {
                if (mIsDeleteState) {
                    if (((PlayInfoViewHolder) holder).cbSelected.isChecked()) {
                        ((PlayInfoViewHolder) holder).cbSelected.setChecked(false);
                    } else {
                        ((PlayInfoViewHolder) holder).cbSelected.setChecked(true);
                    }
                } else {
                    Intent intent = new Intent(mCtx, VideoPlayerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong(Constant.PLAYER_VIDEO_ID, playInfo.getVideoId());
                    intent.putExtra(Constant.PLAYER_BUNDLE, bundle);
                    mCtx.startActivity(intent);
                }
            });


            ((PlayInfoViewHolder) holder).cbSelected.setOnCheckedChangeListener((buttonView, isChecked) ->
                    mCtx.onDeleteListChange(playInfo, isChecked));
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setData(List<PlayInfo> itemList, boolean isUpdate) {
        if (isUpdate) {
            mDataList.clear();
        }
        mDataList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void setEditDelete(boolean isDeleteState) {
        mIsDeleteState = isDeleteState;
        LogUtil.i(TAG, "mIsDeleteState=" + mIsDeleteState);
        notifyDataSetChanged();
    }
}
