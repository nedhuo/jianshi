package com.hngg.jianshi.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseActivity;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.bean.SearchInfo;
import com.hngg.jianshi.data.database.utils.SearchInfoUtil;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.jianshi.utils.StatusBarUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements TextView.OnEditorActionListener {
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

    private Activity mCtx = this;
    private SearchPresenter mPresenter;
    private SearchInfoUtil mSearchInfoDao;
    private List<String> mSearchList;
    private List<String> mRecommendList;
    private String mSearchField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setFontColor(getWindow(), getColor(R.color.color_statusBar_font));
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mPresenter = new SearchPresenter(this);
        mSearchList = new ArrayList<>();
        mSearchInfoDao = DbManager.getInstance(this).getSearchInfoDao();
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<SearchInfo> searchInfos = mSearchInfoDao.queryAll();
        mSearchList.clear();
        for (SearchInfo searchInfo : searchInfos) {
            mSearchList.add(searchInfo.getSearchField());
        }
        mFl_history.setAdapter(new TagAdapter<String>(mSearchList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(mCtx)
                        .inflate(R.layout.item_history_flow,
                                parent, false);
                ((TextView) view).setText(s);
                return view;
            }
        });

    }

    @Override
    protected void initView() {

        mFl_history.setOnTagClickListener((view, position, parent) -> {
            mEt_input.setText(mSearchList.get(position));
            //   Toast.makeText(this, mSearchList.get(position), Toast.LENGTH_SHORT).show();
            mSearchField = mSearchList.get(position);
            //  mPresenter.onRefresh(mSearchList.get(position));
            jumpPage(mSearchList.get(position));
            return true;
        });

        mFl_recommend.setAdapter(new TagAdapter<String>(mRecommendList) {
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
            mEt_input.setText(mRecommendList.get(position));
            mSearchField = mRecommendList.get(position);
            //    mPresenter.onRefresh(mRecommendList.get(position));
            jumpPage(mRecommendList.get(position));
            return true;
        });

        mTv_cancel.setOnClickListener(v -> onBackPressed());

        mEt_input.setOnEditorActionListener(this);
    }

    @Override
    protected void initData() {
        String[] recommendArray = getResources().getStringArray(R.array.recommend_search);
        mRecommendList = Arrays.asList(recommendArray);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        LogUtil.i(TAG, actionId + ":actionId");
        switch (actionId) {
            case EditorInfo.IME_ACTION_GO:
            case EditorInfo.IME_ACTION_SEARCH:
            case EditorInfo.IME_ACTION_SEND:
            case EditorInfo.IME_ACTION_NEXT:
            case EditorInfo.IME_ACTION_DONE:
            case EditorInfo.IME_ACTION_NONE:
            case EditorInfo.IME_ACTION_UNSPECIFIED:
                String text = mEt_input.getText().toString();
                if ((text = checkInput(text)) != null) {
                    mSearchField = text;
                    //   mPresenter.onRefresh(text);
                    jumpPage(text);
                }
                LogUtil.i(TAG, "搜索" + text);
                break;
        }
        return true;
    }

    private String checkInput(String str) {
        String trim = str.trim();
        if ("".equals(trim)) {
            return null;
        }
        return trim;
    }

    @Override
    public void setData(List<ItemList> itemList, boolean isUpdate) {

    }

    public void setData(Data videoData) {

    }

    public void jumpPage(String searchField) {
        /*保存数据库*/
        persistentInfo(searchField);
        Intent intent = new Intent(this, SearchResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SEARCH_FIELD, mSearchField);
        //   bundle.putSerializable(Constant.SEARCH_VIDEO_DATA, videoData);
        intent.putExtra(Constant.SEARCH_BUNDLE, bundle);
        startActivity(intent);
    }

    /**
     * 储存数据库
     */
    private void persistentInfo(String searchField) {
        mSearchInfoDao.add(searchField);
    }
}