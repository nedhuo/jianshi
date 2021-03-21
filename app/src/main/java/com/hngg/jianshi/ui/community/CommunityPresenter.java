package com.hngg.jianshi.ui.community;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
    private Disposable mDisposable;

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

    public void initRecyclerView() {
        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        CommunityAdapter communityAdapter = new CommunityAdapter(mRootView.getActivity());
        RecyclerViewWrapper adapterWrapper =
                new RecyclerViewWrapper(communityAdapter, mRootView.getActivity());
        mRootView.initRecyclerView(manager, adapterWrapper);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose();
    }

}
