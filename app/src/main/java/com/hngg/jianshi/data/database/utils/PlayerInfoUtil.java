package com.hngg.jianshi.data.database.utils;

import com.hngg.jianshi.data.database.bean.PlayInfo;
import com.hngg.jianshi.data.database.dao.DaoSession;
import com.hngg.jianshi.data.database.dao.PlayInfoDao;

import java.util.List;

public class PlayerInfoUtil {
    private final DaoSession mDaoSession;

    public PlayerInfoUtil(DaoSession daoSession) {
        mDaoSession = daoSession;
    }


    /**
     * @return true
     */
    public long querySeekTime(long videoId) {
        List<PlayInfo> list = mDaoSession.getPlayInfoDao().queryBuilder()
                .where(PlayInfoDao.Properties.VideoId.eq(videoId)).list();
        if (list == null || list.size() <= 0) {
            return 0L;
        }
        return list.get(0).getSeekTime();
    }

    public void updateSeekTime(PlayInfo playInfo, long seekTime) {
        List<PlayInfo> list = mDaoSession.getPlayInfoDao().queryBuilder()
                .where(PlayInfoDao.Properties.VideoId.eq(playInfo.getVideoId())).list();
        if (list == null || list.size() <= 0) {
            playInfo.setSeekTime(seekTime);
            mDaoSession.getPlayInfoDao().insert(playInfo);
        } else {
            list.get(0).setSeekTime(seekTime);
            mDaoSession.getPlayInfoDao().insertOrReplace(list.get(0));
        }
    }

    public List<PlayInfo> queryAll() {
        return mDaoSession.getPlayInfoDao().queryBuilder().list();
    }

    public PlayInfo queryByVideoId(long videoId) {
        List<PlayInfo> list = mDaoSession.getPlayInfoDao().queryBuilder()
                .where(PlayInfoDao.Properties.VideoId.eq(videoId)).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public void deleteList(List<PlayInfo> mDeleteList) {
        //for (PlayInfo playInfo : mDeleteList) {
            mDaoSession.getPlayInfoDao().deleteInTx(mDeleteList);
      //  }
    }
}
