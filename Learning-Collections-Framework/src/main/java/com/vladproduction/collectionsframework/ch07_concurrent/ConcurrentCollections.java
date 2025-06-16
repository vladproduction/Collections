package com.vladproduction.collectionsframework.ch07_concurrent;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConcurrentCollections - Mastering Thread-Safe Collections
 * this class demonstrates the power of Java's concurrent collections - designed
 * for high-performance multi-threaded applications without explicit synchronization.
 *
 * Learning Objectives:
 * - Understand lock-free and fine-grained locking strategies
 * - Master ConcurrentHashMap, CopyOnWriteArrayList, and BlockingQueues
 * - Learn when to choose concurrent vs synchronized collections
 * - Explore atomic operations and memory consistency
 */
public class ConcurrentCollections {

    private static final int THREAD_COUNT = 10;
    private static final int OPERATIONS_PER_THREAD = 1000;

    public static void main(String[] args) {

        System.out.println("CONCURRENT COLLECTIONS:");
        System.out.println("=".repeat(50));

        demonstrateConcurrentHashMap();
        demonstrateCopyOnWriteArrayList();
        demonstrateBlockingQueues();
        demonstrateConcurrentSkipListMap();
        performanceComparison();

    }

    /**
     * ConcurrentHashMap: The Crown Jewel of Concurrent Collections
     * Features:
     * - Segment-based locking (Java 7) → CAS operations (Java 8+)
     * - No blocking on reads
     * - Atomic compound operations
     */
    private static void demonstrateConcurrentHashMap() {
        System.out.println("\n1. ConcurrentHashMap");
        System.out.println("=".repeat(50));

        ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();

        // Atomic operations - thread-safe without explicit locks
        System.out.println("\nAtomic Operations:");
        wordCount.putIfAbsent("hello", 1);
        wordCount.putIfAbsent("world", 1);

        // Atomic increment - safe for concurrent access
        Integer newCount = wordCount.computeIfPresent("hello", (key, val) -> val + 1);
        System.out.println(" - hello count after increment: " + newCount);

        // Merge operation - combines put and update atomically
        wordCount.merge("java", 1, Integer::sum);
        wordCount.merge("java", 1, Integer::sum);
        System.out.println(" - java count after merge: " + wordCount.get("java"));

        // Concurrent modification demonstration
        System.out.println("\nConcurrent Modification Test:");

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        AtomicInteger totalOperations = new AtomicInteger(0);

        // Submit multiple tasks that modify the map concurrently
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    String key = "thread-" + threadId + "-key-" + (j % 10);
                    wordCount.merge(key, 1, Integer::sum);
                    totalOperations.incrementAndGet();
                }
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Total operations completed: " + totalOperations.get());
        System.out.println("Map size: " + wordCount.size());
        System.out.println("Sample entries: " +
                wordCount.entrySet().stream().limit(5).toList());

