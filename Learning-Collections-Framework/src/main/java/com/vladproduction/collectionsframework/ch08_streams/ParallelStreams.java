package com.vladproduction.collectionsframework.ch08_streams;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
import java.util.function.*;

/**
 * ParallelStreams - Mastering Parallel Processing with Java Streams
 *
 * This class demonstrates the power of parallel streams in Java, which leverage
 * the Fork/Join framework to process data across multiple CPU cores. Parallel
 * streams can significantly improve performance for CPU-intensive operations
 * on large datasets.
 *
 * Key Concepts Covered:
 * - Parallel stream creation and execution
 * - Performance considerations and benchmarking
 * - Thread safety and stateful operations
 * - When to use parallel vs sequential streams
 * - Custom ForkJoinPool configuration
 * - Parallel reduction operations
 * - Common pitfalls and best practices
 *
 * Learning Objectives:
 * - Understand parallel stream execution model
 * - Learn when parallel streams provide benefits
 * - Master thread-safe operations in parallel contexts
 * - Practice performance optimization techniques
 * - Implement complex parallel processing scenarios
 *
 * Important Notes:
 * - Parallel streams use the common ForkJoinPool by default
 * - Not all operations benefit from parallelization
 * - Thread safety is crucial for shared mutable state
 * - Overhead exists for task splitting and result combining
 *
 * @author vladproduction
 * @version 1.0
 * @since Java 8
 */
public class ParallelStreams {

    // Thread-safe collections for demonstration
    private static final ConcurrentHashMap<String, Integer> sharedMap = new ConcurrentHashMap<>();
    private static final AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("=== ⚡ Parallel Streams Processing ===".toUpperCase());
        System.out.println("=".repeat(50));
        System.out.println();

        // Basic parallel stream operations
        demonstrateParallelBasics();

        // Performance comparison
        demonstratePerformanceComparison();

        // Thread safety considerations
        demonstrateThreadSafety();

        // Parallel reduction operations
        demonstrateParallelReduction();

        // Custom ForkJoinPool usage
        demonstrateCustomForkJoinPool();

        // Complex parallel processing scenarios
        demonstrateComplexParallelProcessing();

        // Best practices and pitfalls
        demonstrateBestPractices();

