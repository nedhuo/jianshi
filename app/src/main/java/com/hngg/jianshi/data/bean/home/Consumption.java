package com.hngg.jianshi.data.bean.home;


/**
 * Date: 2021/2/16
 * Timer: 16:37
 * Author: nedhuo
 * Description:
 */

import java.io.Serializable;

/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Consumption implements Serializable {

    private int collectionCount;
    private int shareCount;
    private int replyCount;
    private int realCollectionCount;
    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }
    public int getCollectionCount() {
        return collectionCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }
    public int getShareCount() {
        return shareCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
    public int getReplyCount() {
        return replyCount;
    }

    public void setRealCollectionCount(int realCollectionCount) {
        this.realCollectionCount = realCollectionCount;
    }
    public int getRealCollectionCount() {
        return realCollectionCount;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "collectionCount=" + collectionCount +
                ", shareCount=" + shareCount +
                ", replyCount=" + replyCount +
                ", realCollectionCount=" + realCollectionCount +
                '}';
    }
}
