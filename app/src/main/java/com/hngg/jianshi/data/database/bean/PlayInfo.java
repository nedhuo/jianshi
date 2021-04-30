package com.hngg.jianshi.data.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
@Entity
public class PlayInfo {

    @Id(autoincrement = true)
    private Long id;

    private Long videoId;
    private String title;
    private String category;
    private String description;
    private int duration;   //时长

    private String playUrl;
    private Long fileSize;
    private String filePath;

    private Long authorId;
    private String authorName;
    private String authorIcon;
    private String authorDesc;

    private Long seekTime; //当前进度
}
