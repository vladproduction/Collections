package com.vladproduction.collectionsframework.ch04_maps;

import java.util.*;

/**
 * LinkedHashMap extends HashMap and maintains insertion order (or access order).
 * It uses a doubly-linked list to maintain the ordering of entries.
 * Provides O(1) performance for basic operations while preserving order.
 */
public class LinkedHashMapExamples {
    public static void main(String[] args) {
        System.out.println("LINKEDHASHMAP EXAMPLES");
        System.out.println("======================\n");

        LinkedHashMapExamples demo = new LinkedHashMapExamples();

        demo.insertionOrderMaintenance();
        demo.accessOrderMode();
        demo.performanceComparison();
        demo.lruCacheImplementation();
        demo.iterationPredictability();
        demo.constructorVariations();
        demo.bulkOperations();
        demo.comparisonWithOtherMaps();
        demo.bestPractices();
        demo.realWorldUseCases();

    }

    private void insertionOrderMaintenance() {
        System.out.println("1. INSERTION ORDER MAINTENANCE");
        System.out.println("===============================");

        // LinkedHashMap maintains insertion order
        LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        String[] keys = {"Charlie", "Alice", "Bob", "Diana", "Eve"};

        for (String key : keys) {
            int value = key.length();
            linkedMap.put(key, value);
            hashMap.put(key, value);
        }

        System.out.println("Insertion order: " + Arrays.toString(keys));
        System.out.println("LinkedHashMap:   " + linkedMap.keySet());
        System.out.println("HashMap:         " + hashMap.keySet());

        System.out.println("\nIteration over LinkedHashMap:");
        linkedMap.forEach((key, value) ->
                System.out.print(key + "(" + value + ") ")
        );

        System.out.println("\n\nIteration over HashMap:");
        hashMap.forEach((key, value) ->
                System.out.print(key + "(" + value + ") ")
        );

        System.out.println("\n");
    }

    private void accessOrderMode() {
        System.out.println("2. ACCESS ORDER MODE");
        System.out.println("====================");

        // Create LinkedHashMap with access order
        LinkedHashMap<String, String> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);

        accessOrderMap.put("First", "1st");
        accessOrderMap.put("Second", "2nd");
        accessOrderMap.put("Third", "3rd");
        accessOrderMap.put("Fourth", "4th");

        System.out.println("Initial order: " + accessOrderMap.keySet());

        // Access some elements (get operations)
        System.out.println("Accessing 'Second': " + accessOrderMap.get("Second"));
        System.out.println("After accessing 'Second': " + accessOrderMap.keySet());

        System.out.println("Accessing 'First': " + accessOrderMap.get("First"));
        System.out.println("After accessing 'First': " + accessOrderMap.keySet());

        // put() on existing key also counts as access
        accessOrderMap.put("Third", "3rd updated");
        System.out.println("After updating 'Third': " + accessOrderMap.keySet());

        // Compare with insertion order LinkedHashMap
        LinkedHashMap<String, String> insertionOrderMap = new LinkedHashMap<>();
        insertionOrderMap.putAll(accessOrderMap);

