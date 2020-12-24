package com.hngg.jianshi.data.bean.home;

/**
 * Date: 2020/11/24
 * Timer: 16:55
 * Author: nedhuo
 * Description:
 */

import java.util.List;

/**
 * Auto-generated: 2020-11-22 14:0:2
 *  ItemCollection
 *
 *  videoCollectionOfFollow
 *  videoCollectionForCover
 */
public class ItemCollection extends  Data {

    private String dataType;
    private Header header;
    private List<ItemList> itemList;
    private int count;
    private List<String> adTrack;
    private String footer;
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public String getDataType() {
        return dataType;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
    public Header getHeader() {
        return header;
    }

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

    public void setAdTrack(List<String> adTrack) {
        this.adTrack = adTrack;
    }
    public List<String> getAdTrack() {
        return adTrack;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
    public String getFooter() {
        return footer;
    }

}