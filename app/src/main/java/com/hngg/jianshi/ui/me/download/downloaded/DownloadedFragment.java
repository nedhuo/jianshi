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
import com.hngg.jianshi.base.BaseFragment;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadedFragment extends BaseFragment {

    @BindView(R.id.rv_downloaded)
    RecyclerView rvDownloaded;
    private List<VideoTaskInfo> mDataList;
    private DownloadedAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_downloaded, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mDataList = new ArrayList<>();
        ButterKnife.bind(this, view);
        List<VideoTaskInfo> taskInfoList = DbManager.getInstance(getActivity())
                .getVideoTaskDao().queryAllComplete();
        mDataList.addAll(taskInfoList);
        mAdapter = new DownloadedAdapter(getActivity(), taskInfoList);

        rvDownloaded.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDownloaded.setAdapter(mAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        List<VideoTaskInfo> videoTaskInfos = DbManager.getInstance(getActivity())
                .getVideoTaskDao().queryAllComplete();
        mAdapter.setData(videoTaskInfos, true);
        super.setUserVisibleHint(isVisibleToUser);
    }

    public void setDeleteEditState(boolean isDeleteState) {

    }
}
