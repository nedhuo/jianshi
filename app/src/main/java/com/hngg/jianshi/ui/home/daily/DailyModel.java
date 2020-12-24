package com.hngg.jianshi.ui.home.daily;

import android.util.Log;


import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.CommonCache;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.home.DailyRootBean;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;

/**
 * Date: 2020/11/19
 * Timer: 19:45
 * Author: nedhuo
 * Description:
 * EventBus完成Model与Presenter之间数据通信
 * Model层主要
 */
public class DailyModel extends BaseModel implements DailyContract.Model {

    private static final String TAG = "DailyModel";
    private KaiYanHttpUtil httpUtil;
    private String mNextPageUrl = "";
    private List<ItemList> mItemDatas;


    public List<ItemList> getItemDatas() {
        return mItemDatas;
    }

    /**
     * repositoryManager
     */
    @Inject
    public DailyModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);

        Log.i(TAG, repositoryManager.toString());
//        httpUtil = new KaiYanHttpUtil();
//        reLoad();
    }

    public Observable<List<DailyRootBean>> getHomeData(int lastIdQueried, boolean update) {
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(ApiInterface.class)
                .getDailyDataChannels()
                // flatMap 返回一个Observable，它基于将您提供的函数应用于源ObservableSource发出的每个项来发出项，
                // 其中该函数返回ObservableSource，然后合并这些生成的ObservableSource并发出此合并的结果。
                //flatMap 就是
                .flatMap();
    }

    public void loadMore() {
        Observable<DailyRootBean> compose1 = httpUtil.getService(ApiInterface.class)
                .nextPage(mNextPageUrl)
                .compose(httpUtil.applySchedulers(new BaseObserver<DailyRootBean>() {
                    @Override
                    protected void onSuccess(DailyRootBean homeData) {
                        if (mNextPageUrl == null || "".equals(mNextPageUrl)) {
                            //无数据了
                        }
                        mNextPageUrl = homeData.getNextPageUrl();
                        mItemDatas.addAll(homeData.getItemList());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                    }
                }));
    }

    public void reLoad() {
        Observable.just(mRepositoryManager
                .obtainRetrofitService(ApiInterface.class)
                .getDailyDataChannels()
                // flatMap 返回一个Observable，
                // 它基于将您提供的函数应用于源ObservableSource发出的每个项来发出项，
                // 其中该函数返回ObservableSource，然后合并这些生成的ObservableSource并发出此合并的结果。
                //flatMap 就是
                .flatMap(new Function<Observable<DailyRootBean>, ObservableSource<DailyRootBean>() {

                }));


        mItemDatas.clear();
        Observable<DailyRootBean> compose = httpUtil.getService(ApiInterface.class)
                .getDailyDataChannels()
                //compose 通过应用一个指定的转换器转换ObservableSource
                .compose(httpUtil.applySchedulers(new BaseObserver<DailyRootBean>() {
                    @Override
                    protected void onSuccess(DailyRootBean homeData) {
                        mNextPageUrl = homeData.getNextPageUrl();
                        mItemDatas.addAll(homeData.getItemList());
                        //     mPresenter.mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                    }
                }));

    }
}
