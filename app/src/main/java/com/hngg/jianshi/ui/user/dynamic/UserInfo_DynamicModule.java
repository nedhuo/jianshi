package com.hngg.jianshi.ui.user.dynamic;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserInfo_DynamicModule {
    private UserInfo_DynamicFragment view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public UserInfo_DynamicModule(UserInfo_DynamicFragment view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    UserInfo_DynamicFragment provideUserInfo_DynamicView() {
        return this.view;
    }
}