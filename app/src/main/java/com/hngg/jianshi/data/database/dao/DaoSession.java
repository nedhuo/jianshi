package com.hngg.jianshi.data.database.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hngg.jianshi.data.database.bean.VideoInfo;
import com.hngg.jianshi.data.database.bean.VideoResolutionInfo;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;

import com.hngg.jianshi.data.database.dao.VideoInfoDao;
import com.hngg.jianshi.data.database.dao.VideoResolutionInfoDao;
import com.hngg.jianshi.data.database.dao.VideoTaskInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig videoInfoDaoConfig;
    private final DaoConfig videoResolutionInfoDaoConfig;
    private final DaoConfig videoTaskInfoDaoConfig;

    private final VideoInfoDao videoInfoDao;
    private final VideoResolutionInfoDao videoResolutionInfoDao;
    private final VideoTaskInfoDao videoTaskInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        videoInfoDaoConfig = daoConfigMap.get(VideoInfoDao.class).clone();
        videoInfoDaoConfig.initIdentityScope(type);

        videoResolutionInfoDaoConfig = daoConfigMap.get(VideoResolutionInfoDao.class).clone();
        videoResolutionInfoDaoConfig.initIdentityScope(type);

        videoTaskInfoDaoConfig = daoConfigMap.get(VideoTaskInfoDao.class).clone();
        videoTaskInfoDaoConfig.initIdentityScope(type);

        videoInfoDao = new VideoInfoDao(videoInfoDaoConfig, this);
        videoResolutionInfoDao = new VideoResolutionInfoDao(videoResolutionInfoDaoConfig, this);
        videoTaskInfoDao = new VideoTaskInfoDao(videoTaskInfoDaoConfig, this);

        registerDao(VideoInfo.class, videoInfoDao);
        registerDao(VideoResolutionInfo.class, videoResolutionInfoDao);
        registerDao(VideoTaskInfo.class, videoTaskInfoDao);
    }
    
    public void clear() {
        videoInfoDaoConfig.clearIdentityScope();
        videoResolutionInfoDaoConfig.clearIdentityScope();
        videoTaskInfoDaoConfig.clearIdentityScope();
    }

    public VideoInfoDao getVideoInfoDao() {
        return videoInfoDao;
    }

    public VideoResolutionInfoDao getVideoResolutionInfoDao() {
        return videoResolutionInfoDao;
    }

    public VideoTaskInfoDao getVideoTaskInfoDao() {
        return videoTaskInfoDao;
    }

}