package com.hngg.jianshi.utils;

import android.app.Activity;
import android.content.Context;

import com.blankj.utilcode.util.FileUtils;
import com.hngg.jianshi.data.VideoTaskState;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;
import com.hngg.jianshi.data.database.dao.VideoTaskInfoDao;

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
        taskInfo.setPoster(videoData.getCover().getFeed());
        taskInfo.setFilePath(FileUtil.getDownloadPath(context));
        taskInfo.setTaskState(VideoTaskState.STATE_OTHER);
        taskInfo.setUrl(videoData.getPlayUrl());
        taskInfo.setVideoId(videoData.getId());
        taskInfo.setVideoName(videoData.getTitle());
        taskInfo.setAuthorDesc(videoData.getAuthor().getDescription());
        taskInfo.setAuthorIcon(videoData.getAuthor().getIcon());
        taskInfo.setAuthorId((long) videoData.getAuthor().getId());
        taskInfo.setAuthorName(videoData.getAuthor().getName());
        taskInfo.setDuration(videoData.getDuration());
        taskInfo.setDescription(videoData.getDescription());

        LogUtil.i(TAG, "当前生成ID$uniqueId");
        return taskInfo;
    }

    /**
     * 避免因为文件误删导致Id出错
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

    public static boolean isDownloaded(Context ctx, String playUrl, String title) {
        boolean isDownloaded = DbManager.getInstance(ctx).getVideoTaskDao()
                .queryIsDownloaded(playUrl);
        String path = FileUtil.getDownloadPath(ctx);
        boolean fileExists = FileUtils.isFileExists(path + "/" + title+".mp4");
        //TODO 如果文件存在，数据库不存在，则将数据保存导数据库一份，否则，删除数据库文件 对字符串进行统一构建
        return fileExists;
    }
}
