package com.hngg.jianshi.ui.tag;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
@Module
public class TagDetailModule {
    private TagDetailContract.View view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public TagDetailModule(TagDetailActivity view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TagDetailContract.View provideTagDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TagDetailContract.Model provideTagDetailModel(TagDetailModel model) {
        return model;
    }
}
