package com.hngg.jianshi.data.database.bean;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data: 观看历史
 */
public class HistoryInfo {

    @Id(autoincrement = true)
    private long id;

    @Index(unique = true)
    private long videoId;

    private String url;

    private String poster;

    private String videoName;

    private String filePath;

    private long browsingTime;

    private long videoTime;
}
