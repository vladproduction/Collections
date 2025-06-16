package com.vladproduction.collectionsframework.ch04_maps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMapExamples demonstrates key features and usage patterns
 * of Java's ConcurrentHashMap. It covers thread-safe basic operations,
 * atomic methods, iteration behavior, performance characteristics,
 * and best practices for concurrent map use.
 */
public class ConcurrentHashMapExamples {
    public static void main(String[] args) {

        System.out.println("CONCURRENTHASHMAP EXAMPLES");
        System.out.println("==========================\n");

        ConcurrentHashMapExamples demo = new ConcurrentHashMapExamples();

        demo.basicUsage();
        demo.atomicOperations();
        demo.iterationBehavior();
        demo.performanceCharacteristics();
        demo.bulkOperations();
        demo.concurrencyPitfalls();
        demo.bestPractices();
        demo.realWorldUseCases();

    }

    /**
     * Demonstrates basic thread-safe put/get operations.
     * ConcurrentHashMap allows concurrent read and write without locks.
     */
    private void basicUsage() {
        System.out.println("1. BASIC USAGE");
        System.out.println("==============");

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("A", 1);
        map.put("B", 2);

        System.out.println("Initial map: " + map);

        // Concurrent get
        System.out.println("Get value for key 'A': " + map.get("A"));

        // Overwrite value
        map.put("A", 10);
        System.out.println("After updating 'A': " + map);

        // Remove key
        map.remove("B");
        System.out.println("After removing 'B': " + map);

        System.out.println();
    }

    /**
     * Demonstrates atomic operations like computeIfAbsent, merge, and putIfAbsent,
     * which allow thread-safe updates without explicit synchronization.
     */
    private void atomicOperations() {
        System.out.println("2. ATOMIC OPERATIONS");
        System.out.println("====================");

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("A", 1);
        map.put("B", 2);

        System.out.println("Initial map: " + map);

        // putIfAbsent - adds only if key not present
        map.putIfAbsent("A", 10); // won't overwrite
        map.putIfAbsent("C", 3);
        System.out.println("After putIfAbsent: " + map);

        // computeIfAbsent - compute value if absent atomically
        map.computeIfAbsent("D", k -> 4);
        System.out.println("After computeIfAbsent('D'): " + map);

        // merge - atomically merges value if key exists
        map.merge("B", 3, Integer::sum);
        System.out.println("After merge('B', 3, sum): " + map);

        System.out.println();
    }

    /**
     * Shows how iteration over ConcurrentHashMap is weakly consistent,
     * meaning it reflects some but not necessarily all modifications during iteration,
     * and never throws ConcurrentModificationException.
     */
    private void iterationBehavior() {
        System.out.println("3. ITERATION BEHAVIOR");
        System.out.println("=====================");

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        System.out.println("Initial map: " + map);

        System.out.println("Iterating over map entries:");

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
            if ("B".equals(entry.getKey())) {
                System.out.println("  Adding D=4 during iteration");
                map.put("D", 4);  // Modification during iteration
            }
        }

