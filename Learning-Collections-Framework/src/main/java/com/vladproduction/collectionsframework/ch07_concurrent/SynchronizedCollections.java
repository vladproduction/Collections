package com.vladproduction.collectionsframework.ch07_concurrent;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SynchronizedCollections - Understanding Legacy Thread Safety
 * This class explores synchronized collections (Collections.synchronizedXxx),
 * their limitations, and when they might still be useful compared to
 * modern concurrent collections.
 *
 * Learning Objectives:
 * - Understand Collections.synchronizedXxx wrapper methods
 * - Learn the limitations and pitfalls of synchronized collections
 * - Compare synchronized vs concurrent collections performance
 * - Master proper iteration patterns for synchronized collections
 * - Understand when synchronized collections are still appropriate
 */
public class SynchronizedCollections {

    private static final int THREAD_COUNT = 5;
    private static final int OPERATIONS_COUNT = 10_000;

    public static void main(String[] args) {

        System.out.println("Synchronized Collections".toUpperCase());
        System.out.println("=".repeat(50));
        System.out.println();

        demonstrateSynchronizedWrappers();
        demonstrateIterationChallenges();
        demonstrateCompoundOperationProblems();
        performanceComparisonForSynchronizedCollections();
        demonstrateBestPractices();
        demonstrateWhenToUseSynchronized();

    }

