package com.hngg.jianshi.ui.discover;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.data.bean.discover.DisCoverRootBean;
import com.hngg.jianshi.ui.adapter.DisCoverAdapter;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Date: 2020/11/19
 * Timer: 16:46
 * Author: nedhuo
 * Description:
 */
public class DisCoverPresenter extends BasePresenter<DisCoverContract.Model, DisCoverContract.View> {
    DisCoverModel mModel = (DisCoverModel) super.mModel;
    DisCoverFragment mRootView = (DisCoverFragment) super.mRootView;
    private final String TAG = "DisCoverPresenter";
    private Disposable mDisposable;

    @Inject
    DisCoverPresenter(DisCoverContract.Model model, DisCoverContract.View rootView) {
        super(model, rootView);
    }

    public void getCommunityData(boolean isUpdate) {

        mDisposable = mModel.getDisCoverData()
                .subscribe(new Consumer<DisCoverRootBean>() {
                    @Override
                    public void accept(DisCoverRootBean rootBean) throws Exception {
                        mRootView.setData(rootBean.getItemList(), isUpdate);
                    }
                });
    }

    public void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRootView.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        DisCoverAdapter adapter = new DisCoverAdapter(mRootView.getActivity());
        mRootView.initRecyclerView(adapter, layoutManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