        System.out.println();

    }

    /**
     * CopyOnWriteArrayList: Read-Optimized Thread Safety
     * Perfect for scenarios with:
     * - Many readers, few writers
     * - Event listener lists
     * - Observer patterns
     */
    private static void demonstrateCopyOnWriteArrayList() {
        System.out.println("2. CopyOnWriteArrayList: Read-Optimized Threading");
        System.out.println("=".repeat(50));

        CopyOnWriteArrayList<String> eventListeners = new CopyOnWriteArrayList<>();

        // Populate initial listeners
        eventListeners.addAll(Arrays.asList("Logger", "Auditor", "Metrics"));

        System.out.println("\nRead Performance Test:");

        // Simulate many readers
        ExecutorService readers = Executors.newFixedThreadPool(5);
        AtomicInteger readCount = new AtomicInteger(0);

        for (int i = 0; i < 5; i++) {
            readers.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    //reading is completely lock-free
                    eventListeners.forEach(listener -> {
                        //simulate processing
                        if (listener.contains("Log")) {
                            readCount.incrementAndGet();
                        }
                    });
                }
            });
        }

        //simulate occasional writers
        ExecutorService writers = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            final int writerId = i;
            writers.submit(() -> {
                try {
                    Thread.sleep(100); //let readers start
                    eventListeners.add("Writer-" + writerId);
                    Thread.sleep(100);
                    if (!eventListeners.isEmpty()) {
                        eventListeners.remove("Metrics");
                        eventListeners.add("NewMetrics-" + writerId);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        shutdown(readers, writers);

        System.out.println("Read operations completed: " + readCount.get());
        System.out.println("Final listeners: " + eventListeners);

        // Memory implications
        System.out.println("\nMemory Considerations:");
        System.out.println(" - Pros: Lock-free reads, iterator consistency");
        System.out.println(" - Cons: Memory overhead on writes, not suitable for frequent modifications");

        System.out.println();

    }

    /**
     * BlockingQueues: Producer-Consumer Excellence
     * Demonstrates various blocking queue implementations:
     * - ArrayBlockingQueue: Bounded, array-based
     * - LinkedBlockingQueue: Optionally bounded, linked nodes
     * - PriorityBlockingQueue: Unbounded, priority-ordered
     */
    private static void demonstrateBlockingQueues() {
        System.out.println("3. BlockingQueues: Producer-Consumer Patterns");
        System.out.println("=".repeat(50));

        // 📦 ArrayBlockingQueue - bounded capacity
        BlockingQueue<String> tasks = new ArrayBlockingQueue<>(5);

        System.out.println("\nProducer-Consumer with ArrayBlockingQueue:");

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String task = "Task - " + i;
                    tasks.put(task);   //block if queue is full
                    System.out.println("🏭 Produced: " + task + " (Queue size: " + tasks.size() + ")");
                    Thread.sleep(100);

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        //Consumer thread
        Thread consumer = new Thread(() -> {
            try{
                while (!Thread.currentThread().isInterrupted()) {
                    String task = tasks.take(); // blocks if queue is empty
                    System.out.println("🛠️ Consumed: " + task + " (Queue size: " + tasks.size() + ")");
                    Thread.sleep(200); // Simulate processing time
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            Thread.sleep(2000); // Let consumer finish remaining tasks
            consumer.interrupt();
            consumer.join(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // PriorityBlockingQueue demonstration
        System.out.println("\nPriorityBlockingQueue - Task Prioritization:");

        PriorityBlockingQueue<Task> priorityTasks = new PriorityBlockingQueue<>();

        // Add tasks with different priorities
        priorityTasks.offer(new Task("Low Priority Task", 3));
        priorityTasks.offer(new Task("Critical Task", 1));
        priorityTasks.offer(new Task("Medium Priority Task", 2));
        priorityTasks.offer(new Task("Another Critical Task", 1));

        System.out.println("Tasks processed by priority:");
        while (!priorityTasks.isEmpty()) {
            System.out.println("Processing: " + priorityTasks.poll());
        }

        System.out.println();

    }

    /**
     * ConcurrentSkipListMap: Sorted Concurrent Map
     * Features:
     * - Concurrent sorted map implementation
     * - Lock-free operations
     * - NavigableMap interface
     */
    private static void demonstrateConcurrentSkipListMap() {
        System.out.println("4. ConcurrentSkipListMap: Sorted Concurrent Access");
        System.out.println("=" .repeat(50));

        ConcurrentSkipListMap<Integer, String> sortedMap = new ConcurrentSkipListMap<>();

        // Concurrent insertions
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
             final int threadId = i;
             executor.submit(() -> {
                 for (int j = 0; j < 10; j++) {
                     int key = threadId * 100 + j;
                     sortedMap.put(key, "value-" + key);
                 }
             });
        }

        executor.shutdown();
        try{
            executor.awaitTermination(2, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("\nSorted Map Operations:");
        System.out.println("First 5 entries: " + sortedMap.headMap(5));
        System.out.println("Last 5 keys: " + sortedMap.descendingKeySet().stream().limit(5).toList());
        System.out.println("Range [100-110): " + sortedMap.subMap(100, 110));

        System.out.println();

    }

    /**
     * Performance Comparison: Concurrent vs Synchronized
     * Benchmarks different thread-safety approaches to demonstrate
     * the performance benefits of concurrent collections.
     */
    private static void performanceComparison() {
        System.out.println("5. Performance Showdown: Concurrent vs Synchronized");
        System.out.println("=" .repeat(50));

        // Test : ConcurrentHashMap vs Synchronized HashMap
        System.out.println("\nConcurrentHashMap vs Collections.synchronizedMap(HashMap)");

        Map<Integer, String> concurrentMap = new ConcurrentHashMap<>();
        Map<Integer, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        long concurrentTime = benchmarkMap(concurrentMap, "ConcurrentHashMap");
        long synchronizedTime = benchmarkMap(synchronizedMap, "Synchronized HashMap");

        System.out.printf("🏆 Winner: %s (%.1fx faster)\n",
                concurrentTime < synchronizedTime ? "ConcurrentHashMap" : "Synchronized HashMap",
                Math.max(concurrentTime, synchronizedTime) / (double) Math.min(concurrentTime, synchronizedTime));

        System.out.println("\nKey Takeaways:");
        System.out.println(" - ConcurrentHashMap: Lock-free reads, fine-grained write locks");
        System.out.println(" - Synchronized Collections: Full synchronization on all operations");
        System.out.println(" - Use Case: Choose concurrent collections for high-throughput applications");
    }


    /**
     * Benchmarks map operations with multiple threads
     */
    private static long benchmarkMap(Map<Integer, String> map, String mapType) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        long startTime = System.nanoTime();

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    int key = threadId * OPERATIONS_PER_THREAD + j;
                    map.put(key, "value-" + key);
                    map.get(key);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.printf("%s: %d ms (%d operations)\n", mapType, duration,
                THREAD_COUNT * OPERATIONS_PER_THREAD * 2);

        return duration;
    }

    /**
     * Utility method to shutdown multiple executor services
     */
    private static void shutdown(ExecutorService... executors) {
        for (ExecutorService executor : executors) {
            executor.shutdown();
            try {
                executor.awaitTermination(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Task class for priority queue demonstration
     */
    private static class Task implements Comparable<Task> {
        private final String description;
        private final int priority;

        public Task(String description, int priority) {
            this.description = description;
            this.priority = priority;
        }

        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }

        @Override
        public String toString() {
            return String.format("%s (Priority: %d)", description, priority);
        }
    }


}

























