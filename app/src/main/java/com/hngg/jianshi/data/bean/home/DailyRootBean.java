package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;
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
public class DailyRootBean implements Serializable{

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