        System.out.println("Insertion order map: " + insertionOrderMap.keySet());
        insertionOrderMap.get("Second"); // Access doesn't change order
        System.out.println("After accessing in insertion order map: " + insertionOrderMap.keySet());
        System.out.println();
    }

    private void performanceComparison() {
        System.out.println("3. PERFORMANCE COMPARISON");
        System.out.println("==========================");

        System.out.println("Performance characteristics:");
        System.out.println("LinkedHashMap: O(1) for basic operations + linked list overhead");
        System.out.println("HashMap: O(1) for basic operations");
        System.out.println("TreeMap: O(log n) for basic operations");

        // Performance test
        int size = 50000;

        // LinkedHashMap performance
        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>();
        long startTime = System.nanoTime();

        for (int i = 0; i < size; i++) {
            linkedMap.put(i, "Value" + i);
        }

        long linkedInsertTime = System.nanoTime() - startTime;

        // HashMap performance
        HashMap<Integer, String> hashMap = new HashMap<>();
        startTime = System.nanoTime();

        for (int i = 0; i < size; i++) {
            hashMap.put(i, "Value" + i);
        }

        long hashInsertTime = System.nanoTime() - startTime;

        System.out.println("Insertion of " + size + " elements:");
        System.out.println("LinkedHashMap: " + linkedInsertTime / 1_000_000 + " ms");
        System.out.println("HashMap: " + hashInsertTime / 1_000_000 + " ms");

        // Iteration performance
        startTime = System.nanoTime();
        for (Map.Entry<Integer, String> entry : linkedMap.entrySet()) {
            // Just iterate
        }
        long linkedIterTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            // Just iterate
        }
        long hashIterTime = System.nanoTime() - startTime;

        System.out.println("Iteration over " + size + " elements:");
        System.out.println("LinkedHashMap: " + linkedIterTime / 1_000_000 + " ms");
        System.out.println("HashMap: " + hashIterTime / 1_000_000 + " ms");
        System.out.println();
    }

    private void lruCacheImplementation() {
        System.out.println("4. LRU CACHE IMPLEMENTATION");
        System.out.println("============================");

        //Simple LRU cache using LinkedHashMap
        LRUCache<String, String> cache = new LRUCache<>(3);

        System.out.println("Adding items to LRU cache (capacity: 3):");
        cache.put("A", "Value A");
        System.out.println("After adding A: " + cache.keySet());

        cache.put("B", "Value B");
        System.out.println("After adding B: " + cache.keySet());

        cache.put("C", "Value C");
        System.out.println("After adding C: " + cache.keySet());

        cache.put("D", "Value D"); // Should evict A
        System.out.println("After adding D: " + cache.keySet());

        // Access B (moves it to end)
        System.out.println("Accessing B: " + cache.get("B"));
        System.out.println("After accessing B: " + cache.keySet());

        cache.put("E", "Value E"); // Should evict C (least recently used)
        System.out.println("After adding E: " + cache.keySet());

        System.out.println("Final cache contents:");
        cache.forEach((key, value) -> System.out.println("  " + key + " -> " + value));
        System.out.println();
    }

    //helper class
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true); // true = access-order
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }

        public Set<K> keySet() {
            return super.keySet(); // just exposing to print nicely in order
        }
    }

    private void iterationPredictability() {
        System.out.println("5. ITERATION PREDICTABILITY");
        System.out.println("============================");

        LinkedHashMap<String, Integer> predictableMap = new LinkedHashMap<>();
        HashMap<String, Integer> unpredictableMap = new HashMap<>();

        String[] data = {"Gamma", "Alpha", "Beta", "Delta", "Epsilon"};

        for (String item : data) {
            predictableMap.put(item, item.length());
            unpredictableMap.put(item, item.length());
        }

        System.out.println("Original insertion order: " + Arrays.toString(data));

        System.out.println("\nLinkedHashMap iterations (always same order):");
        for (int i = 1; i <= 3; i++) {
            System.out.print("Iteration " + i + ": ");
            predictableMap.keySet().forEach(key -> System.out.print(key + " "));
            System.out.println();
        }

        System.out.println("\nHashMap iterations (order may vary):");
        for (int i = 1; i <= 3; i++) {
            System.out.print("Iteration " + i + ": ");
            unpredictableMap.keySet().forEach(key -> System.out.print(key + " "));
            System.out.println();
        }

        System.out.println("\nLinkedHashMap guarantees consistent iteration order!");
        System.out.println();
    }

    private void constructorVariations() {
        System.out.println("6. CONSTRUCTOR VARIATIONS");
        System.out.println("==========================");

        // Default constructor (insertion order)
        LinkedHashMap<String, String> default1 = new LinkedHashMap<>();
        System.out.println("Default constructor: insertion order, capacity=16, loadFactor=0.75");

        // Constructor with initial capacity
        LinkedHashMap<String, String> withCapacity = new LinkedHashMap<>(32);
        System.out.println("With capacity: insertion order, capacity=32, loadFactor=0.75");

        // Constructor with capacity and load factor
        LinkedHashMap<String, String> withCapacityAndLoad = new LinkedHashMap<>(32, 0.8f);
        System.out.println("With capacity and load factor: insertion order, capacity=32, loadFactor=0.8");

        // Constructor with access order
        LinkedHashMap<String, String> accessOrder = new LinkedHashMap<>(16, 0.75f, true);
        System.out.println("Access order: capacity=16, loadFactor=0.75, accessOrder=true");

        // Constructor from existing map
        Map<String, String> sourceMap = Map.of("A", "1", "B", "2", "C", "3");
        LinkedHashMap<String, String> fromMap = new LinkedHashMap<>(sourceMap);
        System.out.println("From existing map: " + fromMap);

        // Demonstrate the difference
        accessOrder.put("X", "1");
        accessOrder.put("Y", "2");
        accessOrder.put("Z", "3");

        System.out.println("Access order before access: " + accessOrder.keySet());
        accessOrder.get("X"); // Access X
        System.out.println("Access order after accessing X: " + accessOrder.keySet());
        System.out.println();
    }

    private void bulkOperations() {
        System.out.println("7. BULK OPERATIONS");
        System.out.println("==================");

        LinkedHashMap<String, Integer> base = new LinkedHashMap<>();
        base.put("A", 1);
        base.put("B", 2);
        base.put("C", 3);

        Map<String, Integer> additional = Map.of("D", 4, "E", 5, "B", 20); // B will be updated

        System.out.println("Base map: " + base);
        System.out.println("Additional map: " + additional);

        // putAll maintains order of existing keys, adds new ones at end
        LinkedHashMap<String, Integer> merged = new LinkedHashMap<>(base);
        merged.putAll(additional);
        System.out.println("After putAll: " + merged);
        System.out.println("Key order: " + merged.keySet());

        // Bulk operations with Java 8 methods
        LinkedHashMap<String, Integer> computed = new LinkedHashMap<>();
        computed.put("X", 10);
        computed.put("Y", 20);
        computed.put("Z", 30);

        System.out.println("Before replaceAll: " + computed);
        computed.replaceAll((key, value) -> value * 2);
        System.out.println("After replaceAll (* 2): " + computed);
        System.out.println("Order preserved: " + computed.keySet());

        // Conditional operations preserve order
        computed.putIfAbsent("W", 40);
        System.out.println("After putIfAbsent: " + computed);
        System.out.println("Order with new element: " + computed.keySet());

        // computeIfAbsent - only adds if key is missing
        computed.computeIfAbsent("V", k -> 100);
        System.out.println("After computeIfAbsent (V): " + computed);

        // computeIfPresent - modifies existing key
        computed.computeIfPresent("Y", (k, v) -> v + 5);
        System.out.println("After computeIfPresent (Y): " + computed);

        // merge - adds or updates
        computed.merge("X", 50, Integer::sum); // X already exists
        computed.merge("U", 10, Integer::sum); // U is new
        System.out.println("After merge (X, U): " + computed);

        // clear operation
        System.out.println("Clearing map...");
        computed.clear();
        System.out.println("After clear, isEmpty = " + computed.isEmpty());
        System.out.println();
    }

    private void comparisonWithOtherMaps() {
        System.out.println("8. COMPARISON WITH OTHER MAPS");
        System.out.println("==============================");

        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("C", 3);
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        System.out.println("HashMap (no order guarantee): " + hashMap.keySet());

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("C", 3);
        linkedHashMap.put("A", 1);
        linkedHashMap.put("B", 2);
        System.out.println("LinkedHashMap (insertion order): " + linkedHashMap.keySet());

        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("C", 3);
        treeMap.put("A", 1);
        treeMap.put("B", 2);
        System.out.println("TreeMap (sorted by key): " + treeMap.keySet());

        System.out.println("\nUse cases:");
        System.out.println("HashMap         - Fastest lookup, no order.");
        System.out.println("LinkedHashMap   - Predictable iteration (insertion or access order).");
        System.out.println("TreeMap         - Natural or custom sorted keys.");
        System.out.println();
    }

    private void bestPractices() {
        System.out.println("9. BEST PRACTICES");
        System.out.println("=================");

        System.out.println("- Use LinkedHashMap when predictable iteration order is required.");
        System.out.println("- For access-order (like LRU cache), enable accessOrder=true.");
        System.out.println("- Prefer LinkedHashMap over HashMap if order consistency matters.");
        System.out.println("- Avoid using it for sorting — TreeMap or streams are better for that.");
        System.out.println("- Capacity planning: set initial capacity if many entries are expected to reduce rehashing.");
        System.out.println("- When overriding removeEldestEntry for custom eviction, document it clearly.");
        System.out.println("- Be mindful of memory: entries are not weakly referenced like WeakHashMap.");
        System.out.println("- Iterate using entrySet() when keys and values are both needed — it's more efficient.");
        System.out.println();
    }

    private void realWorldUseCases(){
        System.out.println("10. REAL-WORLD USE CASES");
        System.out.println("========================");

        // 1. Maintaining insertion order in JSON-like serialization
        LinkedHashMap<String, Object> jsonData = new LinkedHashMap<>();
        jsonData.put("id", 101);
        jsonData.put("name", "Alice");
        jsonData.put("email", "alice@example.com");
        System.out.println("Ordered data for JSON serialization: " + jsonData);

        // 2. Implementing a simple LRU cache
        LRUCache<String, String> simpleCache = new LRUCache<>(2);
        simpleCache.put("A", "Alpha");
        simpleCache.put("B", "Beta");
        simpleCache.get("A"); // access A
        simpleCache.put("C", "Gamma"); // evicts B
        System.out.println("LRU Cache contents (access-order): " + simpleCache);

        // 3. Menu or UI component ordering
        LinkedHashMap<String, String> menuItems = new LinkedHashMap<>();
        menuItems.put("home", "Home");
        menuItems.put("profile", "Profile");
        menuItems.put("settings", "Settings");
        System.out.println("Menu display order: " + menuItems.keySet());

        // 4. Form field validations in fixed order
        LinkedHashMap<String, String> formErrors = new LinkedHashMap<>();
        formErrors.put("username", "Username is required");
        formErrors.put("password", "Password must be at least 8 characters");
        formErrors.put("email", "Invalid email format");
        System.out.println("Form errors in input order:");
        formErrors.forEach((field, error) -> System.out.println(" - " + field + ": " + error));

        System.out.println();
    }



}
