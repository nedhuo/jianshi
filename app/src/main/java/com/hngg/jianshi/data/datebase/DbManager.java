package com.hngg.jianshi.data.datebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 使用GreenDao对数据库进行管理
 * <p>
 * 搜索历史库
 * <p>
 * 观看历史记录
 * <p>
 * 我的收藏库
 * <p>
 * 下载库
 */
public class DbManager {
    @SuppressLint("StaticFieldLeak")
    private static DbManager mDbManager;
    private DaoSession mDaoSession;
    private final Context context;
    private DaoMaster mDaoMaster;
    private DaoMaster.DevOpenHelper mHelper;

    /**
     * 获取单例
     */
    public static DbManager getInstance(Context context) {
        if (mDbManager == null) {
            synchronized (DbManager.class) {
                if (mDbManager == null) {
                    mDbManager = new DbManager(context);
                }
            }
        }
        return mDbManager;
    }


    private DbManager(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, "jianshi.db", null);
        mDaoMaster = new DaoMaster(getWritableDatabase(context));
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public VideoTaskInfoDao getVideoTaskDao() {
        return mDaoSession.getVideoTaskInfoDao();
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase(Context context) {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, "jianshi.db", null);
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase(Context context) {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, "jianhsi.db", null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }
}
