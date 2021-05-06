package com.hngg.jianshi.data.database.utils;

import com.hngg.jianshi.data.database.bean.CollectionInfo;
import com.hngg.jianshi.data.database.dao.DaoSession;

import java.util.List;

public class CollectionInfoUtil {
    private final DaoSession mDaoSession;

    public CollectionInfoUtil(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    public List<CollectionInfo> queryNotFinished() {
        return mDaoSession.getCollectionInfoDao().queryBuilder()
                .list();
    }
}
