package com.hngg.jianshi.ui.me.download.downloading;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public interface DownloadingContract {
    interface View extends IView {
    }
    //Model 层定义接口, 外部只需关心 Model 返回的数据, 无需关心内部细节, 即是否使用缓存
    interface Model extends IModel {
    }
}
