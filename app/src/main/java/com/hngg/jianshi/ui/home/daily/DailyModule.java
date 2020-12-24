package com.hngg.jianshi.ui.home.daily;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 2020/11/19
 * Timer: 19:42
 * Author: nedhuo
 * Description:
 *      @Module Module类注解
 *      @DailyScope 自定义范围，具体作用范围不太了解
 *      @Provides  提供对象注解
 */
@Module
public class DailyModule {
    private final DailyFragment view;

    DailyModule(DailyContract.View view){
        this.view= (DailyFragment) view;
    }

    @DailyScope
    @Provides
    DailyContract.View provideDailyView() {
        return view;
    }

    @DailyScope
    @Provides
    DailyContract.Model provideDailyModel(DailyModel model) {
        return model;
    }
}
