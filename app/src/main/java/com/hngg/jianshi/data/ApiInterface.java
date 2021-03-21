package com.hngg.jianshi.data;

import com.hngg.jianshi.data.bean.community.CommunityRootBean;
import com.hngg.jianshi.data.bean.discover.DisCoverRootBean;
import com.hngg.jianshi.data.bean.home.DailyRootBean;
import com.hngg.jianshi.data.bean.home.RelationVideoBean;
import com.hngg.jianshi.data.bean.recommend.RecommendRootBean;
import com.hngg.jianshi.data.bean.reply.JsonRootBean;
import com.hngg.network.KaiYanApi;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
    //
    @GET("api/v4/tabs/selected")
    Observable<DailyRootBean> getDailyData();

    //daily下一页接口
    //
    @GET()
    Observable<DailyRootBean> getDailyNextPage(@Url String url);

    //相关视频链接
    ////http://baobab.kaiyanapp.com/api/v4/video/related?id=186856
    @GET("api/v4/video/related")
    Observable<RelationVideoBean> getRelationVideo(@Query("id") String id);

    //相关回复链接
    //http://baobab.kaiyanapp.com/api/v2/replies/video?videoId=186856
    @GET("api/v2/replies/video")
    Observable<JsonRootBean> getVideoReply(@Query("videoId") long id);


    //推荐
    //http://baobab.kaiyanapp.com/api/v5/index/tab/allRec
    @GET("api/v5/index/tab/allRec")
    Observable<RecommendRootBean> getRecommendData();

    //推荐 下一页
    //http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=1&isTag=true&adIndex=5
    @GET()
    Observable<RecommendRootBean> getRecommendNextPage(@Url String url);

    //社区
    //http://baobab.kaiyanapp.com/api/v7/community/tab/rec
    @GET("api/v7/community/tab/rec")
    Observable<CommunityRootBean> getCommunityData();

    //社区
    //http://baobab.kaiyanapp.com/api/v7/community/tab/rec?startScore=1613737807000&pageCount=2
    @GET()
    Observable<CommunityRootBean> getCommunityNextPage(@Url String url);

    //发现
    //http://baobab.kaiyanapp.com/api/v7/index/tab/discovery
    //http://baobab.kaiyanapp.com/api/v4/discovery
    @GET("api/v7/index/tab/discovery")
    Observable<DisCoverRootBean> getDisCoverData();


    //分类  http://baobab.kaiyanapp.com/api/v4/discovery/category
    //      http://baobab.kaiyanapp.com/api/v4/categories

    ///http://baobab.kaiyanapp.com/api/v4/category/24/?title=%E6%97%B6%E5%B0%9A


    //日历
    //http://baobab.kaiyanapp.com/api/v7/roamingCalendar/index?date=2021-5-22


    //搜索
    //http://baobab.kaiyanapp.com/api/v1/search?&num=10&start=10&query=%E2%80%9C%E5%B9%BF%E5%91%8A%E2%80%9D
}
