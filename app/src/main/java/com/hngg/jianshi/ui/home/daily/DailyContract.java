package com.hngg.jianshi.ui.home.daily;

import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

/**
 * Date: 2020/11/19
 * Timer: 19:37
 * Author: nedhuo
 * Description:这里根据 Google 官方的 MVP 架构,可以在 Contract 中定义 MVP 类的接口, 便于管理,
 * 本框架无需定义 Presenter 接口, 所以在 Contract 中只定义 View 和 Model 的接口
 *
 *
 */
public interface DailyContract {
    //对于经常在日常开发中使用到的关于 UI 的方法可以定义到 IView 中, 如显示隐藏进度条, 和显示文字消息
    interface View extends IView{

        void initRecyclerView(RecyclerView.Adapter adapter);
    }

    //Model 层定义接口, 外部只需关心 Model 返回的数据, 无需关心内部细节, 即是否使用缓存
    interface Model extends IModel{}
}
