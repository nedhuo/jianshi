package com.hngg.jianshi.widget;

import com.hngg.jianshi.data.database.bean.PlayInfo;

public interface PlayInfoCallBack {
    void onDeleteListChange(PlayInfo playInfo, boolean isChecked);
}
