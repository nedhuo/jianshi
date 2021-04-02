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
import com.hngg.jianshi.R;

public class GlideUtil {
//    public static void loadImage(Context context, String imageUrl, ImageView view) {
//        Glide.with(context).load(imageUrl).apply(new BaseRequestOptions<T>() {
//            @NonNull
//            @Override
//            public T diskCacheStrategy(@NonNull DiskCacheStrategy strategy) {
//                return super.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//            }
//
//            @NonNull
//            @Override
//            public T placeholder(@Nullable Drawable drawable) {
//                return super.placeholder(R.mipmap.ic_launcher);
//            }
//        }).into(view);
//    }
//
//
//    public static void loadCircleImage(Context context, String imageUrl, ImageView view) {
//        Glide.with(context).load(imageUrl).apply(new BaseRequestOptions<T>() {
//            @NonNull
//            @Override
//            public T diskCacheStrategy(@NonNull DiskCacheStrategy strategy) {
//                return super.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//            }
//
//            @NonNull
//            @Override
//            public T placeholder(@Nullable Drawable drawable) {
//                return super.placeholder(R.mipmap.ic_launcher);
//            }
//
//            @NonNull
//            @Override
//            public T circleCrop() {
//                return super.circleCrop();
//            }
//        }).into(view);
//    }
}
