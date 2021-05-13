package com.hngg.jianshi.data.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

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

    private Long taskId;  //Aria任务ID

    private int downId;  //int类型id 不重复  用于通知

    @Index(unique = true)
    private Long videoId; //视频自带的id

    private String videoName;

    private String poster;

    private String filePath;

    private Long fileSize;
    private int duration;
    private String category;
    private String description;
    private Long authorId;
    private String authorName;
    private String authorIcon;
    private String authorDesc;

    @Transient
    private Long speed;

    @Transient
    private boolean isRunning;

    private int percent;

    private Long downloadSize;

    private String url;

    /**
     * @Link[VideoTaskState]
     * */
    private int taskState;

    private Long createTime;

    @Generated(hash = 74490904)
    public VideoTaskInfo(Long id, Long taskId, int downId, Long videoId,
            String videoName, String poster, String filePath, Long fileSize,
            int duration, String category, String description, Long authorId,
            String authorName, String authorIcon, String authorDesc, int percent,
            Long downloadSize, String url, int taskState, Long createTime) {
        this.id = id;
        this.taskId = taskId;
        this.downId = downId;
        this.videoId = videoId;
        this.videoName = videoName;
        this.poster = poster;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.duration = duration;
        this.category = category;
        this.description = description;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorIcon = authorIcon;
        this.authorDesc = authorDesc;
        this.percent = percent;
        this.downloadSize = downloadSize;
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

    public Long getVideoId() {
        return this.videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
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

    public Long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorIcon() {
        return this.authorIcon;
    }

    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    public String getAuthorDesc() {
        return this.authorDesc;
    }

    public void setAuthorDesc(String authorDesc) {
        this.authorDesc = authorDesc;
    }

    public int getPercent() {
        return this.percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Long getDownloadSize() {
        return this.downloadSize;
    }

    public void setDownloadSize(Long downloadSize) {
        this.downloadSize = downloadSize;
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


    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}