        System.out.println("Map after iteration: " + map);
        System.out.println();
    }

    /**
     * Explains ConcurrentHashMap's performance features, including lock striping
     * which allows concurrent access by segmenting the map to reduce contention.
     * Demonstrates concurrent writes by multiple threads without blocking.
     */
    private void performanceCharacteristics() {
        System.out.println("4. PERFORMANCE CHARACTERISTICS");
        System.out.println("==============================");

        System.out.println("ConcurrentHashMap uses lock striping (segment-level locking) to allow " +
                "multiple threads to access different parts of the map concurrently.");
        System.out.println("This leads to better scalability under high contention compared to synchronized maps.\n");

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        Runnable writer = () -> {
            for (int i = 0; i < 5; i++) {
                String threadName = Thread.currentThread().getName();
                map.put(i, threadName);
                System.out.println(threadName + " put key " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(writer, "Writer-1");
        Thread t2 = new Thread(writer, "Writer-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nFinal map contents: " + map);
        System.out.println();

    }

    /**
     * Demonstrates bulk operations introduced in Java 8, like forEach, search, and reduce,
     * which support parallelism and thread-safe computations on the map.
     */
    private void bulkOperations() {
        System.out.println("5. BULK OPERATIONS");
        System.out.println("==================");

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 5);
        map.put("B", 3);
        map.put("C", 7);
        map.put("D", 2);

        System.out.println("Initial map: " + map);

        System.out.println("forEach to print all entries:");
        map.forEach(1, (key, value) -> System.out.println("  " + key + " -> " + value));

        // Search for a key with value > 5
        String keyWithLargeValue = map.search(1, (k, v) -> v > 5 ? k : null);
        System.out.println("Search result (value > 5): " + keyWithLargeValue);

        // Reduce keys into a concatenated string
        String concatKeys = map.reduceKeys(1, (k1, k2) -> k1 + "," + k2);
        System.out.println("Concatenated keys: " + concatKeys);

        // Reduce values to their sum
        Integer sumValues = map.reduceValues(1, Integer::sum);
        System.out.println("Sum of values: " + sumValues);

        System.out.println();
    }

    /**
     * Highlights common concurrency pitfalls with ConcurrentHashMap,
     * such as the lack of atomicity in compound operations,
     * which require external synchronization if needed.
     */
    private void concurrencyPitfalls() {
        System.out.println("6. CONCURRENCY PITFALLS");
        System.out.println("=======================");

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);

        System.out.println("Initial map: " + map);

        // Compound action: get and then put - not atomic
        Integer oldValue = map.get("A");
        Integer newValue = (oldValue == null) ? 1 : oldValue + 1;
        map.put("A", newValue);

        System.out.println("After non-atomic increment (get + put): " + map);

        // This operation is NOT thread-safe if multiple threads run it concurrently
        System.out.println("Warning: compound actions like 'get + put' are not atomic.");

        // Correct approach using atomic compute method
        map.compute("A", (key, val) -> (val == null) ? 1 : val + 1);
        System.out.println("After atomic compute increment: " + map);

        System.out.println();
    }

    /**
     * Covers best practices such as preferring atomic methods over compound actions,
     * avoiding null keys/values, and leveraging bulk operations for better performance.
     */
    private void bestPractices() {
        System.out.println("7. BEST PRACTICES");
        System.out.println("=================");

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Avoid null keys and values (ConcurrentHashMap doesn't allow them)
        try {
            map.put(null, 1);
        } catch (NullPointerException e) {
            System.out.println("Null keys are not allowed: " + e);
        }

        try {
            map.put("A", null);
        } catch (NullPointerException e) {
            System.out.println("Null values are not allowed: " + e);
        }

        // Prefer atomic methods like compute, merge over separate get/put
        map.put("A", 1);
        map.merge("A", 1, Integer::sum);
        System.out.println("After merge increment: " + map);

        // Use bulk operations for efficiency in large maps
        int sum = map.reduceValuesToInt(1, Integer::intValue, 0, Integer::sum);
        System.out.println("Sum of values with reduceValuesToInt: " + sum);

        System.out.println();
    }

    /**
     * Demonstrates real-world use cases such as caching, counting frequencies,
     * and implementing thread-safe memoization with ConcurrentHashMap.
     */
    private void realWorldUseCases() {
        System.out.println("8. REAL WORLD USE CASES");
        System.out.println("=======================");

        // Example 1: Simple cache with ConcurrentHashMap
        ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        cache.put("key1", "value1");
        System.out.println("Cache lookup key1: " + cache.get("key1"));

        // Example 2: Counting frequencies atomically
        ConcurrentHashMap<String, Integer> frequencies = new ConcurrentHashMap<>();
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};

        for (String word : words) {
            frequencies.merge(word, 1, Integer::sum);
        }
        System.out.println("Word frequencies: " + frequencies);

        // Example 3: Thread-safe memoization pattern
        ConcurrentHashMap<Integer, Integer> memo = new ConcurrentHashMap<>();
        int input = 5;
        int result = memo.computeIfAbsent(input, this::expensiveComputation);
        System.out.println("Memoized computation for " + input + ": " + result);

        System.out.println();
    }

    // Helper method for memoization example
    private int expensiveComputation(int x) {
        // Simulate expensive computation
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return x * x;
    }

}
