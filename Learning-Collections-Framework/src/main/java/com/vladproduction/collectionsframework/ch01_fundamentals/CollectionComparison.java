package com.vladproduction.collectionsframework.ch01_fundamentals;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Comprehensive comparison of different collection types
 * Covers: Performance characteristics, use cases, and trade-offs
 */
public class CollectionComparison {
    public static void main(String[] args) {

        CollectionComparison demo = new CollectionComparison();

        System.out.println("\n=== Collection Comparison ===\n");

        demo.compareListImplementations();
        demo.compareSetImplementations();
        demo.compareMapImplementations();
        demo.compareQueueImplementations();
        demo.performanceComparison();
        demo.memoryUsageComparison();

        printUsageGuidelines();

    }

    /**
     * Comparisons of different list implementations:
     * */
    private void compareListImplementations() {
        System.out.println("1. LIST IMPLEMENTATIONS COMPARISON");
        System.out.println("===================================");

        //create ArrayList, LinkedList, Vector
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        List<String> vector = new Vector<>();

        System.out.println("ArrayList:");
        System.out.println("  - Based on dynamic array");
        System.out.println("  - Random access: O(1)");
        System.out.println("  - Insertion/Deletion at end: O(1) amortized");
        System.out.println("  - Insertion/Deletion at beginning/middle: O(n)");
        System.out.println("  - Memory efficient");
        System.out.println("  - Not thread-safe");

        System.out.println("\nLinkedList:");
        System.out.println("  - Based on doubly-linked list");
        System.out.println("  - Random access: O(n)");
        System.out.println("  - Insertion/Deletion at ends: O(1)");
        System.out.println("  - Insertion/Deletion at middle: O(1) if node reference available");
        System.out.println("  - Higher memory overhead (node objects)");
        System.out.println("  - Implements Deque interface");
        System.out.println("  - Not thread-safe");

        System.out.println("\nVector:");
        System.out.println("  - Legacy class, similar to ArrayList");
        System.out.println("  - Synchronized (thread-safe)");
        System.out.println("  - Performance overhead due to synchronization");
        System.out.println("  - Generally avoid in favor of ArrayList + Collections.synchronizedList()");

        demonstrateListOperations(arrayList, linkedList, vector);
        System.out.println();
    }

