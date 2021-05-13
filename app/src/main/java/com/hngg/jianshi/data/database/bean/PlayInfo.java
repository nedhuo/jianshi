package com.hngg.jianshi.data.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

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
    private String videoName;
    private String category;
    private String description;
    private int duration;   //时长
    private String poster;
    private String playUrl;
    private Long videoSize;
    private String filePath;

    private Long authorId;
    private String authorName;
    private String authorIcon;
    private String authorDesc;

    private Long seekTime; //当前进度

    @Generated(hash = 277248303)
    public PlayInfo(Long id, Long videoId, String videoName, String category,
            String description, int duration, String poster, String playUrl,
            Long videoSize, String filePath, Long authorId, String authorName,
            String authorIcon, String authorDesc, Long seekTime) {
        this.id = id;
        this.videoId = videoId;
        this.videoName = videoName;
        this.category = category;
        this.description = description;
        this.duration = duration;
        this.poster = poster;
        this.playUrl = playUrl;
        this.videoSize = videoSize;
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

    public String getVideoName() {
        return this.videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
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

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlayUrl() {
        return this.playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Long getVideoSize() {
        return this.videoSize;
    }

    public void setVideoSize(Long videoSize) {
        this.videoSize = videoSize;
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
