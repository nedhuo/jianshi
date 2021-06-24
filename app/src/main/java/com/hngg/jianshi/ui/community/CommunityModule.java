package com.hngg.jianshi.ui.community;

import com.hngg.jianshi.ui.home.recommend.RecommendContract;
import com.hngg.jianshi.ui.home.recommend.RecommendModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 2021/2/20
 * Timer: 9:11
 * Author: nedhuo
 * Description:
 */
@Module
public class CommunityModule {
    private CommunityContract.View view;

    CommunityModule(CommunityContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public CommunityContract.View provideCommunityView() {
        return view;
    }


    @Provides
    @FragmentScope
    public CommunityContract.Model provideCommunityModel(CommunityModel model) {
        return model;
    }
}