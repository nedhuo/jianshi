package com.hngg.jianshi.data.database.utils;

import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.database.bean.HistoryInfo;
import com.hngg.jianshi.data.database.dao.DaoSession;

public class HistoryInfoUtil {
    private final DaoSession mDaoSession;

    public HistoryInfoUtil(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    /**
     * @return true 添加成功
     * */
    public boolean add(HistoryInfo info) {
        long l = mDaoSession.getHistoryInfoDao().insertOrReplace(info);
        if (l == -1) {
            return false;
        }
        return true;
    }

    /**
     * @return true 添加成功
     * */
    public boolean add(Data data) {
        HistoryInfo historyInfo = new HistoryInfo();
        historyInfo.setVideoId(data.getVideoId());
        historyInfo.setVideoName(data.getTitle());
        historyInfo.setVideoTime(System.currentTimeMillis());
        historyInfo.setPoster(data.getCover().getFeed());
        historyInfo.setUrl(data.getPlayUrl());
        historyInfo.setAuthorDesc(data.getAuthor().getDescription());
        historyInfo.setAuthorIcon(data.getAuthor().getIcon());
        historyInfo.setAuthorId((long) data.getAuthor().getId());
        historyInfo.setAuthorName(data.getAuthor().getName());
        return add(historyInfo);
    }

    public void deleteAll(){
        mDaoSession.getHistoryInfoDao().deleteAll();
    }
}
