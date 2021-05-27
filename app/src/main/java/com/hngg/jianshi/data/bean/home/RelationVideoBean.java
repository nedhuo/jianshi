package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;
import java.util.List;

/**
 * Date: 2021/2/17
 * Timer: 10:39
 * Author: nedhuo
 * Description:
 */
public class RelationVideoBean implements Serializable {
    private List<ItemList> itemList;
    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public boolean isAdExist() {
        return adExist;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }
}