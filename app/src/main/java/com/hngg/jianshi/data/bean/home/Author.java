package com.hngg.jianshi.data.bean.home;

/**
 * Date: 2020/11/24
 * Timer: 12:09
 * Author: nedhuo
 * Description:
 */
public class Author {

    private int id;
    private String icon;
    private String name;
    private String description;
    private String link;
    private long latestReleaseTime;
    private int videoNum;
    private String adTrack;
    private Follow follow;
    private Shield shield;
    private int approvedNotReadyVideoCount;
    private boolean ifPgc;
    private int recSort;
    private boolean expert;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getLink() {
        return link;
    }

    public void setLatestReleaseTime(long latestReleaseTime) {
        this.latestReleaseTime = latestReleaseTime;
    }
    public long getLatestReleaseTime() {
        return latestReleaseTime;
    }

    public void setVideoNum(int videoNum) {
        this.videoNum = videoNum;
    }
    public int getVideoNum() {
        return videoNum;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }
    public String getAdTrack() {
        return adTrack;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
    public Follow getFollow() {
        return follow;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }
    public Shield getShield() {
        return shield;
    }

    public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
        this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
    }
    public int getApprovedNotReadyVideoCount() {
        return approvedNotReadyVideoCount;
    }

    public void setIfPgc(boolean ifPgc) {
        this.ifPgc = ifPgc;
    }
    public boolean getIfPgc() {
        return ifPgc;
    }

    public void setRecSort(int recSort) {
        this.recSort = recSort;
    }
    public int getRecSort() {
        return recSort;
    }

    public void setExpert(boolean expert) {
        this.expert = expert;
    }
    public boolean getExpert() {
        return expert;
    }

}