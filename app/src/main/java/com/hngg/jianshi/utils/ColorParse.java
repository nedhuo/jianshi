package com.hngg.jianshi.utils;

import android.app.Activity;
import android.graphics.Bitmap;

import androidx.palette.graphics.Palette;

public class ColorParse {
    private static final String TAG = "ColorParse";

    public static void parseBitmap(Activity context, Bitmap bitmap) {
        Palette.Builder builder = Palette.from(bitmap);

        builder.generate(palette -> {
            Palette.Swatch swatch = palette.getVibrantSwatch(); //获取到充满活力的这种色调
//            Palette.Swatch s = palette.getDarkVibrantSwatch(); //获取充满活力的黑
//            Palette.Swatch s = palette.getLightVibrantSwatch(); //获取充满活力的亮
//            Palette.Swatch s = palette.getMutedSwatch(); //获取柔和的色调
//            Palette.Swatch s = palette.getDarkMutedSwatch(); //获取柔和的黑
//            Palette.Swatch s = palette.getLightMutedSwatch(); //获取柔和的亮
            if (swatch != null) {
                //设置Status字体颜色
                StatusBarUtil.setFontColor(context.getWindow(), swatch.getRgb());
                // view.setBackgroundColor(swatch.getRgb());
            } else {
                LogUtil.e(TAG, "swatch为空");
            }

            //   List<Palette.Swatch> mSwatch ＝palette.getSwatches()//获取到多种颜色

            // vibrant.getBodyTextColor();
            // 获取文本颜色，避免在特殊颜色背景下，看不到文本

            //   vibrant.getTitleTextColor();

        });
    }
}
