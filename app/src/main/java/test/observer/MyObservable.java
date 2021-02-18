package test.observer;

import java.util.Observable;

/**
 * Date: 2020/12/28
 * Timer: 9:32
 * Author: nedhuo
 * Description:
 * JDK中的Observable内有一个list集合，用于存储Observer
 */
public class MyObservable extends Observable {
    /**
     * 每一次时间推送后，都需要setChanged()才可以推送下一次
     * 或者说，每一次事件推送前，都需要调用<code>setChanged</code>
     */
    public void setChanged() {
        super.setChanged();
    }
}
