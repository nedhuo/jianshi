package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;

/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
 class Shield implements Serializable {

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
