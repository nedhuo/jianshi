package com.hngg.jianshi.ui.tag;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hngg.jianshi.R;
import com.hngg.jianshi.component.DaggerTagDetailComponent;
import com.hngg.jianshi.data.bean.taginfo.TagInfoBean;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class TagDetailActivity extends BaseActivity<TagDetailPresenter>
        implements TagDetailContract.View {
    private long mTagId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTagDetailComponent
                .builder()
                .appComponent(appComponent)
                .tagDetailModule(new TagDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_tagdetail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            LogUtil.i(TAG, "mPresenter为null");
            return;

        }
        /*接收数据*/
        String userType = "PGC";
        Bundle bundleExtra = getIntent().getBundleExtra(Constant.TAGDETAIL_BUNDLE);
        if (bundleExtra != null) {
            mTagId = bundleExtra.getLong(Constant.TAGDETAIL_BEAN);
            if (mTagId == -1) {
                LogUtil.e(TAG, "接收数据为null");
                return;
            }
        } else {
            LogUtil.e(TAG, "接收数据为null");
            return;
        }

        mPresenter.initData(mTagId);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void setTabInfo(TagInfoBean.TabInfo tabInfo) {

    }
}
