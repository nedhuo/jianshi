package com.hngg.jianshi.ui.me.playinfo;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseActivity;

import butterknife.ButterKnife;

public class PlayInfoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playinfo);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
