package com.hngg.jianshi.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.hngg.jianshi.R;

public class GlideUtil {
    public static void loadImage(Context context, String imageUrl, ImageView view) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    public static void loadImage(View context, String imageUrl, ImageView view) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    public static void loadImage(View context, String imageUrl, String place, ImageView view) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                .into(view);
    }

    public static void loadCircleImage(Context context, String imageUrl, ImageView view) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.mipmap.ic_launcher)
                .circleCrop()
                .into(view);
    }

    public static void loadCircleImage(View context, String imageUrl, ImageView view) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.mipmap.ic_launcher)
                .circleCrop()
                .into(view);
    }
}
