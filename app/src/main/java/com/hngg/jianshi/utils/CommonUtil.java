package com.hngg.jianshi.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Date: 2021/2/14
 * Timer: 17:42
 * Author: nedhuo
 * Description:
 */
public class CommonUtil {
    private static final String TAG = "CommonUtil";
    // private static int mCurrentId = -1;

    /**
     * 将时间格式从 int 转为 00:00
     */
    public static String intToTime(int duration) {
        String time;
        int seconds = duration % 60;
        int minutes = duration / 60;

        if (minutes < 10)
            time = "0" + minutes;
        else time = "" + minutes;
        time = time + ":";
        if (seconds < 10)
            time = time + "0" + seconds;
        else time = time + seconds;
        return time;
    }

    public static String longToDate(long lo) {
        Date date = new Date(lo);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sd.format(date);
    }

    /**
     * 生成唯一 int 类型ID
     */
    public static int generatorUniqueID(Context context) {
        int uniqueId = SpUtil.getInt(context, Constant.UNIQUE_ID, -1);
        if (uniqueId > -1) {
            uniqueId++;
            SpUtil.putInt(context, Constant.UNIQUE_ID, uniqueId);
            return uniqueId;
        } else {
            /*
             * 两种情况， 第一次取值
             *          储存文件丢失
             * 丢给前台访问数据库进行判断处理
             * */
            uniqueId++;
            SpUtil.putInt(context, Constant.UNIQUE_ID, uniqueId);
            return uniqueId;
        }
    }


    /**
     * long数据转kb
     */
    public static String sizeTranform(Long data) {
        if (data == null)
            return "";
        float GB = 1024 * 1024 * 1024F;
        float MB = 1024 * 1024F;
        float KB = 1024F;
        float B = 1F;
        if (data / GB >= 1)
            return ((data / GB) * 100) / 100.0 + "gb";
        else if (data / MB >= 1)
            return ((data / GB) * 100) / 100.0 + "mb";
        else if (data / KB >= 1)
            return ((data / GB) * 100) / 100.0 + "kb";
        else if (data / B >= 1)
            return ((data / GB) * 100) / 100.0 + "b";
        else return data + "b";
    }


    private boolean checkID(Context context) {
        List<VideoTaskInfo> list = DbManager.getInstance(context).getVideoTaskDao().queryAll();
        if (list != null && list.size() != 0) {
            LogUtil.i(TAG, "list.get(0).getDownId()" + list.get(0).getDownId());
            LogUtil.i(TAG, "list.get(list.size()-1).getDownId()"
                    + list.get(list.size() - 1).getDownId());
        }
        return false;
    }
}
