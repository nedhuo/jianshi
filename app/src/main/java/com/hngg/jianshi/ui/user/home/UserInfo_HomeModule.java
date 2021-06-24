package com.hngg.jianshi.ui.user.home;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserInfo_HomeModule {
    private UserInfo_HomeFragment view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public UserInfo_HomeModule(UserInfo_HomeFragment view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    UserInfo_HomeFragment provideUserInfo_HomeView() {
        return this.view;
    }
}
