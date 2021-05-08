package com.hngg.jianshi.data.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data: 观看历史
 */
@Entity
public class HistoryInfo {

    @Id(autoincrement = true)
    private long id;

    private long videoId;

    private String url;

    private String poster;

    private String videoName;
    
    private long createTime;

    private long videoTime;

    private Long authorId;
    private String authorName;
    private String authorIcon;
    private String authorDesc;
    @Generated(hash = 193584672)
    public HistoryInfo(long id, long videoId, String url, String poster,
            String videoName, long createTime, long videoTime, Long authorId,
            String authorName, String authorIcon, String authorDesc) {
        this.id = id;
        this.videoId = videoId;
        this.url = url;
        this.poster = poster;
        this.videoName = videoName;
        this.createTime = createTime;
        this.videoTime = videoTime;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorIcon = authorIcon;
        this.authorDesc = authorDesc;
    }
    @Generated(hash = 1690888989)
    public HistoryInfo() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
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
    public String getPoster() {
        return this.poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getVideoName() {
        return this.videoName;
    }
    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
    public long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public long getVideoTime() {
        return this.videoTime;
    }
    public void setVideoTime(long videoTime) {
        this.videoTime = videoTime;
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
}
