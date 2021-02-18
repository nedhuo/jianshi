package com.hngg.jianshi.ui.home.recommend;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.hngg.jianshi.data.bean.recommend.RecommendRootBean;
import com.hngg.jianshi.ui.adapter.RecommendAdapter;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;


import javax.inject.Inject;


import io.reactivex.android.schedulers.AndroidSchedulers;

import timber.log.Timber;

/**
 * Date: 2020/11/19
 * Timer: 16:18
 * Author: nedhuo
 * Description:
 */
public class RecommendPresent extends BasePresenter<RecommendContract.Model, RecommendContract.View> {
    RecommendModel mModel = (RecommendModel) super.mModel;
    RecommendFragment mRootView = (RecommendFragment) super.mRootView;
    private String mNextPageUrl;
    private final String TAG="RecommendPresent";

    @Inject
    public RecommendPresent(RecommendContract.Model model, RecommendContract.View rootView) {
        super(model, rootView);
    }

    public void obtainRecommendData() {
        mModel.getRecommendData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<RecommendRootBean>() {
                    @Override
                    protected void onSuccess(RecommendRootBean rootBean) {
                        mNextPageUrl = rootBean.getNextPageUrl();
                        mRootView.setData(rootBean.getItemList(), true);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Timber.e(e,TAG);
                    }
                });
    }

    public void obtainRecommendNextPage() {
        if (!mNextPageUrl.equals("") && mNextPageUrl != null) {
            mModel.getRecommendNextPage(mNextPageUrl).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<RecommendRootBean>() {
                        @Override
                        protected void onSuccess(RecommendRootBean rootBean) {
                            mNextPageUrl = rootBean.getNextPageUrl();
                            mRootView.setData(rootBean.getItemList(), false);
                        }

                        @Override
                        public void onFail(Throwable e) {
                            Timber.e(e, TAG);
                        }
                    });
        } else {
            mRootView.mRefreshLayout.setNoMoreData(true);
        }

    }

    public void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRootView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecommendAdapter adapter = new RecommendAdapter(mRootView.getActivity());
        mRootView.initRecyclerView(layoutManager, adapter);
    }
}
