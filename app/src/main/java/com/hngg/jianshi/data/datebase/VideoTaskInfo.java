package com.hngg.jianshi.data.datebase;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data: 视频下载记录
 * id           int       自增唯一
 * videoId
 * videoName    String
 * filePath     String
 * videoImage   String
 * taskState    int
 */

@Entity
public class VideoTaskInfo {

    @Id(autoincrement = true)
    private Long id;

    private Long taskId;

    private int downId;

    private String videoName;

    private String poster;

    private String filePath;

    @Index(unique = true)
    private Long videoId;

    private String url;

    private int taskState;

    private Long createTime;

    @Generated(hash = 2059500070)
    public VideoTaskInfo(Long id, Long taskId, int downId, String videoName,
            String poster, String filePath, Long videoId, String url, int taskState,
            Long createTime) {
        this.id = id;
        this.taskId = taskId;
        this.downId = downId;
        this.videoName = videoName;
        this.poster = poster;
        this.filePath = filePath;
        this.videoId = videoId;
        this.url = url;
        this.taskState = taskState;
        this.createTime = createTime;
    }

    @Generated(hash = 481335027)
    public VideoTaskInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public int getDownId() {
        return this.downId;
    }

    public void setDownId(int downId) {
        this.downId = downId;
    }

    public String getVideoName() {
        return this.videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getVideoId() {
        return this.videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTaskState() {
        return this.taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    
}