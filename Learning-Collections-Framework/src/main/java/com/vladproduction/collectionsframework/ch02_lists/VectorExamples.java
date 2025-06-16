package com.vladproduction.collectionsframework.ch02_lists;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Comprehensive examples of Vector usage, features, and best practices
 * Covers: Basic operations, synchronization, capacity management, performance characteristics, and legacy features
 */
public class VectorExamples {

    public static void main(String[] args) {

        VectorExamples demo = new VectorExamples();

        System.out.println("\n=== VECTOR EXAMPLES ===");

        demo.basicOperations();
        demo.capacityManagement();
        demo.synchronizationFeatures();
        demo.performanceCharacteristics();
        demo.searchingAndSorting();
        demo.advancedOperations();
        demo.vectorVsArrayList();
        demo.legacyMethods();
        demo.commonPitfalls();
        demo.bestPractices();

    }

    /**
     * Demonstrates basic Vector operations
     */
    private void basicOperations() {
        System.out.println("\n1. BASIC VECTOR OPERATIONS");
        System.out.println("==========================");

        // Create Vector
        Vector<String> fruits = new Vector<>();
        System.out.println("Creating Vector: " + fruits);
        System.out.println("Initial size: " + fruits.size());
        System.out.println("Initial capacity: " + fruits.capacity());
        System.out.println("Is empty: " + fruits.isEmpty());

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        System.out.println("\nVector after adding: " + fruits);
        System.out.println("Capacity after adding 3 elements: " + fruits.capacity());

        // Add element at specific index
        fruits.add(1, "Grape");
        System.out.println("After inserting 'Grape' at index 1: " + fruits);

        // Vector-specific addition methods
        fruits.addElement("Mango");  // Legacy method
        fruits.insertElementAt("Kiwi", 2);  // Legacy method
        System.out.println("After using legacy add methods: " + fruits);

        // Accessing elements
        System.out.println("\nAccessing elements:");
        System.out.println("Element at index 0: " + fruits.get(0));
        System.out.println("Element at index 0 (legacy): " + fruits.elementAt(0));
        System.out.println("First element: " + fruits.firstElement());
        System.out.println("Last element: " + fruits.lastElement());

        // Modify element
        String oldValue = fruits.set(2, "Strawberry");
        System.out.println("\nReplaced element: " + oldValue + " with 'Strawberry': " + fruits);

        // Legacy modify method
        fruits.setElementAt("Blueberry", 3);
        System.out.println("After legacy setElementAt: " + fruits);

        // Finding elements
        System.out.println("\nSearching elements:");
        System.out.println("Index of 'Banana': " + fruits.indexOf("Banana"));
        System.out.println("Index of 'Banana' (legacy): " + fruits.indexOf("Banana", 0));
        System.out.println("Contains 'Apple': " + fruits.contains("Apple"));

        // Removing elements
        System.out.println("\nRemoving elements:");
        fruits.remove("Grape");
        System.out.println("After removing 'Grape': " + fruits);
        String removed = fruits.remove(1);
        System.out.println("Removed element at index 1 ('" + removed + "'): " + fruits);

        // Legacy removal methods
        fruits.removeElement("Kiwi");
        System.out.println("After removeElement('Kiwi'): " + fruits);
        fruits.removeElementAt(2);
        System.out.println("After removeElementAt(2): " + fruits);

        System.out.println("Final size: " + fruits.size());
        System.out.println("Final capacity: " + fruits.capacity());
        System.out.println();
    }

