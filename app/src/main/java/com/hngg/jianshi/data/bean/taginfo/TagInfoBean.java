package com.hngg.jianshi.data.bean.taginfo;

import com.hngg.jianshi.data.bean.home.Follow;

import java.io.Serializable;
import java.util.List;

public class TagInfoBean implements Serializable {
    /**
     * Copyright 2021 json.cn
     */
    private TabInfo tabInfo;
    private TagInfo tagInfo;

    public void setTabInfo(TabInfo tabInfo) {
        this.tabInfo = tabInfo;
    }

    public class TagInfo {

        private String dataType;
        private int id;
        private String name;
        private String description;
        private String headerImage;
        private String bgPicture;
        private String actionUrl;
        private int recType;
        private Follow follow;
        private long tagFollowCount;
        private int tagVideoCount;
        private int tagDynamicCount;
        private long lookCount;
        private String shareLinkUrl;
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

        public void setHeaderImage(String headerImage) {
            this.headerImage = headerImage;
        }
        public String getHeaderImage() {
            return headerImage;
        }

        public void setBgPicture(String bgPicture) {
            this.bgPicture = bgPicture;
        }
        public String getBgPicture() {
            return bgPicture;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }
        public String getActionUrl() {
            return actionUrl;
        }

        public void setRecType(int recType) {
            this.recType = recType;
        }
        public int getRecType() {
            return recType;
        }

        public void setFollow(Follow follow) {
            this.follow = follow;
        }
        public Follow getFollow() {
            return follow;
        }

        public void setTagFollowCount(long tagFollowCount) {
            this.tagFollowCount = tagFollowCount;
        }
        public long getTagFollowCount() {
            return tagFollowCount;
        }

        public void setTagVideoCount(int tagVideoCount) {
            this.tagVideoCount = tagVideoCount;
        }
        public int getTagVideoCount() {
            return tagVideoCount;
        }

        public void setTagDynamicCount(int tagDynamicCount) {
            this.tagDynamicCount = tagDynamicCount;
        }
        public int getTagDynamicCount() {
            return tagDynamicCount;
        }

        public void setLookCount(long lookCount) {
            this.lookCount = lookCount;
        }
        public long getLookCount() {
            return lookCount;
        }

        public void setShareLinkUrl(String shareLinkUrl) {
            this.shareLinkUrl = shareLinkUrl;
        }
        public String getShareLinkUrl() {
            return shareLinkUrl;
        }

        @Override
        public String toString() {
            return "TagInfo{" +
                    "dataType='" + dataType + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", headerImage='" + headerImage + '\'' +
                    ", bgPicture='" + bgPicture + '\'' +
                    ", actionUrl='" + actionUrl + '\'' +
                    ", recType=" + recType +
                    ", follow=" + follow +
                    ", tagFollowCount=" + tagFollowCount +
                    ", tagVideoCount=" + tagVideoCount +
                    ", tagDynamicCount=" + tagDynamicCount +
                    ", lookCount=" + lookCount +
                    ", shareLinkUrl='" + shareLinkUrl + '\'' +
                    '}';
        }
    }

    public TabInfo getTabInfo() {
        return tabInfo;
    }

    public void setTagInfo(TagInfo tagInfo) {
        this.tagInfo = tagInfo;
    }

    public TagInfo getTagInfo() {
        return tagInfo;
    }


    public static class TabInfo {

        private List<TabList> tabList;
        private int defaultIdx;

        public void setTabList(List<TabList> tabList) {
            this.tabList = tabList;
        }

        public List<TabList> getTabList() {
            return tabList;
        }

        public void setDefaultIdx(int defaultIdx) {
            this.defaultIdx = defaultIdx;
        }

        public int getDefaultIdx() {
            return defaultIdx;
        }

        @Override
        public String toString() {
            return "TabInfo{" +
                    "tabList=" + tabList +
                    ", defaultIdx=" + defaultIdx +
                    '}';
        }
    }

    public static class TabList {

        private int id;
        private String name;
        private String apiUrl;
        private int tabType;
        private int nameType;
        private String adTrack;

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

        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public void setTabType(int tabType) {
            this.tabType = tabType;
        }

        public int getTabType() {
            return tabType;
        }

        public void setNameType(int nameType) {
            this.nameType = nameType;
        }

        public int getNameType() {
            return nameType;
        }

        public void setAdTrack(String adTrack) {
            this.adTrack = adTrack;
        }

        public String getAdTrack() {
            return adTrack;
        }

        @Override
        public String toString() {
            return "TabList{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", apiUrl='" + apiUrl + '\'' +
                    ", tabType=" + tabType +
                    ", nameType=" + nameType +
                    ", adTrack='" + adTrack + '\'' +
                    '}';
        }
    }
}
