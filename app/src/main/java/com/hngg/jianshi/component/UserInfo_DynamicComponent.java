package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.user.dynamic.UserInfo_DynamicFragment;
import com.hngg.jianshi.ui.user.dynamic.UserInfo_DynamicModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = UserInfo_DynamicModule.class, dependencies = AppComponent.class)
public interface UserInfo_DynamicComponent {
    void inject(UserInfo_DynamicFragment fragment);
}