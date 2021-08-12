package com.sample.tools;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * <p>@ClassName ArrayBlockingQueueTest</p>
 * <p>@description TODO</p>
 *
 * @author Ethan.liu
 * @version 1.0
 * @date 2021/8/12 15:18
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        arrayBlockingQueue.add(1);
        Integer poll = arrayBlockingQueue.poll();

        System.out.println(poll);


    }

}
