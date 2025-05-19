package com.vladproduction.app05.map.treemap;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapRemoval {
    public static void main(String[] args) {

        // Initialize and populate the TreeMap
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Apple", 5);
        treeMap.put("Banana", 10);
        treeMap.put("Orange", 8);
        treeMap.put("Cherry", 12);
        System.out.println("Initial TreeMap: " + treeMap);
        System.out.println();

        // 1. Remove by key (simple removal) remove(key) is straightforward for removing specific entries
        System.out.println("Removing 'Apple' using remove(key):");
        Integer removedValue = treeMap.remove("Apple");
        System.out.println("Removed value: " + removedValue);
        System.out.println("TreeMap after removal: " + treeMap);
        System.out.println();

        // 2. Remove while iterating with iterator (safe) Use an Iterator and call iterator.remove() for safe removal during iteration
        System.out.println("Removing entries while iterating with iterator:");
        Iterator<Map.Entry<String, Integer>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getKey().equals("Banana")) {
                iterator.remove(); // safe removal during iteration
                System.out.println("Removed 'Banana'");
            }
        }
        System.out.println("TreeMap after iterator removal: " + treeMap);
        System.out.println();

        // 3. Remove by key during foreach loop (NOT recommended)
        // Avoid removing items directly from the collection inside an enhanced for loop as it throws ConcurrentModificationException
        System.out.println("Attempting removal during for-each loop (NOT recommended):");
        try {
            for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
                if (entry.getKey().equals("Orange")) {
                    // This will throw ConcurrentModificationException
                    treeMap.remove(entry.getKey());
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e);
        }
        System.out.println();

        // 4. Correct way to remove entries conditionally:
        System.out.println("Removing entries with key 'Orange' using remove():");
        if (treeMap.containsKey("Orange")) {
            treeMap.remove("Orange");
            System.out.println("Removed 'Orange'");
        }
        System.out.println("TreeMap after conditional removal: " + treeMap);
        System.out.println();

        // 5. Remove first and last entries
        // pollFirstEntry() and pollLastEntry() remove and return the smallest/largest key-value pair
        System.out.println("Removing first entry:");
        Map.Entry<String, Integer> firstEntry = treeMap.pollFirstEntry();
        System.out.println("Removed: " + firstEntry);
        System.out.println("Removing last entry:");
        Map.Entry<String, Integer> lastEntry = treeMap.pollLastEntry();
        System.out.println("Removed: " + lastEntry);
        System.out.println("Final TreeMap: " + treeMap);

    }
}
