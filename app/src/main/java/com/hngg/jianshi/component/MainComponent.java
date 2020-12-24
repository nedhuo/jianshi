package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.MainActivity;
import com.hngg.jianshi.ui.MainModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Date: 2020/11/18
 * Timer: 19:17
 * Author: nedhuo
 * Description:
 */

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
