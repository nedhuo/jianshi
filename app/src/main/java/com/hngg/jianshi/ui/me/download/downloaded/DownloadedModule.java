package com.hngg.jianshi.ui.me.download.downloaded;

import com.hngg.jianshi.ui.me.MeContract;
import com.hngg.jianshi.ui.me.MeModel;
import com.hngg.jianshi.ui.me.MeScope;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
@Module
public class DownloadedModule {
    private DownloadedContract.View view;

    public DownloadedModule(DownloadedFragment view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    DownloadedContract.View provideDownloadedView() {
        return this.view;
    }


    @FragmentScope
    @Provides
    DownloadedContract.Model provideDownloadedModel(DownloadedContract.Model model) {
        return model;
    }
}
