package com.hngg.jianshi.data.bean.home;

/**
 * Date: 2020/11/24
 * Timer: 18:04
 * Author: nedhuo
 * Description:
 */

import java.io.Serializable;

/**
 * Auto-generated: 2020-11-22 14:0:2
 */
public class Header implements Serializable {

    private int id;
    private String title;
    private String textAlign;
    private String cover;
    private Label label;
    private String actionUrl;
    private String icon;
    private String iconType;
    private String description;
    private long time;
    private String followType;
    private boolean showHateVideo;
    private int tagId;
    private String tagName;
    private String issuerName;
    private boolean topShow;

    public String getFollowType() {
        return followType;
    }

    public void setFollowType(String followType) {
        this.followType = followType;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public boolean isTopShow() {
        return topShow;
    }

    public void setTopShow(boolean topShow) {
        this.topShow = topShow;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isShowHateVideo() {
        return showHateVideo;
    }

    public void setShowHateVideo(boolean showHateVideo) {
        this.showHateVideo = showHateVideo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    @Override
    public String toString() {
        return "Header{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", textAlign='" + textAlign + '\'' +
                ", cover='" + cover + '\'' +
                ", label=" + label +
                ", actionUrl='" + actionUrl + '\'' +
                ", icon='" + icon + '\'' +
                ", iconType='" + iconType + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", followType='" + followType + '\'' +
                ", showHateVideo=" + showHateVideo +
                ", tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", topShow=" + topShow +
                '}';
    }
}
