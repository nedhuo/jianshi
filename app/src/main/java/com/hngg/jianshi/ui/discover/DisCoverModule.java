package com.hngg.jianshi.ui.discover;


import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 2020/11/19
 * Timer: 16:48
 * Author: nedhuo
 * Description:
 */
@Module
public class DisCoverModule {
    private DisCoverContract.View view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public DisCoverModule(DisCoverContract.View view) {
        this.view = view;
    }

    @DisCoverScope
    @Provides
    DisCoverContract.View provideDisCoverView() {
        return this.view;
    }

    @DisCoverScope
    @Provides
    DisCoverContract.Model provideDisCoverModel(DisCoverModel model) {
        return model;
    }
}
