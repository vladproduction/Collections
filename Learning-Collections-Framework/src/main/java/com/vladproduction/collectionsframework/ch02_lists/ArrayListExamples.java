package com.vladproduction.collectionsframework.ch02_lists;

import java.util.*;

/**
 * Comprehensive examples of ArrayList usage, features, and best practices
 * Covers: Basic operations, capacity management, performance characteristics, and advanced features
 */
public class ArrayListExamples {
    public static void main(String[] args) {

        ArrayListExamples demo = new ArrayListExamples();

        System.out.println("\n=== ARRAYLIST EXAMPLES ===");

        demo.basicOperations();
        demo.capacityManagement();
        demo.performanceCharacteristics();
        demo.searchingAndSorting();
        demo.advancedOperations();
        demo.arrayListVsArray();
        demo.commonPitfalls();
        demo.bestPractices();

    }

    /**
     * Demonstrates basic ArrayList operations
     */
    private void basicOperations() {
        System.out.println("\n1. BASIC ARRAYLIST OPERATIONS");
        System.out.println("==============================");

        //create ArrayList
        List<String> fruits = new ArrayList<>();
        System.out.println("Creating ArrayList: " + fruits);
        System.out.println("Initial size: " + fruits.size());
        System.out.println("Is empty: " + fruits.isEmpty());

        //adding elements:
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        System.out.println("\nArrayList after adding: " + fruits);

        //add element at specific index:
        fruits.add(1, "Grape");
        System.out.println("\nAfter inserting 'Grape' at index 1: " + fruits);

        //accessing elements:
        System.out.println("\nAccessing elements:");
        System.out.println("Element at index '0': " + fruits.get(0));
        System.out.println("First Element: " + fruits.get(0));
        System.out.println("Last Element: " + fruits.get(fruits.size() - 1));

        //modify element:
        String oldValue = fruits.set(2, "Mango");
        System.out.println("\nReplaced element: " + oldValue + " with 'Mango': " + fruits);

        // Finding elements
        System.out.println("\nSearching elements:");
        System.out.println("Index of 'Banana': " + fruits.indexOf("Banana"));
        System.out.println("Index of 'Mango': " + fruits.indexOf("Mango"));
        System.out.println("Contains 'Apple': " + fruits.contains("Apple"));
        System.out.println("Contains 'Pear': " + fruits.contains("Pear"));

        // Removing elements
        System.out.println("\nRemoving elements:");
        fruits.remove("Grape");
        System.out.println("After removing 'Grape': " + fruits);
        String removed = fruits.remove(1);
        System.out.println("Removed element at index 1 ('" + removed + "'): " + fruits);

        System.out.println("Final size: " + fruits.size());
        System.out.println();

    }

    /**
     * Demonstrates ArrayList capacity management
     */
    private void capacityManagement() {
        System.out.println("2. CAPACITY MANAGEMENT");
        System.out.println("======================");

        //default capacity is 10 for ArrayList
        List<String> defaultList = new ArrayList<>();
        System.out.println("\nInitial capacity for ArrayList by default is 10");

        //custom capacity is available by constructor
        List<String> customCapacityList = new ArrayList<>(20);
        System.out.println("Custom capacity for ArrayList available by constructor");

        //creating from another collection:
        List<Integer> sourceData = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> fromCollection = new ArrayList<>(sourceData);
        System.out.println("\nArrayList created from collection: " + fromCollection);

        // Demonstrating capacity growth
        System.out.println("\nCapacity growth demonstration:");
        List<Integer> growthDemo = new ArrayList<>(2); //small capacity initialized
        System.out.println("Creating ArrayList with capacity '2'");

        for (int i = 0; i < 10; i++) {
            growthDemo.add(i);
            if (i == 2 || i == 3 || i == 5 || i == 8) {
                System.out.println("After adding " + i + " elements, size is: " + growthDemo.size());
                //it grows as: 2 -> 3 -> 4 -> 6 -> 9 -> 13
            }
        }

        //manual capacity management:
        ArrayList<Integer> manualCapacity = new ArrayList<>();
        manualCapacity.ensureCapacity(100);  //Array list has this method to Pre-allocate capacity
        System.out.println("\nEnsured capacity of 100 for better performance when adding many elements");

        //trimming to size
        ArrayList<Integer> trimDemo = new ArrayList<>(100);
        trimDemo.add(1);
        trimDemo.add(2);
        trimDemo.add(3);
        trimDemo.trimToSize(); //reduce capacity to actual size
        System.out.println("\nTrimmed ArrayList to size: " + trimDemo.size());
        System.out.println();


    }

