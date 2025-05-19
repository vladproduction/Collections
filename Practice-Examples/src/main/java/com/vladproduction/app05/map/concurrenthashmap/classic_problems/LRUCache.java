package com.vladproduction.app05.map.concurrenthashmap.classic_problems;

import java.util.concurrent.*;


/**
 * Problem 3: LRU Cache with ConcurrentHashMap
 * 🔧 Goal:
 * Implement a thread-safe LRU (Least Recently Used) cache using ConcurrentHashMap + ConcurrentLinkedDeque for access ordering.
 * */
public class LRUCache<K, V> {
    private final int capacity;
    private final ConcurrentHashMap<K, V> map;
    private final ConcurrentLinkedDeque<K> deque;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new ConcurrentHashMap<>();
        this.deque = new ConcurrentLinkedDeque<>();
    }

    public V get(K key) {
        if (!map.containsKey(key)) return null;
        // Move to recent
        deque.remove(key);
        deque.addFirst(key);
        return map.get(key);
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            deque.remove(key);
        } else if (map.size() >= capacity) {
            // Evict the least recently used
            K lru = deque.pollLast();
            if (lru != null) {
                map.remove(lru);
            }
        }

        deque.addFirst(key);
        map.put(key, value);
    }

    public void printState() {
        System.out.println("Cache: " + map);
        System.out.println("Order: " + deque);
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.printState();

        cache.get(1); // Access 1 → moves to front
        cache.put(4, "Four"); // Evicts 2
        cache.printState();
    }
}
