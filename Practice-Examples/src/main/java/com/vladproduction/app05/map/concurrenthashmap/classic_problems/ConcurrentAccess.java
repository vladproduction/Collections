package com.vladproduction.app05.map.concurrenthashmap.classic_problems;

import java.util.concurrent.*;

public class ConcurrentAccess {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.merge("count", 1, Integer::sum);
            }
        };

        for (int i = 0; i < 4; i++) {
            executor.submit(task);
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Final count: " + map.get("count")); // Expected: 4000
    }
}
