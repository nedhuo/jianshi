package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.home.recommend.RecommendFragment;
import com.hngg.jianshi.ui.home.recommend.RecommendModule;
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
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}