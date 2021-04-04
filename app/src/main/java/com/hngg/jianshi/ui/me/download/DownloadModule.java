package com.hngg.jianshi.ui.me.download;

import com.hngg.jianshi.ui.MainActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */

@Module
public class DownloadModule {

    private DownloadActivity view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public DownloadModule(DownloadActivity view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    DownloadActivity provideDownloadView() {
        return this.view;
    }
}
