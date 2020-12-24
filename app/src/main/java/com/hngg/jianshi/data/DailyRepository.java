package com.hngg.jianshi.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.jess.arms.integration.IRepositoryManager;

/**
 * Date: 2020/11/28
 * Timer: 9:51
 * Author: nedhuo
 * Description:
 */
public class DailyRepository implements IRepositoryManager {
    @NonNull
    @Override
    public <T> T obtainRetrofitService(@NonNull Class<T> service) {
        return null;
    }

    @NonNull
    @Override
    public <T> T obtainCacheService(@NonNull Class<T> cache) {
        return null;
    }

    @Override
    public void clearAllCache() {

    }

    @NonNull
    @Override
    public Context getContext() {
        return null;
    }
}
