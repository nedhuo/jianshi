package com.hngg.jianshi.utils;

import android.os.Environment;

import java.io.File;

/**
 * 文件工具类
 */
public class FileUtil {


    /**
     * Environment.getExternalStorageDirectory().getAbsolutePath()
     * 内部存储/
     */
    public void get() {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();

    }

    public void checkPathExisted(String path) {
        File file = new File(path);
        if (!file.exists()) {
            /*不存在*/

        }
    }


}
