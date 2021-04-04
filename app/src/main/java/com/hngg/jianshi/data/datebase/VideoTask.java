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
public class VideoTask {

    @Id(autoincrement = true)
    private long id;

    private int downId;

    private String videoName;

    private String poster;

    private String filePath;

    @Index(unique = true)
    private long videoId;

    private String url;

    private int taskState;

    private long createTime;

    @Generated(hash = 899477973)
    public VideoTask(long id, int downId, String videoName, String poster,
            String filePath, long videoId, String url, int taskState,
            long createTime) {
        this.id = id;
        this.downId = downId;
        this.videoName = videoName;
        this.poster = poster;
        this.filePath = filePath;
        this.videoId = videoId;
        this.url = url;
        this.taskState = taskState;
        this.createTime = createTime;
    }

    @Generated(hash = 1455847522)
    public VideoTask() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getVideoId() {
        return this.videoId;
    }

    public void setVideoId(long videoId) {
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

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

}