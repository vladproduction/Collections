package com.vladproduction.app05.map.treemap;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapIteration {
    public static void main(String[] args) {

        // Create and populate the TreeMap
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Apple", 5);
        treeMap.put("Banana", 10);
        treeMap.put("Orange", 8);

        System.out.println("Original TreeMap: " + treeMap);
        System.out.println();

        // 1. Iterate using entrySet() with for-each loop: entrySet() provides a set of key-value pairs for iteration
        System.out.println("Iterate using entrySet() with for-each:");
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        System.out.println();

        // 2. Iterate using keySet() with for-each loop: keySet() gives all keys; you can get corresponding values with get()
        System.out.println("Iterate using keySet() with for-each:");
        for (String key : treeMap.keySet()) {
            System.out.println(key + " => " + treeMap.get(key));
        }
        System.out.println();

        // 3. Iterate using values() (only values): values() iterates over just the values
        System.out.println("Iterate over values() only:");
        for (Integer value : treeMap.values()) {
            System.out.println("Value: " + value);
        }
        System.out.println();

        // 4. Using an Iterator over entrySet(): Iterator allows explicit control over the iteration process
        System.out.println("Iterate using Iterator over entrySet():");
        Iterator<Map.Entry<String, Integer>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        System.out.println();

        // 5. Using Java 8 forEach() with lambda: forEach() with lambda provides a concise way to iterate (Java 8+)
        System.out.println("Using forEach() with lambda:");
        treeMap.forEach((key, value) -> {
            System.out.println(key + " => " + value);
        });
        System.out.println();

        // 6. Descending order iteration: descendingMap() allows iteration in reverse sorted order
        System.out.println("Iterate in descending order:");
        for (Map.Entry<String, Integer> entry : treeMap.descendingMap().entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }


    }
}
