package com.hngg.jianshi.ui.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hngg.jianshi.R;
import com.hngg.jianshi.utils.StatusBarUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.flowLayout_history)
    TagFlowLayout mFl_history;

    @BindView(R.id.flowLayout_recommend)
    TagFlowLayout mFl_recommend;

    @BindView(R.id.tv_cancel)
    TextView mTv_cancel;
    @BindView(R.id.et_input)
    EditText mEt_input;
    @BindView(R.id.tv_deleteHistory)
    TextView mTv_deleteHistory;
    private String[] mRecommendArray;

    private Activity mCtx = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initData();
    }

    private void initData() {
        StatusBarUtil.setFontColor(getWindow(), getColor(R.color.color_statusBar_font));

        mRecommendArray = getResources().getStringArray(R.array.recommend_search);
        /*TODO 数据库查询搜索记录数据 取十个或者二十个 时间排序*/

        mFl_history.setAdapter(new TagAdapter<String>(mRecommendArray) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(mCtx)
                        .inflate(R.layout.item_history_flow,
                                parent, false);
                ((TextView) view).setText(s);
                return view;
            }
        });

        mFl_history.setOnTagClickListener((view, position, parent) -> {
            Toast.makeText(this, mRecommendArray[position], Toast.LENGTH_SHORT).show();
            return true;
        });

        mFl_recommend.setAdapter(new TagAdapter<String>(mRecommendArray) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(mCtx)
                        .inflate(R.layout.item_recommend_flow,
                                parent, false);
                ((TextView) view).setText(s);
                return view;
            }
        });

        mFl_recommend.setOnTagClickListener((view, position, parent) -> {
            Toast.makeText(this, mRecommendArray[position], Toast.LENGTH_SHORT).show();
            return true;
        });

        mTv_cancel.setOnClickListener(v -> onBackPressed());
    }
}
