package com.hngg.jianshi.data.database.utils;

import com.hngg.jianshi.data.database.dao.DaoSession;
import com.hngg.jianshi.data.database.dao.VideoTaskInfoDao;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;
import com.hngg.jianshi.data.VideoTaskState;
import com.hngg.jianshi.utils.LogUtil;

import org.greenrobot.greendao.Property;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class VideoTaskInfoUtil {

    private static final String TAG = "VideoTaskInfoUtil";
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
                .list();
    }

    public List<VideoTaskInfo> queryAll(Property orderBy) {
        return mDaoSession.getVideoTaskInfoDao().queryBuilder()
                .orderAsc(orderBy)
                .list();
    }

    public boolean queryIsDownloaded(String url) {
        List<VideoTaskInfo> list = mDaoSession.getVideoTaskInfoDao().queryBuilder().list();
        for (VideoTaskInfo taskInfo : list) {
            if (taskInfo.getUrl().equals(url))
                return true;
        }
        return false;
    }

    public boolean queryIsExist(String url) {
        return false;
    }

    public void add(VideoTaskInfo taskInfo) {
        long isSuccess = mDaoSession.insert(taskInfo);
        if (isSuccess == -1) {
            LogUtil.e(TAG, "VideoTaskInfo添加数据失败");
        }
    }

    public void delete(VideoTaskInfo taskInfo) {
        mDaoSession.delete(taskInfo);
    }

    public List<VideoTaskInfo> queryAllComplete() {
        return mDaoSession.getVideoTaskInfoDao().queryBuilder()
                .where(VideoTaskInfoDao.Properties.TaskState.eq(VideoTaskState.STATE_COMPLETE))
                .list();
    }

    public VideoTaskInfo queryByVideoId(long videoId) {
        List<VideoTaskInfo> list = mDaoSession.getVideoTaskInfoDao().queryBuilder()
                .where(VideoTaskInfoDao.Properties.VideoId.eq(videoId)).list();
        if (list != null && list.size() >= 1) {
            return list.get(0);
        }
        return null;
    }
}
