package com.hngg.network.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Date: 2020/11/23
 * Timer: 19:51
 * Author: nedhuo
 * Description:
 */
public class CommonRequestInterceptor implements Interceptor {
    private final String TAG = "CommonRequestInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("os","android");

        return chain.proceed(builder.build());
    }
}
