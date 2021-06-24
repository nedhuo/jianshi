package com.hngg.jianshi.data.bean.community;

import java.io.Serializable;

/**
 * Date: 2021/2/21
 * Timer: 9:37
 * Author: nedhuo
 * Description:
 */

public class Owner implements Serializable {

    private long uid;
    private String nickname;
    private String avatar;
    private String userType;
    private boolean ifPgc;
    private String description;
    private String area;
    private String gender;
    private long registDate;
    private long releaseDate;
    private String cover;
    private String actionUrl;
    private boolean followed;
    private boolean limitVideoOpen;
    private String library;
    private long birthday;
    private String country;
    private String city;
    private String university;
    private String job;
    private boolean expert;

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setIfPgc(boolean ifPgc) {
        this.ifPgc = ifPgc;
    }

    public boolean getIfPgc() {
        return ifPgc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setRegistDate(long registDate) {
        this.registDate = registDate;
    }

    public long getRegistDate() {
        return registDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public boolean getFollowed() {
        return followed;
    }

    public void setLimitVideoOpen(boolean limitVideoOpen) {
        this.limitVideoOpen = limitVideoOpen;
    }

    public boolean getLimitVideoOpen() {
        return limitVideoOpen;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getLibrary() {
        return library;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setExpert(boolean expert) {
        this.expert = expert;
    }

    public boolean getExpert() {
        return expert;
    }

}