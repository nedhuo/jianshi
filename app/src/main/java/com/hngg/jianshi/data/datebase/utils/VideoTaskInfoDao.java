package com.hngg.jianshi.data.datebase.utils;

import android.content.Context;

import com.hngg.jianshi.data.datebase.DbManager;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class VideoTaskInfoDao {

    public static void aa(Context context){
        DbManager.getInstance(context).getVideoTaskDao().queryBuilder();
    }
}
