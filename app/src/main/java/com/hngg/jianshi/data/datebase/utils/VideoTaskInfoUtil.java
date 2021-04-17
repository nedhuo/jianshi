package com.hngg.jianshi.data.datebase.utils;

import com.hngg.jianshi.data.datebase.DaoSession;
import com.hngg.jianshi.data.datebase.VideoTaskInfo;
import com.hngg.jianshi.data.datebase.VideoTaskInfoDao;
import com.hngg.jianshi.data.datebase.VideoTaskState;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class VideoTaskInfoUtil {

    private final DaoSession mDaoSession;

    public VideoTaskInfoUtil(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    public List<VideoTaskInfo> queryNotFinished() {
        return mDaoSession.getVideoTaskInfoDao().queryBuilder()
                .where(VideoTaskInfoDao.Properties.TaskState.notEq(VideoTaskState.STATE_COMPLETE))
                .list();
    }

    public void updateVideoTaskInfo(VideoTaskInfo videoTaskInfo) {
        mDaoSession.update(videoTaskInfo);
    }

    public List<VideoTaskInfo> queryAll() {
        return mDaoSession.getVideoTaskInfoDao().queryBuilder()
                .orderRaw(VideoTaskInfoDao.Properties.DownId.toString())
                .list();
    }
}
