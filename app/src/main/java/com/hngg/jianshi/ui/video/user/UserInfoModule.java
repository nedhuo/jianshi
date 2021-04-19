package com.hngg.jianshi.ui.video.user;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
@Module
public class UserInfoModule {
    private UserInfoContract.View view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public UserInfoModule(UserInfoActivity view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserInfoContract.View provideUserInfoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UserInfoContract.Model provideUserInfoModel(UserInfoModel model) {
        return model;
    }
}
