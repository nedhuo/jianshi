package com.hngg.network;

/**
 * Date: 2020/11/22
 * Timer: 16:04
 * Author: nedhuo
 * Description:
 *      添加Debug模式下的OkHttp日志打印功能，需要从App模块获取当前运行模式，
 *      因此需要module间的信息通讯
 *      使用依赖倒置原则
 *  如果是debug模式就打印网络日志
 */
public interface INetworkRequiredInfo {
    boolean isDebug();
}
