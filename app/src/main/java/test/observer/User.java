package test.observer;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Date: 2020/12/28
 * Timer: 9:31
 * Author: nedhuo
 * Description:
 */
public class User implements Observer {

    private String message;

    @Override
    public void update(Observable o, Object arg) {
        message = (String) arg;
        print();
    }

    private void print() {
        System.out.println(message);
    }
}
