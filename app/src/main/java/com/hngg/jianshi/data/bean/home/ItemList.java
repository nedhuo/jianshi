package com.hngg.jianshi.data.bean.home;
/**
 * Date: 2020/11/24
 * Timer: 12:08
 * Author: nedhuo
 * Description:
 */

public class ItemList {

    private String type;
    private Data data;
    private String trackingData;
    private String tag;
    private int id;
    private int adIndex;
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public void setTrackingData(String trackingData) {
        this.trackingData = trackingData;
    }
    public String getTrackingData() {
        return trackingData;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setAdIndex(int adIndex) {
        this.adIndex = adIndex;
    }
    public int getAdIndex() {
        return adIndex;
    }

}