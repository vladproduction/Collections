package com.vladproduction.ch07_generic.p08_generic_cache_with_expiration;

public class MainApp {
    public static void main(String[] args) {

        GenericCache<String, String> cache = new GenericCache<>(2000); // 2 second TTL

        cache.put("key1", "value1");
        cache.put("key2", "value2", 1000); // 1 second TTL

        System.out.println("Cache get key1: " + cache.get("key1"));
        System.out.println("Cache get key2: " + cache.get("key2"));

        // Wait and check expiration (in real code, you might not want to sleep)
        try {
            Thread.sleep(1100);
            System.out.println("After 1.1 seconds:");
            System.out.println("Cache get key1: " + cache.get("key1"));
            System.out.println("Cache get key2: " + cache.get("key2")); // the expected time is expired
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
