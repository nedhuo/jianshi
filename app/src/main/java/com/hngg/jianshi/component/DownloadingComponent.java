package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.me.download.downloading.DownloadingContract;
import com.hngg.jianshi.ui.me.download.downloading.DownloadingFragment;
import com.hngg.jianshi.ui.me.download.downloading.DownloadingModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */

@FragmentScope
@Component(modules = DownloadingModule.class, dependencies = AppComponent.class)
public interface DownloadingComponent {
    void inject(DownloadingContract.View rootView);
}
