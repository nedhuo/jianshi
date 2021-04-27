package com.hngg.jianshi.ui.me.download.downloaded;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;
import com.hngg.jianshi.data.datebase.DbManager;
import com.hngg.jianshi.utils.LogUtil;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadedFragment extends BaseFragment<DownloadedPresenter>
        implements DownloadedContract.View {

    @BindView(R.id.rv_downloaded)
    RecyclerView rvDownloaded;
    private List<VideoTaskInfo> mDataList;
    private DownloadedAdapter mAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerDownloadedComponent
//                .builder()
//                .appComponent(appComponent)
//                .downloadedModule(new DownloadedModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_downloaded, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            LogUtil.i(TAG, "mPresenter为null");
            return;
        }
        mDataList = new ArrayList<>();
        List<VideoTaskInfo> taskInfoList = DbManager.getInstance(mContext)
                .getVideoTaskDao().queryAllComplete();
        mDataList.addAll(taskInfoList);
        mAdapter = new DownloadedAdapter(mContext, taskInfoList);

        rvDownloaded.setLayoutManager(new LinearLayoutManager(mContext));
        rvDownloaded.setAdapter(mAdapter);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }


}
