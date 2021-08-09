package com.sample.tools;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.Data;

/**
 * <p>@ClassName TaskTest</p>
 * <p>@description TODO</p>
 *
 * @author Ethan.liu
 * @version 1.0
 * @date 2021/8/9 16:57
 */
public class TaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Result result = new Result();
        result.setAAA("a");

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Result> submit = executorService.submit(new Task(result), result);

        Result result1 = submit.get();

        System.out.println(result==result1);

        System.out.println(result1.getAAA().equals("a"));


    }


    static class Task implements Runnable{

        Result result;

        public  Task(Result result){
            this.result = result;
        }

        @Override
        public void run() {
            String aaa = result.getAAA();
            result.setXXX(aaa);
        }
    }

    @Data
    static
    class Result{

        private String AAA;

        private String XXX;

    }
}
