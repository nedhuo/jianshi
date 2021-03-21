package com.hngg.jianshi.ui.calender;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Date: 2021/2/24
 * Timer: 10:11
 * Author: nedhuo
 * Description:
 */
public class CacheDemo {
    //
    static String memoryCache = null;
    static String diskCache = null;

    public static void main(String[] args) {

        /*
         * 第一个Observable 模拟从内存中获取数据
         * */
        Observable<String> memory = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                //先判断内存中有无数据
                if (memoryCache != null) {
                    emitter.onNext(memoryCache);
                } else {
                    //若无数据，直接结束该事件
                    emitter.onComplete();
                }

            }
        });

        /*
         * 第二个Observable 模拟从磁盘中获取数据
         * */
        Observable<String> disk = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                //先判断内存中有无数据
                if (memoryCache != null) {
                    emitter.onNext(memoryCache);
                } else {
                    //若无数据，直接结束该事件
                    emitter.onComplete();
                }

            }
        });

        /*
         * 第三个Observable 模拟从网络中获取数据
         *
         * */
        Observable<String> network = Observable.just("从网络中获取数据");

        /*
         * 通过concat()和firstElement()操作符实现缓存功能
         *
         * 通过concat()合并memory,disk,network3个被观察者的事件
         * 并将他们串联成队列
         * */

        Disposable subscribe = Observable.concat(memory, disk, network)
                //firstElement 从串联队列中取出并发送第一个有效事件，依次检查三个
                .firstElement()
                .doOnSuccess(new Consumer<String>() {
                    @Override
                    public void accept(String o) throws Exception {
                        //取出数据

                        memoryCache = o;
                        diskCache = o;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //对数据进行消费
                    }
                });

        /*
         * merge操作符可以对数据进行合并,并且同时发送
         * */
        Disposable subscribe1 = Observable.merge(memory, network).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });

    }
}
