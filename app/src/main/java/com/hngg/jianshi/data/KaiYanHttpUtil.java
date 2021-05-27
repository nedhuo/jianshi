package com.hngg.jianshi.data;

import android.util.Log;

import com.hngg.jianshi.data.bean.home.DailyRootBean;
import com.hngg.network.BaseHttpUtil;
import com.hngg.network.errorhandler.ExceptionHandle;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;

/**
 * Date: 2020/11/24
 * Timer: 11:21
 * Author: nedhuo
 * Description:
 */
public class KaiYanHttpUtil extends BaseHttpUtil {
    public KaiYanHttpUtil(){
        init();
    }

    private String TAG = "KaiYanHttpUtil";

    @Override
    protected Interceptor getInterceptor() {
        return null;
    }

    @Override
    protected <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                System.out.println(response.toString());
                Log.i(TAG, "response:" + response.toString());
                if (response instanceof DailyRootBean) {
                    throw new ExceptionHandle.ServerException();
                }
                return response;
            }
        };
    }
}
