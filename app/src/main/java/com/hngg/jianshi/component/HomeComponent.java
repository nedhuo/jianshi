package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.home.HomeFragment;
import com.hngg.jianshi.ui.home.HomeModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Date: 2020/11/19
 * Timer: 16:27
 * Author: nedhuo
 * Description:
 */
@FragmentScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment fragment);
}
