package com.hngg.jianshi.data.database.utils;

import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.database.bean.CollectionInfo;
import com.hngg.jianshi.data.database.dao.CollectionInfoDao;
import com.hngg.jianshi.data.database.dao.DaoSession;

import java.util.List;

public class CollectionInfoUtil {
    private final DaoSession mDaoSession;
    private static final String TAG = "CollectionInfoUtil";

    public CollectionInfoUtil(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    public List<CollectionInfo> queryAll() {
        return mDaoSession.getCollectionInfoDao().queryBuilder().list();
    }

    /**
     * @return true  表示数据已存在
     */
    public boolean queryIsExist(long videoId) {
        List<CollectionInfo> list = mDaoSession.getCollectionInfoDao().queryBuilder()
                .where(CollectionInfoDao.Properties.VideoId.eq(videoId)).list();
        if (list == null || list.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * @return true 收藏成功
     */
    public boolean onCollection(CollectionInfo info) {
        long l = mDaoSession.getCollectionInfoDao().insertOrReplace(info);
        if (l == -1) {
            return false;
        }
        return true;
    }

    /**
     * @return true 收藏成功
     */
    public boolean onCollection(Data videoData) {
        CollectionInfo collectionInfo = new CollectionInfo();
        collectionInfo.setAuthorDesc(videoData.getAuthor().getDescription());
        collectionInfo.setAuthorIcon(videoData.getAuthor().getIcon());
        collectionInfo.setAuthorId((long) videoData.getAuthor().getId());
        collectionInfo.setAuthorName(videoData.getAuthor().getName());
        collectionInfo.setCategory(videoData.getCategory());
        collectionInfo.setDuration(videoData.getDuration());
        collectionInfo.setPlayUrl(videoData.getPlayUrl());
        collectionInfo.setPoster(videoData.getCover().getFeed());
        collectionInfo.setVideoDesc(videoData.getDescription());
        collectionInfo.setVideoId(videoData.getId());
        collectionInfo.setVideoName(videoData.getTitle());
        //collectionInfo.setVideoSize();

        return onCollection(collectionInfo);
    }

    /**
     * 删除收藏记录
     *
     * @return true 删除成功
     */
    public boolean cancelCollection(Data videoData) {
        long videoId = videoData.getVideoId();
        CollectionInfo collectionInfo = mDaoSession.getCollectionInfoDao().queryBuilder()
                .where(CollectionInfoDao.Properties.VideoId.eq(videoId)).build().unique();
        if (collectionInfo != null) {
            mDaoSession.getCollectionInfoDao().delete(collectionInfo);
            return true;
        }
        return false;
    }
}
