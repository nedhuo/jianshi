package com.hngg.jianshi.data.bean.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Date: 2020/11/24
 * Timer: 12:12
 * Author: nedhuo
 * Description:
 */
public class Data implements Serializable {
    private String dataType;
    private long id;
    private String title;
    private String description;
    private String library;
    private List<Tags> tags;
    private Consumption consumption;
    private String resourceType;
    private String slogan;
    private Provider provider;
    private String category;
    private Author author;
    private Cover cover;
    private String playUrl;
    private String thumbPlayUrl;
    private int duration;
    private WebUrl webUrl;
    private long releaseTime;
    private List<PlayInfo> playInfo;
    private String campaign;
    private String waterMarks;
    private boolean ad;
    //  private List<String> adTrack;
    private String type;
    private String titlePgc;
    private String descriptionPgc;
    private String remark;
    private boolean ifLimitVideo;
    private int searchWeight;
    private String brandWebsiteInfo;
    private VideoPosterBean videoPosterBean;
    private int idx;
    private String shareAdTrack;
    private String favoriteAdTrack;
    private String webAdTrack;
    private long date;
    private String promotion;
    private String label;
    private List<String> labelList;
    private String descriptionEditor;
    private boolean collected;
    private boolean reallyCollected;
    private boolean played;
    private List<String> subtitles;
    private String lastViewTime;
    private String playlists;
    private String src;
    private String recallSource;
    private String recall_source;

    private String text;
    private String font;
    private String actionUrl;

    private Header header;
    private List<ItemList> itemList;
    private int count;
    private String footer;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getThumbPlayUrl() {
        return thumbPlayUrl;
    }

    public void setThumbPlayUrl(String thumbPlayUrl) {
        this.thumbPlayUrl = thumbPlayUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public WebUrl getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(WebUrl webUrl) {
        this.webUrl = webUrl;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public List<PlayInfo> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<PlayInfo> playInfo) {
        this.playInfo = playInfo;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getWaterMarks() {
        return waterMarks;
    }

    public void setWaterMarks(String waterMarks) {
        this.waterMarks = waterMarks;
    }

    public boolean isAd() {
        return ad;
    }

    public void setAd(boolean ad) {
        this.ad = ad;
    }

//    public List<String> getAdTrack() {
//        return adTrack;
//    }
//
//    public void setAdTrack(List<String> adTrack) {
//        this.adTrack = adTrack;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitlePgc() {
        return titlePgc;
    }

    public void setTitlePgc(String titlePgc) {
        this.titlePgc = titlePgc;
    }

    public String getDescriptionPgc() {
        return descriptionPgc;
    }

    public void setDescriptionPgc(String descriptionPgc) {
        this.descriptionPgc = descriptionPgc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isIfLimitVideo() {
        return ifLimitVideo;
    }

    public void setIfLimitVideo(boolean ifLimitVideo) {
        this.ifLimitVideo = ifLimitVideo;
    }

    public int getSearchWeight() {
        return searchWeight;
    }

    public void setSearchWeight(int searchWeight) {
        this.searchWeight = searchWeight;
    }

    public String getBrandWebsiteInfo() {
        return brandWebsiteInfo;
    }

    public void setBrandWebsiteInfo(String brandWebsiteInfo) {
        this.brandWebsiteInfo = brandWebsiteInfo;
    }

    public VideoPosterBean getVideoPosterBean() {
        return videoPosterBean;
    }

    public void setVideoPosterBean(VideoPosterBean videoPosterBean) {
        this.videoPosterBean = videoPosterBean;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getShareAdTrack() {
        return shareAdTrack;
    }

    public void setShareAdTrack(String shareAdTrack) {
        this.shareAdTrack = shareAdTrack;
    }

    public String getFavoriteAdTrack() {
        return favoriteAdTrack;
    }

    public void setFavoriteAdTrack(String favoriteAdTrack) {
        this.favoriteAdTrack = favoriteAdTrack;
    }

    public String getWebAdTrack() {
        return webAdTrack;
    }

    public void setWebAdTrack(String webAdTrack) {
        this.webAdTrack = webAdTrack;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public String getDescriptionEditor() {
        return descriptionEditor;
    }

    public void setDescriptionEditor(String descriptionEditor) {
        this.descriptionEditor = descriptionEditor;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public boolean isReallyCollected() {
        return reallyCollected;
    }

    public void setReallyCollected(boolean reallyCollected) {
        this.reallyCollected = reallyCollected;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public List<String> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }

    public String getLastViewTime() {
        return lastViewTime;
    }

    public void setLastViewTime(String lastViewTime) {
        this.lastViewTime = lastViewTime;
    }

    public String getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String playlists) {
        this.playlists = playlists;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getRecallSource() {
        return recallSource;
    }

    public void setRecallSource(String recallSource) {
        this.recallSource = recallSource;
    }

    public String getRecall_source() {
        return recall_source;
    }

    public void setRecall_source(String recall_source) {
        this.recall_source = recall_source;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

}