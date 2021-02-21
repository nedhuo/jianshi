package com.hngg.jianshi.ui.community;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hngg.jianshi.data.bean.community.CommunityRootBean;
import com.hngg.jianshi.ui.adapter.CommunityAdapter;
import com.hngg.jianshi.ui.adapter.RecyclerViewWrapper;
import com.hngg.jianshi.ui.home.recommend.RecommendFragment;
import com.hngg.jianshi.ui.home.recommend.RecommendModel;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.mvp.IPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Date: 2021/2/20
 * Timer: 9:01
 * Author: nedhuo
 * Description:
 */
public class CommunityPresenter extends BasePresenter
        <CommunityContract.Model, CommunityContract.View> {
    CommunityModel mModel = (CommunityModel) super.mModel;
    CommunityFragment mRootView = (CommunityFragment) super.mRootView;
    private String mNextPageUrl;
    private final String TAG = "CommunityPresent";

    @Inject
    public CommunityPresenter(CommunityContract.Model model, CommunityContract.View rootView) {
        super(model, rootView);
    }

    /**
     * @param isUpdate 是否刷新 加载首条数据时 isUpdate==true
     */
    public void getCommunityData(boolean isUpdate) {
        if (isUpdate) {
            mModel.getCommunityData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<CommunityRootBean>() {
                        @Override
                        protected void onSuccess(CommunityRootBean rootBean) {
                            mNextPageUrl = rootBean.getNextPageUrl();
                            mRootView.setData(rootBean.getItemList(), true);
                        }

                        @Override
                        public void onFail(Throwable e) {
                            Timber.e(e, TAG);
                        }
                    });
        } else if (!mNextPageUrl.equals("") && mNextPageUrl != null) {
            mModel.getCommunityNextData(mNextPageUrl)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<CommunityRootBean>() {
                        @Override
                        protected void onSuccess(CommunityRootBean rootBean) {
                            mNextPageUrl = rootBean.getNextPageUrl();
                            mRootView.setData(rootBean.getItemList(), false);
                        }

                        @Override
                        public void onFail(Throwable e) {
                            Timber.e(e, TAG);
                        }
                    });
        } else {
            mRootView.notifyNoData();
        }

    }

    public void initRecyclerView() {
        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        CommunityAdapter communityAdapter = new CommunityAdapter(mRootView.getActivity());
        RecyclerViewWrapper adapterWrapper =
                new RecyclerViewWrapper(communityAdapter, mRootView.getActivity());
        mRootView.initRecyclerView(manager, adapterWrapper);
    }
}
