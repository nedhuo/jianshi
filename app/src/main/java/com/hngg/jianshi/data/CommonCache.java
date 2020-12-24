package com.hngg.jianshi.data;

import com.hngg.jianshi.data.bean.home.DailyRootBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Date: 2020/12/1
 * Timer: 15:27
 * Author: nedhuo
 * Description:
 */
public interface CommonCache {
   // @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
  //  Observable<Reply<List<User>>> getUsers(Observable<List<User>> oUsers, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<DailyRootBean>>> getHomeData(Observable<List<DailyRootBean>> homeData,
                                                       DynamicKey idLastUserQueried,
                                                       EvictProvider evictProvider);

}
