package com.hngg.jianshi.ui.user.works;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserInfo_WorksModule {
    private UserInfo_WorksFragment itemView;

    public UserInfo_WorksModule(UserInfo_WorksFragment view) {
        this.itemView = view;
    }

    @FragmentScope
    @Provides
    UserInfo_WorksFragment provideUserInfo_WorksView() {
        return this.itemView;
    }
}