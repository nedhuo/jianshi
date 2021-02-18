package com.hngg.jianshi.ui.home.daily;

import android.util.Log;


import com.hngg.jianshi.data.ApiInterface;
import com.hngg.jianshi.data.KaiYanHttpUtil;
import com.hngg.jianshi.data.bean.home.DailyRootBean;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.network.KaiYanApi;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Date: 2020/11/19
 * Timer: 19:45
 * Author: nedhuo
 * Description:
 * EventBus完成Model与Presenter之间数据通信
 * Model层主要
 */
public class DailyModel extends BaseModel implements DailyContract.Model {

    private static final String TAG = "DailyModel";
    private KaiYanHttpUtil httpUtil;
    private String mNextPageUrl = "";
    private List<ItemList> mItemDatas;


    /**
     * repositoryManager
     */
    @Inject
    public DailyModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        httpUtil = new KaiYanHttpUtil();
    }

    /**
     * 明确任务
     * 目的：请求网络数据，显示到App页面上
     */

    public Observable<DailyRootBean> refresh() {
        return httpUtil.getService(ApiInterface.class)
                .getDailyData()
                .compose(httpUtil.applySchedulers());
    }

    public Observable<DailyRootBean> loadMore(String urlPath) {
        //处理baseUrl重复问题
        if (urlPath.contains(KaiYanApi.baseUrl)) {
            urlPath = urlPath.replace(KaiYanApi.baseUrl, "");
        }
        return httpUtil.getService(ApiInterface.class)
                .getDailyNextPage(urlPath)
                .compose(httpUtil.applySchedulers());
    }
}
