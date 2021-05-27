package com.hngg.jianshi.ui.home.recommend;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.hngg.jianshi.data.bean.recommend.RecommendRootBean;
import com.hngg.jianshi.ui.adapter.RecommendAdapter;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Date: 2020/11/19
 * Timer: 16:18
 * Author: nedhuo
 * Description:
 */
public class RecommendPresent extends BasePresenter<RecommendContract.Model, RecommendContract.View> {
    private RecommendModel mModel = (RecommendModel) super.mModel;
    private RecommendFragment mRootView = (RecommendFragment) super.mRootView;
    private String mNextPageUrl = "";
    private final String TAG = "RecommendPresent";

    @Inject
    public RecommendPresent(RecommendContract.Model model, RecommendContract.View rootView) {
        super(model, rootView);
    }


    public void onRefresh() {
        mModel.getRecommendData()
                .subscribe(new BaseObserver<RecommendRootBean>() {
                    @Override
                    protected void onSuccess(RecommendRootBean rootBean) {
                        mNextPageUrl = rootBean.getNextPageUrl();
                        mRootView.setData(rootBean.getItemList(), true);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        LogUtil.e(TAG, "推荐页面数据加载失败" + e.getMessage());
                        mRootView.setData(null, true);
                    }
                });

    }

    public void onLoadMore() {
        if (!mNextPageUrl.equals("")) {
            mModel.getRecommendNextPage(mNextPageUrl).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<RecommendRootBean>() {
                        @Override
                        protected void onSuccess(RecommendRootBean rootBean) {
                            mNextPageUrl = rootBean.getNextPageUrl();
                            mRootView.setData(rootBean.getItemList(), false);
                        }

                        @Override
                        public void onFail(Throwable e) {
                            LogUtil.e(TAG, "推荐页面数据加载失败" + e.getMessage());
                            mRootView.setData(null, false);
                        }
                    });
        } else {
            LogUtil.e(TAG, "推荐页面无更多数据");
            mRootView.setData(null, false);
        }
    }


    public void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRootView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecommendAdapter adapter = new RecommendAdapter(mRootView.getActivity());
        mRootView.initRecyclerView(layoutManager, adapter);
    }
}
