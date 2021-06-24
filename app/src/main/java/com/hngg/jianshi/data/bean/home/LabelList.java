package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;

/**
 * Date: 2021/2/18
 * Timer: 21:21
 * Author: nedhuo
 * Description:
 */
class LabelList implements Serializable {

    private String text;
    private String actionUrl;
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    public String getActionUrl() {
        return actionUrl;
    }

}
