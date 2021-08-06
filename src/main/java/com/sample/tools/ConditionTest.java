package com.sample.tools;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>@ClassName ConditionTest</p>
 * <p>@description TODO</p>
 *
 * @author Ethan.liu
 * @version 1.0
 * @date 2021/8/5 16:06
 */
public class ConditionTest {

    private static final Lock LOCK = new ReentrantLock();

    private static final Condition DONE = LOCK.newCondition();

    private static Object OBJECT = null;

    public static void main(String[] args) {


        new Thread(() -> {
            try {

                LOCK.lock();
                while (OBJECT==null){
                    DONE.await(1,TimeUnit.SECONDS);
                }

                System.out.println("干活。。。");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }).start();


        new Thread(() -> {
            try {


                LOCK.lock();
                Thread.sleep(100000);
               OBJECT = new Object();
                if (DONE != null){
                    DONE.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }).start();




    }

}
