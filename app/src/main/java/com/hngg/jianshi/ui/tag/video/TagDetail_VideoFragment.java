package com.hngg.jianshi.ui.tag.video;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.utils.LogUtil;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TagDetail_VideoFragment extends Fragment {
    @BindView(R.id.classicsHeader)
    ClassicsHeader mClassicsHeader;
    @BindView(R.id.rv_tagVideos)
    RecyclerView rvTagVideos;
    @BindView(R.id.classicsFooter)
    ClassicsFooter mClassicsFooter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private Handler mHandler;
    private static final String TAG = "TagDetail_VideoFragment";
    private List<ItemList> mDataList;
    private TagDetailVideosAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tag_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        mRefreshLayout.setRefreshHeader(mClassicsHeader);
        mRefreshLayout.setRefreshFooter(mClassicsFooter);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            mHandler.sendEmptyMessage(101);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            mHandler.sendEmptyMessage(102);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTagVideos.setLayoutManager(layoutManager);
        mAdapter = new TagDetailVideosAdapter(getActivity(), mDataList);

        rvTagVideos.setAdapter(mAdapter);
    }

    /**
     * header
     * content
     */
    public void setData(List<ItemList> itemList, boolean isUpdate) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        if (isUpdate) {
            if (mRefreshLayout != null) {
                mRefreshLayout.finishRefresh();
            }
            mDataList.clear();
        } else {
            if (mRefreshLayout != null) {
                mRefreshLayout.finishLoadMore();
            }
        }
        if (itemList == null) {
            LogUtil.e(TAG, "数据为null");
            return;
        }
        mDataList.addAll(itemList);
        if (mAdapter != null) {
            mAdapter.setData(mDataList);
        }
    }

    public void setHandler(Handler handler) {
        mHandler = handler;
    }
}