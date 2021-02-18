package com.hngg.jianshi.data.bean.home;

/**
 * Date: 2020/11/24
 * Timer: 17:39
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
public class Cover implements Serializable {

    private String feed;
    private String detail;
    private String blurred;
    private String sharing;
    private String homepage;
    public void setFeed(String feed) {
        this.feed = feed;
    }
    public String getFeed() {
        return feed;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getDetail() {
        return detail;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }
    public String getBlurred() {
        return blurred;
    }

    public void setSharing(String sharing) {
        this.sharing = sharing;
    }
    public String getSharing() {
        return sharing;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
    public String getHomepage() {
        return homepage;
    }

}
