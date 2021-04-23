package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.user.works.UserInfo_WorksFragment;
import com.hngg.jianshi.ui.user.works.UserInfo_WorksModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = UserInfo_WorksModule.class, dependencies = AppComponent.class)
public interface UserInfo_WorksComponent {
    void inject(UserInfo_WorksFragment fragment);
}
