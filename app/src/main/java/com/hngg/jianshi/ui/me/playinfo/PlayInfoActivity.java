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
import com.hngg.jianshi.utils.StatusBarUtil;
import com.hngg.jianshi.widget.BottomDeleteDialog;
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
    private boolean mIsDeleteState = false;
    private BottomDeleteDialog mDeleteDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setFontColor(getWindow(), getColor(R.color.color_statusBar_font));
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
            if (mIsDeleteState) {
                mIsDeleteState = false;
                mAdapter.setEditDelete(false);
                mDeleteList.clear();
                openDeleteDialog(false);
            } else {
                mIsDeleteState = true;
                mAdapter.setEditDelete(true);
                openDeleteDialog(true);
            }
        });
    }

    @Override
    public void onDeleteListChange(PlayInfo playInfo, boolean isChecked) {
        if (!mIsDeleteState) {
            //如果当前状态为非删除编辑状态
            mIsDeleteState = true;
            mAdapter.setEditDelete(true);
            openDeleteDialog(true);
        }
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

    @Override
    protected void onPause() {
        super.onPause();

        mIsDeleteState = false;
        mDeleteList.clear();
        mAdapter.setEditDelete(false);
        openDeleteDialog(false);
    }

    public void openDeleteDialog(boolean isEdit) {
        if (mDeleteDialog == null) {
            mDeleteDialog = new BottomDeleteDialog(this) {
                @Override
                public void onDeleteListener() {
                    if (mDeleteList != null && mDeleteList.size() > 0) {
                        mPlayerInfoDao.deleteList(mDeleteList);
                        mPlayInfoList = mPlayerInfoDao.queryAll();
                        mAdapter.setData(mPlayInfoList, true);
                    }
                }

                @Override
                public void onCancelListener() {
                    mDeleteList.clear();
                    mIsDeleteState = false;
                    mAdapter.setEditDelete(false);
                }
            };
        }
        if (isEdit) {
            mDeleteDialog.show();
        } else {
            mDeleteDialog.dismiss();
        }
    }
}
