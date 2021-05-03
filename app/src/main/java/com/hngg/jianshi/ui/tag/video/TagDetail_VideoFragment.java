package com.hngg.jianshi.ui.tag.video;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.utils.LogUtil;

import java.util.List;

public class TagDetail_VideoFragment extends Fragment {
    private Handler mHandler;
    private static final String TAG = "TagDetail_VideoFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tag_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void setData(List<ItemList> itemList) {
        if (itemList != null && itemList.size() != 0) {
            LogUtil.i(TAG,itemList.toString());
        }
    }

    public void setHandler(Handler handler) {
        mHandler = handler;
    }
}
