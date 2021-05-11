package com.hngg.jianshi.ui.me.history;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseActivity;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.bean.HistoryInfo;
import com.hngg.jianshi.data.database.utils.HistoryInfoUtil;
import com.hngg.jianshi.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    private HistoryInfoUtil mHistoryDao;
    private HistoryAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void initData() {
        mHistoryDao = DbManager.getInstance(this).getHistoryInfoDao();
    }

    @Override
    protected void initView() {
        ibBack.setOnClickListener(v -> onBackPressed());

        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvHistory.setLayoutManager(manager);
        mAdapter = new HistoryAdapter(this);
        rvHistory.setAdapter(mAdapter);

        tvDelete.setOnClickListener(v -> {
            //TODO 删除历史纪录
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<HistoryInfo> historyInfos = mHistoryDao.queryAll();
        mAdapter.setData(historyInfos, true);
        LogUtil.i(TAG, "数据长度" + historyInfos.size());
    }
}
