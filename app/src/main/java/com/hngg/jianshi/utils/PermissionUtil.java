package com.hngg.jianshi.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;


import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 权限工具类
 * <p>
 * 权限请求逻辑
 * 第一次打开项目，整体请求一遍，使用某项功能之前再请求一遍（跳转设置页面）
 */
public class PermissionUtil {
    private final int REQUEST_CODE = 0;

    private String[] mPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NOTIFICATION_POLICY
    };

    public  void checkPermissions(Activity context) {
        if (!EasyPermissions.hasPermissions(context, mPermissions)) {
            EasyPermissions.requestPermissions(context,
                    "权限请求", REQUEST_CODE, mPermissions);
            if (EasyPermissions.hasPermissions(context,mPermissions)){
                new AppSettingsDialog.Builder(context)
                        .setTitle("权限已被禁用")
                        .setRationale("如果不打开权限则无法完全使用该应用功能,请酌情开启权限")
                        .setRationale("")
                        .build()
                        .show();
            }
        }
    }


}
