package com.hngg.network;


/**
 * Date: 2020/11/22
 * Timer: 13:31
 * Author: nedhuo
 * Description:
 */
public class  KaiYanApi {

    public static final String baseHttpUrl = "http://baobab.kaiyanapp.com/";

    public static final String baseHttpsUrl="https://baobab.kaiyanapp.com/";
    //日常（首页）
    public static final String homePageUrl = "api/v4/tabs/selected";

    // http://baobab.kaiyanapp.com/api/v4/webview/?title=%E6%95%A2%E4%B8%BA%E4%B8%80%E5%88%BB+%E8%87%B4%E8%83%9C%E4%B9%8B%E6%97%B6&url=http%3A%2F%2Fwww.eyepetizer.net%2Fvideos_article.html%3Fnid%3D489%26shareable%3D
    //查看往期的链接
    public static final String feedUrl = "api/v2/feed?issueIndex=1";

    //发现
    public static final String discoverUrl = "api/v4/discovery";

    //分类
    public static final String categoryUrl = "api/v4/categories";

    //热门 Banner
    public static final String hotUrl = "api/v4/discovery/hot";

    //关注
    public static final String followUrl = "api/v4/tabs/follow";

    //
    public static final String rankListUrl = "api/v4/rankList";

    //http://baobab.kaiyanapp.com/api/v4/categories/videoList?id=14&udid=d2807c895f0348a180148c9dfa6f2feeac0781b5
    //分类Item查询  id   udid
    static final String categoryDetailsUrl = "api/v4/categories/videoList?";

    static final String videoRelatedUrl = "api/v4/video/related?";

    //["艺术","街头","摄影日常","创意广告","美食","旅行","健身","汽车","黑科技"]
    static final String keywordUrl = "api/v3/queries/hot";

    static final String searchUrl = "api/v1/search?&num=10&start=10";

    static final String authorUrl = "api/v4/pgcs/detail/tab";

    //推荐   参数 http://baobab.kaiyanapp.com/api/v7/community/tab/rec?refreshCount=1&udid=d07e6618dd4446629393f593a9b832f7eb0ed504
    static final String recommendUrl = "api/v7/community/tab/rec?";

    //旅游推荐
    static final String bbb =
            "/api/v7/roamingCalendar/index?date=2020-11-05&udid=d07e6618dd4446629393f593a9b832f7eb0ed504&vc=6030101&vn=6.3.10&size=1080X2207&deviceModel=V1813BT&first_channel=vivo&last_channel=vivo&system_version_code=28 HTTP/1.1";

    //消息推送
    static final String ccc =
            "GET /api/v3/messages?udid=d07e6618dd4446629393f593a9b832f7eb0ed504&vc=6030101&vn=6.3.10&size=1080X2207&deviceModel=V1813BT&first_channel=vivo&last_channel=vivo&system_version_code=28 HTTP/1.1";


}
