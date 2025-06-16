package com.vladproduction.collectionsframework.ch07_concurrent;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ThreadSafetyExamples - Understanding Thread Safety Patterns
 * Demonstrates various approaches to achieving thread safety with collections,
 * from basic synchronization to advanced lock-free techniques.
 *
 * Learning Objectives:
 * - Understand different levels of thread safety
 * - Master synchronization strategies and their trade-offs
 * - Learn about race conditions and how to prevent them
 * - Explore atomic operations and memory visibility
 * - Compare performance implications of different approaches
 */
public class ThreadSafetyExamples {

    private static final int THREAD_COUNT = 5;
    private static final int OPERATIONS_PER_THREAD = 10000;

    public static void main(String[] args) {

        System.out.println("THREAD SAFETY EXAMPLES:");
        System.out.println("=".repeat(50));
        System.out.println();

        demonstrateRaceConditions();
        demonstrateSynchronizationStrategies();
        demonstrateReadWriteLocks();
        demonstrateAtomicOperations();
        demonstrateThreadSafetyLevels();
        explainPerformanceTradeoffs();
        explainInternalMechanisms();
        demonstrateMemoryVisibility();

    }

    /**
     * Race Conditions:
     * Shows what happens when multiple threads access shared mutable state
     * without proper synchronization.
     */
    private static void demonstrateRaceConditions(){
        System.out.println("1. Race Conditions: When Threads Collide (touch each other)");
        System.out.println("=" .repeat(50));

        // Unsafe counter - demonstrates race condition
        class UnsafeCounter{
            private int count = 0;
            private final List<String> operations = new ArrayList<>();

            public void increment(String threadName){
                int temp = count;
                //simulate som processing time to increase chance occur race condition
                try{
                    Thread.sleep(0, 1000); //1 microsecond
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                count = temp + 1;
                operations.add(threadName + " incremented to " + count);
            }

            public int getCount(){
                return count;
            }

            public List<String> getOperations(){
                return new ArrayList<>(operations);
            }
        }

        UnsafeCounter unsafeCounter = new UnsafeCounter();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        System.out.println("Launching " + THREAD_COUNT + " threads with unsafe counter...");

        for(int i = 0; i < THREAD_COUNT; i++){
            final String threadName = "Thread-" + i;
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                     unsafeCounter.increment(threadName);
                }
            });
        }

        shutdownAndWait(executorService);

        System.out.println(" - Expected count: " + (THREAD_COUNT * 100));
        System.out.println(" - Actual count: " + unsafeCounter.getCount());
        System.out.println(" - Lost updates: " + ((THREAD_COUNT * 100) - unsafeCounter.getCount()));

        if(unsafeCounter.getCount() != THREAD_COUNT * 100){
            System.out.println("Race Condition detected! Some increments were lost.");
        }else {
            System.out.println("Lucky run - no Race Condition occurred!");
        }

        System.out.println();

    }

    /**
     * Synchronization Strategies: Solving the Race Condition Problem
     * Demonstrates different synchronization approaches and their characteristics.
     */
    private static void demonstrateSynchronizationStrategies(){
        System.out.println("2. Synchronization Strategies Comparison");
        System.out.println("=" .repeat(50));

        System.out.println("\n- Strategy 1: Synchronized methods");
        class SynchronizedCounter{
            private int count = 0;

            public synchronized void increment(){
                count++;
            }

            public synchronized int getCount(){
                return count;
            }
        }

        System.out.println("- Strategy 2: Synchronized blocks");
        class SynchronizedBlockCounter{
            private int count = 0;
            private final Object lock = new Object();

            public void increment(){
                synchronized (lock){
                    count++;
                }
            }
            public int getCount(){
                synchronized (lock){
                    return count;
                }
            }
        }

        System.out.println("- Strategy 3: AtomicInteger ");
        class AtomicCounter{
            private final AtomicInteger count = new AtomicInteger(0);

            public void increment(){
                count.incrementAndGet();
            }

            public int getCount(){
                return count.get();
            }
        }

        System.out.println("\nPerformance Comparison:");

        long syncMethodTime = benchmarkCounter(new SynchronizedCounter(), "Synchronized Methods");
        long syncBlockTime = benchmarkCounter(new SynchronizedBlockCounter(), "Synchronized Blocks");
        long atomicTime = benchmarkCounter(new AtomicCounter(), "AtomicInteger");

        System.out.println("\nResults Analysis: (fastest approach determines the winner!)");

        Map<String, Long> results = Map.of(
                "Synchronized Methods", syncMethodTime,
                "Synchronized Blocks", syncBlockTime,
                "AtomicInteger",  atomicTime
        );

        String winner = results.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("unknown");

        System.out.println("Winner: " + winner);

        System.out.println();

    }

    /**
     * Read-Write Locks: Optimizing for Read-Heavy Workloads
     * Demonstrates how ReadWriteLock can improve performance when
     * you have many readers and few writers.
     */
    private static void demonstrateReadWriteLocks(){
        System.out.println("3. ReadWriteLock: Optimizing Read-Heavy Workloads");
        System.out.println("=" .repeat(50));

        class ReadWriteMap<K, V>{
            private final Map<K,V> map = new HashMap<>();
            private final ReadWriteLock lock = new ReentrantReadWriteLock();

            public V get(K key){
                lock.readLock().lock();
                try {
                    return map.get(key);
                } finally {
                    lock.readLock().unlock();
                }
            }

            public void put(K key, V value){
                lock.writeLock().lock();
                try {
                    map.put(key, value);
                } finally {
                    lock.writeLock().unlock();
                }
            }

            public int size(){
                lock.readLock().lock();
                try {
                    return map.size();
                } finally {
                    lock.readLock().unlock();
                }
            }

            public Set<K> keySet(){
                lock.readLock().lock();
                try {
                    return map.keySet();
                } finally {
                    lock.readLock().unlock();
                }
            }

        }

        ReadWriteMap<String, Integer> readWriteMap = new ReadWriteMap<>();

        //pre-populate the map
        for (int i = 0; i < 1000; i++) {
            readWriteMap.put("key-" + i, i);
        }

        System.out.println("Simulate read-heavy workload (90% read, 10% write)...");

        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger readCount = new AtomicInteger(0);
        AtomicInteger writeCount = new AtomicInteger(0);

        long start = System.nanoTime();

        // Submit mixed read/write tasks
        for (int i = 0; i < 10; i++) {
             executor.submit(() -> {
                 Random random = new Random();
                 for (int j = 0; j < 1000; j++) {
                      if(random.nextInt(10) < 9){     //90% reads
                          String key = "key-" + random.nextInt(1000);
                          readWriteMap.get(key);
                          readCount.incrementAndGet();
                      } else {                              //10% writes
                          String key = "key-" + random.nextInt(1000);
                          readWriteMap.put(key, random.nextInt());
                          writeCount.incrementAndGet();
                      }
                 }
             });
        }

        shutdownAndWait(executor);

        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000;   //convert to milliseconds

        System.out.println("\nReadWriteLock Performance:");
        System.out.println(" - Duration: " + duration + "ms");
        System.out.println(" - Read operations: " + readCount.get());
        System.out.println(" - Write operations: " + writeCount.get());
        System.out.println(" - Final map size: " + readWriteMap.size());

        System.out.println("\nReadWriteLock Benefits:");
        System.out.println(" + Multiple threads can read simultaneously");
        System.out.println(" + Writers get exclusive access");
        System.out.println(" + Perfect for read-heavy scenarios");
        System.out.println(" - Overhead may not be worth it for write-heavy workloads");

        System.out.println();

    }

    /**
     * Atomic Operations: Lock-Free Thread Safety
     * Explores atomic classes and compare-and-swap operations.
     */
    private static void demonstrateAtomicOperations(){
        System.out.println("4. Atomic Operations: Lock-Free Magic");
        System.out.println("=" .repeat(50));

        AtomicInteger atomicCounter = new AtomicInteger(0);
        ConcurrentHashMap<String, AtomicInteger> statisticsMap = new ConcurrentHashMap<>();

        System.out.println("Testing atomic operations under concurrent load...");

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);  //THREAD_COUNT = 5

        for (int i = 0; i < THREAD_COUNT; i++) {
            final String threadName = "Thread-" + i;
            executorService.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                     //atomic incrementing
                    atomicCounter.incrementAndGet();

                    //atomic compare-and-swap example
                    int current, newValue;
                    do{
                        current = atomicCounter.get();
                        newValue = current + 1;
                    } while (!atomicCounter.compareAndSet(current, newValue) && current < 1_000_000);

                    //update statistic automatically
                    statisticsMap.computeIfAbsent(threadName, k -> new AtomicInteger(0))
                            .incrementAndGet();
                }
            });
        }

        shutdownAndWait(executorService);

        System.out.println("\nAtomic Operations Results:");
        System.out.println(" - Final counter value: " + atomicCounter.get());
        System.out.println(" - Operations per thread:");
        statisticsMap.forEach((thread, count) ->
                System.out.println("  " + thread + ": " + count.get() + " operations"));

        System.out.println("\nAtomic Operations Advantages:");
        System.out.println(" - Lock-free - no thread blocking");
        System.out.println(" - Wait-free reads");
        System.out.println(" - Compare-and-swap (CAS) for updates");
        System.out.println(" - Better performance under low contention");

        System.out.println();

        /*Reasoning:
        Each thread runs 1,000 iterations,
        Each iteration increments the thread-specific count in statisticsMap,
        The code ensures each thread's AtomicInteger in statisticsMap is incremented exactly once per iteration,
        Total increments per thread should be exactly 1000,
        The sum of all these counts should match the total increments,
                which reaffirms the total counter value of 10,000 (assuming 10 threads).
        */


    }

    /**
     * Thread Safety Levels: Understanding the Spectrum
     * Demonstrates different levels of thread safety in collections.
     */
    private static void demonstrateThreadSafetyLevels(){
        System.out.println("5. Thread Safety Levels: From Unsafe to Bulletproof");
        System.out.println("=" .repeat(50));

        System.out.println("\tThread Safety Classification:\n");

        // Level 1: Thread-unsafe
        System.out.println("Level 1 - Thread-Unsafe:");
        System.out.println("   ArrayList, HashMap, HashSet");
        System.out.println(" - No synchronization");
        System.out.println(" - Race conditions possible");
        System.out.println(" - Best performance (single-threaded)");

        // Level 2: Synchronized wrappers
        System.out.println("\nLevel 2 - Synchronized Wrappers:");
        System.out.println("   Collections.synchronizedList(), Collections.synchronizedMap()");
        System.out.println(" - Thread-safe individual operations");
        System.out.println(" - Compound operations need external synchronization");
        System.out.println(" - Poor performance under contention");

        // Level 3: Concurrent collections
        System.out.println("\nLevel 3 - Concurrent Collections:");
        System.out.println("   ConcurrentHashMap, CopyOnWriteArrayList");
        System.out.println(" - Thread-safe compound operations");
        System.out.println(" - Better performance than synchronized wrappers");
        System.out.println(" - Fine-grained locking or lock-free algorithms");

        // Level 4: Immutable collections
        System.out.println("\nLevel 4 - Immutable Collections:");
        System.out.println("   Collections.unmodifiableList(), List.of()");
        System.out.println(" - Inherently thread-safe (cannot be modified)");
        System.out.println(" - No synchronization overhead");
        System.out.println(" - Cannot be modified after creation");

        // Practical demonstration
        System.out.println("\nPractical Example - Compound Operations:");

        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        // Unsafe compound operation with synchronized map
        System.out.println("\n - Synchronized Map - Unsafe compound operation:");
        System.out.println("   if (!map.containsKey(key)) map.put(key, value); // Race condition!");

        // Safe compound operation with concurrent map
        System.out.println("\n - Concurrent Map - Safe compound operation:");
        System.out.println("   map.putIfAbsent(key, value); // Atomic operation");

        // Demonstrate the difference
        String key = "counter";
        synchronizedMap.put(key, 0);
        concurrentMap.put(key, 0);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Unsafe increment with synchronized map (requires external synchronization)
        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    synchronized (synchronizedMap) { // External synchronization needed!
                        Integer current = synchronizedMap.get(key);
                        synchronizedMap.put(key, current + 1);
                    }
                }
            });
        }

        // Safe increment with concurrent map
        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    concurrentMap.compute(key, (k, v) -> v + 1); // Atomic operation
                }
            });
        }

        shutdownAndWait(executor);

        System.out.println("\nResults after 3000 increments each:");
        System.out.println("   Synchronized Map: " + synchronizedMap.get(key));
        System.out.println("   Concurrent Map: " + concurrentMap.get(key));

        System.out.println();

        /*if comment synchronized block for synchronized map, output:
        Results after 3000 increments each:
        Synchronized Map: 1705               not consistent
        Concurrent Map: 3000*/

        /*uncomment same part give us consistent result:
        Results after 3000 increments each:
        Synchronized Map: 3000               consistent
        Concurrent Map: 3000*/
    }

    /**
     * Performance Trade-offs Between Thread Safety Levels
     * */
    private static void explainPerformanceTradeoffs(){
        System.out.println("6. Performance Trade-offs Between Thread Safety Levels:");
        System.out.println("=".repeat(50));

        System.out.println("\nLevel 1 - Thread-Unsafe (e.g., ArrayList):");
        System.out.println("  - No synchronization overhead");
        System.out.println("  - Fast in single-threaded context");
        System.out.println("  - Risk of race conditions in multi-threaded use");

        System.out.println("Level 2 - Synchronized Wrappers (e.g., Collections.synchronizedMap()):");
        System.out.println("  - Adds synchronization at method level");
        System.out.println("  - Significant overhead under contention");
        System.out.println("  - Good for infrequent synchronizations");

        System.out.println("Level 3 - Concurrent Collections (e.g., ConcurrentHashMap):");
        System.out.println("  - Fine-grained locking or lock-free algorithms");
        System.out.println("  - Better concurrency and less contention");
        System.out.println("  - Slightly increased complexity may impact performance in some cases");

        System.out.println("Level 4 - Immutable Collections:");
        System.out.println("  - No synchronization overhead, inherently thread-safe");
        System.out.println("  - Cost of creating new copies upon modifications");
        System.out.println("  - Ideal for read-heavy scenarios with infrequent modifications");
        System.out.println();
    }

    /**
     * Internal Mechanisms of Thread-Safe Collections
     * */
    private static void explainInternalMechanisms(){
        System.out.println("7. Internal Mechanisms of Thread-Safe Collections:");
        System.out.println("=".repeat(50));

        System.out.println("\n1. Synchronized Wrappers:");
        System.out.println("   - Wraps standard collection with synchronized blocks");
        System.out.println("   - Method-level locking (e.g., synchronized methods)");
        System.out.println("   - External synchronization needed for compound actions");

        System.out.println("2. Concurrent Collections (e.g., ConcurrentHashMap):");
        System.out.println("   - Use lock striping or fine-grained locking");
        System.out.println("     - e.g., ConcurrentHashMap divides the map into segments");
        System.out.println("   - Employ lock-free algorithms for some operations (e.g., compare-and-swap)");
        System.out.println("   - Allow higher concurrency with minimal contention");

        System.out.println("3. Immutable Collections:");
        System.out.println("   - Created with final state, cannot be modified");
        System.out.println("   - Thread safety is inherent as objects can't change");
        System.out.println("   - Mutations require creating new instances");
        System.out.println();
    }

    /**
     * Memory Visibility: The Hidden Thread Safety Challenge
     * Demonstrates memory visibility issues and how to solve them.
     */
    private static void demonstrateMemoryVisibility(){
        System.out.println("8. Memory Visibility: The Hidden Challenge");
        System.out.println("=".repeat(50));

        System.out.println("\nUnderstanding Memory Visibility:\n");
        System.out.println(" - problem occur in class VisibilityProblem ");
        System.out.println(" - Solution 1: Volatile keyword in class VolatileSolution");
        System.out.println(" - Solution 2: Atomic references in class AtomicSolution");

        // Problematic example - memory visibility issue
        class VisibilityProblem {
            private boolean flag = false;
            private int counter = 0;

            public void writer() {
                counter = 42;
                flag = true; // Signal that counter is ready
            }

            public void reader(String label) {
                if (flag) {
                    System.out.println("[" + label + "] VisibilityProblem - Counter value: " + counter);
                } else {
                    System.out.println("[" + label + "] VisibilityProblem - Flag not set yet");
                }
            }
        }

        // Solution 1: Volatile keyword
        class VolatileSolution {
            private volatile boolean flag = false;
            private volatile int counter = 0;

            public void writer() {
                counter = 42;
                flag = true;
            }

            public void reader(String label) {
                if (flag) {
                    System.out.println("[" + label + "] VolatileSolution - Counter value: " + counter);
                } else {
                    System.out.println("[" + label + "] VolatileSolution - Flag not set yet");
                }
            }
        }

        // Solution 2: Atomic references
        class AtomicSolution {
            private final AtomicInteger counter = new AtomicInteger(0);
            private volatile boolean flag = false;

            public void writer() {
                counter.set(42);
                flag = true;
            }

            public void reader(String label) {
                if (flag) {
                    System.out.println("[" + label + "] AtomicSolution - Counter value: " + counter.get());
                } else {
                    System.out.println("[" + label + "] AtomicSolution - Flag not set yet");
                }
            }
        }

        System.out.println("\nTesting Memory Visibility Solutions:");

        VolatileSolution volatileSolution = new VolatileSolution();
        AtomicSolution atomicSolution = new AtomicSolution();
        VisibilityProblem visibilityProblem = new VisibilityProblem();

        ExecutorService executorProblemExample = Executors.newFixedThreadPool(4);
        ExecutorService executorVolatile = Executors.newFixedThreadPool(4);
        ExecutorService executorAtomic = Executors.newFixedThreadPool(4);

        try {
            //testing visibility problem
            Thread.sleep(3000);
            executorProblemExample.submit(() -> {
                try{
                    Thread.sleep(10);
                    System.out.println(ConsoleColors.RED + "\n--- Running VisibilityProblem (No Synchronization) ---" + ConsoleColors.RESET);
                    visibilityProblem.writer();
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });

            executorProblemExample.submit(() -> {
                for (int i = 0; i < 10; i++) {
                    visibilityProblem.reader("VisibilityProblem");
                    try{
                        Thread.sleep(5);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
            });
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        try {
            //testing volatile solution:
            Thread.sleep(8000);
            executorVolatile.submit(() -> {
                try{
                    Thread.sleep(10);
                    System.out.println(ConsoleColors.GREEN + "\n--- Running VolatileSolution ---" + ConsoleColors.RESET);
                    volatileSolution.writer();
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });

            executorVolatile.submit(() -> {
                for (int i = 0; i < 10; i++) {
                     volatileSolution.reader("VolatileSolution");
                     try{
                         Thread.sleep(5);
                     }catch (InterruptedException e){
                         Thread.currentThread().interrupt();
                     }
                }
            });
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        try {
            //testing atomic solution:
            Thread.sleep(12000);
            executorAtomic.submit(() -> {
                try{
                    Thread.sleep(10);
                    System.out.println(ConsoleColors.CYAN + "\n--- Running AtomicSolution ---" + ConsoleColors.RESET);
                    atomicSolution.writer();
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });

            executorAtomic.submit(() -> {
                for (int i = 0; i < 10; i++) {
                    atomicSolution.reader("AtomicSolution");
                    try{
                        Thread.sleep(5);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
            });
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        shutdownAndWait(executorProblemExample);

        shutdownAndWait(executorVolatile);

        shutdownAndWait(executorAtomic);

        System.out.println("\n" + ConsoleColors.MAGENTA + "=== Memory Visibility Key Points ===" + ConsoleColors.RESET);
        System.out.println(" - Problem: Changes by one thread may not be visible to others");
        System.out.println(" - CPU caches and compiler optimizations can cause issues");
        System.out.println("Solutions:");
        System.out.println("   • volatile keyword - ensures visibility");
        System.out.println("   • Atomic classes - thread-safe operations + visibility");
        System.out.println("   • synchronized blocks - mutual exclusion + visibility");
        System.out.println("   • Concurrent collections - built-in memory consistency");

        System.out.println("\n" + ConsoleColors.MAGENTA + "=== Best Practices Summary ===" + ConsoleColors.RESET);
        System.out.println("1) Use concurrent collections for thread-safe data structures");
        System.out.println("2) Use atomic classes for simple counters and flags");
        System.out.println("3) Use volatile for simple visibility without atomicity needs");
        System.out.println("4) Use synchronized for complex critical sections");
        System.out.println("5) Prefer immutable objects when possible");

        System.out.println();

    }

    /**
     * Performance benchmark for different counter implementations
     */
    private static long benchmarkCounter(Object counter, String description){
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        long start = System.nanoTime();

        for (int i = 0; i < THREAD_COUNT; i++) {
             executor.submit(() -> {
                 for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                     if(counter instanceof SynchronizedCounter){
                         ((SynchronizedCounter) counter).increment();
                     } else if(counter instanceof SynchronizedBlockCounter){
                         ((SynchronizedBlockCounter) counter).increment();
                     } else if(counter instanceof AtomicCounter){
                         ((AtomicCounter) counter).getCount();
                     }
                 }
             });
        }

        shutdownAndWait(executor);

        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000; //converting to milliseconds

        int finalCount = 0;
        if(counter instanceof SynchronizedCounter){
            finalCount = ((SynchronizedCounter) counter).getCount();
        } else if(counter instanceof SynchronizedBlockCounter){
            finalCount = ((SynchronizedBlockCounter) counter).getCount();
        } else if(counter instanceof AtomicCounter){
            finalCount = ((AtomicCounter) counter).getCount();
        }

        System.out.printf(" %s : %dms (Final count: %d)\n", description, duration, finalCount);
        return duration;

    }

    /**
     * Utility method to shutdown executor and wait for completion
     */
    private static void shutdownAndWait(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    // Inner classes for demonstration
    private static class SynchronizedCounter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    private static class SynchronizedBlockCounter {
        private int count = 0;
        private final Object lock = new Object();

        public void increment() {
            synchronized (lock) {
                count++;
            }
        }

        public int getCount() {
            synchronized (lock) {
                return count;
            }
        }
    }

    private static class AtomicCounter {
        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }

    /**
     * ANSI escape codes for color coding (if supported by console)
     * */
    private static class ConsoleColors {
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String CYAN = "\u001B[36m";
        public static final String YELLOW = "\u001B[33m";
        public static final String MAGENTA = "\u001B[35m";
    }
}





















