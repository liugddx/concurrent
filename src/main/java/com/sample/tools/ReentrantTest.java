package com.sample.tools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>@ClassName ReentrantTest</p>
 * <p>@description TODO</p>
 *
 * @author Ethan.liu
 * @version 1.0
 * @date 2021/8/5 13:39
 */
public class ReentrantTest {

    private static int a = 0;

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
               try {
                   lock.lock();
                   a++;
                   System.out.println(a);
               }finally {
                   lock.unlock();
               }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    a++;
                    System.out.println(a);
                }finally {
                    lock.unlock();
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



}
