package com.hngg.network;

import android.content.Context;
import android.text.TextUtils;

import com.hngg.network.http.cookie.CookieJarImpl;
import com.hngg.network.http.cookie.store.PersistentCookieStore;
import com.hngg.network.http.interceptor.BaseInterceptor;
import com.hngg.network.http.interceptor.CacheInterceptor;
import com.hngg.network.http.interceptor.logging.Level;
import com.hngg.network.http.interceptor.logging.LoggingInterceptor;
import com.hngg.network.utils.Utils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by goldze on 2017/5/10.
 * RetrofitClient封装单例类, 实现网络请求
 */
public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 60; //超时时间
    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024; //缓存时间
    public static String baseUrl = "https://www.oschina.net/"; //服务端根路径
    private final Context mContext = Utils.getContext();
    private static Retrofit mRetrofit;
    private static RetrofitClient INSTANCE;

    private Cache cache = null;
    private File httpCacheDirectory;


    public static RetrofitClient getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitClient();
                }
            }
        }
        return INSTANCE;
    }

    private RetrofitClient() {
        this(baseUrl, null);
    }

    private RetrofitClient(String url, Map<String, String> headers) {
        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "goldze_cache");
        }
        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, CACHE_TIMEOUT);
            }
        } catch (Exception e) {
            //  KLog.e("Could not create http cache", e);
        }

        // ThreadUtils.getFixedPool(6,8);
        mRetrofit = new Retrofit.Builder()
                .client(getOkHttpClient(headers))
                .addConverterFactory(GsonConverterFactory.create())//数据类型转换
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加该类到Retrofit,使得Retrofit与Rxjava可以无缝切换
                .baseUrl(url)
                .build();

    }

    /**
     * 构建OkHttp
     */
    private OkHttpClient getOkHttpClient(Map<String, String> headers) {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor
                .Builder()//构建者模式
                .loggable(BuildConfig.DEBUG) //是否开启日志打印
                .setLevel(Level.BASIC) //打印的等级
                .log(Platform.INFO) // 打印类型
                .request("Request") // request的Tag
                .response("Response")// Response的Tag
                .addHeader("log-header", "I am the log request header.") // 添加打印头, 注意 key 和 value 都不能是中文
                .build();

        return new OkHttpClient.Builder()
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(mContext)))
//                .cache(cache)
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(new CacheInterceptor(mContext))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();
    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return mRetrofit.create(service);
    }

    /**
     * /**
     * execute your customer API
     * For example:
     * MyApiService service =
     * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
     * <p>
     * RetrofitClient.getInstance(MainActivity.this)
     * .execute(service.lgon("name", "password"), subscriber)
     * * @param subscriber
     */

    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        return null;
    }
}