    /**
     * Demonstrates operations on different list types
     * */
    private void demonstrateListOperations(List<String> arrayList, List<String> linkedList, List<String> vector) {

        System.out.println("\nOperation demonstrations:");

        // Adding elements
        String[] elements = {"A", "B", "C", "D", "E"};
        for (String element : elements) {
            arrayList.add(element);
            linkedList.add(element);
            vector.add(element);
        }

        System.out.println("All lists after adding elements: " + Arrays.toString(elements));

        // Random access performance difference
        System.out.println("\nRandom access (get by index):");

        //timing for ArrayList get(index)
        long startTime = System.nanoTime();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i);
        }
        long arrayListTime = System.nanoTime() - startTime;

        //timing for LinkedList get(index)
        startTime = System.nanoTime();
        for (int i = 0; i < linkedList.size(); i++) {
            linkedList.get(i);
        }
        long linkedListTime = System.nanoTime() - startTime;

        System.out.println("ArrayList random access time: " + arrayListTime + " ns");
        System.out.println("LinkedList random access time: " + linkedListTime + " ns");
        System.out.println("ArrayList is " + (linkedListTime / Math.max(arrayListTime, 1)) + " x faster for random access");

    }

    /**
     * Comparisons of different set implementations:
     * */
    private void compareSetImplementations() {
        System.out.println("2. SET IMPLEMENTATIONS COMPARISON");
        System.out.println("=================================");

        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();

        System.out.println("HashSet:");
        System.out.println("  - Based on hash table");
        System.out.println("  - No ordering guarantee");
        System.out.println("  - Add/Remove/Contains: O(1) average, O(n) worst case");
        System.out.println("  - Best performance for basic operations");
        System.out.println("  - Allows one null element");

        System.out.println("\nLinkedHashSet:");
        System.out.println("  - HashSet + linked list for ordering");
        System.out.println("  - Maintains insertion order");
        System.out.println("  - Add/Remove/Contains: O(1) average");
        System.out.println("  - Slightly slower than HashSet");
        System.out.println("  - Higher memory usage");

        System.out.println("\nTreeSet:");
        System.out.println("  - Based on Red-Black tree (balanced BST)");
        System.out.println("  - Maintains sorted order");
        System.out.println("  - Add/Remove/Contains: O(log n)");
        System.out.println("  - Implements NavigableSet");
        System.out.println("  - Does not allow null elements");
        System.out.println("  - Elements must be Comparable or use Comparator");

        demonstrateSetOrdering(hashSet, linkedHashSet, treeSet);
        System.out.println();
    }

    /**
     * Demonstrates ordering behavior of different sets
     * */
    private void demonstrateSetOrdering(Set<String> hashSet, Set<String> linkedHashSet, Set<String> treeSet) {

        String[] elements = {"Zebra", "Apple", "Dog", "Cat", "Elephant", "Bird"};

        // Add elements to all sets
        for (String element : elements) {
            hashSet.add(element);
            linkedHashSet.add(element);
            treeSet.add(element);
        }

        System.out.println("\nInsertion order: " + Arrays.toString(elements));
        System.out.println("HashSet order: " + hashSet);
        System.out.println("LinkedHashSet order: " + linkedHashSet);
        System.out.println("TreeSet order: " + treeSet);

        // TreeSet additional operations
        TreeSet<String> navigableSet = (TreeSet<String>) treeSet;
        System.out.println("\nTreeSet navigation methods:");
        System.out.println("First: " + navigableSet.first());
        System.out.println("Last: " + navigableSet.last());
        System.out.println("Lower than 'Dog': " + navigableSet.lower("Dog"));
        System.out.println("Higher than 'Dog': " + navigableSet.higher("Dog"));
        System.out.println("Subset from 'Bird' to 'Dog': " + navigableSet.subSet("Bird", "Dog"));

    }

    /**
     * Comparison of different map implementations:
     * */
    private void compareMapImplementations() {
        System.out.println("3. MAP IMPLEMENTATIONS COMPARISON");
        System.out.println("=================================");

        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();
        Map<String, Integer> hashtable = new Hashtable<>();

        System.out.println("HashMap:");
        System.out.println("  - Based on hash table");
        System.out.println("  - No ordering guarantee");
        System.out.println("  - Put/Get/Remove: O(1) average, O(n) worst case");
        System.out.println("  - Allows one null key and multiple null values");
        System.out.println("  - Not thread-safe");

        System.out.println("\nLinkedHashMap:");
        System.out.println("  - HashMap + linked list for ordering");
        System.out.println("  - Maintains insertion or access order");
        System.out.println("  - Put/Get/Remove: O(1) average");
        System.out.println("  - Can be used for LRU cache implementation");

        System.out.println("\nTreeMap:");
        System.out.println("  - Based on Red-Black tree");
        System.out.println("  - Maintains sorted order of keys");
        System.out.println("  - Put/Get/Remove: O(log n)");
        System.out.println("  - Implements NavigableMap");
        System.out.println("  - Keys must be Comparable or use Comparator");

        System.out.println("\nHashtable:");
        System.out.println("  - Legacy class, similar to HashMap");
        System.out.println("  - Synchronized (thread-safe)");
        System.out.println("  - Does not allow null keys or values");
        System.out.println("  - Generally avoid in favor of HashMap + ConcurrentHashMap");

        demonstrateMapOrdering(hashMap, linkedHashMap, treeMap);
        System.out.println();
    }

    /**
     * Demonstrates ordering behavior of different maps
     * */
    private void demonstrateMapOrdering(Map<String, Integer> hashMap, Map<String, Integer> linkedHashMap, Map<String, Integer> treeMap) {

        String[] keys = {"Charlie", "Alice", "Bob", "David", "Eve"};

        // Add elements to all maps
        for (int i = 0; i < keys.length; i++) {
            hashMap.put(keys[i], i + 1);
            linkedHashMap.put(keys[i], i + 1);
            treeMap.put(keys[i], i + 1);
        }

        System.out.println("\nInsertion order: " + Arrays.toString(keys));
        System.out.println("HashMap keys: " + hashMap.keySet());
        System.out.println("LinkedHashMap keys: " + linkedHashMap.keySet());
        System.out.println("TreeMap keys: " + treeMap.keySet());

        // TreeMap additional operations
        TreeMap<String, Integer> navigableMap = (TreeMap<String, Integer>) treeMap;
        System.out.println("\nTreeMap navigation methods:");
        System.out.println("First key: " + navigableMap.firstKey());
        System.out.println("Last key: " + navigableMap.lastKey());
        System.out.println("Keys before 'Charlie': " + navigableMap.headMap("Charlie"));
        System.out.println("Keys from 'Charlie': " + navigableMap.tailMap("Charlie"));
    }

    /**
     * Queue implementations comparisons:
     * */
    private void compareQueueImplementations(){
        System.out.println("4. QUEUE IMPLEMENTATIONS COMPARISON");
        System.out.println("===================================");

        Queue<String> linkedListQueue = new LinkedList<>();
        Queue<String> arrayDeque = new ArrayDeque<>();
        Queue<String> priorityQueue = new PriorityQueue<>();
        Queue<String> concurrentQueue = new ConcurrentLinkedQueue<>();

        System.out.println("LinkedList (as Queue):");
        System.out.println("  - Doubly-linked list implementation");
        System.out.println("  - FIFO operations: O(1)");
        System.out.println("  - Can be used as Queue or Deque");
        System.out.println("  - Higher memory overhead");

        System.out.println("\nArrayDeque:");
        System.out.println("  - Resizable array implementation");
        System.out.println("  - Better performance than LinkedList for queue operations");
        System.out.println("  - Can be used as Queue or Deque");
        System.out.println("  - No capacity restrictions");
        System.out.println("  - Not thread-safe");

        System.out.println("\nPriorityQueue:");
        System.out.println("  - Heap-based priority queue");
        System.out.println("  - Elements ordered by priority (natural ordering or Comparator)");
        System.out.println("  - Add/Remove: O(log n)");
        System.out.println("  - Peek: O(1)");
        System.out.println("  - Not thread-safe");

        System.out.println("\nConcurrentLinkedQueue:");
        System.out.println("  - Lock-free, thread-safe implementation");
        System.out.println("  - Based on linked nodes");
        System.out.println("  - Wait-free for producer-consumer scenarios");
        System.out.println("  - Size() operation is O(n)");

        demonstrateQueueBehavior(linkedListQueue, arrayDeque, priorityQueue);
        System.out.println();
    }

    /**
     * Demonstrates behavior of different queue types
     */
    private void demonstrateQueueBehavior(Queue<String> linkedListQueue, Queue<String> arrayDeque, Queue<String> priorityQueue) {

        String[] elements = {"Third", "First", "Second", "Fourth", "Fifth"};

        //add elements to all queues
        for (int i = 0; i < elements.length; i++) {
            linkedListQueue.add(elements[i]);
            arrayDeque.add(elements[i]);
            priorityQueue.add(elements[i]);
        }

        System.out.println("\nInsertion order: " + Arrays.toString(elements));
        System.out.println("LinkedList queue: " + linkedListQueue);
        System.out.println("ArrayDeque: " + arrayDeque);
        System.out.println("PriorityQueue: " + priorityQueue);

        System.out.println("\nPooling order:");
        System.out.println("LinkedList: ");
        Queue<String> temp1 = new LinkedList<>(linkedListQueue);
        while (!temp1.isEmpty()){
            System.out.print(temp1.poll() + " ");
        }
        System.out.println();

        System.out.println("\nArrayDeque: ");
        Queue<String> temp2 = new ArrayDeque<>(arrayDeque);
        while (!temp2.isEmpty()){
            System.out.print(temp2.poll() + " ");
        }
        System.out.println();

        System.out.println("\nPriorityQueue: ");
        Queue<String> temp3 = new PriorityQueue<>(priorityQueue);
        while (!temp3.isEmpty()) {
            System.out.print(temp3.poll() + " ");
        }
        System.out.println();

    }

    /**
     * Performance comparison between different collection types
     * */
    private void performanceComparison(){
        System.out.println("5. PERFORMANCE COMPARISON");
        System.out.println("=========================");

        int size = 100_000;

        System.out.println("\nAdding " + size + " elements");

        //List performance comparison by add operation:

        long start = System.currentTimeMillis();
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        long arrayListTime = System.currentTimeMillis() - start;

        System.out.println("ArrayList time to add elements: " + arrayListTime + "ms");

        start = System.currentTimeMillis();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
             linkedList.add(i);
        }
        long linkedListTime = System.currentTimeMillis() - start;
        System.out.println("LinkedList time to add elements: " + linkedListTime + "ms");

        //Set performance comparison by add operation:
        start = System.currentTimeMillis();
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            hashSet.add(i);
        }
        long hashSetTime = System.currentTimeMillis() - start;
        System.out.println("HashSet time to add elements: " + hashSetTime + "ms");

        start = System.currentTimeMillis();
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            treeSet.add(i);
        }
        long treeSetTime = System.currentTimeMillis() - start;
        System.out.println("TreeSet time to add elements: " + treeSetTime + "ms");

        //random access performance
        System.out.println("\nRandom access performance (1000 lookups):");
        Random random = new Random(42);    //fixed seed for a consistent result

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
             arrayList.get(random.nextInt(arrayList.size()));
        }
        long arrayListAccessTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.get(random.nextInt(linkedList.size()));
        }
        long linkedListAccessTime = System.nanoTime() - start;

        System.out.println("ArrayList random access: " + arrayListAccessTime / 1000 + " ns per operation");
        System.out.println("LinkedList random access: " + linkedListAccessTime / 1000 + " ns per operation");

        // Contains operation performance
        System.out.println("\nContains operation performance (1000 searches):");
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.contains(random.nextInt(size));
        }
        long arrayListContainsTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            hashSet.contains(random.nextInt(size));
        }
        long hashSetContainsTime = System.nanoTime() - start;

        System.out.println("ArrayList contains: " + arrayListContainsTime / 1000 + " ns per operation");
        System.out.println("HashSet contains: " + hashSetContainsTime / 1000 + " ns per operation");
        System.out.println();

    }

    /**
     * Memory usage comparison
     */
    private void memoryUsageComparison(){
        System.out.println("6. MEMORY USAGE CONSIDERATIONS");
        System.out.println("==============================");

        System.out.println("ArrayList:");
        System.out.println("  - Stores elements in array");
        System.out.println("  - Memory overhead: ~50% when capacity > size");
        System.out.println("  - Compact storage, good cache locality");

        System.out.println("\nLinkedList:");
        System.out.println("  - Each element wrapped in Node object");
        System.out.println("  - Node overhead: ~24 bytes per element (on 64-bit JVM)");
        System.out.println("  - Poor cache locality due to scattered memory");

        System.out.println("\nHashSet/HashMap:");
        System.out.println("  - Hash table with linked lists/trees for collisions");
        System.out.println("  - Load factor affects memory usage");
        System.out.println("  - Default load factor: 0.75");

        System.out.println("\nTreeSet/TreeMap:");
        System.out.println("  - Tree nodes with parent/child references");
        System.out.println("  - Higher memory overhead than hash-based collections");
        System.out.println("  - Memory usage proportional to number of elements");

        // Practical memory usage example
        demonstrateMemoryUsage();
        System.out.println();
    }

    /**
     * Demonstrates practical memory usage differences
     */
    private void demonstrateMemoryUsage(){
        System.out.println("\nMemory usage estimation for 1M integers:");

        // Rough estimates for 64-bit JVM
        int elements = 1_000_000;
        int integerObjectSize = 16; // Integer object overhead
        int arrayListElementSize = 4; // Reference size
        int linkedListNodeSize = 24; // Node overhead + references
        int hashSetBucketOverhead = 4; // Rough estimate

        long arrayListMemory = elements * (arrayListElementSize + integerObjectSize);
        long linkedListMemory = elements * (linkedListNodeSize + integerObjectSize);
        long hashSetMemory = elements * (hashSetBucketOverhead + integerObjectSize) * 4/3; // Load factor

        System.out.println("ArrayList: ~" + (arrayListMemory / 1024 / 1024) + " MB");
        System.out.println("LinkedList: ~" + (linkedListMemory / 1024 / 1024) + " MB");
        System.out.println("HashSet: ~" + (hashSetMemory / 1024 / 1024) + " MB");

        System.out.println("\nNote: These are rough estimates. Actual memory usage");
        System.out.println("depends on JVM implementation, object padding, and GC overhead.");

    }

    /**
     * Summary of when to use which collection
     */
    public static void printUsageGuidelines() {
        System.out.println("7. USAGE GUIDELINES");
        System.out.println("===================");

        System.out.println("Use ArrayList when:");
        System.out.println("  - You need fast random access by index");
        System.out.println("  - You do more reading than inserting/deleting");
        System.out.println("  - Memory efficiency is important");

        System.out.println("\nUse LinkedList when:");
        System.out.println("  - You frequently insert/delete at beginning or middle");
        System.out.println("  - You need to implement Queue/Deque operations");
        System.out.println("  - You don't need random access");

        System.out.println("\nUse HashSet when:");
        System.out.println("  - You need to ensure uniqueness");
        System.out.println("  - You need fast contains() operations");
        System.out.println("  - Order doesn't matter");

        System.out.println("\nUse TreeSet when:");
        System.out.println("  - You need sorted unique elements");
        System.out.println("  - You need range operations (subSet, headSet, tailSet)");
        System.out.println("  - You can accept O(log n) performance");

        System.out.println("\nUse HashMap when:");
        System.out.println("  - You need key-value associations");
        System.out.println("  - You need fast lookups by key");
        System.out.println("  - Order doesn't matter");

        System.out.println("\nUse TreeMap when:");
        System.out.println("  - You need sorted key-value pairs");
        System.out.println("  - You need range operations on keys");
        System.out.println("  - You can accept O(log n) performance");

        System.out.println("\nUse ArrayDeque when:");
        System.out.println("  - You need Queue/Deque operations");
        System.out.println("  - Better performance than LinkedList for queue operations");

        System.out.println("\nUse PriorityQueue when:");
        System.out.println("  - You need elements processed by priority");
        System.out.println("  - You're implementing algorithms like Dijkstra's or A*");
    }

}























