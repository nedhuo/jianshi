package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.community.CommunityFragment;
import com.hngg.jianshi.ui.community.CommunityModule;
import com.hngg.jianshi.ui.home.recommend.RecommendFragment;
import com.hngg.jianshi.ui.home.recommend.RecommendModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Date: 2021/2/20
 * Timer: 9:14
 * Author: nedhuo
 * Description:
 */
@FragmentScope
@Component(modules = CommunityModule.class, dependencies = AppComponent.class)
public interface CommunityComponent {
    void inject(CommunityFragment fragment);
}