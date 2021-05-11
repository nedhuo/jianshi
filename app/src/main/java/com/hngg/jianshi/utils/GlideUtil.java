package com.hngg.jianshi.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.jess.arms.http.imageloader.glide.BlurTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class GlideUtil {
    public static void loadImage(Context context, String imageUrl, ImageView view) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                //  .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    public static void loadImage(View context, String imageUrl, ImageView view) {
        DrawableCrossFadeFactory factory =
                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();

        Glide.with(context)
                .load(imageUrl)
                .transition(withCrossFade(factory))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                //    .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    public static void loadImage(View context, String imageUrl, String place, ImageView view) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                //     .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(view);
    }

    public static void loadCircleImage(Context context, String imageUrl, ImageView view) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                //     .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .circleCrop()
                .into(view);
    }

    public static void loadCircleImage(View context, String imageUrl, ImageView view) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                //    .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .circleCrop()
                .into(view);
    }

    public static void loadBlurImage(Context context, String imageUrl, ImageView view) {
        Glide.with(context).load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)//缓存
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25)))
                .into(view);
    }
}
