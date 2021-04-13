package com.hngg.jianshi.ui.me.download.downloading;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */

@Module
public class DownloadingModule {
    private DownloadingContract.View view;

    public DownloadingModule(DownloadingContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    DownloadingContract.View provideDownloadingView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    DownloadingContract.Model provideDownloadingModel(DownloadingContract.Model model) {
        return model;
    }
}