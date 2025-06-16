package com.vladproduction.collectionsframework.ch01_fundamentals;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Demonstrates different types of iterators in Java Collections Framework
 * Covers: Iterator, ListIterator, Enhanced for-loop, forEach method
 */
public class IteratorExamples {

    public static void main(String[] args) {

        IteratorExamples demo = new IteratorExamples();

        System.out.println("\n=== ITERATOR EXAMPLES ===\n");

        demo.demonstrateBasicIterator();
        demo.demonstrateListIterator();
        demo.demonstrateEnhancedForLoop();
        demo.demonstrateForEachMethod();
        demo.demonstrateIteratorSafety();
        demo.demonstrateFailFastBehavior();
        demonstrateCustomIterator();

    }

    /**
     * Demonstrates basic Iterator usage
     */
    private void demonstrateBasicIterator() {
        System.out.println("1. BASIC ITERATOR");
        System.out.println("=================");

        List<String> languages = new ArrayList<>();
        languages.addAll(Arrays.asList("Java", "Python", "JavaScript", "C++", "Go"));

        System.out.println("Original list: " + languages);

        // Using Iterator for traversal
        System.out.println("\nTraversing with Iterator:");
        Iterator<String> languageIterator = languages.iterator();
        while (languageIterator.hasNext()) {
            String language = languageIterator.next();
            System.out.println("- " + language);
        }
        System.out.println();

        // Using Iterator for safe removal
        System.out.println("\nRemoving languages containing 'a':");
        Iterator<String> iterator = languages.iterator();
        while (iterator.hasNext()) {
            String language = iterator.next();
            if (language.toLowerCase().contains("a")) {
                iterator.remove();  //safe removal during iteration
                System.out.println("- Removed language: " + language);
            }
        }
        System.out.println("Remaining languages: " + languages);
        System.out.println();

    }

    /**
     * Demonstrates ListIterator for bidirectional traversal
     */
    private void demonstrateListIterator() {
        System.out.println("2. LIST ITERATOR");
        System.out.println("=================");

        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(1, 2, 3, 4, 5));

        System.out.println("Original list: " + numbers);

        //forward traversal with ListIterator
        System.out.println("\nForward traversal:");
        ListIterator<Integer> listIterator = numbers.listIterator();
        while (listIterator.hasNext()) {
            int index = listIterator.nextIndex();
            Integer value = listIterator.next();
            System.out.println("Index " + index + ": " + value);
        }

        //backward traversal with ListIterator
        System.out.println("\nBackward traversal:");
        while (listIterator.hasPrevious()) {
            int index = listIterator.previousIndex();
            Integer value = listIterator.previous();
            System.out.println("Index " + index + ": " + value);
        }

        // Modification during iteration
        System.out.println("\nModifying during iteration (multiply even numbers by 10):");
        listIterator = numbers.listIterator();
        while (listIterator.hasNext()) {
            Integer value = listIterator.next();
            if (value % 2 == 0) {
                listIterator.set(value * 10); //safe modification during iteration
            }
        }
        System.out.println("Modified list: " + numbers);

