package com.hngg.jianshi.data.bean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 * https://baobab.kaiyanapp.com/api/v5/userInfo/tab?id=2171&userType=PGC
 */
public class UserInfoBean {

    /**
     * tabInfo : {"tabList":[{"id":0,"name":"首页","apiUrl":"https://baobab.kaiyanapp.com/api/v5/userInfo/tab/index?id=2171&userType=PGC","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"作品","apiUrl":"https://baobab.kaiyanapp.com/api/v4/pgcs/videoList?id=2171","tabType":0,"nameType":0,"adTrack":null},{"id":3,"name":"动态","apiUrl":"https://baobab.kaiyanapp.com/api/v5/userInfo/tab/dynamics?id=2171&userType=PGC","tabType":0,"nameType":0,"adTrack":null}],"defaultIdx":0}
     * pgcInfo : {"dataType":"PgcInfo","id":2171,"icon":"http://img.kaiyanapp.com/0117b9108c7cff43700db8af5e24f2bf.jpeg","name":"开眼科技精选","brief":"344 个视频  /  694408 次收藏  /  469037 次分享","description":"新知识与一切先进生产力","actionUrl":"eyepetizer://pgc/detail/2171/?title=%E5%BC%80%E7%9C%BC%E7%A7%91%E6%8A%80%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1","area":"","gender":"","registDate":-1,"followCount":541053,"follow":{"itemType":"author","itemId":2171,"followed":false},"self":false,"cover":"http://img.kaiyanapp.com/000d96c25e74024a64a04b596af3b7e4.png","videoCount":344,"shareCount":469037,"collectCount":694408,"myFollowCount":0,"videoCountActionUrl":null,"myFollowCountActionUrl":null,"followCountActionUrl":null,"privateMessageActionUrl":null,"medalsNum":0,"medalsActionUrl":null,"tagNameExport":null,"worksRecCount":0,"worksSelectedCount":0,"shield":{"itemType":"author","itemId":2171,"shielded":false},"expert":false}
     */

    private TabInfoBean tabInfo;
    private PgcInfoBean pgcInfo;

    public TabInfoBean getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(TabInfoBean tabInfo) {
        this.tabInfo = tabInfo;
    }

    public PgcInfoBean getPgcInfo() {
        return pgcInfo;
    }

    public void setPgcInfo(PgcInfoBean pgcInfo) {
        this.pgcInfo = pgcInfo;
    }

    public static class TabInfoBean {
        /**
         * tabList : [{"id":0,"name":"首页","apiUrl":"https://baobab.kaiyanapp.com/api/v5/userInfo/tab/index?id=2171&userType=PGC","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"作品","apiUrl":"https://baobab.kaiyanapp.com/api/v4/pgcs/videoList?id=2171","tabType":0,"nameType":0,"adTrack":null},{"id":3,"name":"动态","apiUrl":"https://baobab.kaiyanapp.com/api/v5/userInfo/tab/dynamics?id=2171&userType=PGC","tabType":0,"nameType":0,"adTrack":null}]
         * defaultIdx : 0
         */

        private int defaultIdx;
        private List<TabListBean> tabList;

        public int getDefaultIdx() {
            return defaultIdx;
        }

        public void setDefaultIdx(int defaultIdx) {
            this.defaultIdx = defaultIdx;
        }

        public List<TabListBean> getTabList() {
            return tabList;
        }

        public void setTabList(List<TabListBean> tabList) {
            this.tabList = tabList;
        }

        public static class TabListBean {
            /**
             * id : 0
             * name : 首页
             * apiUrl : https://baobab.kaiyanapp.com/api/v5/userInfo/tab/index?id=2171&userType=PGC
             * tabType : 0
             * nameType : 0
             * adTrack : null
             */

            private int id;
            private String name;
            private String apiUrl;
            private int tabType;
            private int nameType;
         //   private Object adTrack;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getApiUrl() {
                return apiUrl;
            }

            public void setApiUrl(String apiUrl) {
                this.apiUrl = apiUrl;
            }

            public int getTabType() {
                return tabType;
            }

            public void setTabType(int tabType) {
                this.tabType = tabType;
            }

            public int getNameType() {
                return nameType;
            }