    /**
     * Demonstrates performance characteristics
     */
    private void performanceCharacteristics() {
        System.out.println("3. PERFORMANCE CHARACTERISTICS");
        System.out.println("===============================");

        ArrayList<Integer> list = new ArrayList<>();

        // Adding at end - O(1) amortized
        System.out.println("\nAdding elements at end (O(1) amortized):");
        long start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.add(i);
        }
        long end = System.nanoTime();
        System.out.println("Added 10_000 elements in: " + (end - start) / 1_000_000 + "ms");

        // Random access - O(1)
        System.out.println("\nRandom access (O(1)):");
        start = System.nanoTime();
        Random random = new Random(42); //fixed
        for (int i = 0; i < 1000; i++) {
            list.get(random.nextInt(list.size()));
        }
        end = System.nanoTime();
        System.out.println("1,000 random access operations in: " + (end - start) / 1_000 + " μs");

        // Insertion at beginning - O(n)
        System.out.println("\nInsertion at beginning (O(n)):");
        ArrayList<Integer> insertionDemo = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            insertionDemo.add(i);
        }
        start = System.nanoTime();
        insertionDemo.add(0, -1);
        end = System.nanoTime();
        System.out.println("Insertion at the beginning took: " + (end - start) / 1_000 + " μs");

        // Search operation - O(n)
        System.out.println("\nLinear search (O(n)):");
        start = System.nanoTime();
        list.contains(9999);
        end = System.nanoTime();
        System.out.println("Linear search took: " + (end - start) / 1_000 + " μs");

        System.out.println("\nPerformance Summary:");
        System.out.println("- Access by index: O(1)");
        System.out.println("- Add at end: O(1) amortized");
        System.out.println("- Add at beginning/middle: O(n)");
        System.out.println("- Remove from end: O(1)");
        System.out.println("- Remove from beginning/middle: O(n)");
        System.out.println("- Search: O(n)");
        System.out.println();

    }

    /**
     * Demonstrates searching and sorting operations
     */
    private void searchingAndSorting() {
        System.out.println("4. SEARCHING AND SORTING");
        System.out.println("==============================");

        ArrayList<String> names = new ArrayList<>();
        names.addAll(Arrays.asList("Charlie", "Alice", "Bob", "David", "Eve"));
        System.out.println("Original names: " + names);

        // Linear search
        System.out.println("\nLinear search:");
        System.out.println("Index of 'Bob' " + names.indexOf("Bob"));
        System.out.println("Last Index of 'Alice' " + names.lastIndexOf("Alice"));

        //Sorting operations
        System.out.println("\nSorting operations:");
        ArrayList<String> sortingNames = new ArrayList<>(names);
        Collections.sort(sortingNames);
        System.out.println("Natural sort : " + sortingNames);

        Collections.sort(sortingNames, Collections.reverseOrder());
        System.out.println("Reversed sort : " + sortingNames);

        //Custom sorting:
        Collections.sort(sortingNames, (a, b) -> Integer.compare(a.hashCode(), b.length()));
        System.out.println("Sorting by length: " + sortingNames);

        // Binary search (requires sorted list)
        Collections.sort(names);
        System.out.println("\nBinary search on sorted names: " + names + ": ");
        int charlieIndex = Collections.binarySearch(names, "Charlie");
        System.out.println("Index of 'Charlie': " + charlieIndex);

        //not found case of searching:
        int frankNotExistIndex = Collections.binarySearch(names, "Frank");
        System.out.println("Binary Search for 'Frank' not found: " + frankNotExistIndex);
        System.out.println("Insertion point would be: " + (-frankNotExistIndex - 1));

        //shuffling
        Collections.shuffle(names);
        System.out.println("\nShuffled names: " + names);
        System.out.println();

    }

    /**
     * Demonstrates advanced ArrayList operations
     */
    private void advancedOperations() {
        System.out.println("5. ADVANCED OPERATIONS");
        System.out.println("==============================");

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original numbers: " + numbers);

        //sublist operations
        System.out.println("\nSublist operations:");
        List<Integer> subList = numbers.subList(2, 7);
        System.out.println("SubList (index 2-6): " + subList);

        //modifying sublist affects original:
        subList.set(0, 99);
        System.out.println("\nUpdate sublist element");
        System.out.println("Modifying sublist affects original numbers: \n" + numbers);

        // Bulk operations
        System.out.println("\nBulk operations:");
        ArrayList<Integer> evenNumbers = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        System.out.println("Even numbers: " + evenNumbers);

        numbers.retainAll(evenNumbers);
        System.out.println("After retainAll (intersection): " + numbers);

        // Java 8+ Stream operations
        System.out.println("\nStream operations:");
        ArrayList<String> words = new ArrayList<>();
        words.addAll(Arrays.asList("apple", "banana", "cherry", "date", "elderberry"));
        System.out.println("Original words: " + words);

        ArrayList<String> longWords = words.stream()
                .filter(word -> word.length() > 5)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Words with length > 5: " + longWords);

        ArrayList<String> uppercaseWords = words.stream()
                .map(word -> word.toUpperCase())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Uppercase words: " + uppercaseWords);

        // small challenge interview task(!!!):
        // Not starting with 'p' or 'P' and capitalize first letter
        List<String> notes = Arrays.asList("car", "place", "Dog", "Product", "anagram", "Gear");
        System.out.println("Notes before: " + notes);
        List<String> result = notes.stream()
                .filter(note -> !note.toLowerCase().startsWith("p"))
                .map(note -> note.substring(0, 1).toUpperCase() + note.substring(1))
                .toList();
        System.out.println("Processed Notes: " + result);

        // removeIf (Java 8+)
        ArrayList<Integer> numbersToFilter = new ArrayList<>();
        numbersToFilter.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        numbersToFilter.removeIf(n -> n % 2 == 0);
        System.out.println("After removing even numbers: " + numbersToFilter);
        System.out.println();
    }

    /**
     * Compares ArrayList with traditional arrays
     */
    private void arrayListVsArray() {

        System.out.println("6. ARRAYLIST VS TRADITIONAL ARRAY");
        System.out.println("==================================");

        // Traditional array
        String[] stringArray = new String[5];
        stringArray[0] = "Array1";
        stringArray[1] = "Array2";
        System.out.println("Traditional array: " + Arrays.toString(stringArray));
        System.out.println("Array length (fixed): " + stringArray.length);

        // ArrayList
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("List1");
        stringList.add("List2");
        System.out.println("ArrayList: " + stringList);
        System.out.println("ArrayList size (dynamic): " + stringList.size());

        System.out.println("\nKey differences:");
        System.out.println("Array:");
        System.out.println("  + Fixed size, better memory efficiency");
        System.out.println("  + Faster access (no method call overhead)");
        System.out.println("  + Can store primitives directly");
        System.out.println("  - Size cannot be changed");
        System.out.println("  - No built-in utility methods");

        System.out.println("\nArrayList:");
        System.out.println("  + Dynamic size");
        System.out.println("  + Rich set of utility methods");
        System.out.println("  + Better integration with Collections framework");
        System.out.println("  - Slight memory overhead");
        System.out.println("  - Only stores objects (autoboxing for primitives)");

        // Converting between array and ArrayList
        System.out.println("\nConversion operations:");

        // Array to ArrayList
        String[] sourceArray = {"A", "B", "C"};
        ArrayList<String> fromArray = new ArrayList<>(Arrays.asList(sourceArray));
        System.out.println("Array to ArrayList: " + fromArray);

        // ArrayList to Array
        String[] fromList = stringList.toArray(new String[0]);
        System.out.println("ArrayList to Array: " + Arrays.toString(fromList));
        System.out.println();

    }

    /**
     * Demonstrates common pitfalls and how to avoid them
     * */
    private void commonPitfalls(){

        System.out.println("7. COMMON PITFALLS");
        System.out.println("==================");

        // Pitfall 1: ConcurrentModificationException
        System.out.println("Pitfall 1: ConcurrentModificationException");
        ArrayList<String> items = new ArrayList<>();
        items.addAll(Arrays.asList("A", "B", "C", "D", "E"));

        System.out.println("WRONG: Modifying list during enhanced for-loop");
        try {
            for (String item : items) {
                if (item.equals("C")) {
                    items.remove(item); // This will throw exception
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Exception caught: " + e.getClass().getSimpleName());
        }

        System.out.println("CORRECT: Using Iterator.remove()");
        Iterator<String> iterator = items.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.equals("C")) {
                iterator.remove(); // Safe removal
            }
        }
        System.out.println("After safe removal: " + items);

        // Pitfall 2: Index out of bounds
        System.out.println("\nPitfall 2: IndexOutOfBoundsException");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
        try {
            int value = numbers.get(5); // Index 5 doesn't exist
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("CORRECT: Check bounds before access");
        int index = 5;
        if (index >= 0 && index < numbers.size()) {
            int value = numbers.get(index);
        } else {
            System.out.println("Index " + index + " is out of bounds for size " + numbers.size());
        }

        // Pitfall 3: Null values
        System.out.println("\nPitfall 3: Null pointer issues");
        ArrayList<String> nullDemo = new ArrayList<>();
        nullDemo.add("Hello");
        nullDemo.add(null);
        nullDemo.add("World");

        System.out.println("WRONG: Calling method on possible null");
        for (String str : nullDemo) {
            try {
                System.out.println(str.toUpperCase()); // NullPointerException for null element
            } catch (NullPointerException e) {
                System.out.println("Exception caught: " + e.getClass().getSimpleName());
            }
        }

        System.out.println("CORRECT: Check for null before using");
        for (String str : nullDemo) {
            if (str != null) {
                System.out.println(str.toUpperCase());
            } else {
                System.out.println("Null value encountered, skipping");
            }
        }

        System.out.println();

    }

    /**
     * Demonstrates best practices to use ArrayList
     * */
    private void bestPractices(){
        System.out.println("8. BEST PRACTICES");
        System.out.println("=================");

        // 1. Specify initial capacity if size is known (performance optimization)
        System.out.println("1. Specify initial capacity when size is known");
        ArrayList<String> cities = new ArrayList<>(100); // avoids unnecessary resizing
        cities.add("New York");
        cities.add("London");
        cities.add("Paris");
        System.out.println("Cities: " + cities);

        // 2. Use generics to avoid raw types and casting
        System.out.println("\n2. Use generics to ensure type safety");
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        for (Integer num : numbers) {
            System.out.println("Number: " + num);
        }

        // 3. Use enhanced for-loop or iterator for safe traversal
        System.out.println("\n3. Use enhanced for-loop for read-only traversal");
        for (String city : cities) {
            System.out.println("City: " + city);
        }

        // 4. Use iterator to safely remove elements while iterating
        System.out.println("\n4. Use Iterator to remove elements safely");
        Iterator<Integer> numberIterator = numbers.iterator();
        while (numberIterator.hasNext()) {
            Integer num = numberIterator.next();
            if (num < 15) {
                numberIterator.remove();
            }
        }
        System.out.println("After removal: " + numbers);

        // 5. Avoid unnecessary boxing/unboxing in performance-critical code
        System.out.println("\n5. Avoid unnecessary boxing/unboxing");
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            numbers.add(i); // avoid repetitive autoboxing in hot loops
            sum += numbers.get(i); // this will unbox repeatedly
        }
        System.out.println("Sum (may have performance hit): " + sum);

        // 6. Check for null elements where applicable
        System.out.println("\n6. Handle nulls explicitly if your list allows them");
        cities.add(null);
        for (String city : cities) {
            if (city != null) {
                System.out.println(city.toUpperCase());
            } else {
                System.out.println("Null city encountered");
            }
        }

        // 7. Prefer Collections.unmodifiableList() for read-only views
        System.out.println("\n7. Provide unmodifiable view if list should not change");
        List<String> unmodifiableCities = Collections.unmodifiableList(cities);
        System.out.println("Unmodifiable list: " + unmodifiableCities);
        // unmodifiableCities.add("Tokyo"); // throws UnsupportedOperationException

        // 8. Use subList for list partitioning (with care)
        System.out.println("\n8. Use subList for partitioning (modifies original list!)");
        List<String> firstTwo = cities.subList(0, 2);
        System.out.println("Sublist: " + firstTwo);
        firstTwo.set(0, "Berlin");
        System.out.println("Original list after sublist modification: " + cities);

        // 9. Convert to array safely
        System.out.println("\n9. Convert ArrayList to array using toArray()");
        String[] cityArray = cities.toArray(new String[0]);
        System.out.println("Array: " + Arrays.toString(cityArray));

    }

}






