package com.vladproduction.app05.map.collections_synchronizedmap.classic_problems;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Use a synchronized map to implement a thread-safe cache that auto-loads values if missing.
 * */
public class AutoFillCache {

    private final Map<String, String> cache = Collections.synchronizedMap(new HashMap<>());

    public String getValue(String key) {
        synchronized (cache) {
            return cache.computeIfAbsent(key, k -> {
                System.out.println("Loading value for: " + k);
                return "Value-" + k.toUpperCase();
            });
        }
    }

    public static void main(String[] args) {
        AutoFillCache cache = new AutoFillCache();

        Runnable r1 = () -> System.out.println("Thread 1: " + cache.getValue("user1"));
        Runnable r2 = () -> System.out.println("Thread 2: " + cache.getValue("user1"));

        new Thread(r1).start();
        new Thread(r2).start();
    }
}