        // Real-world parallel processing examples
        demonstrateRealWorldExamples();
    }

    /**
     * Demonstrates basic parallel stream operations
     *
     * Parallel Stream Creation:
     * - collection.parallelStream(): Create parallel stream from collection
     * - stream.parallel(): Convert sequential stream to parallel
     * - stream.sequential(): Convert parallel stream back to sequential
     *
     * Key Characteristics:
     * - Operations are divided among available CPU cores
     * - Uses Fork/Join framework internally
     * - Order of execution is not guaranteed
     * - Results are combined automatically
     */
    private static void demonstrateParallelBasics() {
        System.out.println("1. Parallel Stream Basics:");

        List<Integer> numbers = IntStream.rangeClosed(1, 1000)
                .boxed()
                .collect(Collectors.toList());

        //check for existing and available processors on that machine
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());

        // Creating parallel streams
        // Method 1: parallelStream()
        long sum1 = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println("Sum using parallelStream(): " + sum1);

        // Method 2: stream().parallel()
        long sum2 = numbers.stream()
                .parallel()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println("Sum using stream().parallel(): " + sum2);

        // Checking if stream is parallel
        boolean isParallel = numbers.parallelStream().isParallel();
        System.out.println("Stream is parallel: " + isParallel);

        // Converting back to sequential
        long sum3 = numbers.parallelStream()
                .sequential()  // Convert to sequential
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println("Sum after converting to sequential: " + sum3);

        // Demonstrating parallel execution with thread names
        System.out.println("\nThread usage in parallel stream:");
        numbers.parallelStream()
                .limit(10)
                .forEach(n -> System.out.println("Processing " + n + " on thread: " + Thread.currentThread().getName())
                );


        System.out.println();
    }

    /**
     * Demonstrates performance comparison between sequential and parallel streams
     *
     * Performance Factors:
     * - Data size: Larger datasets benefit more from parallelization
     * - Operation complexity: CPU-intensive operations show better improvement
     * - Number of cores: More cores generally mean better parallel performance
     * - Overhead: Task splitting and result combining have costs
     *
     * When Parallel Streams Help:
     * - Large datasets (typically >10,000 elements)
     * - CPU-intensive operations
     * - Independent operations (no shared state)
     * - Stateless operations
     */
    private static void demonstratePerformanceComparison() {
        System.out.println("2. Performance Comparison:");

        // Create large dataset for meaningful comparison
        List<Integer> largeDataset = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .toList();

        // Test 1: Simple sum operation
        System.out.println("Test 1: Sum of 10 million integers");

        long startTime = System.currentTimeMillis();
        long sequentialSum = largeDataset.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        long parallelSum = largeDataset.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long parallelTime = System.currentTimeMillis() - startTime;

        System.out.println("  Sequential: " + sequentialTime + "ms, Result: " + sequentialSum);
        System.out.println("  Parallel: " + parallelTime + "ms, Result: " + parallelSum);
        System.out.printf("  Speedup: %.2fx%n", (double) sequentialTime / parallelTime);

        // Test 2: CPU-intensive operation (finding prime numbers)
        System.out.println("\nTest 2: Finding prime numbers (1-50,000)");

        startTime = System.currentTimeMillis();
        long sequentialPrimes = IntStream.rangeClosed(1, 50_000)
                .filter(ParallelStreams::isPrime)
                .count();
        sequentialTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        long parallelPrimes = IntStream.rangeClosed(1, 50_000)
                .parallel()
                .filter(ParallelStreams::isPrime)
                .count();
        parallelTime = System.currentTimeMillis() - startTime;

        System.out.println("  Sequential: " + sequentialTime + "ms, Primes found: " + sequentialPrimes);
        System.out.println("  Parallel: " + parallelTime + "ms, Primes found: " + parallelPrimes);
        System.out.printf("  Speedup: %.2fx%n", (double) sequentialTime / parallelTime);

        // Test 3: Operation that doesn't benefit from parallelization
        System.out.println("\nTest 3: Simple operation on small dataset");
        List<Integer> smallDataset = IntStream.rangeClosed(1, 50).boxed().collect(Collectors.toList());

        startTime = System.nanoTime();
        int sequentialSmallSum = smallDataset.stream().mapToInt(Integer::intValue).sum();
        long sequentialNanoTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int parallelSmallSum = smallDataset.parallelStream().mapToInt(Integer::intValue).sum();
        long parallelNanoTime = System.nanoTime() - startTime;

        System.out.println("  Sequential: " + sequentialNanoTime + "ns, Result: " + sequentialSmallSum);
        System.out.println("  Parallel: " + parallelNanoTime + "ns, Result: " + parallelSmallSum);
        System.out.println("  Note: Parallel overhead makes it slower for small datasets");

        System.out.println();
    }

    /**
     * Demonstrates thread safety considerations in parallel streams
     *
     * Thread Safety Issues:
     * - Shared mutable state can cause race conditions
     * - Non-atomic operations can produce incorrect results
     * - Collections like ArrayList, HashMap are not thread-safe
     *
     * Solutions:
     * - Use thread-safe collections (ConcurrentHashMap, etc.)
     * - Use atomic operations (AtomicInteger, etc.)
     * - Avoid shared mutable state when possible
     * - Use collect() with thread-safe collectors
     */
    private static void demonstrateThreadSafety() {
        System.out.println("3. Thread Safety Considerations:");

        List<Integer> numbers = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        // UNSAFE: Using non-thread-safe collection
        System.out.println("UNSAFE EXAMPLE - Non-thread-safe operations:");
        List<Integer> unsafeList = new ArrayList<>();
        // This can cause issues due to race conditions
        numbers.parallelStream()
                .limit(10)
                .forEach(n -> {
                    // DON'T DO THIS - ArrayList is not thread-safe
                    // unsafeList.add(n * 2); // This would cause race conditions
                    System.out.println("Would add " + (n * 2) + " to unsafe list");
                });

        // SAFE: Using thread-safe collections
        System.out.println("\nSAFE EXAMPLE - Thread-safe operations:");

        // Using ConcurrentHashMap
        sharedMap.clear();
        numbers.parallelStream()
                .limit(10)
                .forEach(n -> sharedMap.put("key" + n, n * 2));

        System.out.println("Thread-safe map size: " + sharedMap.size());

        // Using AtomicInteger
        atomicCounter.set(0);
        numbers.parallelStream()
                .limit(100)
                .forEach(n -> atomicCounter.incrementAndGet());

        System.out.println("Atomic counter value: " + atomicCounter.get());

        // SAFE: Using collect() with thread-safe collectors
        List<Integer> safeCollectedList = numbers.parallelStream()
                .map(n -> n * 2)
                .collect(Collectors.toList()); // Thread-safe collection

        System.out.println("Safely collected list size: " + safeCollectedList.size());

        // Demonstrating race condition with regular counter
        System.out.println("\nRace condition demonstration:");
        final int[] unsafeCounter = {0};
        numbers.parallelStream()
                .limit(1000)
                .forEach(n -> unsafeCounter[0]++); // Race condition!

        System.out.println("Unsafe counter (expected 1000): " + unsafeCounter[0]);
        System.out.println("Result may vary due to race conditions");

        System.out.println();
    }

    /**
     * Demonstrates parallel reduction operations
     *
     * Reduction Operations:
     * - reduce(): Combines elements using associative function
     * - collect(): Mutable reduction into containers
     * - Parallel-safe reductions maintain associativity
     *
     * Key Requirements for Parallel Reductions:
     * - Associativity: (a op b) op c = a op (b op c)
     * - Identity element: identity op a = a
     * - Combiner function for parallel execution
     */
    private static void demonstrateParallelReduction() {
        System.out.println("4. Parallel Reduction Operations:");

        List<Integer> numbers = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        // Basic reduction operations
        System.out.println("Basic reductions:");

        // Sum reduction
        int sum = numbers.parallelStream()
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);

        // Product reduction
        int product = Arrays.asList(1, 2, 3, 4, 5).parallelStream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product of [1,2,3,4,5]: " + product);

        // String concatenation (demonstrating associativity importance)
        String concatenated = Arrays.asList("A", "B", "C", "D").parallelStream()
                .reduce("", String::concat);
        System.out.println("Concatenated: " + concatenated);

        // Complex reduction with three-argument reduce
        System.out.println("\nComplex reductions:");

        List<String> words = Arrays.asList("hello", "world", "java", "streams", "parallel", "processing");

        // Counting total characters
        int totalChars = words.parallelStream()
                .reduce(0,
                        (sum_so_far, word) -> sum_so_far + word.length(),  // accumulator
                        Integer::sum);                                      // combiner
        System.out.println("Total characters: " + totalChars);

        // Collecting to different types
        System.out.println("\nCollection operations:");

        // Collect to Set
        Set<Integer> evenNumbers = numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toSet());
        System.out.println("Even numbers count: " + evenNumbers.size());

        // Collect to Map
        Map<String, Integer> wordLengths = words.parallelStream()
                .collect(Collectors.toConcurrentMap(
                        word -> word,
                        String::length
                ));
        System.out.println("Word lengths: " + wordLengths);

        // Grouping operation
        Map<Integer, List<String>> wordsByLength = words.parallelStream()
                .collect(Collectors.groupingByConcurrent(String::length));
        System.out.println("Words grouped by length: " + wordsByLength);

        // Custom collector for statistics
        IntSummaryStatistics stats = numbers.parallelStream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println("Statistics: " + stats);

        System.out.println();
    }

    /**
     * Demonstrates custom ForkJoinPool usage
     *
     * Custom ForkJoinPool allows:
     * - Controlling thread pool size
     * - Isolating parallel operations
     * - Preventing interference with common pool
     * - Custom thread configuration
     */
    private static void demonstrateCustomForkJoinPool() {
        System.out.println("5. Custom ForkJoinPool Usage:");

        List<Integer> numbers = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());

        // Using default common ForkJoinPool
        System.out.println("Default ForkJoinPool parallelism: " +
                ForkJoinPool.commonPool().getParallelism());

        long start = System.currentTimeMillis();
        long defaultResult = numbers.parallelStream()
                .mapToLong(n -> expensiveOperation(n))
                .sum();
        long defaultTime = System.currentTimeMillis() - start;
        System.out.println("Default pool time: " + defaultTime + "ms, result: " + defaultResult);

        // Using custom ForkJoinPool with specific parallelism
        ForkJoinPool customPool = new ForkJoinPool(2); // Only 2 threads
        try {
            start = System.currentTimeMillis();
            long customResult = customPool.submit(() ->
                    numbers.parallelStream()
                            .mapToLong(n -> expensiveOperation(n))
                            .sum()
            ).get();
            long customTime = System.currentTimeMillis() - start;
            System.out.println("Custom pool (2 threads) time: " + customTime + "ms, result: " + customResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            customPool.shutdown();
        }

        // Demonstrating pool isolation
        System.out.println("\nPool isolation demonstration:");
        ForkJoinPool isolatedPool = new ForkJoinPool(4);
        try {
            isolatedPool.submit(() -> {
                numbers.parallelStream()
                        .limit(5)
                        .forEach(n -> System.out.println("Isolated pool - Processing " + n +
                                " on thread: " + Thread.currentThread().getName()));
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            isolatedPool.shutdown();
        }

        System.out.println();
    }

    /**
     * Demonstrates complex parallel processing scenarios
     *
     * Advanced Scenarios:
     * - Nested parallel operations
     * - Pipeline with multiple parallel stages
     * - Conditional parallelization
     * - Parallel processing with exception handling
     */
    private static void demonstrateComplexParallelProcessing() {
        System.out.println("6. Complex Parallel Processing Scenarios:");

        // Scenario 1: Pipeline with multiple parallel stages
        System.out.println("Multi-stage parallel pipeline:");

        List<String> documents = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> "Document" + i + " with content that needs processing")
                .collect(Collectors.toList());

        Map<String, Integer> processedDocuments = documents.parallelStream()
                // Stage 1: Parallel text processing
                .collect(Collectors.toConcurrentMap(
                        doc -> doc.split(" ")[0], // Extract document ID
                        doc -> doc.split(" ").length // Count words
                ))
                .entrySet().parallelStream()
                // Stage 2: Parallel filtering and transformation
                .filter(entry -> entry.getValue() > 5)
                .collect(Collectors.toConcurrentMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() * 2 // Double the word count
                ));

        System.out.println("Processed documents count: " + processedDocuments.size());

        // Scenario 2: Conditional parallelization
        System.out.println("\nConditional parallelization:");

        List<Integer> dataset = IntStream.rangeClosed(1, 10_000).boxed().toList();
        boolean useParallel = dataset.size() > 1000; // Use parallel for large datasets

        Stream<Integer> stream = useParallel ? dataset.parallelStream() : dataset.stream();
        long result = stream
                .filter(n -> n % 2 == 0)
                .mapToLong(Integer::longValue)
                .sum();

        System.out.println("Used parallel: " + useParallel + ", Result: " + result);

        // Scenario 3: Parallel processing with exception handling
        System.out.println("\nParallel processing with exception handling:");

        List<String> mixedData = Arrays.asList("123", "456", "abc", "789", "def", "101112");

        List<Integer> validNumbers = mixedData.parallelStream()
                .map(s -> {
                    try {
                        return Optional.of(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        return Optional.<Integer>empty();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        System.out.println("Valid numbers extracted: " + validNumbers);

        // Scenario 4: Parallel aggregation with custom collector
        System.out.println("\nCustom parallel aggregation:");

        List<Transaction> transactions = IntStream.rangeClosed(1, 1000)
                .mapToObj(i -> new Transaction("Account" + (i % 10), i * 1.5))
                .collect(Collectors.toList());

        Map<String, Double> accountTotals = transactions.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        Transaction::getAccount,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        System.out.println("Account totals calculated: " + accountTotals.size() + " accounts");
        accountTotals.entrySet().stream()
                .limit(3)
                .forEach(entry -> System.out.println("  " + entry.getKey() + ": $" +
                        String.format("%.2f", entry.getValue())));

        System.out.println();
    }

    /**
     * Demonstrates best practices and common pitfalls
     *
     * Best Practices:
     * - Use parallel streams for large datasets and CPU-intensive operations
     * - Ensure operations are stateless and side-effect free
     * - Prefer immutable objects and thread-safe operations
     * - Use appropriate collectors for thread safety
     *
     * Common Pitfalls:
     * - Using parallel streams with small datasets
     * - Sharing mutable state without synchronization
     * - Using blocking operations in parallel streams
     * - Assuming parallel is always faster
     */
    private static void demonstrateBestPractices() {
        System.out.println("7. Best Practices and Common Pitfalls:");

        // Best Practice 1: Stateless operations
        System.out.println("✅ GOOD: Stateless operations");
        List<Integer> numbers = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        List<Integer> squared = numbers.parallelStream()
                .map(n -> n * n)  // Stateless transformation
                .collect(Collectors.toList());
        System.out.println("Squared numbers computed: " + squared.size());

        // Best Practice 2: Use appropriate data structures
        System.out.println("\n✅ GOOD: Using thread-safe collections");
        ConcurrentHashMap<Integer, String> safeMap = numbers.parallelStream()
                .collect(Collectors.toConcurrentMap(
                        n -> n,
                        n -> "Value" + n,
                        (v1, v2) -> v1,
                        ConcurrentHashMap::new
                ));
        System.out.println("Thread-safe map created with " + safeMap.size() + " entries");

        // Pitfall 1: Overusing parallel streams
        System.out.println("\n❌ PITFALL: Using parallel for small datasets");
        List<Integer> smallList = Arrays.asList(1, 2, 3, 4, 5);

        long start = System.nanoTime();
        int sequentialSum = smallList.stream().mapToInt(Integer::intValue).sum();
        long sequentialTime = System.nanoTime() - start;

        start = System.nanoTime();
        int parallelSum = smallList.parallelStream().mapToInt(Integer::intValue).sum();
        long parallelTime = System.nanoTime() - start;

        System.out.println("Sequential: " + sequentialTime + "ns");
        System.out.println("Parallel: " + parallelTime + "ns");
        System.out.println("Parallel is slower due to overhead!");

        // Pitfall 2: Side effects and shared state
        System.out.println("\n❌ PITFALL: Side effects in parallel streams");
        List<String> sideEffectList = new ArrayList<>();

        System.out.println("This would cause race conditions:");
        System.out.println("numbers.parallelStream().forEach(n -> sideEffectList.add(\"Item\" + n));");
        System.out.println("Instead, use collect() or thread-safe collections");

        // Best Practice 3: Measuring performance
        System.out.println("\n✅ GOOD: Always measure performance");
        System.out.println("Use benchmarking tools like JMH for accurate measurements");
        System.out.println("Consider warmup time and multiple iterations");

        // Best Practice 4: Know when NOT to use parallel
        System.out.println("\n✅ GOOD: Know when to avoid parallel streams");
        System.out.println("Avoid parallel streams when:");
        System.out.println("- Dataset is small (< 10,000 elements typically)");
        System.out.println("- Operations are I/O bound");
        System.out.println("- Order of processing matters");
        System.out.println("- Operations have side effects");

        System.out.println();
    }

    /**
     * Demonstrates real-world parallel processing examples
     *
     * Real-World Scenarios:
     * - Image processing
     * - Financial calculations
     * - Data analysis and reporting
     * - Batch processing
     * - Scientific computations
     */
    private static void demonstrateRealWorldExamples() {
        System.out.println("8. Real-World Parallel Processing Examples:");

        // Example 1: Log file analysis
        System.out.println("Example 1: Log File Analysis");
        List<String> logEntries = generateLogEntries(10000);

        long start = System.currentTimeMillis();
        Map<String, Long> errorCounts = logEntries.parallelStream()
                .filter(entry -> entry.contains("ERROR"))
                .map(entry -> entry.split(" ")[2]) // Extract error type
                .collect(Collectors.groupingByConcurrent(
                        Function.identity(),
                        Collectors.counting()
                ));
        long analysisTime = System.currentTimeMillis() - start;

        System.out.println("Log analysis completed in: " + analysisTime + "ms");
        System.out.println("Error types found: " + errorCounts.size());

        // Example 2: Financial portfolio calculation
        System.out.println("\nExample 2: Financial Portfolio Calculation");
        List<Stock> portfolio = generatePortfolio(1000);

        start = System.currentTimeMillis();
        double totalValue = portfolio.parallelStream()
                .mapToDouble(stock -> stock.getPrice() * stock.getQuantity())
                .sum();

        double averageReturn = portfolio.parallelStream()
                .mapToDouble(Stock::getExpectedReturn)
                .average()
                .orElse(0.0);

        long calculationTime = System.currentTimeMillis() - start;

        System.out.println("Portfolio calculation completed in: " + calculationTime + "ms");
        System.out.println("Total portfolio value: $" + String.format("%.2f", totalValue));
        System.out.println("Average expected return: " + String.format("%.2f%%", averageReturn));

        // Example 3: Text processing and analysis
        System.out.println("\nExample 3: Text Processing and Analysis");
        List<String> documents = generateDocuments(5000);

        start = System.currentTimeMillis();
        Map<String, Integer> wordFrequency = documents.parallelStream()
                .flatMap(doc -> Arrays.stream(doc.toLowerCase().split("\\W+")))
                .filter(word -> word.length() > 3)
                .collect(Collectors.toConcurrentMap(
                        word -> word,
                        word -> 1,
                        Integer::sum
                ));

        List<Map.Entry<String, Integer>> topWords = wordFrequency.entrySet().parallelStream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toList());

        long textProcessingTime = System.currentTimeMillis() - start;

        System.out.println("Text processing completed in: " + textProcessingTime + "ms");
        System.out.println("Unique words found: " + wordFrequency.size());
        System.out.println("Top 5 words:");
        topWords.stream()
                .limit(5)
                .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue()));

        // Example 4: Scientific computation (Monte Carlo simulation)
        System.out.println("\nExample 4: Monte Carlo Pi Estimation");
        int iterations = 10_000_000;

        start = System.currentTimeMillis();
        long pointsInCircle = IntStream.range(0, iterations)
                .parallel()
                .mapToObj(i -> new double[]{Math.random(), Math.random()})
                .mapToLong(point -> (point[0] * point[0] + point[1] * point[1]) <= 1.0 ? 1 : 0)
                .sum();

        double piEstimate = 4.0 * pointsInCircle / iterations;
        long simulationTime = System.currentTimeMillis() - start;

        System.out.println("Monte Carlo simulation completed in: " + simulationTime + "ms");
        System.out.println("Pi estimate: " + String.format("%.6f", piEstimate));
        System.out.println("Actual Pi: " + String.format("%.6f", Math.PI));
        System.out.println("Error: " + String.format("%.6f", Math.abs(Math.PI - piEstimate)));

        System.out.println("\n" + "=".repeat(50));
        System.out.println("Parallel Streams demonstration completed!");
    }

    // Helper methods

    /**
     * Simple prime checking function for performance testing
     */
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * Simulates an expensive operation
     */
    private static long expensiveOperation(int n) {
        // Simulate CPU-intensive work
        long result = 0;
        for (int i = 0; i < n % 1000; i++) {
            result += Math.sqrt(i);
        }
        return result;
    }

    /**
     * Generates sample log entries for analysis
     */
    private static List<String> generateLogEntries(int count) {
        String[] logLevels = {"INFO", "WARN", "ERROR", "DEBUG"};
        String[] errorTypes = {"NullPointer", "IOException", "SQLException", "Timeout"};
        Random random = new Random();

        return IntStream.range(0, count)
                .mapToObj(i -> {
                    String level = logLevels[random.nextInt(logLevels.length)];
                    String message = level.equals("ERROR") ?
                            errorTypes[random.nextInt(errorTypes.length)] :
                            "Normal operation";
                    return String.format("2024-01-01 12:00:%02d %s %s - Message %d",
                            i % 60, level, message, i);
                })
                .collect(Collectors.toList());
    }

    /**
     * Generates sample stock portfolio for financial calculations
     */
    private static List<Stock> generatePortfolio(int count) {
        String[] stockSymbols = {"AAPL", "GOOGL", "MSFT", "AMZN", "TSLA", "META", "NFLX", "NVDA"};
        Random random = new Random();

        return IntStream.range(0, count)
                .mapToObj(i -> new Stock(
                        stockSymbols[random.nextInt(stockSymbols.length)] + "_" + i,
                        100 + random.nextDouble() * 500, // Price between $100-600
                        random.nextInt(100) + 1,         // Quantity 1-100
                        random.nextDouble() * 20 - 5     // Expected return -5% to 15%
                ))
                .collect(Collectors.toList());
    }

    /**
     * Generates sample text documents for processing
     */
    private static List<String> generateDocuments(int count) {
        String[] words = {
                "parallel", "stream", "processing", "java", "performance", "thread", "concurrent",
                "data", "analysis", "algorithm", "optimization", "computation", "framework",
                "development", "programming", "software", "application", "system", "architecture",
                "scalable", "efficient", "robust", "reliable", "maintainable", "extensible"
        };
        Random random = new Random();

        return IntStream.range(0, count)
                .mapToObj(i -> {
                    StringBuilder doc = new StringBuilder();
                    int wordCount = 50 + random.nextInt(100); // 50-149 words per document
                    for (int j = 0; j < wordCount; j++) {
                        doc.append(words[random.nextInt(words.length)]).append(" ");
                    }
                    return doc.toString().trim();
                })
                .collect(Collectors.toList());
    }

    /**
     * Simple Transaction class for demonstration
     */
    static class Transaction {
        private final String account;
        private final double amount;

        public Transaction(String account, double amount) {
            this.account = account;
            this.amount = amount;
        }

        public String getAccount() {
            return account;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return String.format("Transaction{account='%s', amount=%.2f}", account, amount);
        }
    }

    /**
     * Simple Stock class for financial calculations
     */
    static class Stock {
        private final String symbol;
        private final double price;
        private final int quantity;
        private final double expectedReturn;

        public Stock(String symbol, double price, int quantity, double expectedReturn) {
            this.symbol = symbol;
            this.price = price;
            this.quantity = quantity;
            this.expectedReturn = expectedReturn;
        }

        public String getSymbol() {
            return symbol;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getExpectedReturn() {
            return expectedReturn;
        }

        @Override
        public String toString() {
            return String.format("Stock{symbol='%s', price=%.2f, quantity=%d, expectedReturn=%.2f%%}",
                    symbol, price, quantity, expectedReturn);
        }
    }

}
