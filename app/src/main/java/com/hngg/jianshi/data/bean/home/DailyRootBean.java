package com.hngg.jianshi.data.bean.home;

import java.util.List;
/**
 * Date: 2020/11/22
 * Timer: 13:39
 * Author: nedhuo
 * Description:   TODO 自动生成注解？
 */

/**
 * Copyright 2020 json.cn
 */



/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class DailyRootBean {

    private List<ItemList> itemList;
    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
    private long date;
    private long nextPublishTime;
    private String dialog;
    private String topIssue;
    private int refreshCount;
    private int lastStartId;
    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }
    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }
    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }
    public boolean getAdExist() {
        return adExist;
    }

    public void setDate(long date) {
        this.date = date;
    }
    public long getDate() {
        return date;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }
    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }
    public String getDialog() {
        return dialog;
    }

    public void setTopIssue(String topIssue) {
        this.topIssue = topIssue;
    }
    public String getTopIssue() {
        return topIssue;
    }

    public void setRefreshCount(int refreshCount) {
        this.refreshCount = refreshCount;
    }
    public int getRefreshCount() {
        return refreshCount;
    }

    public void setLastStartId(int lastStartId) {
        this.lastStartId = lastStartId;
    }
    public int getLastStartId() {
        return lastStartId;
    }

}

/**
 * Copyright 2020 json.cn
 */


/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
 class Tags {

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


/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
 class Consumption {

    private int collectionCount;
    private int shareCount;
    private int replyCount;
    private int realCollectionCount;
    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }
    public int getCollectionCount() {
        return collectionCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }
    public int getShareCount() {
        return shareCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
    public int getReplyCount() {
        return replyCount;
    }

    public void setRealCollectionCount(int realCollectionCount) {
        this.realCollectionCount = realCollectionCount;
    }
    public int getRealCollectionCount() {
        return realCollectionCount;
    }

}


 class Follow {

    private String itemType;
    private int itemId;
    private boolean followed;
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public String getItemType() {
        return itemType;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getItemId() {
        return itemId;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
    public boolean getFollowed() {
        return followed;
    }

}



/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
 class Shield {

    private String itemType;
    private int itemId;
    private boolean shielded;
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public String getItemType() {
        return itemType;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getItemId() {
        return itemId;
    }

    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }
    public boolean getShielded() {
        return shielded;
    }

}





/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
 class WebUrl {

    private String raw;
    private String forWeibo;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public void setForWeibo(String forWeibo) {
        this.forWeibo = forWeibo;
    }
    public String getForWeibo() {
        return forWeibo;
    }

}




/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
 class UrlList {

    private String name;
    private String url;
    private long size;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setSize(long size) {
        this.size = size;
    }
    public long getSize() {
        return size;
    }

}




/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
 class PlayInfo {

    private int height;
    private int width;
    private List<UrlList> urlList;
    private String name;
    private String type;
    private String url;
    public void setHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public int getWidth() {
        return width;
    }

    public void setUrlList(List<UrlList> urlList) {
        this.urlList = urlList;
    }
    public List<UrlList> getUrlList() {
        return urlList;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

}





 class Label {

    private String text;
    private String card;
    private String detail;
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setCard(String card) {
        this.card = card;
    }
    public String getCard() {
        return card;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getDetail() {
        return detail;
    }

}


 class LabelList {

    private String text;
    private String actionUrl;
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    public String getActionUrl() {
        return actionUrl;
    }

}





