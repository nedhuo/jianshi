package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.home.HomeModule;
import com.hngg.jianshi.ui.me.download.DownloadActivity;
import com.hngg.jianshi.ui.me.download.DownloadModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import org.jetbrains.annotations.Contract;

import dagger.Component;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
@ActivityScope
@Component(modules = DownloadModule.class, dependencies = AppComponent.class)
public interface DownloadComponent {
    void inject(DownloadActivity rootView);
}
