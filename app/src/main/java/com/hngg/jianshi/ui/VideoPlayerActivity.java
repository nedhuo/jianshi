package com.hngg.jianshi.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseActivity;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.utils.PlayerInfoUtil;
import com.hngg.jianshi.widget.CustomVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPlayerActivity extends BaseActivity {
    @BindView(R.id.viewPlayer)
    CustomVideoPlayer viewPlayer;
    private PlayerInfoUtil mPlayerInfoDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        ButterKnife.bind(this);
        mPlayerInfoDao = DbManager.getInstance(this).getPlayerInfoDao();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
}
