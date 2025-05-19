package com.vladproduction.app05.map.linkedhashmap.classic_problems;

import java.util.*;

public class MyOrderedMap<K, V> {

    private final Map<K, V> map = new HashMap<>();
    private final List<K> order = new LinkedList<>();

    public static void main(String[] args) {

        MyOrderedMap<String, Integer> orderedMap = new MyOrderedMap<>();

        orderedMap.put("apple", 1);
        orderedMap.put("banana", 2);
        orderedMap.put("cherry", 3);

        System.out.println("Get 'banana': " + orderedMap.get("banana"));

        orderedMap.put("banana", 22); // update value, but not order

        System.out.println("\nContents in insertion order:");
        orderedMap.printInOrder();

        orderedMap.remove("apple");

        System.out.println("\nAfter removing 'apple':");
        orderedMap.printInOrder();

    }

    // Put method (insert or update)
    public void put(K key, V value) {
        if (!map.containsKey(key)) {
            order.add(key); // preserve order only on first insert
        }
        map.put(key, value);
    }

    // Get method
    public V get(K key) {
        return map.get(key);
    }

    // Remove method
    public void remove(K key) {
        if (map.containsKey(key)) {
            map.remove(key);
            order.remove(key);
        }
    }

    // Check if key exists
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    // Print in insertion order
    public void printInOrder() {
        for (K key : order) {
            System.out.println(key + " : " + map.get(key));
        }
    }

    // Size
    public int size() {
        return map.size();
    }

    // Optional: Clear
    public void clear() {
        map.clear();
        order.clear();
    }

}
