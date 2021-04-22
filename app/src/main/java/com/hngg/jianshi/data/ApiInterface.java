package com.hngg.jianshi.data;

import com.hngg.jianshi.data.bean.userinfo.UserInfoBean;
import com.hngg.jianshi.data.bean.community.CommunityRootBean;
import com.hngg.jianshi.data.bean.discover.DisCoverRootBean;
import com.hngg.jianshi.data.bean.home.DailyRootBean;
import com.hngg.jianshi.data.bean.home.RelationVideoBean;
import com.hngg.jianshi.data.bean.recommend.RecommendRootBean;
import com.hngg.jianshi.data.bean.reply.ReplyRootBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_DynamicBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_HomeBean;
import com.hngg.jianshi.data.bean.userinfo.UserInfo_WorksBean;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Date: 2020/11/22
 * Timer: 15:11
 * Author: nedhuo
 * Description:
 * 网络请求封装接口，retrofit会去创建这些接口的实现类
 */
public interface ApiInterface {

    //daily数据接口
    @GET("api/v4/tabs/selected")
    Observable<DailyRootBean> getDailyData();

    //daily下一页接口
    @GET()
    Observable<DailyRootBean> getDailyNextPage(@Url String url);

    //相关视频链接  http://baobab.kaiyanapp.com/api/v4/video/related?id=186856
    @GET("api/v4/video/related")
    Observable<RelationVideoBean> getRelationVideo(@Query("id") String id);

    //http://baobab.kaiyanapp.com/api/v2/video/248522?udid=9457b933f3bd434ba69e350e1112ec623fc61dee&vc=7000111&vn=7.0.11&size=1080X2207&deviceModel=V1813BT&first_channel=vivo&last_channel=vivo&system_version_code=28

    //相关回复链接  http://baobab.kaiyanapp.com/api/v2/replies/video?videoId=186856
    @GET("api/v2/replies/video")
    Observable<ReplyRootBean> getVideoReply(@Query("videoId") long id);

    //推荐 http://baobab.kaiyanapp.com/api/v5/index/tab/allRec
    @GET("api/v5/index/tab/allRec")
    Observable<RecommendRootBean> getRecommendData();

    //推荐 下一页  http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=1&isTag=true&adIndex=5
    @GET()
    Observable<RecommendRootBean> getRecommendNextPage(@Url String url);

    //社区 http://baobab.kaiyanapp.com/api/v7/community/tab/rec
    @GET("api/v7/community/tab/rec")
    Observable<CommunityRootBean> getCommunityData();

    //社区下一页 http://baobab.kaiyanapp.com/api/v7/community/tab/rec?startScore=1613737807000&pageCount=2
    @GET()
    Observable<CommunityRootBean> getCommunityNextPage(@Url String url);

    //主题创作大厅 eyepetizer:http://baobab.kaiyanapp.com/api/v7/community/tagSquare?tabIndex=0
    //话题讨论大厅 eyepetizer://webview/?title=&url=https%3a%2f%2fm.eyepetizer.net%2fu2%2funiversal-card%3fpage_label%3dtopic_square%26page_type%3dcard

    //发现
    //http://baobab.kaiyanapp.com/api/v7/index/tab/discovery
    //http://baobab.kaiyanapp.com/api/v4/discovery
    @GET("api/v7/index/tab/discovery")
    Observable<DisCoverRootBean> getDisCoverData();

    /**
     * UserInfoData
     * https://baobab.kaiyanapp.com/api/v5/userInfo/tab?id=2171&userType=PGC
     */
    @GET("api/v5/userInfo/tab?id=2171&userType=PGC")
    Observable<UserInfoBean> getUserInfo();

    /**
     * https://baobab.kaiyanapp.com/api/v5/userInfo/tab/index?id=2171&userType=PGC
     */
    @GET("api/v5/userInfo/tab/index?id=2171&userType=PGC")
    Observable<UserInfo_HomeBean> getUserInfo_Home();
    /**
     * https://baobab.kaiyanapp.com/api/v4/pgcs/videoList?id=2171
     * nextpage https://baobab.kaiyanapp.com/api/v4/pgcs/videoList?start=10&num=10&id=2171&strategy=date
     */
    @GET("api/v4/pgcs/videoList?id=2171")
    Observable<UserInfo_WorksBean> getUserInfo_Works();
    /**
     * https://baobab.kaiyanapp.com/api/v5/userInfo/tab/dynamics?id=2171&userType=PGC
     * nextPage https://baobab.kaiyanapp.com/api/v5/userInfo/tab/dynamics?start=10&num=10&id=2171&userType=PGC
     */
    @GET("api/v5/userInfo/tab/dynamics?id=2171&userType=PGC")
    Observable<UserInfo_DynamicBean> getUserInfo_Dynamics();






    /**
     * tag http://baobab.kaiyanapp.com/api/v6/tag/index?id=1022&udid=9457b933f3bd434ba69e350e1112ec623fc61dee&vc=7000111&vn=7.0.11
     *
     *      apiUrl=http://baobab.kaiyanapp.com/api/v1/tag/videos?id=1022
     *      apiUrl=http://baobab.kaiyanapp.com/api/v6/tag/dynamics?id=1022
     * */
    //http://baobab.kaiyanapp.com/api/v3/tag/videos?tagId=24&strategy=shareCount

    //分类  http://baobab.kaiyanapp.com/api/v4/discovery/category
    //      http://baobab.kaiyanapp.com/api/v4/categories

    ///http://baobab.kaiyanapp.com/api/v4/category/24/?title=%E6%97%B6%E5%B0%9A


    /**
     * strategy : monthly, weekly, historical
     * http://baobab.kaiyanapp.com/api/v3/ranklist?num=10&strategy=monthly
     */


    //日历
    //http://baobab.kaiyanapp.com/api/v7/roamingCalendar/index?date=2021-5-22

    //http://baobab.kaiyanapp.com/api/v3/categories/detail?id=18
    //最受欢迎
//http://baobab.kaiyanapp.com/api/v3/categories/videoList?id=2&strategy=mostPopular
    //搜索
    //http://baobab.kaiyanapp.com/api/v1/search?&num=10&start=10&query=%E2%80%9C%E5%B9%BF%E5%91%8A%E2%80%9D


    /**
     * http://baobab.kaiyanapp.com/api/v2/follow/newlist?uid=301178684
     * 内置链接
     *      http://baobab.kaiyanapp.com/api/v1/follow/newlist?itemType=user&uid=301178684
     *      内置链接 eyepetizer://pgc/detail/2163/?title=%E5%BC%80%E7%9C%BC%E9%9F%B3%E4%B9%90%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1
     *
     *      http://baobab.kaiyanapp.com/api/v1/follow/newlist?itemType=category&uid=301178684
     *      无数据
     *
     * http://baobab.kaiyanapp.com/api/v1/follow/myFansList?uid=301178695
     * 内嵌链接 ： eyepetizer://pgc/detail/303084692/?title=%E5%BC%80%E7%9C%BC%E7%94%A8%E6%88%B7_gq909j&userType=NORMAL&tabIndex=0
     * */
}
