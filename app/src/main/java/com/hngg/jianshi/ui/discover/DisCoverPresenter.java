package com.hngg.jianshi.ui.discover;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.data.bean.discover.DisCoverRootBean;
import com.hngg.jianshi.ui.adapter.DisCoverAdapter;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.network.Observer.BaseObserver;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;


/**
 * Date: 2020/11/19
 * Timer: 16:46
 * Author: nedhuo
 * Description:
 */
public class DisCoverPresenter extends BasePresenter<DisCoverContract.Model, DisCoverContract.View> {
    private DisCoverModel mModel = (DisCoverModel) super.mModel;
    private DisCoverFragment mRootView = (DisCoverFragment) super.mRootView;

    @Inject
    DisCoverPresenter(DisCoverContract.Model model, DisCoverContract.View rootView) {
        super(model, rootView);
    }

    void getCommunityData() {
        mModel.getDisCoverData()
                .subscribe(new BaseObserver<DisCoverRootBean>() {
                    @Override
                    protected void onSuccess(DisCoverRootBean o) {
                        mRootView.setData(o.getItemList(), true, false);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        LogUtil.e(TAG, e.getMessage());
                        mRootView.setData(null, true, false);
                    }
                });
    }


    public void onLoadMore() {
        mRootView.setData(null, false, true);
    }

    public void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRootView.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        DisCoverAdapter adapter = new DisCoverAdapter(mRootView.getActivity());
        mRootView.initRecyclerView(adapter, layoutManager);
    }

}
