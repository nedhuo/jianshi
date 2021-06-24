package com.hngg.jianshi.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * 文件工具类
 */
public class FileUtil {

    private static final String TAG = "FileUtil";

    /**
     * Environment.getExternalStorageDirectory().getAbsolutePath()
     * 内部存储/
     */
    @Deprecated
    public static String getDownloadPath() {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        Context context;
//        context.getExternalFilesDir("string/video");
        String filePath = absolutePath + "/jianshi/video";

        File file = new File(filePath);
        boolean canWrite = file.canWrite();
        if (!file.exists()) {
            Log.i(TAG, file.getPath() + "路径不存在,新建中...");
            if (!file.mkdirs()) {
                Log.e(TAG, file + "路径创建失败" + absolutePath);
                Log.e(TAG, "视频存储可能出现异常");
                //TODO 待优化 Android11路径访问失败
                Log.e(TAG, "创建保存路径为" + absolutePath);
                return absolutePath;
            }
        }
        Log.e(TAG, "创建保存路径为" + filePath);
        return filePath;
    }


    public static String getDownloadPath(Context context) {
        //getExternalFilesDir(null)将返回您的应用存储文件夹，位于（内部存储）/Android/data/your.app.name/file/
        File file = context.getExternalFilesDir("jianshi/video");
        if (file == null) {
            return getDownloadPath();
        } else {
            Log.i(TAG, file.getPath() + "路径不存在,新建中...");
            if (!file.mkdirs()) {
                Log.e(TAG, file + "路径创建失败");
                Log.e(TAG, "视频存储可能出现异常");
                return getDownloadPath();
            }
        }
        return file.getPath();
    }


    public void checkPathExisted(String path) {
        File file = new File(path);
        if (!file.exists()) {
            /*不存在*/

        }
    }


}
