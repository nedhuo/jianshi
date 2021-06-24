package com.hngg.jianshi.base;

import androidx.fragment.app.Fragment;

import com.hngg.jianshi.data.bean.home.ItemList;

import java.util.List;

public class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();

    public void setData(List<ItemList> itemList, boolean isUpdate) {

    }
}
