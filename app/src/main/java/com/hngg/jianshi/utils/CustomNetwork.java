package com.hngg.jianshi.utils;

import com.hngg.network.BuildConfig;
import com.hngg.network.INetworkRequiredInfo;

/**
 * Date: 2020/11/23
 * Timer: 13:35
 * Author: nedhuo
 * Description:
 */
public class CustomNetwork implements INetworkRequiredInfo {
    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