    /**
     * Demonstrates Vector capacity management
     */
    private void capacityManagement() {
        System.out.println("2. CAPACITY MANAGEMENT");
        System.out.println("======================");

        // Default capacity is 10 for Vector
        Vector<String> defaultVector = new Vector<>();
        System.out.println("Default Vector capacity: " + defaultVector.capacity());

        // Custom initial capacity
        Vector<String> customCapacity = new Vector<>(20);
        System.out.println("Custom capacity Vector: " + customCapacity.capacity());

        // Custom capacity with increment
        Vector<String> customIncrement = new Vector<>(10, 5);
        System.out.println("Vector with capacity 10 and increment 5: " + customIncrement.capacity());

        // Creating from another collection
        List<Integer> sourceData = Arrays.asList(1, 2, 3, 4, 5);
        Vector<Integer> fromCollection = new Vector<>(sourceData);
        System.out.println("\nVector created from collection: " + fromCollection);
        System.out.println("Capacity: " + fromCollection.capacity());

        // Demonstrating capacity growth
        System.out.println("\nCapacity growth demonstration:");
        Vector<Integer> growthDemo = new Vector<>(2, 3); // capacity 2, increment 3
        System.out.println("Initial capacity: " + growthDemo.capacity());

        for (int i = 0; i < 10; i++) {
            growthDemo.add(i);
            if (i == 1 || i == 4 || i == 7) {
                System.out.println("After adding " + (i + 1) + " elements, capacity: " + growthDemo.capacity());
            }
        }

        // Manual capacity management
        Vector<Integer> manualCapacity = new Vector<>();
        manualCapacity.ensureCapacity(100);
        System.out.println("\nAfter ensureCapacity(100): " + manualCapacity.capacity());

        // Adding elements
        for (int i = 0; i < 5; i++) {
            manualCapacity.add(i);
        }
        System.out.println("Size: " + manualCapacity.size() + ", Capacity: " + manualCapacity.capacity());

        // Trim to size
        manualCapacity.trimToSize();
        System.out.println("After trimToSize - Size: " + manualCapacity.size() + ", Capacity: " + manualCapacity.capacity());

        // Capacity increment demonstration
        System.out.println("\nCapacity increment comparison:");
        Vector<Integer> defaultIncrement = new Vector<>(5); // doubles capacity
        Vector<Integer> fixedIncrement = new Vector<>(5, 2); // adds 2 each time

        System.out.println("Default increment (doubles):");
        for (int i = 0; i < 12; i++) {
            defaultIncrement.add(i);
            if (i == 4 || i == 9 || i == 11) {
                System.out.println("  Size: " + defaultIncrement.size() + ", Capacity: " + defaultIncrement.capacity());
            }
        }

        System.out.println("Fixed increment (+2):");
        for (int i = 0; i < 12; i++) {
            fixedIncrement.add(i);
            if (i == 4 || i == 6 || i == 8 || i == 10) {
                System.out.println("  Size: " + fixedIncrement.size() + ", Capacity: " + fixedIncrement.capacity());
            }
        }
        System.out.println();
    }

