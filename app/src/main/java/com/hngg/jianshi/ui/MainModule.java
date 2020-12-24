package com.hngg.jianshi.ui;

import com.jess.arms.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

/**
 * Date: 2020/11/18
 * Timer: 19:14
 * Author: nedhuo
 * Description:
 *      Module 可以提供当前业务逻辑所对应的 View 和 Model 接口 (Contract 中定义的接口) 的实现类,
 *      Model 需要 AppComponent 中提供的 RepositoryManager 来实现网络请求和数据缓存, 所以需要通过
 *      Component 依赖 AppComponent 来拿到这个对象
 */
@Module
public class MainModule {
    private MainActivity view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public MainModule(MainActivity view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainActivity provideMainView() {
        return this.view;
    }
}
