package com.hngg.jianshi.utils;

import android.util.Log;

/**
 * 集中管理，方便后期处理
 */
public class LogUtil {
    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }
}