        // Adding elements during iteration
        System.out.println("\nAdding elements during iteration:");
        listIterator = numbers.listIterator();
        while (listIterator.hasNext()) {
            Integer value = listIterator.next();
            if (value > 10) {
                listIterator.add(999); //add after current element
//                break; //if uncomment only 1 element will be added due to the condition
            }
        }
        System.out.println("After adding elements: " + numbers);
        System.out.println();
    }

    /**
     * Demonstrates enhanced for-loop (for each loop)
     */
    private void demonstrateEnhancedForLoop() {
        System.out.println("3. ENHANCED FOR-LOOP");
        System.out.println("==================");

        Set<String> languagesSet = new HashSet<>();
        languagesSet.addAll(Arrays.asList("Java", "Python", "JavaScript", "C++", "Go"));

        System.out.println("\nOriginal set of languages: " + languagesSet);

        System.out.println("\nUsing enhanced for-loop with set:");
        for (String language : languagesSet) {
            System.out.println("- " + language);
        }

        //with array of number elements
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("\nUsing enhanced for-loop with array:");
        for (int number : numbers) {
            System.out.println("- " + number);
        }
        System.out.println();

        //with array of String elements
        String[] strings = {"Hello", "World", "of", "Java"};
        System.out.println("\nUsing enhanced for-loop with array of Strings:");
        for (String string : strings) {
            System.out.println("- " + string);
        }

        // Limitations of enhanced for-loop
        System.out.println("\nLimitations of enhanced for-loop:");
        System.out.println("- Cannot modify the collection during iteration");
        System.out.println("- Cannot access index information");
        System.out.println("- Cannot iterate backwards");
        System.out.println("- Cannot remove elements safely");
        System.out.println();

    }

    /**
     * Demonstrates forEach method with lambda expressions
     */
    private void demonstrateForEachMethod() {
        System.out.println("4. FOREACH METHOD (JAVA 8+)");
        System.out.println("============================");

        System.out.println("\nUsing forEach with lambda:");
        List<String> cities = new ArrayList<>();
        cities.addAll(Arrays.asList("New York", "London", "Paris", "Tokyo", "Beijing"));
        cities.forEach(city -> System.out.println("- " + city));

        System.out.println("\nUsing forEach method reference:");
        cities.forEach(System.out::println);
        System.out.println();

        System.out.println("\nUsing forEach with complex operations:");
        cities.forEach(city -> {
            String cityUpperCase = city.toUpperCase();
            int cityLength = city.length();
            System.out.println("- " + cityUpperCase + " (" + cityLength + " chars)");
        });

        // forEach with Map
        System.out.println("\nUsing forEach with Map:");
        Map<String, Integer> population = new HashMap<>();
        population.put("New York", 8_000_000);
        population.put("London", 12_500_000);
        population.put("Paris", 7_000_000);
        population.forEach((city, pop) ->
                System.out.println(city + ": " + String.format("%,d", pop) + " people")
        );
        System.out.println();
    }

    /**
     * Demonstrates iterator safety for concurrent modification
     */
    private void demonstrateIteratorSafety() {
        System.out.println("5. ITERATOR SAFETY");
        System.out.println("==================");

        List<String> animals = new ArrayList<>();
        animals.addAll(Arrays.asList("Cat", "Dog", "Elephant", "Tiger", "Lion", "Orangutan"));

        System.out.println("Original list: " + animals);

        // WRONG: Direct modification during enhanced for-loop
        System.out.println("\nWRONG approach - direct modification during iteration:");
        try{
            for (String animal : animals) {
                if(animal.length() > 3){
                    animals.remove(animal); // This can cause ConcurrentModificationException
                }
            }
        }catch (ConcurrentModificationException e){
            System.out.println("ConcurrentModificationException caught: " + e.getMessage());
        }

        // CORRECT: Using Iterator.remove()
        System.out.println("\nCORRECT approach - using Iterator.remove() - List<String>:");
        animals.clear();
        animals.addAll(Arrays.asList("Cat", "Dog", "Elephant", "Tiger", "Lion", "Orangutan"));

        Iterator<String> iterator = animals.iterator();
        while (iterator.hasNext()) {
            String animal = iterator.next();
            if (animal.length() > 3) {
                iterator.remove(); // Safe removal
                System.out.println("Removed: " + animal);
            }
        }
        System.out.println("Final list: " + animals);
        System.out.println();

        // CORRECT: Using Iterator.remove()
        System.out.println("\nCORRECT approach - using Iterator.remove() - List<Integer>:");
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original list integers: " + numbers);
        Iterator<Integer> integerIterator = numbers.iterator();
        while (integerIterator.hasNext()) {
            Integer number = integerIterator.next();
            if(number % 2 == 0 || number < 5){      //one of this condition is true: removing
                integerIterator.remove();
                System.out.println("Removed  even integer: " + number);
            }
        }
        System.out.println("Final list integers: " + numbers);
        System.out.println();
    }

    /**
     * Demonstrates fail-fast behavior of iterators
     */
    private void demonstrateFailFastBehavior() {
        System.out.println("6. FAIL-FAST BEHAVIOR");
        System.out.println("====================");

        List<String> items = new ArrayList<>();
        items.addAll(Arrays.asList("Item1", "Item2", "Item3", "Item4"));

        System.out.println("Original list: " + items);

        Iterator<String> iterator1 = items.iterator();
        Iterator<String> iterator2 = items.iterator();

        System.out.println("\nUsing first iterator:");
        System.out.println(iterator1.next()); // Item1

        System.out.println("\nModifying collection with second iterator:");
        iterator2.next(); // Item1
        iterator2.remove(); // Remove Item1

        System.out.println("List after modification: " + items);

        try {
            System.out.println("\nTrying to continue with first iterator:");
            System.out.println(iterator1.next()); // This should throw exception
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException: Iterator detected concurrent modification");
        }

        // Demonstrating fail-safe collections (from java.util.concurrent)
        System.out.println("\nFail-safe alternative (ConcurrentLinkedQueue):");
        Queue<String> concurrentQueue = new ConcurrentLinkedQueue<>();
        concurrentQueue.addAll(Arrays.asList("A", "B", "C", "D", "E", "F"));
        System.out.println("Original queue: " + concurrentQueue);

        Iterator<String> safeIterator = concurrentQueue.iterator();
        System.out.println("First element in queue: " + safeIterator.next());
        //modify during iteration - no exception with Concurrent Collections
        concurrentQueue.add("G");
        System.out.println("Added element during iteration");
        System.out.println("Continue iteration next() method called: " + safeIterator.next());
        System.out.println("Continue iteration next() method called: " + safeIterator.next());
        System.out.println("Continue iteration next() method called: " + safeIterator.next());
        System.out.println("Is Contains 'G' in the concurrentQueue? " + concurrentQueue.contains("G"));
        System.out.println("Final queue: " + concurrentQueue);
        System.out.println();

    }

    /**
     * Demonstration of custom iterator
     * */
    public static void demonstrateCustomIterator() {
        System.out.println("7. CUSTOM ITERATOR");
        System.out.println("==================");

        CountDownIterable countdown = new CountDownIterable(7);
        System.out.println("Custom countdown iterator:");
        for (Integer number : countdown) {
            System.out.println("Countdown: " + number);
        }

    }

    /**
     * Utility method to demonstrate custom iterator implementation
     */
    public static class CountDownIterable implements Iterable<Integer> {

        private final int start;

        public CountDownIterable(int start) {
            this.start = start;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {

                private int current = start;

                @Override
                public boolean hasNext() {
                    return current > 0;
                }

                @Override
                public Integer next() {
                    if(!hasNext()){
                        throw new NoSuchElementException();
                    }
                    return current--;
                }
            };
        }
    }
}















