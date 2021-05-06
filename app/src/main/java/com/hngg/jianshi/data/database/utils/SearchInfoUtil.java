package com.hngg.jianshi.data.database.utils;

import com.hngg.jianshi.data.database.bean.SearchInfo;
import com.hngg.jianshi.data.database.dao.DaoSession;

import java.util.List;

public class SearchInfoUtil {
    private final DaoSession mDaoSession;

    public SearchInfoUtil(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    public List<SearchInfo> queryAll() {
        return mDaoSession.getSearchInfoDao().queryBuilder()
                .list();
    }


    public void add(String data){
        SearchInfo searchInfo = new SearchInfo();
        searchInfo.setSearchField(data);
        searchInfo.setSearchTime(System.currentTimeMillis());
        long id = mDaoSession.getSearchInfoDao().insertOrReplace(searchInfo);
    }

}
