package com.vladproduction.app05.map.treemap;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapBasics {
    public static void main(String[] args) {

        /*Basics of TreeMap
        Implements NavigableMap interface, which extends SortedMap
        Stores keys in sorted order (natural ordering or by custom Comparator)
        Uses Red-Black Tree internally — balanced binary search tree
        Average operations: O(log n) for put(), get(), remove()
        Does not allow null keys but allows null values
        Useful for maintaining a sorted collection with fast retrieval and update, especially when ordering matters.*/

        // Instantiate a TreeMap with String keys and Integer values
        // TreeMap stores keys in sorted order (natural order for Strings)
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        // Adding key-value pairs to the TreeMap
        treeMap.put("Banana", 10);
        treeMap.put("Apple", 5);
        treeMap.put("Orange", 8);

        // Printing the TreeMap
        // Keys are automatically sorted: {Apple=5, Banana=10, Orange=8}
        System.out.println(treeMap);

        // Iterating over the entries (key-value pairs) in sorted order
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            // Accessing and printing each key and its corresponding value
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        // Removing an element by key
        // Removes the entry with key "Apple"
        treeMap.remove("Apple");

        // Removing and retrieving the first entry in the TreeMap (smallest key)
        Map.Entry<String, Integer> firstEntry = treeMap.pollFirstEntry();
        // Prints the removed first entry
        System.out.println("Removed first entry: " + firstEntry);

        // Removing and retrieving the last entry in the TreeMap (largest key)
        Map.Entry<String, Integer> lastEntry = treeMap.pollLastEntry();
        // Prints the removed last entry
        System.out.println("Removed last entry: " + lastEntry);

    }
}
