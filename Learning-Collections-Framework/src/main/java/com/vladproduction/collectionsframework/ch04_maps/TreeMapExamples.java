package com.vladproduction.collectionsframework.ch04_maps;

import java.time.LocalTime;
import java.util.*;

/**
 * Demonstrates key features and use cases of TreeMap in Java.
 * <p>
 * TreeMap is a NavigableMap implementation that maintains keys in sorted order.
 * This example class covers:
 * - Basic operations and iteration
 * - Custom comparators
 * - Navigable methods
 * - Submaps and views
 * - Performance considerations
 * - TreeMap vs other maps (HashMap, LinkedHashMap)
 * - Real-world use cases like leaderboards and sorted data handling
 * <p>
 * All examples maintain natural or custom key ordering to illustrate the behavior.
 */
public class TreeMapExamples {
    public static void main(String[] args) {

        System.out.println("TREEMAP EXAMPLES");
        System.out.println("================\n");

        TreeMapExamples demo = new TreeMapExamples();

        demo.basicOperations();
        demo.customComparatorUsage();
        demo.navigableMethods();
        demo.subMapViews();
        demo.performanceCharacteristics();
        demo.nullHandling();
        demo.bulkOperations();
        demo.comparisonWithOtherMaps();
        demo.bestPractices();
        demo.realWorldUseCases();

    }

    private void basicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("===================");

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        treeMap.put("Charlie", 3);
        treeMap.put("Alice", 1);
        treeMap.put("Bob", 2);

        System.out.println("TreeMap (sorted by keys): " + treeMap);

        treeMap.remove("Bob");
        System.out.println("After removing 'Bob': " + treeMap);

