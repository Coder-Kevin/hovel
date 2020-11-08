package com.hovel.base.thread.synccontainer;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.NotThreadSafe;

import java.util.Vector;

@NotThreadSafe
@Slf4j
public class VectorExample2 {

    public static Vector<Integer> list = new Vector<>();

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                list.remove(i); // get恰好已被remove移除就会异常
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                list.get(i);
            }
        }).start();


    }

}
