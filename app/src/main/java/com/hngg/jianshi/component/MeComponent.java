package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.home.HomeFragment;
import com.hngg.jianshi.ui.me.MeFragment;
import com.hngg.jianshi.ui.me.MeModule;
import com.hngg.jianshi.ui.me.MeScope;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Date: 2020/11/19
 * Timer: 16:43
 * Author: nedhuo
 * Description:
 */
@MeScope
@Component(modules = MeModule.class, dependencies = AppComponent.class)
public interface MeComponent {
    void inject(MeFragment fragment);
}