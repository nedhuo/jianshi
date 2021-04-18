package com.hngg.jianshi.utils;

import android.app.Activity;

import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.database.dao.VideoTaskInfoDao;
import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;
import com.hngg.jianshi.data.datebase.VideoTaskState;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadUtil {
    private static final String TAG = "DownloadUtil";


    public static VideoTaskInfo createVideoTaskInfo(Activity context, Data videoData) {
        VideoTaskInfo taskInfo = new VideoTaskInfo();
        int uniqueId = CommonUtil.generatorUniqueID(context);
        if (uniqueId == 0) {
            /*校验*/
            uniqueId = checkUniqueId(context);
        }
        taskInfo.setCreateTime(System.currentTimeMillis());
        taskInfo.setDownId(uniqueId);
        taskInfo.setPoster(videoData.getAuthor().getIcon());
        taskInfo.setFilePath(FileUtil.getDownloadPath());
        taskInfo.setTaskState(VideoTaskState.STATE_OTHER);
        taskInfo.setUrl(videoData.getPlayUrl());
        taskInfo.setVideoId(videoData.getId());
        taskInfo.setVideoName(videoData.getTitle());

        LogUtil.i(TAG, "当前生成ID$uniqueId");
        return taskInfo;
    }

    /**
     * 当生成int 类型ID为0时，查询数据库进行判断
     * 判断并计算返回真正的downId
     */
    private static int checkUniqueId(Activity context) {
        List<VideoTaskInfo> taskInfoList = DbManager.getInstance(context)
                .getVideoTaskDao().queryAll(VideoTaskInfoDao.Properties.DownId);
        if (taskInfoList.size() == 0)
            return 0;
        else {
            LogUtil.i(TAG, "SP文件已破坏，重新计算当前downId");
            /*获取真实唯一ID*/
            int downId = taskInfoList.get(taskInfoList.size() - 1).getDownId();
            LogUtil.i(TAG, "重新查找回DownId为" + downId + 1);
            SpUtil.putInt(context, Constant.UNIQUE_ID, downId + 2);
            return downId + 1;
        }
    }
}
