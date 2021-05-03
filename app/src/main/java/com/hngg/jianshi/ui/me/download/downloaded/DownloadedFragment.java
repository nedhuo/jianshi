package com.hngg.jianshi.ui.me.download.downloaded;

import android.app.Activity;
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
    private Activity mContext = getActivity();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_downloaded, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mDataList = new ArrayList<>();
        List<VideoTaskInfo> taskInfoList = DbManager.getInstance(mContext)
                .getVideoTaskDao().queryAllComplete();
        mDataList.addAll(taskInfoList);
        mAdapter = new DownloadedAdapter(mContext, taskInfoList);

        rvDownloaded.setLayoutManager(new LinearLayoutManager(mContext));
        rvDownloaded.setAdapter(mAdapter);
    }





}
