package com.hngg.jianshi.data.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

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

    @Generated(hash = 1292466478)
    public PlayInfo(Long id, Long videoId, String title, String category,
            String description, int duration, String playUrl, Long fileSize,
            String filePath, Long authorId, String authorName, String authorIcon,
            String authorDesc, Long seekTime) {
        this.id = id;
        this.videoId = videoId;
        this.title = title;
        this.category = category;
        this.description = description;
        this.duration = duration;
        this.playUrl = playUrl;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorIcon = authorIcon;
        this.authorDesc = authorDesc;
        this.seekTime = seekTime;
    }

    @Generated(hash = 620864500)
    public PlayInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVideoId() {
        return this.videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPlayUrl() {
        return this.playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public Long getSeekTime() {
        return this.seekTime;
    }

    public void setSeekTime(Long seekTime) {
        this.seekTime = seekTime;
    }
}
