package com.hngg.jianshi.data.bean.recommend;

import com.hngg.jianshi.data.bean.home.Data;

import java.io.Serializable;

/**
 * Date: 2021/2/18
 * Timer: 10:54
 * Author: nedhuo
 * Description:
 */

public class Content implements Serializable {

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

    @Override
    public String toString() {
        return "Content{" +
                "type='" + type + '\'' +
                ", data=" + data +
                ", trackingData='" + trackingData + '\'' +
                ", tag='" + tag + '\'' +
                ", id=" + id +
                ", adIndex=" + adIndex +
                '}';
    }
}