        System.out.println("Contains key 'Alice'? " + treeMap.containsKey("Alice"));
        System.out.println("Contains value 3? " + treeMap.containsValue(3));

        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println();

    }

    private void customComparatorUsage() {
        System.out.println("2. CUSTOM COMPARATOR USAGE");
        System.out.println("==========================");

        // Reverse alphabetical order
        TreeMap<String, Integer> reverseOrderMap = new TreeMap<>(Comparator.reverseOrder());
        reverseOrderMap.put("Banana", 2);
        reverseOrderMap.put("Apple", 1);
        reverseOrderMap.put("Cherry", 3);
        System.out.println("Reverse order map: " + reverseOrderMap);

        // Case-insensitive comparator
        TreeMap<String, Integer> caseInsensitiveMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitiveMap.put("apple", 10);
        caseInsensitiveMap.put("Banana", 20);
        caseInsensitiveMap.put("APPLE", 30); // Will overwrite 'apple'
        System.out.println("Case-insensitive map: " + caseInsensitiveMap);

        System.out.println();

    }

    private void navigableMethods() {
        System.out.println("3. NAVIGABLE METHODS");
        System.out.println("====================");

        TreeMap<Integer, String> navMap = new TreeMap<>();
        navMap.put(10, "Ten");
        navMap.put(20, "Twenty");
        navMap.put(30, "Thirty");
        navMap.put(40, "Forty");

        System.out.println("Original TreeMap: " + navMap);

        System.out.println("Lower than 25: " + navMap.lowerKey(25));     // 20
        System.out.println("Floor of 30: " + navMap.floorKey(30));       // 30
        System.out.println("Ceiling of 25: " + navMap.ceilingKey(25));   // 30
        System.out.println("Higher than 30: " + navMap.higherKey(30));   // 40

        System.out.println("First entry: " + navMap.firstEntry());
        System.out.println("Last entry: " + navMap.lastEntry());

        System.out.println("Poll first (remove): " + navMap.pollFirstEntry());
        System.out.println("Poll last (remove): " + navMap.pollLastEntry());
        System.out.println("After polling: " + navMap);

        System.out.println();
    }

    private void subMapViews() {
        System.out.println("4. SUBMAP VIEWS");
        System.out.println("================");

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(10, "Ten");
        treeMap.put(20, "Twenty");
        treeMap.put(30, "Thirty");
        treeMap.put(40, "Forty");
        treeMap.put(50, "Fifty");

        System.out.println("Original TreeMap: " + treeMap);

        // headMap: keys less than 30
        SortedMap<Integer, String> head = treeMap.headMap(30);
        System.out.println("HeadMap (<30): " + head);

        // tailMap: keys greater than or equal to 30
        SortedMap<Integer, String> tail = treeMap.tailMap(30);
        System.out.println("TailMap (>=30): " + tail);

        // subMap: keys from 20 (inclusive) to 50 (exclusive)
        SortedMap<Integer, String> sub = treeMap.subMap(20, 50);
        System.out.println("SubMap (20 to 50): " + sub);

        // NavigableMap variants with bounds
        NavigableMap<Integer, String> bounded = treeMap.subMap(20, true, 40, true);
        System.out.println("Bounded subMap (20 to 40, inclusive): " + bounded);

        System.out.println();
    }

    private void performanceCharacteristics() {
        System.out.println("5. PERFORMANCE CHARACTERISTICS");
        System.out.println("==============================");

        System.out.println("TreeMap is implemented as a Red-Black tree, a balanced binary search tree.");
        System.out.println("Common operations (get, put, remove) have O(log n) time complexity.");
        System.out.println("Iteration over entries is in sorted key order, with O(n) complexity.");
        System.out.println("Compared to HashMap (O(1) average), TreeMap is slower due to tree traversal.");
        System.out.println("However, TreeMap supports ordered operations and range views, which HashMap cannot.");
        System.out.println("Use TreeMap when sorted keys or navigation methods are required.");
        System.out.println("For large datasets with frequent access, consider trade-offs between speed and ordering.");
        System.out.println();
    }

    private void nullHandling() {
        System.out.println("6. NULL HANDLING");
        System.out.println("=================");

        TreeMap<String, String> treeMap = new TreeMap<>();
        //trying to put null as a key: throwing an exception
        try {
            treeMap.put(null, "NullKey");
        } catch (NullPointerException e) {
            System.out.println("Exception on inserting null key occurred.");
            System.out.println("Caught NullPointerException: " + e.getMessage());
        }

        // Null values are allowed
        treeMap.put("Key1", null);
        treeMap.put("Key2", "Value2");

        System.out.println("\nTreeMap after adding null value: " + treeMap);

        // Accessing null key throws exception
        try {
            treeMap.get(null);
        } catch (NullPointerException e) {
            System.out.println("\nException on getting null key: " + e);
        }

        System.out.println();

    }

    private void bulkOperations() {
        System.out.println("7. BULK OPERATIONS");
        System.out.println("==================");

        TreeMap<String, Integer> baseMap = new TreeMap<>();
        baseMap.put("A", 1);
        baseMap.put("B", 2);
        baseMap.put("C", 3);

        Map<String, Integer> additionalMap = Map.of("D", 4, "E", 5, "B", 20); // 'B' will be updated

        System.out.println("Base map: " + baseMap);
        System.out.println("Additional map: " + additionalMap);

        // putAll - merges additional entries, updating existing keys
        baseMap.putAll(additionalMap);
        System.out.println("After putAll: " + baseMap);

        // replaceAll - double all values
        baseMap.replaceAll((key, value) -> value * 2);
        System.out.println("After replaceAll (values * 2): " + baseMap);

        // putIfAbsent - add only if absent
        baseMap.putIfAbsent("F", 12);
        baseMap.putIfAbsent("A", 99); // won't overwrite existing 'A'

        System.out.println("After putIfAbsent (F added, A stay unchanged): " + baseMap);
        System.out.println();

    }

    private void comparisonWithOtherMaps() {
        System.out.println("8. COMPARISON WITH OTHER MAPS");
        System.out.println("==============================");

        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();

        List<String> keys = List.of("C", "A", "B");

        for (String key : keys) {
            hashMap.put(key, key.charAt(0) - 'A' + 1);
            linkedHashMap.put(key, key.charAt(0) - 'A' + 1);
            treeMap.put(key, key.charAt(0) - 'A' + 1);
        }

        System.out.println("HashMap (no order guaranteed): " + hashMap);
        System.out.println("LinkedHashMap (insertion order): " + linkedHashMap);
        System.out.println("TreeMap (sorted key order): " + treeMap);

        System.out.println();
        System.out.println("Summary:");
        System.out.println("- HashMap: Fastest, no order guarantee.");
        System.out.println("- LinkedHashMap: Maintains insertion order.");
        System.out.println("- TreeMap: Sorted order, supports range queries, slower due to tree structure.");
        System.out.println();
    }

    private void bestPractices() {
        System.out.println("9. BEST PRACTICES");
        System.out.println("==================");

        System.out.println("- Use TreeMap when you need keys in sorted order or efficient range queries.");
        System.out.println("- Always provide a Comparator if natural ordering is not suitable or keys don’t implement Comparable.");
        System.out.println("- Avoid inserting null keys to prevent NullPointerException.");
        System.out.println("- For large datasets with no ordering requirement, consider HashMap for better performance.");
        System.out.println("- When iteration order matters but not sorted order, use LinkedHashMap instead.");
        System.out.println("- Use NavigableMap methods to efficiently implement interval lookups and floor/ceiling queries.");
        System.out.println("- Remember that TreeMap is not synchronized; use Collections.synchronizedSortedMap() if thread-safety is needed.");
        System.out.println();
    }

    private void realWorldUseCases() {
        System.out.println("10. REAL WORLD USE CASES");
        System.out.println("========================");

        // Example 1: Implementing a leaderboard sorted by scores
        TreeMap<Integer, String> leaderboard = new TreeMap<>(Collections.reverseOrder());
        leaderboard.put(1500, "Alice");
        leaderboard.put(3000, "Bob");
        leaderboard.put(2700, "Carol");

        System.out.println("Leaderboard (highest to lowest): " + leaderboard);

        // Example 2: Scheduling tasks by time
        TreeMap<LocalTime, String> schedule = new TreeMap<>();
        schedule.put(LocalTime.of(9, 0), "Team Meeting");
        schedule.put(LocalTime.of(13, 30), "Client Call");
        schedule.put(LocalTime.of(15, 0), "Project Review");

        System.out.println("Schedule: " + schedule);

        // Example 3: Price lookup with range queries
        TreeMap<Double, String> priceMap = new TreeMap<>();
        priceMap.put(9.99, "Basic");
        priceMap.put(19.99, "Standard");
        priceMap.put(29.99, "Premium");

        System.out.println("Prices: " + priceMap);
        System.out.println("Products priced up to 20: " + priceMap.headMap(20.0));

        System.out.println();
    }

}



























