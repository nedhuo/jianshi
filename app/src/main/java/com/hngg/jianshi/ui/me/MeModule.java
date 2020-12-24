package com.hngg.jianshi.ui.me;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 2020/11/19
 * Timer: 16:23
 * Author: nedhuo
 * Description:
 */
@Module
public class MeModule {
    private MeContract.View view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public MeModule(MeContract.View view) {
        this.view = view;
    }

    @MeScope
    @Provides
    MeContract.View provideMeView() {
        return this.view;
    }

    @MeScope
    @Provides
    MeContract.Model provideMeModel(MeModel model) {
        return model;
    }
}
