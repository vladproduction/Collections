package com.vladproduction.app05.map.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapBasics {
    public static void main(String[] args) {

        /*ConcurrentHashMap is a thread-safe, high-performance hash table introduced in Java 1.5.
        It allows concurrent access and updates without needing external synchronization.*/
        /*Key Features
        Feature	Description
        Thread-safe	Allows safe access by multiple threads without synchronized blocks
        High concurrency	Uses lock striping instead of locking the entire map
        No null key/value	Just like Hashtable, doesn't allow null keys or values
        Performance	Scales well with multi-threaded access (better than Hashtable)*/

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("Java", 10);
        map.put("Python", 20);
        map.put("C++", 30);

        System.out.println("Java score: " + map.get("Java"));

    }
}
