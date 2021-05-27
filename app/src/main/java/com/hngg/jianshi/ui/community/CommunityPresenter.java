package com.hngg.jianshi.ui.community;

import com.hngg.jianshi.data.bean.community.CommunityRootBean;
import com.hngg.jianshi.ui.adapter.CommunityAdapter;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

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
    private Disposable mDisposable;
    private CommunityAdapter mCommunityAdapter;

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
                    .subscribe(new BaseObserver<CommunityRootBean>() {
                        @Override
                        protected void onSuccess(CommunityRootBean rootBean) {
                            mNextPageUrl = rootBean.getNextPageUrl();
                            mRootView.setData(rootBean.getItemList(), true, false);
                        }

                        @Override
                        public void onFail(Throwable e) {
                            LogUtil.e(TAG, "数据错误" + e.getMessage());
                            mRootView.setData(null, true, false);
                        }
                    });
        }
    }

    public void onLoadMore() {
        if (!mNextPageUrl.equals("")) {
            mModel.getCommunityNextData(mNextPageUrl)
                    .subscribe(new BaseObserver<CommunityRootBean>() {
                        @Override
                        protected void onSuccess(CommunityRootBean rootBean) {
                            mNextPageUrl = rootBean.getNextPageUrl();
                            mRootView.setData(rootBean.getItemList(), false, false);
                        }

                        @Override
                        public void onFail(Throwable e) {
                            LogUtil.e(TAG, "数据错误" + e.getMessage());
                            mRootView.setData(null, false, false);
                        }
                    });
        } else {
            mRootView.setData(null, false, true);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose();
    }

}