            public void setNameType(int nameType) {
                this.nameType = nameType;
            }

        }
    }

    public static class PgcInfoBean {
        /**
         * dataType : PgcInfo
         * id : 2171
         * icon : http://img.kaiyanapp.com/0117b9108c7cff43700db8af5e24f2bf.jpeg
         * name : 开眼科技精选
         * brief : 344 个视频  /  694408 次收藏  /  469037 次分享
         * description : 新知识与一切先进生产力
         * actionUrl : eyepetizer://pgc/detail/2171/?title=%E5%BC%80%E7%9C%BC%E7%A7%91%E6%8A%80%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1
         * area :
         * gender :
         * registDate : -1
         * followCount : 541053
         * follow : {"itemType":"author","itemId":2171,"followed":false}
         * self : false
         * cover : http://img.kaiyanapp.com/000d96c25e74024a64a04b596af3b7e4.png
         * videoCount : 344
         * shareCount : 469037
         * collectCount : 694408
         * myFollowCount : 0
         * videoCountActionUrl : null
         * myFollowCountActionUrl : null
         * followCountActionUrl : null
         * privateMessageActionUrl : null
         * medalsNum : 0
         * medalsActionUrl : null
         * tagNameExport : null
         * worksRecCount : 0
         * worksSelectedCount : 0
         * shield : {"itemType":"author","itemId":2171,"shielded":false}
         * expert : false
         */

        private String dataType;
        private int id;
        private String icon;
        private String name;
        private String brief;
        private String description;
        private String actionUrl;
        private String area;
        private String gender;
        private int registDate;
        private int followCount;
        private FollowBean follow;
        private boolean self;
        private String cover;
        private int videoCount;
        private int shareCount;
        private int collectCount;
        private int myFollowCount;
//        private Object videoCountActionUrl;
//        private Object myFollowCountActionUrl;
//        private Object followCountActionUrl;
//        private Object privateMessageActionUrl;
        private int medalsNum;
//        private Object medalsActionUrl;
//        private Object tagNameExport;
        private int worksRecCount;
        private int worksSelectedCount;
        private ShieldBean shield;
        private boolean expert;

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getRegistDate() {
            return registDate;
        }

        public void setRegistDate(int registDate) {
            this.registDate = registDate;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public FollowBean getFollow() {
            return follow;
        }

        public void setFollow(FollowBean follow) {
            this.follow = follow;
        }

        public boolean isSelf() {
            return self;
        }

        public void setSelf(boolean self) {
            this.self = self;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public int getMyFollowCount() {
            return myFollowCount;
        }

        public void setMyFollowCount(int myFollowCount) {
            this.myFollowCount = myFollowCount;
        }

        public void setMedalsNum(int medalsNum) {
            this.medalsNum = medalsNum;
        }

        public int getWorksRecCount() {
            return worksRecCount;
        }

        public void setWorksRecCount(int worksRecCount) {
            this.worksRecCount = worksRecCount;
        }

        public int getWorksSelectedCount() {
            return worksSelectedCount;
        }

        public void setWorksSelectedCount(int worksSelectedCount) {
            this.worksSelectedCount = worksSelectedCount;
        }

        public ShieldBean getShield() {
            return shield;
        }

        public void setShield(ShieldBean shield) {
            this.shield = shield;
        }

        public boolean isExpert() {
            return expert;
        }

        public void setExpert(boolean expert) {
            this.expert = expert;
        }

        public static class FollowBean {
            /**
             * itemType : author
             * itemId : 2171
             * followed : false
             */

            private String itemType;
            private int itemId;
            private boolean followed;

            public String getItemType() {
                return itemType;
            }

            public void setItemType(String itemType) {
                this.itemType = itemType;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }
        }

        public static class ShieldBean {
            /**
             * itemType : author
             * itemId : 2171
             * shielded : false
             */

            private String itemType;
            private int itemId;
            private boolean shielded;

            public String getItemType() {
                return itemType;
            }

            public void setItemType(String itemType) {
                this.itemType = itemType;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public boolean isShielded() {
                return shielded;
            }

            public void setShielded(boolean shielded) {
                this.shielded = shielded;
            }
        }
    }
}
