package com.hngg.jianshi.utils;

import android.content.Context;

import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.data.datebase.VideoTask;
import com.hngg.jianshi.data.datebase.VideoTaskDao;

import org.greenrobot.greendao.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Date: 2021/2/14
 * Timer: 17:42
 * Author: nedhuo
 * Description:
 */
public class CommonUtil {
    // private static int mCurrentId = -1;

    /**
     * 将时间格式从 int 转为 00:00
     */
    public static String intToTime(int duration) {
        String time;
        int minutes = duration / 60;
        int seconds = duration % 60;
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

    public void test(Context context) {
        int uniqueID = generatorUniqueID(context);
        if (uniqueID == 0) {
            DbManager dbManager = DbManager.getInstance(context);


            dbManager.getVideoTaskDao()
                    .queryBuilder()
                    .orderAsc(VideoTaskDao.Properties.DownId)
                    .list();
        }
    }
}
