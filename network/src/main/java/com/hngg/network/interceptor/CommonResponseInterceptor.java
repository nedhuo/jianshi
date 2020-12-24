package com.hngg.network.interceptor;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.IOException;
import java.text.DateFormat;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Date: 2020/11/23
 * Timer: 19:57
 * Author: nedhuo
 * Description:
 */
public class CommonResponseInterceptor implements Interceptor {
    private final String TAG = "ResponseInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        long l = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());

        Log.i(TAG, "requestTime:" + (System.currentTimeMillis() - l));
        return response;
    }
}
