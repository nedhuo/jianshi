package com.hngg.jianshi.data.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data: 视频多分辨率
 */
@Entity
public class VideoResolutionInfo {
    @Id(autoincrement = true)
    private Long id;
    private Long videoInfoId;

    private String name;
    private String type;
    @NotNull
    private String url;
    private Long size;
    @Generated(hash = 169958462)
    public VideoResolutionInfo(Long id, Long videoInfoId, String name, String type,
            @NotNull String url, Long size) {
        this.id = id;
        this.videoInfoId = videoInfoId;
        this.name = name;
        this.type = type;
        this.url = url;
        this.size = size;
    }
    @Generated(hash = 1072949985)
    public VideoResolutionInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getVideoInfoId() {
        return this.videoInfoId;
    }
    public void setVideoInfoId(Long videoInfoId) {
        this.videoInfoId = videoInfoId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Long getSize() {
        return this.size;
    }
    public void setSize(Long size) {
        this.size = size;
    }

    /*TODO 待实现 多视频来源  阿里云与ucloud*/
}