    /**
     * Synchronized Wrappers: The Collections.synchronizedXxx Family
     *
     * Shows how to create synchronized versions of standard collections
     * and their basic thread-safety guarantees.
     */
    private static void demonstrateSynchronizedWrappers() {
        System.out.println("1. Synchronized Collection Wrappers");
        System.out.println("=" .repeat(50));

        // Creating synchronized collections
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        Set<String> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        System.out.println("Available Synchronized Wrappers:");
        System.out.println("Collections.synchronizedList(List<T>)");
        System.out.println("Collections.synchronizedSet(Set<T>)");
        System.out.println("Collections.synchronizedMap(Map<K,V>)");
        System.out.println("Collections.synchronizedCollection(Collection<T>)");
        System.out.println("Collections.synchronizedSortedSet(SortedSet<T>)");
        System.out.println("Collections.synchronizedSortedMap(SortedMap<K,V>)");
        System.out.println("Collections.synchronizedNavigableSet(NavigableSet<T>)");
        System.out.println("Collections.synchronizedNavigableMap(NavigableMap<K,V>)");

        // Basic thread-safe operations
        System.out.println("\nTesting Basic Thread-Safe Operations:");

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        AtomicInteger successfulOperations = new AtomicInteger(0);

        // Multiple threads adding to synchronized list
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    String element = "Thread-" + threadId + "-Item-" + j;
                    synchronizedList.add(element);
                    synchronizedSet.add(element);
                    synchronizedMap.put(element, j);
                    successfulOperations.incrementAndGet();
                }
            });
        }

        shutdownAndWait(executor);

        System.out.println("Results:");
        System.out.println("   Successful operations: " + successfulOperations.get());
        System.out.println("   Synchronized list size: " + synchronizedList.size());
        System.out.println("   Synchronized set size: " + synchronizedSet.size());
        System.out.println("   Synchronized map size: " + synchronizedMap.size());

        System.out.println("\nIndividual operations are thread-safe!");
        System.out.println("But compound operations still need external synchronization...\n");
    }

    /**
     * Iteration Challenges: The Hidden Danger
     *
     * Demonstrates the major pitfall of synchronized collections:
     * iteration requires external synchronization to avoid ConcurrentModificationException.
     */
    private static void demonstrateIterationChallenges() {
        System.out.println("2. Iteration Challenges with Synchronized Collections");
        System.out.println("=" .repeat(50));

        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // Populate the list
        for (int i = 0; i < 1000; i++) {
            synchronizedList.add(i);
        }

        System.out.println("Unsafe Iteration (Will Likely Fail):");

        // Unsafe iteration - will throw ConcurrentModificationException
        Thread unsafeIterator = new Thread(() -> {
            try {
                System.out.println("   Starting unsafe iteration...");
                for (Integer value : synchronizedList) {
                    Thread.sleep(1); // Simulate processing
                    if (value % 100 == 0) {
                        System.out.println("   Processing: " + value);
                    }
                }
                System.out.println("   Unsafe iteration completed (lucky!)");
            } catch (ConcurrentModificationException e) {
                System.out.println("   ConcurrentModificationException caught: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Thread that modifies the list during iteration
        Thread modifier = new Thread(() -> {
            try {
                Thread.sleep(50); // Let iteration start
                for (int i = 0; i < 10; i++) {
                    synchronizedList.add(2000 + i);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        unsafeIterator.start();
        modifier.start();

        try {
            unsafeIterator.join(2000);
            modifier.join(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nSafe Iteration (Proper Synchronization):");

        // Safe iteration with external synchronization
        Thread safeIterator = new Thread(() -> {
            synchronized (synchronizedList) {
                System.out.println("   Starting safe iteration...");
                int count = 0;
                for (Integer value : synchronizedList) {
                    if (value % 200 == 0) {
                        System.out.println("   Processing: " + value);
                        count++;
                    }
                    if (count >= 5) break; // Limit output
                }
                System.out.println("   Safe iteration completed successfully!");
            }
        });

        safeIterator.start();
        try {
            safeIterator.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nIteration Best Practices:");
        System.out.println("1. Always synchronize on the collection during iteration");
        System.out.println("2. Use synchronized blocks: synchronized(collection) { ... }");
        System.out.println("3. Consider creating a copy for long iterations");
        System.out.println("4. Or use ConcurrentCollections that support safe iteration");

        System.out.println();
    }

    /**
     * Compound Operation Problems: When Thread Safety Isn't Enough
     *
     * Shows why compound operations (check-then-act) are not thread-safe
     * even with synchronized collections.
     */
    private static void demonstrateCompoundOperationProblems() {
        System.out.println("3. Compound Operation Problems");
        System.out.println("=" .repeat(50));

        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        Map<String, Integer> resultMap = Collections.synchronizedMap(new HashMap<>());

        // Initialize counters
        synchronizedMap.put("counter1", 0);
        synchronizedMap.put("counter2", 0);

        System.out.println("Testing Compound Operations:");
        System.out.println("   Scenario: Multiple threads incrementing counters");
        System.out.println("   Problem: get() + put() is not atomic\n");

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        // Unsafe compound operation
        System.out.println(" Unsafe Approach (Race Conditions Expected):");
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    // UNSAFE: This is a compound operation!
                    Integer current = synchronizedMap.get("counter1");
                    synchronizedMap.put("counter1", current + 1);
                }
            });
        }

        // Safe compound operation with external synchronization
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    // SAFE: External synchronization
                    synchronized (synchronizedMap) {
                        Integer current = synchronizedMap.get("counter2");
                        synchronizedMap.put("counter2", current + 1);
                    }
                }
            });
        }

        shutdownAndWait(executor);

        System.out.println("Results after " + (THREAD_COUNT * 1000) + " increments each:");
        System.out.println("   Unsafe counter: " + synchronizedMap.get("counter1") +
                " (Expected: " + (THREAD_COUNT * 1000) + ")");
        System.out.println("   Safe counter: " + synchronizedMap.get("counter2") +
                " (Expected: " + (THREAD_COUNT * 1000) + ")");

        int lost1 = (THREAD_COUNT * 1000) - synchronizedMap.get("counter1");
        int lost2 = (THREAD_COUNT * 1000) - synchronizedMap.get("counter2");

        if (lost1 > 0) {
            System.out.println("   Lost updates (unsafe): " + lost1);
        } else {
            System.out.println("   No lost updates in unsafe version (lucky run!)");
        }

        if (lost2 > 0) {
            System.out.println("   Lost updates (safe): " + lost2 + " - This shouldn't happen!");
        } else {
            System.out.println("   No lost updates in safe version");
        }

        System.out.println("\nCommon Compound Operations That Need External Sync:");
        System.out.println("   • if (!map.containsKey(key)) map.put(key, value)");
        System.out.println("   • Integer count = map.get(key); map.put(key, count + 1)");
        System.out.println("   • if (list.size() > 0) list.remove(0)");
        System.out.println("   • if (!set.contains(item)) set.add(item)");

        System.out.println();
    }

    /**
     * ⚡ Performance Comparison: Synchronized vs Concurrent Collections
     *
     * Benchmarks synchronized collections against their concurrent counterparts
     * to show performance differences.
     */
    private static void performanceComparisonForSynchronizedCollections() {
        System.out.println("4. Performance Showdown: Synchronized vs Concurrent");
        System.out.println("=" .repeat(50));

        System.out.println("Map Performance Comparison:");

        // Test subjects
        Map<Integer, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, String> concurrentMap = new ConcurrentHashMap<>();

        // Benchmark both maps
        long syncTime = benchmarkMap(synchronizedMap, "Collections.synchronizedMap");
        long concurrentTime = benchmarkMap(concurrentMap, "ConcurrentHashMap");

        System.out.println("\nPerformance Analysis:");
        double ratio = (double) syncTime / concurrentTime;
        if (syncTime > concurrentTime) {
            System.out.printf("ConcurrentHashMap is %.1fx faster than synchronized HashMap\n", ratio);
        } else {
            System.out.printf("Synchronized HashMap is %.1fx faster than ConcurrentHashMap\n", 1.0/ratio);
        }

        System.out.println("\nList Performance Comparison:");

        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        List<Integer> copyOnWriteList = new CopyOnWriteArrayList<>();

        long syncListTime = benchmarkList(synchronizedList, "Collections.synchronizedList", false);
        long cowListTime = benchmarkList(copyOnWriteList, "CopyOnWriteArrayList", true);

        System.out.println("\nPerformance Insights:");
        System.out.println("Maps:");
        System.out.println("   • ConcurrentHashMap: Fine-grained locking, better scalability");
        System.out.println("   • Synchronized HashMap: Coarse-grained locking, simpler but slower");
        System.out.println("Lists:");
        System.out.println("   • CopyOnWriteArrayList: Optimized for read-heavy workloads");
        System.out.println("   • Synchronized ArrayList: Better for write-heavy workloads");

        System.out.println();
    }

    /**
     * Best Practices: How to Use Synchronized Collections Properly
     *
     * Demonstrates the correct patterns for using synchronized collections.
     */
    private static void demonstrateBestPractices() {
        System.out.println("5. Best Practices for Synchronized Collections");
        System.out.println("=" .repeat(50));

        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

        System.out.println("Best Practice Patterns:\n");

        // Pattern 1: Safe iteration
        System.out.println("Safe Iteration Pattern:");
        System.out.println("   synchronized (syncCollection) {");
        System.out.println("       for (Type item : syncCollection) {");
        System.out.println("           // Process item");
        System.out.println("       }");
        System.out.println("   }");

        // Demonstrate safe iteration
        for (int i = 0; i < 100; i++) {
            syncList.add("Item-" + i);
        }

        System.out.println("\n   Demonstrating safe iteration:");
        synchronized (syncList) {
            int count = 0;
            for (String item : syncList) {
                if (item.contains("1")) {
                    count++;
                }
                if (count >= 5) break; // Limit output
            }
            System.out.println("   Found " + count + " items containing '1'");
        }

        // Pattern 2: Safe compound operations
        System.out.println("\nSafe Compound Operations:");
        System.out.println("   synchronized (syncMap) {");
        System.out.println("       if (!syncMap.containsKey(key)) {");
        System.out.println("           syncMap.put(key, value);");
        System.out.println("       }");
        System.out.println("   }");

        // Demonstrate safe compound operation
        System.out.println("\n   Demonstrating safe compound operations:");
        String key = "testKey";
        synchronized (syncMap) {
            if (!syncMap.containsKey(key)) {
                syncMap.put(key, 1);
                System.out.println("   Added new key: " + key);
            } else {
                syncMap.put(key, syncMap.get(key) + 1);
                System.out.println("   Incremented existing key: " + key);
            }
        }

        // Pattern 3: Copy for long-running operations
        System.out.println("\nCopy Pattern for Long Operations:");
        System.out.println("   List<Type> copy = new ArrayList<>();");
        System.out.println("   synchronized (syncList) {");
        System.out.println("       copy.addAll(syncList);");
        System.out.println("   }");
        System.out.println("   // Now work with copy without holding lock");

        // Demonstrate copy pattern
        List<String> copy = new ArrayList<>();
        synchronized (syncList) {
            copy.addAll(syncList.subList(0, Math.min(10, syncList.size())));
        }
        System.out.println("\n Working with copy (no synchronization needed):");
        System.out.println("   Copy contains " + copy.size() + " items");

        System.out.println("\nSummary of Best Practices:");
        System.out.println("Always synchronize on the collection object for iteration");
        System.out.println("Use external synchronization for compound operations");
        System.out.println("Create copies for long-running operations");
        System.out.println("Minimize time spent in synchronized blocks");
        System.out.println("Consider concurrent collections for better performance");

        System.out.println();
    }

    /**
     * When to Use Synchronized Collections
     *
     * Explains scenarios where synchronized collections might still be appropriate.
     */
    private static void demonstrateWhenToUseSynchronized() {
        System.out.println("6. When to Use Synchronized Collections");
        System.out.println("=".repeat(50));

        System.out.println("Good Use Cases for Synchronized Collections:\n");

        System.out.println("Legacy Code Integration:");
        System.out.println("   • Existing code expects specific Collection types");
        System.out.println("   • Minimal changes needed for thread safety");
        System.out.println("   • Example: Adding thread safety to existing ArrayList usage");

        System.out.println("\nLow Contention Scenarios:");
        System.out.println("   • Few threads accessing the collection");
        System.out.println("   • Infrequent concurrent access");
        System.out.println("   • Performance difference is negligible");

        System.out.println("\nSimple Threading Models:");
        System.out.println("   • Producer-consumer with single producer/consumer");
        System.out.println("   • Master-worker with careful access patterns");
        System.out.println("   • Testing or prototyping applications");

        System.out.println("\nSpecific Collection Types:");
        System.out.println("   • When you need a specific implementation");
        System.out.println("   • LinkedList for deque operations");
        System.out.println("   • TreeSet/TreeMap for sorted collections");

        System.out.println("\nWhen NOT to Use Synchronized Collections:\n");

        System.out.println("High Contention:");
        System.out.println("   • Many threads frequently accessing the collection");
        System.out.println("   • Performance-critical applications");
        System.out.println("   • → Use ConcurrentHashMap, etc. instead");

        System.out.println("\nComplex Operations:");
        System.out.println("   • Frequent compound operations");
        System.out.println("   • Long-running iterations");
        System.out.println("   • → Use concurrent collections with atomic operations");

        System.out.println("\nModern Applications:");
        System.out.println("   • New code should prefer concurrent collections");
        System.out.println("   • Better performance and functionality");

        System.out.println("\nPractical Example - Legacy Integration:");
        demonstrateLegacyIntegration();

        System.out.println("\nReal-world Decision Matrix:");
        System.out.println("┌──────────────────────┬──────────────────┬────────────────────┐");
        System.out.println("│ Scenario             │ Synchronized     │ Concurrent         │");
        System.out.println("├──────────────────────┼──────────────────┼────────────────────┤");
        System.out.println("│ Legacy codebase      │ Good choice      │ May break APIs     │");
        System.out.println("│ High contention      │ Poor perf        │ Excellent          │");
        System.out.println("│ Simple operations    │ Simple           │ Also good          │");
        System.out.println("│ Complex operations   │ Need ext sync    │ Built-in           │");
        System.out.println("│ New development      │ Use modern       │ Recommended        │");
        System.out.println("└──────────────────────┴──────────────────┴────────────────────┘");

        System.out.println("\nFinal Recommendations:");
        System.out.println("• Prefer concurrent collections for new code");
        System.out.println("• Use synchronized collections for legacy integration");
        System.out.println("• Always profile performance in your specific use case");
        System.out.println("• Remember: thread-safety ≠ atomicity for compound operations");

        System.out.println();
    }

    /**
     * Benchmarks a map implementation with concurrent operations
     */
    private static long benchmarkMap(Map<Integer, String> map, String mapType) {
        System.out.println("\nBenchmarking " + mapType + ":");

        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        // Submit concurrent tasks
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < OPERATIONS_COUNT / THREAD_COUNT; j++) {
                    int key = threadId * 1000 + j;
                    map.put(key, "Value-" + key);
                    map.get(key);
                    if (j % 10 == 0) {
                        map.remove(key);
                    }
                }
            });
        }

        shutdownAndWait(executor);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.printf("    %s: %d ms (final size: %d)%n",
                mapType, duration, map.size());

        return duration;
    }

    /**
     * Benchmarks a list implementation with concurrent operations
     */
    private static long benchmarkList(List<Integer> list, String listType, boolean readHeavy) {
        System.out.println("\nBenchmarking " + listType + " (" +
                (readHeavy ? "read-heavy" : "write-heavy") + "):");

        // Pre-populate for read-heavy test
        if (readHeavy) {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        }

        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executor.submit(() -> {
                if (readHeavy) {
                    // Read-heavy workload
                    for (int j = 0; j < OPERATIONS_COUNT / THREAD_COUNT; j++) {
                        if (!list.isEmpty()) {
                            list.get(0); // Read operation
                        }
                        if (j % 100 == 0) {
                            list.add(threadId * 1000 + j); // Occasional write
                        }
                    }
                } else {
                    // Write-heavy workload
                    for (int j = 0; j < (OPERATIONS_COUNT / THREAD_COUNT) / 10; j++) {
                        list.add(threadId * 1000 + j);
                        if (j % 10 == 0 && !list.isEmpty()) {
                            try {
                                list.remove(0);
                            } catch (Exception e) {
                                // Handle concurrent modification gracefully
                            }
                        }
                    }
                }
            });
        }

        shutdownAndWait(executor);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;

        System.out.printf("    %s: %d ms (final size: %d)%n",
                listType, duration, list.size());

        return duration;
    }

    /**
     *  Demonstrates legacy code integration scenario
     */
    private static void demonstrateLegacyIntegration() {
        System.out.println("   Scenario: Adding thread safety to existing code");

        // Simulate legacy method that expects ArrayList
        class LegacyService {
            public void processData(List<String> data) {
                System.out.println("    Legacy service processing " + data.size() + " items");
                // Simulate processing
                data.forEach(item -> {
                    if (item.startsWith("Important")) {
                        System.out.println("    Found: " + item);
                    }
                });
            }
        }

        // Original unsafe approach
        List<String> unsafeList = new ArrayList<>();

        // Thread-safe wrapper approach
        List<String> safeList = Collections.synchronizedList(new ArrayList<>());

        // Populate both lists
        safeList.add("Important-Data-1");
        safeList.add("Regular-Data-1");
        safeList.add("Important-Data-2");

        LegacyService service = new LegacyService();

        // Safe processing with copy
        List<String> safeCopy;
        synchronized (safeList) {
            safeCopy = new ArrayList<>(safeList);
        }

        System.out.println("    Processing with synchronized wrapper:");
        service.processData(safeCopy);
    }

    /**
     * Utility method to properly shutdown executor and wait for completion
     */
    private static void shutdownAndWait(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.err.println("  Executor did not terminate within 10 seconds");
                executor.shutdownNow();
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println(" Executor did not terminate after shutdown");
                }
            }
        } catch (InterruptedException e) {
            System.err.println("  Interrupted while waiting for executor termination");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
