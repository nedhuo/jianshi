package com.hngg.network.interceptor;

import android.util.Log;

import java.io.IOException;

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

    //IOException, SocketTimeoutException
    @Override
    public Response intercept(Chain chain) throws IOException {
        long l = System.currentTimeMillis();
        //TODO  SocketTimeoutException: timeout 异常
        try {
            Response response = chain.proceed(chain.request());
            return response;
        } catch (Exception e) {
            Log.i(TAG, "requestTime:" + (System.currentTimeMillis() - l));
            return null;
        }


    }
}
