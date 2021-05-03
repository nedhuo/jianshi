package com.hngg.jianshi;

import com.arialyy.aria.core.Aria;
import com.hngg.jianshi.widget.GlideImageLoader;
import com.jess.arms.base.BaseApplication;
import com.lzy.ninegrid.NineGridView;

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
        NineGridView.setImageLoader(new GlideImageLoader());
    }




}
