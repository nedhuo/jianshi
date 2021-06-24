package com.hngg.jianshi.widget;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public abstract class DownloadTarget implements Target<Bitmap> {

    private Request request;
    private int width;
    private int height;

    public DownloadTarget(){
        this(SIZE_ORIGINAL, SIZE_ORIGINAL);
    }

    public DownloadTarget(int width, int height) {
        this.width=width;
        this.height=height;

    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {

    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        ToastUtils.showShort("通知图片加载失败");
    }

    @Override
    public void onResourceReady(@NonNull Bitmap resource,
                                @Nullable Transition<? super Bitmap> transition) {
        onloadSuccess(resource, transition);
    }


    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {

    }

    @Override
    public void getSize(@NonNull SizeReadyCallback cb) {
        if (!Util.isValidDimensions(width, height)) {
            throw new IllegalArgumentException(
                    "Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given" + " width: "
                            + width + " and height: " + height + ", either provide dimensions in the constructor"
                            + " or call override()");
        }
        cb.onSizeReady(width, height);
    }

    @Override
    public void removeCallback(@NonNull SizeReadyCallback cb) {

    }

    @Override
    public void setRequest(@Nullable Request request) {
        this.request = request;
    }

    @Nullable
    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    public abstract void onloadSuccess(Bitmap resource, Transition<? super Bitmap> transition);
}
