package com.hngg.jianshi.data.datebase;

import com.arialyy.aria.orm.annotation.Ignore;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data: 与DownloadEntity 保持一致
 * Entity
 */
public class VideoTaskState {
    /**
     * 其它状态
     */
    @Ignore
    public static int STATE_OTHER = -1;
    /**
     * 失败状态
     */
    @Ignore
    public static int STATE_FAIL = 0;
    /**
     * 完成状态
     */
    @Ignore
    public static int STATE_COMPLETE = 1;
    /**
     * 停止状态
     */
    @Ignore
    public static int STATE_STOP = 2;
    /**
     * 等待状态
     */
    @Ignore
    public static int STATE_WAIT = 3;
    /**
     * 正在执行
     */
    @Ignore
    public static int STATE_RUNNING = 4;
    /**
     * 预处理
     */
    @Ignore
    public static int STATE_PRE = 5;
    /**
     * 预处理完成
     */
    @Ignore
    public static int STATE_POST_PRE = 6;
    /**
     * 删除任务
     */
    @Ignore
    public static int STATE_CANCEL = 7;
}
