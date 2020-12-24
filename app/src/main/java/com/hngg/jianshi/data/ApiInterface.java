package com.hngg.jianshi.data;

import com.hngg.jianshi.data.bean.home.DailyRootBean;
import com.hngg.network.KaiYanApi;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Date: 2020/11/22
 * Timer: 15:11
 * Author: nedhuo
 * Description:
 * 网络请求封装接口，retrofit会去创建这些接口的实现类
 */
public interface ApiInterface {

    @GET(KaiYanApi.homePageUrl)
    Observable<DailyRootBean> getDailyDataChannels();

    @GET
    Observable<DailyRootBean> nextPage(String url);

}