    /**
     * Demonstrates Vector's synchronization features
     */
    private void synchronizationFeatures() {
        System.out.println("3. SYNCHRONIZATION FEATURES");
        System.out.println("============================");

        Vector<Integer> sharedVector = new Vector<>();
        final int NUM_THREADS = 5;
        final int ELEMENTS_PER_THREAD = 1000;

        System.out.println("Vector is thread-safe (synchronized)");
        System.out.println("Testing concurrent access with " + NUM_THREADS + " threads");

        // Demonstrate thread safety
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Add elements concurrently
        System.out.println("\nConcurrent addition test:");
        long start = System.currentTimeMillis();

        for (int t = 0; t < NUM_THREADS; t++) {
            final int threadId = t;
            executor.submit(() -> {
                for (int i = 0; i < ELEMENTS_PER_THREAD; i++) {
                    sharedVector.add(threadId * ELEMENTS_PER_THREAD + i);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long end = System.currentTimeMillis();
        System.out.println("Added " + sharedVector.size() + " elements in " + (end - start) + "ms");
        System.out.println("Expected: " + (NUM_THREADS * ELEMENTS_PER_THREAD) + " elements");

        // Demonstrate synchronized iteration
        System.out.println("\nSynchronized iteration:");
        synchronized (sharedVector) {
            System.out.println("First 10 elements: ");
            for (int i = 0; i < Math.min(10, sharedVector.size()); i++) {
                System.out.print(sharedVector.get(i) + " ");
            }
            System.out.println();
        }

        // Using Enumeration (thread-safe iteration)
        System.out.println("\nUsing Enumeration for thread-safe iteration:");
        Enumeration<Integer> enumeration = sharedVector.elements();
        int count = 0;
        System.out.print("First 10 via Enumeration: ");
        while (enumeration.hasMoreElements() && count < 10) {
            System.out.print(enumeration.nextElement() + " ");
            count++;
        }
        System.out.println();

        // Performance comparison with ArrayList + synchronization
        System.out.println("\nPerformance comparison:");
        testConcurrentPerformance();
        System.out.println();
    }

    private void testConcurrentPerformance() {
        final int OPERATIONS = 10000;
        final int THREADS = 4;

        // Vector (built-in synchronization)
        Vector<Integer> vector = new Vector<>();
        long start = System.currentTimeMillis();

        ExecutorService executor1 = Executors.newFixedThreadPool(THREADS);
        for (int t = 0; t < THREADS; t++) {
            executor1.submit(() -> {
                for (int i = 0; i < OPERATIONS / THREADS; i++) {
                    vector.add(i);
                }
            });
        }
        executor1.shutdown();
        try {
            executor1.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long vectorTime = System.currentTimeMillis() - start;

        // ArrayList with Collections.synchronizedList
        List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());
        start = System.currentTimeMillis();

        ExecutorService executor2 = Executors.newFixedThreadPool(THREADS);
        for (int t = 0; t < THREADS; t++) {
            executor2.submit(() -> {
                for (int i = 0; i < OPERATIONS / THREADS; i++) {
                    syncList.add(i);
                }
            });
        }
        executor2.shutdown();
        try {
            executor2.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long syncListTime = System.currentTimeMillis() - start;

        System.out.println("Vector time: " + vectorTime + "ms");
        System.out.println("Synchronized ArrayList time: " + syncListTime + "ms");
        System.out.println("Vector elements: " + vector.size());
        System.out.println("SyncList elements: " + syncList.size());
    }

    /**
     * Demonstrates performance characteristics
     */
    private void performanceCharacteristics() {
        System.out.println("4. PERFORMANCE CHARACTERISTICS");
        System.out.println("===============================");

        Vector<Integer> vector = new Vector<>();

        // Adding at end - O(1) amortized
        System.out.println("\nAdding elements at end (O(1) amortized):");
        long start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            vector.add(i);
        }
        long end = System.nanoTime();
        System.out.println("Added 10,000 elements in: " + (end - start) / 1_000_000 + "ms");

        // Random access - O(1)
        System.out.println("\nRandom access (O(1)):");
        start = System.nanoTime();
        Random random = new Random(42);
        for (int i = 0; i < 1000; i++) {
            vector.get(random.nextInt(vector.size()));
        }
        end = System.nanoTime();
        System.out.println("1,000 random access operations in: " + (end - start) / 1_000 + " μs");

        // Insertion at beginning - O(n)
        System.out.println("\nInsertion at beginning (O(n)):");
        Vector<Integer> insertionDemo = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            insertionDemo.add(i);
        }
        start = System.nanoTime();
        insertionDemo.add(0, -1);
        end = System.nanoTime();
        System.out.println("Insertion at beginning took: " + (end - start) / 1_000 + " μs");

        // Search operation - O(n)
        System.out.println("\nLinear search (O(n)):");
        start = System.nanoTime();
        vector.contains(9999);
        end = System.nanoTime();
        System.out.println("Linear search took: " + (end - start) / 1_000 + " μs");

        // Synchronization overhead
        System.out.println("\nSynchronization overhead comparison:");
        compareSynchronizationOverhead();

        System.out.println("\nPerformance Summary:");
        System.out.println("- Access by index: O(1) but with synchronization overhead");
        System.out.println("- Add at end: O(1) amortized with synchronization overhead");
        System.out.println("- Add at beginning/middle: O(n) with synchronization overhead");
        System.out.println("- Remove from end: O(1) with synchronization overhead");
        System.out.println("- Remove from beginning/middle: O(n) with synchronization overhead");
        System.out.println("- Search: O(n) with synchronization overhead");
        System.out.println("- All operations are thread-safe but slower than ArrayList");
        System.out.println();
    }

    private void compareSynchronizationOverhead() {
        final int OPERATIONS = 100_000;

        // ArrayList (no synchronization)
        ArrayList<Integer> arrayList = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < OPERATIONS; i++) {
            arrayList.add(i);
        }
        long end = System.nanoTime();
        long arrayListTime = end - start;

        // Vector (with synchronization)
        Vector<Integer> vector = new Vector<>();
        start = System.nanoTime();
        for (int i = 0; i < OPERATIONS; i++) {
            vector.add(i);
        }
        end = System.nanoTime();
        long vectorTime = end - start;

        System.out.println("ArrayList time: " + arrayListTime / 1_000_000 + "ms");
        System.out.println("Vector time: " + vectorTime / 1_000_000 + "ms");
        System.out.println("Overhead: " + ((vectorTime - arrayListTime) * 100 / arrayListTime) + "%");
    }

    /**
     * Demonstrates searching and sorting operations
     */
    private void searchingAndSorting() {
        System.out.println("5. SEARCHING AND SORTING");
        System.out.println("========================");

        Vector<String> names = new Vector<>();
        names.addAll(Arrays.asList("Charlie", "Alice", "Bob", "David", "Eve"));
        System.out.println("Original names: " + names);

        // Linear search
        System.out.println("\nLinear search:");
        System.out.println("Index of 'Bob': " + names.indexOf("Bob"));
        System.out.println("Index of 'Bob' from index 1: " + names.indexOf("Bob", 1));
        System.out.println("Last index of 'Alice': " + names.lastIndexOf("Alice"));
        System.out.println("Last index of 'Alice' from index 2: " + names.lastIndexOf("Alice", 2));

        // Sorting operations
        System.out.println("\nSorting operations:");
        Vector<String> sortingNames = new Vector<>(names);
        Collections.sort(sortingNames);
        System.out.println("Natural sort: " + sortingNames);

        Collections.sort(sortingNames, Collections.reverseOrder());
        System.out.println("Reverse sort: " + sortingNames);

        // Custom sorting
        Collections.sort(sortingNames, Comparator.comparing(String::length));
        System.out.println("Sort by length: " + sortingNames);

        // Binary search (requires sorted list)
        Collections.sort(names);
        System.out.println("\nBinary search on sorted names: " + names);
        int charlieIndex = Collections.binarySearch(names, "Charlie");
        System.out.println("Index of 'Charlie': " + charlieIndex);

        // Not found case
        int frankIndex = Collections.binarySearch(names, "Frank");
        System.out.println("Binary search for 'Frank' (not found): " + frankIndex);
        System.out.println("Insertion point would be: " + (-frankIndex - 1));

        // Shuffling
        Collections.shuffle(names);
        System.out.println("\nShuffled names: " + names);

        // Thread-safe searching
        System.out.println("\nThread-safe searching using Enumeration:");
        Enumeration<String> enumeration = names.elements();
        System.out.print("All elements via Enumeration: ");
        while (enumeration.hasMoreElements()) {
            System.out.print(enumeration.nextElement() + " ");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Demonstrates advanced Vector operations
     */
    private void advancedOperations() {
        System.out.println("6. ADVANCED OPERATIONS");
        System.out.println("======================");

        Vector<Integer> numbers = new Vector<>();
        numbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original numbers: " + numbers);

        // Sublist operations
        System.out.println("\nSublist operations:");
        List<Integer> subList = numbers.subList(2, 7);
        System.out.println("SubList (index 2-6): " + subList);

        // Modifying sublist affects original
        subList.set(0, 99);
        System.out.println("After modifying sublist:");
        System.out.println("Original Vector: " + numbers);

        // Bulk operations
        System.out.println("\nBulk operations:");
        Vector<Integer> evenNumbers = new Vector<>(Arrays.asList(2, 4, 6, 8, 10));
        System.out.println("Even numbers: " + evenNumbers);

        Vector<Integer> copyForRetain = new Vector<>(numbers);
        copyForRetain.retainAll(evenNumbers);
        System.out.println("After retainAll (intersection): " + copyForRetain);

        // Using Enumeration for safe iteration
        System.out.println("\nSafe iteration using Enumeration:");
        Enumeration<Integer> enumeration = numbers.elements();
        System.out.print("Forward enumeration: ");
        while (enumeration.hasMoreElements()) {
            System.out.print(enumeration.nextElement() + " ");
        }
        System.out.println();

        // Java 8+ Stream operations
        System.out.println("\nStream operations:");
        Vector<String> words = new Vector<>();
        words.addAll(Arrays.asList("apple", "banana", "cherry", "date", "elderberry"));
        System.out.println("Original words: " + words);

        Vector<String> longWords = words.stream()
                .filter(word -> word.length() > 5)
                .collect(Vector::new, Vector::add, Vector::addAll);
        System.out.println("Words with length > 5: " + longWords);

        Vector<String> uppercaseWords = words.stream()
                .map(String::toUpperCase)
                .collect(Vector::new, Vector::add, Vector::addAll);
        System.out.println("Uppercase words: " + uppercaseWords);

        // Interview challenge task
        List<String> notes = Arrays.asList("car", "place", "Dog", "Product", "anagram", "Gear");
        System.out.println("Notes before: " + notes);
        Vector<String> result = notes.stream()
                .filter(note -> !note.toLowerCase().startsWith("p"))
                .map(note -> note.substring(0, 1).toUpperCase() + note.substring(1))
                .collect(Vector::new, Vector::add, Vector::addAll);
        System.out.println("Processed Notes: " + result);

        // removeIf (Java 8+)
        Vector<Integer> numbersToFilter = new Vector<>();
        numbersToFilter.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        numbersToFilter.removeIf(n -> n % 2 == 0);
        System.out.println("After removing even numbers: " + numbersToFilter);

        // Vector-specific operations
        System.out.println("\nVector-specific operations:");
        Vector<String> demo = new Vector<>();
        demo.addElement("First");
        demo.addElement("Second");
        demo.addElement("Third");

        System.out.println("Vector: " + demo);
        System.out.println("Capacity: " + demo.capacity());

        // Copy into array
        String[] array = new String[demo.size()];
        demo.copyInto(array);
        System.out.println("Copied to array: " + Arrays.toString(array));
        System.out.println();
    }

    /**
     * Compares Vector with ArrayList
     */
    private void vectorVsArrayList() {
        System.out.println("7. VECTOR VS ARRAYLIST");
        System.out.println("=======================");

        // Performance comparison
        System.out.println("Performance comparison (single-threaded):");
        comparePerformance();

        System.out.println("\nKey differences:");
        System.out.println("Vector:");
        System.out.println("  + Thread-safe (synchronized methods)");
        System.out.println("  + Legacy support (since JDK 1.0)");
        System.out.println("  + Rich set of legacy methods");
        System.out.println("  + Enumeration support for thread-safe iteration");
        System.out.println("  + Configurable capacity increment");
        System.out.println("  - Synchronization overhead even in single-threaded use");
        System.out.println("  - Generally slower than ArrayList");
        System.out.println("  - Legacy API can be confusing");

        System.out.println("\nArrayList:");
        System.out.println("  + Better performance (no synchronization overhead)");
        System.out.println("  + Modern API design");
        System.out.println("  + Better integration with modern Java features");
        System.out.println("  + More memory efficient");
        System.out.println("  - Not thread-safe");
        System.out.println("  - Requires external synchronization for concurrent access");

        System.out.println("\nWhen to use Vector:");
        System.out.println("- Legacy applications that already use Vector");
        System.out.println("- When you need built-in thread safety");
        System.out.println("- When working with legacy APIs that expect Vector");
        System.out.println("- Simple concurrent scenarios without complex synchronization");

        System.out.println("\nWhen to use ArrayList:");
        System.out.println("- Single-threaded applications");
        System.out.println("- When performance is critical");
        System.out.println("- Modern applications with proper concurrent collections");
        System.out.println("- When you can manage synchronization externally");

        // Memory usage comparison
        System.out.println("\nMemory usage comparison:");
        compareMemoryUsage();
        System.out.println();
    }

    private void comparePerformance() {
        final int ELEMENTS = 100_000;

        // ArrayList performance
        ArrayList<Integer> arrayList = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < ELEMENTS; i++) {
            arrayList.add(i);
        }
        long arrayListAddTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < ELEMENTS; i++) {
            arrayList.get(i);
        }
        long arrayListGetTime = System.nanoTime() - start;

        // Vector performance
        Vector<Integer> vector = new Vector<>();
        start = System.nanoTime();
        for (int i = 0; i < ELEMENTS; i++) {
            vector.add(i);
        }
        long vectorAddTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < ELEMENTS; i++) {
            vector.get(i);
        }
        long vectorGetTime = System.nanoTime() - start;

        System.out.println("Adding " + ELEMENTS + " elements:");
        System.out.println("  ArrayList: " + arrayListAddTime / 1_000_000 + "ms");
        System.out.println("  Vector: " + vectorAddTime / 1_000_000 + "ms");
        System.out.println("  Overhead: " + ((vectorAddTime - arrayListAddTime) * 100 / arrayListAddTime) + "%");

        System.out.println("Getting " + ELEMENTS + " elements:");
        System.out.println("  ArrayList: " + arrayListGetTime / 1_000_000 + "ms");
        System.out.println("  Vector: " + vectorGetTime / 1_000_000 + "ms");
        System.out.println("  Overhead: " + ((vectorGetTime - arrayListGetTime) * 100 / arrayListGetTime) + "%");
    }

    private void compareMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        final int ELEMENTS = 100_000;

        // Measure ArrayList memory
        System.gc();
        long beforeArrayList = runtime.totalMemory() - runtime.freeMemory();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < ELEMENTS; i++) {
            arrayList.add(i);
        }
        long afterArrayList = runtime.totalMemory() - runtime.freeMemory();
        long arrayListMemory = afterArrayList - beforeArrayList;

