package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;

/**
 * Date: 2020/11/24
 * Timer: 12:10
 * Author: nedhuo
 * Description:
 */
public class VideoPosterBean implements Serializable {

    private double scale;
    private String url;
    private String fileSizeStr;
    public void setScale(double scale) {
        this.scale = scale;
    }
    public double getScale() {
        return scale;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setFileSizeStr(String fileSizeStr) {
        this.fileSizeStr = fileSizeStr;
    }
    public String getFileSizeStr() {
        return fileSizeStr;
    }

}