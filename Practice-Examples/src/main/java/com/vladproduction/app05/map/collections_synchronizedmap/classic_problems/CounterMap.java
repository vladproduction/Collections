package com.vladproduction.app05.map.collections_synchronizedmap.classic_problems;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Maintain a thread-safe counter for multiple items.
 * */
public class CounterMap {

    private final Map<String, Integer> counterMap = Collections.synchronizedMap(new HashMap<>());

    public void increment(String key) {
        synchronized (counterMap) {
            counterMap.put(key, counterMap.getOrDefault(key, 0) + 1);
        }
    }

    public int get(String key) {
        synchronized (counterMap) {
            return counterMap.getOrDefault(key, 0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CounterMap counter = new CounterMap();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment("apple");
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Final Count: " + counter.get("apple")); // Expected: 2000
    }
}
