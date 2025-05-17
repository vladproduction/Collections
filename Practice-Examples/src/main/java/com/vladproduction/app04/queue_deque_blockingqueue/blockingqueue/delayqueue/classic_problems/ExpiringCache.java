package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.delayqueue.classic_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Problem 3: Expiring Cache Entries
 * Scenario: Build a time-sensitive cache that automatically removes old entries.
 * */
public class ExpiringCache {
    public static void main(String[] args) throws InterruptedException {

        DelayQueue<CacheEntry> cacheQueue = new DelayQueue<>();
        Map<String, String> cacheMap = new HashMap<>();

        cacheMap.put("token-1", "user-1");
        cacheQueue.put(new CacheEntry("token-1", 3000));

        Thread cleaner = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    CacheEntry entry = cacheQueue.take();
                    cacheMap.remove(entry.key());
                    System.out.println("Removed expired cache: " + entry.key());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        cleaner.setDaemon(true);
        cleaner.start();

        Thread.sleep(5000); //wait for 5 seconds to see if the cache is cleared
        System.out.println("Cache contain token-1: " + cacheMap.containsKey("token-1"));

    }


    record CacheEntry(String key, long expiryTime) implements Delayed {

        public CacheEntry(String key, long expiryTime) {
            this.key = key;
            this.expiryTime = System.currentTimeMillis() + expiryTime;
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.expiryTime, ((CacheEntry) o).expiryTime);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expiryTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }
}
