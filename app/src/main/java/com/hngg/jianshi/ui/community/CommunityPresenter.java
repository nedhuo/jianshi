package com.hngg.jianshi.ui.community;

import com.hngg.jianshi.data.bean.community.CommunityRootBean;
import com.hngg.jianshi.ui.adapter.CommunityAdapter;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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

            mDisposable = mModel.getCommunityData()
                    .subscribe(new Consumer<CommunityRootBean>() {
                        @Override
                        public void accept(CommunityRootBean rootBean) throws Exception {
                            mNextPageUrl = rootBean.getNextPageUrl();
                            mRootView.setData(rootBean.getItemList(), true);
                        }
                    });
        } else if (!mNextPageUrl.equals("") && mNextPageUrl != null) {
            mDisposable = mModel.getCommunityNextData(mNextPageUrl)
                    .subscribe(new Consumer<CommunityRootBean>() {
                        @Override
                        public void accept(CommunityRootBean rootBean) throws Exception {
                            mNextPageUrl = rootBean.getNextPageUrl();
                            mRootView.setData(rootBean.getItemList(), false);
                        }
                    });
        } else {
            mRootView.notifyNoData();
        }

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose();
    }

}
