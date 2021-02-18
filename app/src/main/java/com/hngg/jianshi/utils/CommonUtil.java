package com.hngg.jianshi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 2021/2/14
 * Timer: 17:42
 * Author: nedhuo
 * Description:
 */
public class CommonUtil {
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

    public static String longToDate(long lo){
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sd.format(date);
    }
}
