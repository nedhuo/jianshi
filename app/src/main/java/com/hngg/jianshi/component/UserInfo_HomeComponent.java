package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.user.home.UserInfo_HomeFragment;
import com.hngg.jianshi.ui.user.home.UserInfo_HomeModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = UserInfo_HomeModule.class, dependencies = AppComponent.class)
public interface UserInfo_HomeComponent {
    void inject(UserInfo_HomeFragment fragment);
}
