package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.tag.TagDetailActivity;
import com.hngg.jianshi.ui.tag.TagDetailModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
@ActivityScope
@Component(modules = TagDetailModule.class, dependencies = AppComponent.class)
public interface TagDetailComponent {
    void inject(TagDetailActivity activity);
}
