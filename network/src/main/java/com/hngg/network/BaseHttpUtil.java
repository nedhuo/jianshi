package com.hngg.network;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hngg.network.KaiYanApi.baseUrl;

/**
 * Date: 2020/11/23
 * Timer: 22:44
 * Author: nedhuo
 * Description:
 *      使用Retrofit2 + OkHttp3 + RxJava2组合的一个网络module
 *      待完善模块： OkHttp拦截器
 *                  已添加OkHttp 日志拦截器，debug模式自动打印日志
 *      使用方法  实现此方法
 *      请求类
 */
public abstract class BaseHttpUtil {
    private final String TAG = "HttpUtils";
    private static INetworkRequiredInfo iNetworkRequiredInfo;
    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();

    /**
     *
     */
    public void init(INetworkRequiredInfo networkRequiredInfo) {
        iNetworkRequiredInfo = networkRequiredInfo;
    }

    /**
     * 这个接口用来留给web调用
     * @param clazz 传入接口
     * @return 所传入接口的实例化对象
     * */
    public <T> T getService(Class<T> clazz) {
        return getRetrofit(clazz).create(clazz);
    }

    /**
     * 获取retrofit对象，里面用map对对象进行了存储
     * */
    private Retrofit getRetrofit(Class clazz) {
        if (retrofitHashMap.get(baseUrl + clazz.getName()) != null) {
            return retrofitHashMap.get(baseUrl + clazz.getName());
        }
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(baseUrl);
        //添加的是OkHttp的拦截器，因此需要在OkHttp的客户端上添加
        retrofitBuilder.client(getOkHttpClient());
        //Json转Bean
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        //
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();
        retrofitHashMap.put(baseUrl + clazz.getName(), retrofit);
        return retrofit;
    }

    /**
     * 获取OkHttp对象
     * 该对象主要用来添加拦截器
     * */
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (iNetworkRequiredInfo != null && iNetworkRequiredInfo.isDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
        }
//        okHttpClientBuilder.addInterceptor()
//        okHttpClientBuilder.addInterceptor(new CommonRequestInterceptor());
//        okHttpClientBuilder.addInterceptor(new CommonResponseInterceptor());
        return okHttpClientBuilder.build();
    }

    /**
     * 对通用的Schedulers进行了封装
     * */
    public <T> ObservableTransformer<T, T> applySchedulers(final Observer<T> observer) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.subscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread());
                observable.subscribe(observer);
                return observable;
            }
        };
    }

    //定义拦截器
    protected abstract Interceptor getInterceptor();

    //定义异常分类处理
    protected abstract <T> Function<T, T> getAppErrorHandler();
}
