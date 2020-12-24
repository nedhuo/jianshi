package com.hngg.jianshi.ui.home;

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
public class HomeModule {
    private HomeFragment view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public HomeModule(HomeFragment view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    HomeFragment provideHomeView() {
        return this.view;
    }

}
