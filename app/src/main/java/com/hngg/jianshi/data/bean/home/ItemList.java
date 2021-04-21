package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;

/**
 * Date: 2020/11/24
 * Timer: 12:08
 * Author: nedhuo
 * Description:
 */

public class ItemList implements Serializable {

    private String type;
    private Data data;
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