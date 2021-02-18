package test.observer;

import android.util.Log;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Date: 2020/12/28
 * Timer: 9:34
 * Author: nedhuo
 * Description:
 */
public class Client {

    private static String TAG = "Client";

    public static void main(String[] args) {
        //JDKMode();

        RxJavaMode();
    }

    /**
     *
     */
    public static void JDKMode() {
        String message = "推送消息";
        MyObservable myObservable = new MyObservable();

        //添加Observer
        User zhangsan = new User();
        myObservable.addObserver(zhangsan);
        User lisi = new User();
        myObservable.addObserver(lisi);
        //推送消息
        myObservable.setChanged();
        myObservable.notifyObservers(message);
        //修改Observer
        myObservable.deleteObserver(zhangsan);
        //推送消息
        myObservable.setChanged();
        myObservable.notifyObservers(message);
    }

    public static void RxJavaMode() {
        //1，创建一个上游 Observable：被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("第一次推送");
                emitter.onNext("第二次推送");
                emitter.onComplete();
            }
        });
        //2，创建一个下游 Observer：观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe");
            }

            @Override
            public void onNext(String value) {
                System.out.println("" + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };
        //3，建立订阅关系
        observable.subscribe(observer);
    }
}
