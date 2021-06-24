package com.hngg.jianshi.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.hngg.jianshi.utils.GlideUtil;
import com.lzy.ninegrid.NineGridView;

public class GlideImageLoader implements NineGridView.ImageLoader {
    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        GlideUtil.loadImage(context, url, imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
