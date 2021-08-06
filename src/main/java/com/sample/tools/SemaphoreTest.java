package com.sample.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p>@ClassName Semaphore</p>
 * <p>@description TODO</p>
 *
 * @author Ethan.liu
 * @version 1.0
 * @date 2021/8/6 10:16
 */
public class SemaphoreTest {


    private static int count = 10;

    static final Semaphore SEMAPHORE = new Semaphore(1);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int s = 0;s<10;s++ ){
            executorService.submit(() -> {
                try {
                    del();
                    System.out.println(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


    }


    static void del() throws InterruptedException {
        SEMAPHORE.release();
        try {
            count-=1;
        }finally {
            SEMAPHORE.acquire();
        }
    }
}