        // Clear and measure Vector memory
        arrayList = null;
        System.gc();
        long beforeVector = runtime.totalMemory() - runtime.freeMemory();
        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < ELEMENTS; i++) {
            vector.add(i);
        }
        long afterVector = runtime.totalMemory() - runtime.freeMemory();
        long vectorMemory = afterVector - beforeVector;

        System.out.println("Memory usage for " + ELEMENTS + " elements:");
        System.out.println("  ArrayList: ~" + arrayListMemory / 1024 + " KB");
        System.out.println("  Vector: ~" + vectorMemory / 1024 + " KB");
        if (arrayListMemory > 0) {
            System.out.println("  Vector overhead: ~" + ((vectorMemory - arrayListMemory) * 100 / arrayListMemory) + "%");
        }
    }

    /**
     * Demonstrates Vector's legacy methods
     */
    private void legacyMethods() {
        System.out.println("8. LEGACY METHODS");
        System.out.println("=================");

        Vector<String> legacy = new Vector<>();

        System.out.println("Legacy methods from Vector class:");

        // Legacy addition methods
        legacy.addElement("First");
        legacy.addElement("Second");
        legacy.insertElementAt("Middle", 1);
        System.out.println("After legacy additions: " + legacy);

        // Legacy access methods
        System.out.println("First element: " + legacy.firstElement());
        System.out.println("Last element: " + legacy.lastElement());
        System.out.println("Element at index 1: " + legacy.elementAt(1));

        // Legacy modification
        legacy.setElementAt("NewMiddle", 1);
        System.out.println("After setElementAt: " + legacy);

        // Legacy removal methods
        legacy.removeElement("First");
        System.out.println("After removeElement: " + legacy);
        legacy.removeElementAt(0);
        System.out.println("After removeElementAt: " + legacy);

        // Refill for other examples
        legacy.addAll(Arrays.asList("A", "B", "C", "D", "E"));

        // Legacy enumeration
        System.out.println("\nUsing Enumeration (legacy iteration):");
        Enumeration<String> enumeration = legacy.elements();
        System.out.print("Elements: ");
        while (enumeration.hasMoreElements()) {
            System.out.print(enumeration.nextElement() + " ");
        }
        System.out.println();

        // Legacy capacity methods
        System.out.println("\nCapacity management:");
        System.out.println("Current capacity: " + legacy.capacity());
        legacy.ensureCapacity(20);
        System.out.println("After ensureCapacity(20): " + legacy.capacity());

        // Copy to array (legacy way)
        String[] array = new String[legacy.size()];
        legacy.copyInto(array);
        System.out.println("Copied to array: " + Arrays.toString(array));

        // Remove all elements
        legacy.removeAllElements();
        System.out.println("After removeAllElements: " + legacy);
        System.out.println("Is empty: " + legacy.isEmpty());

        System.out.println("\nLegacy vs Modern method mapping:");
        System.out.println("Legacy -> Modern equivalent:");
        System.out.println("  addElement(e) -> add(e)");
        System.out.println("  insertElementAt(e, i) -> add(i, e)");
        System.out.println("  elementAt(i) -> get(i)");
        System.out.println("  setElementAt(e, i) -> set(i, e)");
        System.out.println("  removeElement(e) -> remove(e)");
        System.out.println("  removeElementAt(i) -> remove(i)");
        System.out.println("  removeAllElements() -> clear()");
        System.out.println("  elements() -> iterator()");
        System.out.println("  copyInto(array) -> toArray(array)");
        System.out.println("  firstElement() -> get(0)");
        System.out.println("  lastElement() -> get(size()-1)");

        System.out.println("\nNote: Legacy methods exist for backward compatibility");
        System.out.println("but modern List interface methods are preferred.");
        System.out.println();
    }

    /**
     * Shows common mistakes or outdated usage of Vector
     * */
    private void commonPitfalls() {

        System.out.println("9. COMMON PITFALLS");
        System.out.println("=================");


        Vector<String> vector = new Vector<>();

        // 1. Overusing synchronized Vector for single-threaded context
        vector.add("A");
        vector.add("B");
        vector.add("C");

        // 2. Removing elements while iterating using for-each (will throw ConcurrentModificationException)
        try {
            for (String s : vector) {
                if ("B".equals(s)) {
                    vector.remove(s); // BAD: structural modification during iteration
                }
            }
        } catch (Exception e) {
            System.out.println("Exception during for-each remove: " + e);
        }

        // 3. Using Enumeration instead of modern Iterators
        Enumeration<String> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println("Old style enumeration: " + enumeration.nextElement());
        }
        System.out.println();
    }

    /**
     * Shows how to work with Vector correctly and safely
     * */
    private void bestPractices() {
        System.out.println("10. BEST PRACTICES");
        System.out.println("==================");

        Vector<String> vector = new Vector<>();

        vector.add("X");
        vector.add("Y");
        vector.add("Z");

        // 1. Use Iterator for safe modification
        Iterator<String> iterator = vector.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if ("Y".equals(value)) {
                iterator.remove(); // GOOD: use iterator's remove
            }
        }

        // 2. Check Vector content safely
        System.out.println("Vector after safe removal: " + vector);

        // 3. If you don't need thread safety, prefer ArrayList over Vector
        System.out.println("Note: Consider using ArrayList if synchronization isn't needed.");
        System.out.println();
    }

}
