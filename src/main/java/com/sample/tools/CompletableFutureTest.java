package com.sample.tools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p>@ClassName CompletableFutureTest</p>
 * <p>@description TODO</p>
 *
 * @author Ethan.liu
 * @version 1.0
 * @date 2021/8/10 10:34
 */
public class CompletableFutureTest {

    public static void main(String[] args) {

        //任务1 ： 洗水壶 -> 烧开水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1: 洗水壶。。。");
            sleep(1,TimeUnit.SECONDS);

            System.out.println("T1:烧开水。。。");
            sleep(15,TimeUnit.SECONDS);
        });

        //任务2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:  洗茶壶。。。");
            sleep(1,TimeUnit.SECONDS);

            System.out.println("T2:  洗茶杯。。。");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("T2: 拿茶叶。。。");
            sleep(1,TimeUnit.SECONDS);
            return "龙井";
        });

        //任务3：任务1 和 任务2 完成后： 泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2,(unused, s) -> {
            System.out.println("T1:拿到茶叶："+s);
            System.out.println("T1:泡茶。。。");
            return  "上茶:"+s;
        });

        System.out.println(f3.join());

    }

    static void sleep(int t, TimeUnit timeUnit){
        try {
            timeUnit.sleep(t);
        }catch (InterruptedException ignored){}
    }

}
