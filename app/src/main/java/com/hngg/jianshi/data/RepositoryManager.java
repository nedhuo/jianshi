package com.hngg.jianshi.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.jess.arms.integration.IRepositoryManager;

/**
 * Date: 2020/11/26
 * Timer: 10:52
 * Author: nedhuo
 * Description:   用来管理网络请求层以及数据缓存层, 以后可能添加数据库储存层,
 * 专门提供给 Model 层做数据处理
 */
public class RepositoryManager implements IRepositoryManager {
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
