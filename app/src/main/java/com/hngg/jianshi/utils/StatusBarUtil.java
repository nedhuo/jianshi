package com.hngg.jianshi.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.Transition;
import com.hngg.jianshi.widget.DownloadTarget;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class StatusBarUtil {
    public static void setFontColor(@NonNull Window window, int color) {
        View decorView = window.getDecorView();
//        WindowInsetsController controller = decorView.getWindowInsetsController();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        window.setStatusBarColor(color);
//        WindowInsetsController controller = decorView.getWindowInsetsController();
//        if (controller != null) {
//            controller.hide(WindowInsets.Type.statusBars());
//        }
        setTextDark(window, !isDarkColor(color));
    }

    private static void setTextDark(Window window, boolean isDark) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            //WindowInsetsController
            int systemUiVisibility = decorView.getWindowSystemUiVisibility();
            //    WindowInsetsController controller = window.getInsetsController();

            if (isDark) {
//                    controller.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
//                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
//                    controller.setSystemBarsAppearance(0,WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
                decorView.setSystemUiVisibility(systemUiVisibility & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {

                decorView.setSystemUiVisibility(systemUiVisibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    public static boolean isDarkColor(@ColorInt int color) {
        return ColorUtils.calculateLuminance(color) < 0.5;
    }

    public static void setTransparent(@NonNull Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(Color.TRANSPARENT);
    }


    public static void setTransparent(Context context) {
        if (context instanceof Activity) {
            setTransparent(((Activity) context).getWindow());
        }
    }

    /**
     * 解析加载到的图片设置字体颜色
     */
    public static void setFontColorByImage(Activity context, String imageUrl) {
        Glide.with(context).asBitmap().load(imageUrl).into(new DownloadTarget() {
            @Override
            public void onloadSuccess(Bitmap bitmap, Transition<? super Bitmap> transition) {
                ColorParse.parseBitmap(context, bitmap);
            }
        });
    }
}