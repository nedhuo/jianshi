package com.hngg.jianshi.data.bean.taginfo;

import com.hngg.jianshi.data.bean.home.ItemList;

import java.io.Serializable;
import java.util.List;

/**
 * TagDetail下VideosFragment Bean
 * */
public class TagInfoVideosBean implements Serializable {
    private List<ItemList> itemList;
    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
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

    @Override
    public String toString() {
        return "TagInfoVideosBean{" +
                "itemList=" + itemList +
                ", count=" + count +
                ", total=" + total +
                ", nextPageUrl='" + nextPageUrl + '\'' +
                ", adExist=" + adExist +
                '}';
    }
}
