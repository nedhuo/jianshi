package com.hngg.jianshi.ui.me.collection;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseActivity;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.bean.CollectionInfo;
import com.hngg.jianshi.data.database.utils.CollectionInfoUtil;
import com.hngg.jianshi.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionActivity extends BaseActivity {
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.rv_collection)
    RecyclerView rvCollection;
    private CollectionAdapter mAdapter;
    private CollectionInfoUtil mCollectionDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void initData() {
        mCollectionDao = DbManager.getInstance(this).getCollectionInfoDao();
    }

    @Override
    protected void initView() {
        ibBack.setOnClickListener(v -> onBackPressed());

        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvCollection.setLayoutManager(manager);
        mAdapter = new CollectionAdapter(this);
        rvCollection.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        List<CollectionInfo> collectionInfos = mCollectionDao.queryAll();
        mAdapter.setData(collectionInfos, true);
        LogUtil.i(TAG, "数据长度" + collectionInfos.size());
    }
}
