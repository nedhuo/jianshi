package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.home.HomeFragment;
import com.hngg.jianshi.ui.recommend.RecommendFragment;
import com.hngg.jianshi.ui.recommend.RecommendModule;
import com.hngg.jianshi.ui.recommend.RecommendScope;
import com.jess.arms.di.component.AppComponent;

import dagger.Component;

/**
 * Date: 2020/11/19
 * Timer: 16:42
 * Author: nedhuo
 * Description:
 */
@RecommendScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}