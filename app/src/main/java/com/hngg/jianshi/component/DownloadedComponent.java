package com.hngg.jianshi.component;

import com.hngg.jianshi.ui.me.download.downloaded.DownloadedContract;
import com.hngg.jianshi.ui.me.download.downloaded.DownloadedFragment;
import com.hngg.jianshi.ui.me.download.downloaded.DownloadedModule;
import com.hngg.jianshi.ui.me.download.downloading.DownloadingFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */

@FragmentScope
@Component(modules = DownloadedModule.class, dependencies = AppComponent.class)
public interface DownloadedComponent {
    void inject(DownloadedContract.View rootView);
}
