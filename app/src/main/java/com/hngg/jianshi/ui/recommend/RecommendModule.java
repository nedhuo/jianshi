package com.hngg.jianshi.ui.recommend;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 2020/11/19
 * Timer: 16:55
 * Author: nedhuo
 * Description:
 */
@Module
public class RecommendModule {
    private RecommendContract.View view;

    RecommendModule(RecommendContract.View view) {
        this.view = view;
    }

    @Provides
    @RecommendScope
    public RecommendContract.View provideRecommendView() {
        return view;
    }


    @Provides
    @RecommendScope
    public RecommendContract.Model provideRecommendModel(RecommendModel model) {
        return model;
    }
}