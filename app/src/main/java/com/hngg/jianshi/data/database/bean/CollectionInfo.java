package com.hngg.jianshi.data.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data: 收藏记录
 */
@Entity
public class CollectionInfo {
    @Id(autoincrement = true)
    private Long id;

    private Long videoId;
    private String videoName;
    private String poster;
    private String playUrl;
    private String videoDesc;
    private String category;
    private int duration;   //时长
    private Long videoSize;

    private Long authorId;
    private String authorName;
    private String authorIcon;
    private String authorDesc;
    @Generated(hash = 1324195832)
    public CollectionInfo(Long id, Long videoId, String videoName, String poster,
            String playUrl, String videoDesc, String category, int duration,
            Long videoSize, Long authorId, String authorName, String authorIcon,
            String authorDesc) {
        this.id = id;
        this.videoId = videoId;
        this.videoName = videoName;
        this.poster = poster;
        this.playUrl = playUrl;
        this.videoDesc = videoDesc;
        this.category = category;
        this.duration = duration;
        this.videoSize = videoSize;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorIcon = authorIcon;
        this.authorDesc = authorDesc;
    }
    @Generated(hash = 358181051)
    public CollectionInfo() {
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
    public String getVideoDesc() {
        return this.videoDesc;
    }
    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public Long getVideoSize() {
        return this.videoSize;
    }
    public void setVideoSize(Long videoSize) {
        this.videoSize = videoSize;
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
