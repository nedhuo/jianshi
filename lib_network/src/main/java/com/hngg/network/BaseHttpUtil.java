package com.hngg.network;

import com.hngg.network.errorhandler.HttpErrorHandler;
import com.hngg.network.http.interceptor.BaseInterceptor;
import com.hngg.network.http.interceptor.CacheInterceptor;
import com.hngg.network.http.interceptor.logging.Level;
import com.hngg.network.http.interceptor.logging.LoggingInterceptor;
import com.hngg.network.utils.Utils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hngg.network.KaiYanApi.baseHttpUrl;

/**
 * Date: 2020/11/23
 * Timer: 22:44
 * Author: nedhuo
 * Description:
 * 使用Retrofit2 + OkHttp3 + RxJava2组合的一个网络module
 * 待完善模块： OkHttp拦截器
 * 已添加OkHttp 日志拦截器，debug模式自动打印日志
 * 使用方法  实现此方法
 * 请求类
 */
public abstract class BaseHttpUtil {
    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();

    /**
     *
     */
    public void init() {
    }

    /**
     * 这个接口用来留给web调用
     *
     * @param clazz 传入接口
     * @return 所传入接口的实例化对象
     */
    public <T> T getService(Class<T> clazz) {
        return getRetrofit(clazz).create(clazz);
    }

    /**
     * 获取retrofit对象，里面用map对对象进行了存储
     */
    private Retrofit getRetrofit(Class clazz) {
        return getRetrofit(clazz, baseHttpUrl);
    }


    private Retrofit getRetrofit(Class clazz, String baseUrl) {
        if (retrofitHashMap.get(baseUrl + clazz.getName()) != null) {
            return retrofitHashMap.get(baseUrl + clazz.getName());
        }
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//Json转Bean
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitHashMap.put(baseUrl + clazz.getName(), retrofit);
        return retrofit;
    }

    /**
     * 获取OkHttp对象
     * 该对象主要用来添加拦截器
     */
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new LoggingInterceptor.Builder()//构建者模式
                .loggable(BuildConfig.DEBUG) //是否开启日志打印
                .setLevel(Level.BASIC) //打印的等级
                .log(Platform.INFO) // 打印类型
                .request("Request") // request的Tag
                .response("Response")// Response的Tag
                .addHeader("log-header", "I am the log request header.") // 添加打印头, 注意 key 和 value 都不能是中文
                .build());
        okHttpClientBuilder.addInterceptor(new BaseInterceptor(null));
        okHttpClientBuilder.addInterceptor(new CacheInterceptor(Utils.getContext()));
        return okHttpClientBuilder.build();
    }

    /**
     * 对通用的Schedulers进行了封装
     */
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 对网络异常进行处理
     *
     * @return
     */
    public <T> ObservableTransformer<T, T> exceptionTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .onErrorResumeNext(new HttpErrorHandler<T>());
            }
        };
    }

    //定义拦截器
    protected abstract Interceptor getInterceptor();

    //定义异常分类处理
    protected abstract <T> Function<T, T> getAppErrorHandler();
}
