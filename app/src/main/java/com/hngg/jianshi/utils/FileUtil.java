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
    public static String getDownloadPath() {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        Context context;
//        context.getExternalFilesDir("string/video");
        String filePath = absolutePath + "/jianshi/video/";

        File file = new File(filePath);
        if (!file.exists()) {
            Log.i(TAG, file.getPath() + "路径不存在,新建中...");
            if (!file.mkdirs()) {
                Log.e(TAG, file + "路径创建失败");
                Log.e(TAG, "视频存储可能出现异常");
                //TODO 待优化

                return absolutePath;
            }
        }
        return filePath;
    }

    public void checkPathExisted(String path) {
        File file = new File(path);
        if (!file.exists()) {
            /*不存在*/

        }
    }


}
