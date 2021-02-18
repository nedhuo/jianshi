package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.discover.DisCoverFragment;
import com.hngg.jianshi.ui.discover.DisCoverModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Date: 2020/11/19
 * Timer: 16:42
 * Author: nedhuo
 * Description:
 */
@FragmentScope
@Component(modules = DisCoverModule.class, dependencies = AppComponent.class)
public interface DisCoverComponent {
    void inject(DisCoverFragment fragment);
}
