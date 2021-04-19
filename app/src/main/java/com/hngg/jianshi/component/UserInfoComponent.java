package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.video.user.UserInfoActivity;
import com.hngg.jianshi.ui.video.user.UserInfoModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */

@ActivityScope
@Component(modules = UserInfoModule.class, dependencies = AppComponent.class)
public interface UserInfoComponent {
    void inject(UserInfoActivity activity);
}
