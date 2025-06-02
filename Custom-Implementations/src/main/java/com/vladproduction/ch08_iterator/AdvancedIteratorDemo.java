package com.vladproduction.ch08_iterator;

import java.util.*;
import java.util.function.Function;

/**
 * Extended demo showing additional iterator patterns
 */
public class AdvancedIteratorDemo {
    
    public static void runAdvancedExamples() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ADVANCED ITERATOR PATTERNS");
        System.out.println("AdvancedIteratorDemo.runAdvancedExamples();");
        System.out.println("=".repeat(60));
        
        demonstrateSkippingIterator();
        demonstrateWindowedIterator();
        demonstrateGroupingIterator();
        demonstrateIteratorComposition();
    }
    
    private static void demonstrateSkippingIterator() {
        System.out.println("\n--- Skipping Iterator (Take 2, Skip 3 pattern) ---");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        SkippingIterator<Integer> skippingIter = new SkippingIterator<>(
            numbers.iterator(), 3, 2  // Take 2, skip 3
        );
        
        IteratorUtils.printAll(skippingIter, "Take 2, Skip 3 pattern from 1-15");
    }
    
    private static void demonstrateWindowedIterator() {
        System.out.println("\n--- Windowed Iterator (Moving average calculation) ---");
        
        List<Double> prices = Arrays.asList(10.0, 12.0, 11.0, 13.0, 15.0, 14.0, 16.0, 18.0, 17.0);
        WindowedIterator<Double> windowIter = new WindowedIterator<>(prices.iterator(), 3);
        
        System.out.println("3-day moving averages:");
        while (windowIter.hasNext()) {
            List<Double> window = windowIter.next();
            double average = window.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            System.out.printf("Window %s -> Average: %.2f%n", window, average);
        }
    }
    
    private static void demonstrateGroupingIterator() {
        System.out.println("\n--- Grouping Iterator (Group by first letter) ---");
        
        List<String> words = Arrays.asList("apple", "ant", "banana", "berry", "cherry", "cat", "dog", "duck");
        GroupingIterator<String, Character> groupIter = new GroupingIterator<>(
            words.iterator(), 
            word -> word.charAt(0)
        );
        
        while (groupIter.hasNext()) {
            Map.Entry<Character, List<String>> group = groupIter.next();
            System.out.println("Group '" + group.getKey() + "': " + group.getValue());
        }
    }
    
    private static void demonstrateIteratorComposition() {
        System.out.println("\n--- Iterator Composition (Complex data processing pipeline) ---");
        
        // Start with a range of numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
        }
        
        // Create a complex processing pipeline:
        // 1. Filter even numbers
        // 2. Transform to their squares
        // 3. Take only first 5
        // 4. Create windows of size 3
        Iterator<Integer> evenNumbers = IteratorUtils.filter(numbers.iterator(), n -> n % 2 == 0);
        Iterator<Integer> squaredNumbers = IteratorUtils.map(evenNumbers, n -> n * n);
        Iterator<Integer> limitedNumbers = IteratorUtils.limit(squaredNumbers, 5);
        
        // Convert to list to use with windowed iterator
        List<Integer> processedNumbers = IteratorUtils.toList(limitedNumbers);
        WindowedIterator<Integer> windowedIter = new WindowedIterator<>(processedNumbers.iterator(), 3);
        
        System.out.println("Processing pipeline: Even -> Square -> Limit(5) -> Window(3)");
        System.out.println("Input: " + numbers);
        System.out.println("Processed numbers: " + processedNumbers);
        System.out.println("Windows:");
        while (windowedIter.hasNext()) {
            System.out.println("  " + windowedIter.next());
        }
    }

    /**
     * Iterator that skips elements based on a pattern
     */
    static class SkippingIterator<T> implements EnhancedIterator<T> {
        private final Iterator<T> baseIterator;
        private final int skipCount;
        private final int takeCount;
        private int currentPosition = 0;
        private int takenInCurrentGroup = 0;

        public SkippingIterator(Iterator<T> baseIterator, int skipCount, int takeCount) {
            this.baseIterator = baseIterator;
            this.skipCount = skipCount;
            this.takeCount = takeCount;
        }

        @Override
        public boolean hasNext() {
            // Skip elements if we've taken enough in current group
            while (takenInCurrentGroup >= takeCount && baseIterator.hasNext()) {
                for (int i = 0; i < skipCount && baseIterator.hasNext(); i++) {
                    baseIterator.next();
                    currentPosition++;
                }
                takenInCurrentGroup = 0;
            }

            return baseIterator.hasNext();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            takenInCurrentGroup++;
            currentPosition++;
            return baseIterator.next();
        }

        @Override
        public int getPosition() {
            return currentPosition;
        }
    }
    
    /**
     * Iterator that provides windowed/sliding window access to elements
     */
    static class WindowedIterator<T> implements Iterator<List<T>> {
        private final Iterator<T> baseIterator;
        private final int windowSize;
        private final int stepSize;
        private final LinkedList<T> window = new LinkedList<>();
        private boolean isInitialized = false;

        public WindowedIterator(Iterator<T> baseIterator, int windowSize, int stepSize) {
            this.baseIterator = baseIterator;
            this.windowSize = windowSize;
            this.stepSize = stepSize;
        }

        public WindowedIterator(Iterator<T> baseIterator, int windowSize) {
            this(baseIterator, windowSize, 1); // Default step size of 1
        }

        @Override
        public boolean hasNext() {
            if (!isInitialized) {
                // Initialize first window
                for (int i = 0; i < windowSize && baseIterator.hasNext(); i++) {
                    window.add(baseIterator.next());
                }
                isInitialized = true;
            }

            return window.size() == windowSize;
        }

        @Override
        public List<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            List<T> result = new ArrayList<>(window);

            // Slide window by stepSize
            for (int i = 0; i < stepSize; i++) {
                if (!window.isEmpty()) {
                    window.removeFirst();
                }
                if (baseIterator.hasNext()) {
                    window.add(baseIterator.next());
                }
            }

            return result;
        }
    }
    
    /**
     * Iterator that groups consecutive elements based on a key function
     */
    static class GroupingIterator<T, K> implements Iterator<Map.Entry<K, List<T>>> {
        private final Iterator<T> baseIterator;
        private final Function<T, K> keyFunction;
        private T nextElement;
        private boolean hasNextElement;

        public GroupingIterator(Iterator<T> baseIterator, Function<T, K> keyFunction) {
            this.baseIterator = baseIterator;
            this.keyFunction = keyFunction;
            advance();
        }

        private void advance() {
            hasNextElement = baseIterator.hasNext();
            if (hasNextElement) {
                nextElement = baseIterator.next();
            }
        }

        @Override
        public boolean hasNext() {
            return hasNextElement;
        }

        @Override
        public Map.Entry<K, List<T>> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            K currentKey = keyFunction.apply(nextElement);
            List<T> group = new ArrayList<>();
            group.add(nextElement);

            advance();

            // Collect all consecutive elements with the same key
            while (hasNextElement && keyFunction.apply(nextElement).equals(currentKey)) {
                group.add(nextElement);
                advance();
            }

            return new AbstractMap.SimpleEntry<>(currentKey, group);
        }
    }
}