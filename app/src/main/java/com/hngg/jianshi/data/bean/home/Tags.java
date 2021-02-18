package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;

/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
 class Tags implements Serializable {

    private int id;
    private String name;
    private String actionUrl;
    private String adTrack;
    private String desc;
    private String bgPicture;
    private String headerImage;
    private String tagRecType;
    private String childTagList;
    private String childTagIdList;
    private boolean haveReward;
    private boolean ifNewest;
    private String newestEndTime;
    private int communityIndex;


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    public String getActionUrl() {
        return actionUrl;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }
    public String getAdTrack() {
        return adTrack;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }
    public String getBgPicture() {
        return bgPicture;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }
    public String getHeaderImage() {
        return headerImage;
    }

    public void setTagRecType(String tagRecType) {
        this.tagRecType = tagRecType;
    }
    public String getTagRecType() {
        return tagRecType;
    }

    public void setChildTagList(String childTagList) {
        this.childTagList = childTagList;
    }
    public String getChildTagList() {
        return childTagList;
    }

    public void setChildTagIdList(String childTagIdList) {
        this.childTagIdList = childTagIdList;
    }
    public String getChildTagIdList() {
        return childTagIdList;
    }

    public void setHaveReward(boolean haveReward) {
        this.haveReward = haveReward;
    }
    public boolean getHaveReward() {
        return haveReward;
    }

    public void setIfNewest(boolean ifNewest) {
        this.ifNewest = ifNewest;
    }
    public boolean getIfNewest() {
        return ifNewest;
    }

    public void setNewestEndTime(String newestEndTime) {
        this.newestEndTime = newestEndTime;
    }
    public String getNewestEndTime() {
        return newestEndTime;
    }

    public void setCommunityIndex(int communityIndex) {
        this.communityIndex = communityIndex;
    }
    public int getCommunityIndex() {
        return communityIndex;
    }

}
