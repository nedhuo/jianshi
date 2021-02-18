package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;

/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
 class UrlList implements Serializable {

    private String name;
    private String url;
    private long size;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setSize(long size) {
        this.size = size;
    }
    public long getSize() {
        return size;
    }

}
