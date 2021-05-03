package com.hngg.jianshi.data;

import java.util.Random;

/**
 * 负责提供随机数据
 */
public class RandomData {

    /**
     * UserInfo背景页面图片
     */
    public static String obtainUserInfoBg() {
        String[] strings = {
                "https://weiliicimg1.pstatp.com/weili/l/903253260450922566.webp"
        };

        return strings[0];
    }

    public static String obtainTagDetailVideosData() {
        String[] strings = {
                "http://baobab.kaiyanapp.com/api/v1/tag/videos?id=20",
                "http://baobab.kaiyanapp.com/api/v1/tag/videos?id=28",
                "http://baobab.kaiyanapp.com/api/v1/tag/videos?id=12",
                "http://baobab.kaiyanapp.com/api/v1/tag/videos?id=2",
                "http://baobab.kaiyanapp.com/api/v1/tag/videos?id=36",
                "http://baobab.kaiyanapp.com/api/v1/tag/videos?id=14"
        };
        Random random = new Random();
        int i = random.nextInt(5);
        return strings[i];
    }
}
