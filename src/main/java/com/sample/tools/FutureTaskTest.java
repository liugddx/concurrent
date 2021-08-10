package com.sample.tools;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * <p>@ClassName FutureTaskTest</p>
 * <p>@description TODO</p>
 *
 * @author Ethan.liu
 * @version 1.0
 * @date 2021/8/9 17:28
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask =  new FutureTask<>(() -> 1+2);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(futureTask);

        Integer integer = futureTask.get();

        System.out.println(integer);
    }

}
