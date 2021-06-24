package com.hngg.jianshi.ui.viewholder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.youth.banner.Banner;

/**
 * Date: 2021/2/18
 * Timer: 16:51
 * Author: nedhuo
 * Description:
 */
public class BannerViewHolder extends RecyclerView.ViewHolder {

    public final Banner banner;

    public BannerViewHolder(View itemView) {
        super(itemView);
        banner = itemView.findViewById(R.id.banner);
    }
}
