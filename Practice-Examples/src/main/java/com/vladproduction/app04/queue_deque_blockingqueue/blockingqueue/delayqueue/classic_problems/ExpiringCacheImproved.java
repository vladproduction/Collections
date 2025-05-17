package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.delayqueue.classic_problems;

import java.util.concurrent.*;

/**
 *  Improved version for ExpiringCache.java:
 * Use ConcurrentHashMap for thread-safe access.
 * Make CacheEntry store the value as well for further flexibility.
 * Encapsulate adding entries in a put() method.
 * Keep the cleaner thread as a daemon.
 * This sample provides a more robust, thread-safe, extendable solution.
 * */
public class ExpiringCacheImproved {

    private static final DelayQueue<CacheEntry> cacheQueue = new DelayQueue<>();
    private static final ConcurrentMap<String, String> cacheMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {

        // Start cleaner thread
        Thread cleaner = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    CacheEntry entry = cacheQueue.take();
                    // Remove the key in a thread-safe manner
                    if (cacheMap.remove(entry.key(), entry.getValue())) {
                        System.out.println("Removed expired cache: " + entry.key());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cleaner.setDaemon(true);
        cleaner.start();

        // Add cache entries
        put("token-1", "user-1", 3000L);
        put("token-2", "user-1", 10000L);
        Thread.sleep(5000); // wait for 5 seconds

        System.out.println("Cache contain token-1: " + cacheMap.containsKey("token-1")); //expected false, since it was removed by cleaner thread

        //simulate data still stay in cache
        System.out.println("Cache contain token-2: " + cacheMap.containsKey("token-2")); //expected true, since it was not removed by cleaner thread

        // Optional: add more entries or extend functionality

    }

    public static void put(String key, String value, long expiryMillis) {
        cacheMap.put(key, value);
        cacheQueue.put(new CacheEntry(key, expiryMillis, value));
    }

    record CacheEntry(String key, long expiryTime, String value) implements Delayed {

        public CacheEntry(String key, long expiryTime, String value) {
            this.key = key;
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + expiryTime;
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.expiryTime, ((CacheEntry) o).expiryTime);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expiryTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        public String getValue() {
            return value;
        }
    }

}
