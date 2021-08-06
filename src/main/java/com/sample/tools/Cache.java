package com.sample.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>@ClassName Cache</p>
 * <p>@description TODO</p>
 *
 * @author Ethan.liu
 * @version 1.0
 * @date 2021/8/6 11:24
 */
public class Cache<K,V> {

    final Map<K,V> map = new HashMap<>();

    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    final Lock read = readWriteLock.readLock();

    final Lock write = readWriteLock.writeLock();

    V get(K key){
        read.lock();
        try {
            return map.get(key);
        }finally {
            read.unlock();
        }
    }

    void set(K key,V value){
        write.lock();
         try {
            map.put(key,value);
         }finally {
            write.unlock();
        }
    }

}
