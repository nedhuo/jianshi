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
    private Long id;

    private Long videoId;
    private String url;
    private String poster;
    private String videoName;
    private Long createTime;
    private Long videoTime;
    private String videoDesc;

    private int authorId;
    private String authorName;
    private String authorIcon;
    private String authorDesc;
    @Generated(hash = 1649909915)
    public HistoryInfo(Long id, Long videoId, String url, String poster,
            String videoName, Long createTime, Long videoTime, String videoDesc,
            int authorId, String authorName, String authorIcon, String authorDesc) {
        this.id = id;
        this.videoId = videoId;
        this.url = url;
        this.poster = poster;
        this.videoName = videoName;
        this.createTime = createTime;
        this.videoTime = videoTime;
        this.videoDesc = videoDesc;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorIcon = authorIcon;
        this.authorDesc = authorDesc;
    }
    @Generated(hash = 1690888989)
    public HistoryInfo() {
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
    public Long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    public Long getVideoTime() {
        return this.videoTime;
    }
    public void setVideoTime(Long videoTime) {
        this.videoTime = videoTime;
    }
    public String getVideoDesc() {
        return this.videoDesc;
    }
    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }
    public int getAuthorId() {
        return this.authorId;
    }
    public void setAuthorId(int authorId) {
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
