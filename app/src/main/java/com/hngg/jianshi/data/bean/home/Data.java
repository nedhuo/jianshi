package com.hngg.jianshi.data.bean.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.hngg.jianshi.data.bean.community.Owner;
import com.hngg.jianshi.data.bean.recommend.Content;

import java.io.Serializable;
import java.util.List;
import java.util.SplittableRandom;

/**
 * Date: 2020/11/24
 * Timer: 12:12
 * Author: nedhuo
 * Description:
 */
public class Data implements Serializable {
    private String dataType;
    private long id;
    private long uid;
    private long createTime;
    private boolean shade;
    private long updateTime;
    private String image;
    private String title;
    private String description;
    private String subTitle;
    private String bgPicture;
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

    private boolean autoPlay;
    private Content content;

    private long selectedTime;
    private String checkStatus;
    private String area;
    private String city;
    private double longitude;
    private double latitude;
    private boolean ifMock;
    private String validateStatus;
    private String validateResult;
    private int width;
    private int height;
    private boolean addWatermark;
    private String privateMessageActionUrl;
    private String url;
    private List<String> urls;
    private String status;
    private Owner owner;
    private List<String> urlsWithWatermark;
    private RecentOnceReply recentOnceReply;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(long selectedTime) {
        this.selectedTime = selectedTime;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isIfMock() {
        return ifMock;
    }

    public void setIfMock(boolean ifMock) {
        this.ifMock = ifMock;
    }

    public String getValidateStatus() {
        return validateStatus;
    }

    public void setValidateStatus(String validateStatus) {
        this.validateStatus = validateStatus;
    }

    public String getValidateResult() {
        return validateResult;
    }

    public void setValidateResult(String validateResult) {
        this.validateResult = validateResult;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAddWatermark() {
        return addWatermark;
    }

    public void setAddWatermark(boolean addWatermark) {
        this.addWatermark = addWatermark;
    }

    public String getPrivateMessageActionUrl() {
        return privateMessageActionUrl;
    }

    public void setPrivateMessageActionUrl(String privateMessageActionUrl) {
        this.privateMessageActionUrl = privateMessageActionUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getUrlsWithWatermark() {
        return urlsWithWatermark;
    }

    public void setUrlsWithWatermark(List<String> urlsWithWatermark) {
        this.urlsWithWatermark = urlsWithWatermark;
    }

    public RecentOnceReply getRecentOnceReply() {
        return recentOnceReply;
    }

    public void setRecentOnceReply(RecentOnceReply recentOnceReply) {
        this.recentOnceReply = recentOnceReply;
    }

    public boolean isShade() {
        return shade;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

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