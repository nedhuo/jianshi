package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2020-11-22 13:39:12
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
class PlayInfo implements Serializable {

    private int height;
    private int width;
    private List<UrlList> urlList;
    private String name;
    private String type;
    private String url;

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setUrlList(List<UrlList> urlList) {
        this.urlList = urlList;
    }

    public List<UrlList> getUrlList() {
        return urlList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
