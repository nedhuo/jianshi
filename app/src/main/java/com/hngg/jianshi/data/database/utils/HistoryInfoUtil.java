package com.hngg.jianshi.data.database.utils;

import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.database.bean.HistoryInfo;
import com.hngg.jianshi.data.database.dao.DaoSession;
import com.hngg.jianshi.data.database.dao.HistoryInfoDao;

import java.util.List;

public class HistoryInfoUtil {
    private final DaoSession mDaoSession;

    public HistoryInfoUtil(DaoSession daoSession) {
        mDaoSession = daoSession;
    }


    public List<HistoryInfo> queryAll() {
//        List<HistoryInfo> list = new ArrayList<>();
//        List<HistoryInfo> historyInfos = mDaoSession.getHistoryInfoDao().queryBuilder().list();
//        for (HistoryInfo historyInfo : historyInfos) {
//            long videoId = historyInfo.getVideoId();
//            for (HistoryInfo info : list) {
//                if (videoId == info.getVideoId()) {
//                    break;
//                }
//            }
//            list.add(historyInfo);
//        }
//        return list;
        return mDaoSession.getHistoryInfoDao().queryBuilder().list();
    }

    /**
     * @return true 添加成功
     */
    public void add(HistoryInfo info) {
        List<HistoryInfo> list = mDaoSession.getHistoryInfoDao().queryBuilder()
                .where(HistoryInfoDao.Properties.VideoId.eq(info.getVideoId())).list();
//        LogUtil.i("HistoryInfoUtil", "HistoryInfoUtil" + list.size());
        if (list != null && list.size() > 0) {
            HistoryInfo historyInfo = list.get(0);
            historyInfo.setCreateTime(System.currentTimeMillis());
            mDaoSession.getHistoryInfoDao().insertOrReplace(historyInfo);
        } else {
            mDaoSession.getHistoryInfoDao().insertOrReplace(info);
        }
    }

    /**
     * @return true 添加成功
     */
    public void add(Data data) {
        HistoryInfo historyInfo = new HistoryInfo();
        historyInfo.setVideoId(data.getId());
        historyInfo.setVideoName(data.getTitle());
        historyInfo.setVideoTime(System.currentTimeMillis());
        historyInfo.setPoster(data.getCover().getFeed());
        historyInfo.setVideoDesc(data.getDescription());
        historyInfo.setUrl(data.getPlayUrl());
        historyInfo.setCreateTime(System.currentTimeMillis());
        historyInfo.setAuthorDesc(data.getAuthor().getDescription());
        historyInfo.setAuthorIcon(data.getAuthor().getIcon());
        historyInfo.setAuthorId(data.getAuthor().getId());
        historyInfo.setAuthorName(data.getAuthor().getName());
        add(historyInfo);
    }

    public void deleteAll() {
        mDaoSession.getHistoryInfoDao().deleteAll();
    }
}
