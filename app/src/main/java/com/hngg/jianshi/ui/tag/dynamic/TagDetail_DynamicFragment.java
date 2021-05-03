package com.hngg.jianshi.ui.tag.dynamic;

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

import java.util.List;

public class TagDetail_DynamicFragment extends Fragment {
    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tag_dynamic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setData(List<ItemList> itemList) {
    }

    public void setHandler(Handler handler) {
        mHandler=handler;
    }
}
