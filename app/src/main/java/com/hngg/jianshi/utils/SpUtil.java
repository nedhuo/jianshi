package com.hngg.jianshi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.greendao.annotation.Unique;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class SpUtil {

    public static boolean putInt(Context context, String key, int uniqueId) {
        SharedPreferences sp =
                context.getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(Constant.UNIQUE_ID, uniqueId);
        return edit.commit();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }
}
