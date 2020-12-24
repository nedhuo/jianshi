package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.home.daily.DailyFragment;
import com.hngg.jianshi.ui.home.daily.DailyModel;
import com.hngg.jianshi.ui.home.daily.DailyModule;
import com.hngg.jianshi.ui.home.daily.DailyScope;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.module.AppModule;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Date: 2020/11/19
 * Timer: 19:41
 * Author: nedhuo
 * Description:
 */

@DailyScope
@Component(modules = {DailyModule.class},dependencies = {AppComponent.class})
public interface DailyComponent {
    void inject(DailyFragment fragment);
}
