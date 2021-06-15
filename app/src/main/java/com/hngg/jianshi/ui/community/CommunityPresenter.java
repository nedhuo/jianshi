package com.hngg.jianshi.ui.community;

import com.blankj.utilcode.util.ToastUtils;
import com.hngg.jianshi.data.bean.community.CommunityRootBean;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

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


    public void getCommunityData() {
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
                        ToastUtils.showShort(e.getMessage());
                    }
                });
    }

    public void onLoadMore() {
        if (mNextPageUrl != null && !mNextPageUrl.equals("")) {
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

}
