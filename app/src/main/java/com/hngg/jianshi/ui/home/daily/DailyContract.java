package com.hngg.jianshi.ui.home.daily;

import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

/**
 * Date: 2020/11/19
 * Timer: 19:37
 * Author: nedhuo
 * Description:
 */
public interface DailyContract {
    interface View extends IView{

        void initRecyclerView(RecyclerView.Adapter adapter);
    }
    interface Model extends IModel{}
}
