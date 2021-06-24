package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;

/**
 * Date: 2021/2/18
 * Timer: 21:20
 * Author: nedhuo
 * Description:
 */
public class Follow implements Serializable {

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
