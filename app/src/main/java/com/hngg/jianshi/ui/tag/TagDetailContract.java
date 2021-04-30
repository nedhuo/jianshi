package com.hngg.jianshi.ui.tag;

import com.hngg.jianshi.data.bean.taginfo.TagInfoBean;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class TagDetailContract {
    interface View extends IView{
        void setTabInfo(TagInfoBean.TabInfo tabInfo);
    }
    interface Model extends IModel {
        Observable<TagInfoBean> onRefresh(long tagId);
    }
}
