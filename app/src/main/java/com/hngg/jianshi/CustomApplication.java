package com.hngg.jianshi;

import com.arialyy.aria.core.Aria;
import com.jess.arms.base.BaseApplication;

/**
 * Date: 2020/11/23
 * Timer: 13:32
 * Author: nedhuo
 * Description:
 */
public class CustomApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Aria.download(this).register();
    }




}
