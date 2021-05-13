package com.hngg.jianshi.ui.me.playinfo;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseActivity;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.bean.PlayInfo;
import com.hngg.jianshi.data.database.utils.PlayerInfoUtil;
import com.hngg.jianshi.ui.adapter.PlayInfoAdapter;
import com.hngg.jianshi.widget.PlayInfoCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayInfoActivity extends BaseActivity implements PlayInfoCallBack {
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.rv_playerInfo)
    RecyclerView rvPlayerInfo;
    private PlayerInfoUtil mPlayerInfoDao;
    private List<PlayInfo> mPlayInfoList;
    private List<PlayInfo> mDeleteList;
    private PlayInfoAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playinfo);
        ButterKnife.bind(this);
        mDeleteList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void initData() {
        mPlayerInfoDao = DbManager.getInstance(this).getPlayerInfoDao();
        mPlayInfoList = mPlayerInfoDao.queryAll();
    }

    @Override
    protected void initView() {
        ibBack.setOnClickListener(v -> onBackPressed());


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvPlayerInfo.setLayoutManager(layoutManager);
        mAdapter = new PlayInfoAdapter(this);
        rvPlayerInfo.setAdapter(mAdapter);
        mAdapter.setData(mPlayInfoList, true);

        tvDelete.setOnClickListener(v -> {
            if (mAdapter.mIsDeleteState) {
                mAdapter.setEditDelete(false);
                mDeleteList.clear();
            } else {
                mAdapter.setEditDelete(true);
            }
        });
    }

    @Override
    public void onDeleteListChange(PlayInfo playInfo, boolean isChecked) {
        if (mDeleteList.contains(playInfo)) {
            if (!isChecked) {
                mDeleteList.remove(playInfo);
            }
        } else {
            if (isChecked) {
                mDeleteList.add(playInfo);
            }
        }

    }


}
