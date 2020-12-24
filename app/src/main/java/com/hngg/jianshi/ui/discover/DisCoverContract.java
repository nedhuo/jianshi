package com.hngg.jianshi.ui.discover;

import androidx.viewpager.widget.PagerAdapter;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

/**
 * Date: 2020/11/19
 * Timer: 16:47
 * Author: nedhuo
 * Description:
 */
public interface DisCoverContract {

    interface View extends IView {
    }

    //Model 层定义接口, 外部只需关心 Model 返回的数据, 无需关心内部细节, 即是否使用缓存
    interface Model extends IModel {
    }
}
