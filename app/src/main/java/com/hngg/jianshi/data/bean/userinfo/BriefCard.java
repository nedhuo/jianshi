package com.hngg.jianshi.data.bean.userinfo;

import com.hngg.jianshi.data.bean.home.Follow;

//    {
//        "dataType":"BriefCard",
//            "id":2169,
//            "icon":"http://img.kaiyanapp.com/ac6971c1b9fc942c7547d25fc6c60ad2.jpeg",
//            "iconType":"round",
//            "title":"开眼萌宠精选",
//            "subTitle":null,
//            "description":"来自汪星、喵星、蠢萌星的你",
//            "actionUrl":"eyepetizer://pgc/detail/2169/?title=%E5%BC%80%E7%9C%BC%E8%90%8C%E5%AE%A0%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1",
//            "adTrack":null,
//            "follow":Object{...},
//        "ifPgc":true,
//            "uid":0,
//            "ifShowNotificationIcon":false,
//            "expert":false
//    }

public class BriefCard {

    private String dataType;
    private int id;
    private String icon;
    private String iconType;
    private String title;
    private String subTitle;
    private String description;
    private String actionUrl;
    private String adTrack;
    private Follow follow;
    private boolean ifPgc;
    private int uid;
    private boolean ifShowNotificationIcon;
    private boolean expert;

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

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

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getIconType() {
        return iconType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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

    public void setFollow(Follow follow) {
        this.follow = follow;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setIfPgc(boolean ifPgc) {
        this.ifPgc = ifPgc;
    }

    public boolean getIfPgc() {
        return ifPgc;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setIfShowNotificationIcon(boolean ifShowNotificationIcon) {
        this.ifShowNotificationIcon = ifShowNotificationIcon;
    }

    public boolean getIfShowNotificationIcon() {
        return ifShowNotificationIcon;
    }

    public void setExpert(boolean expert) {
        this.expert = expert;
    }

    public boolean getExpert() {
        return expert;
    }
}
