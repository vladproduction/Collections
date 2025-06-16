package com.vladproduction.collectionsframework.ch01_fundamentals;

import java.util.*;

/**
 * Demonstrates the basic concepts and operations of Java Collections Framework
 * Covers: Collection interface, basic operations, and hierarchy overview
 */
public class CollectionBasics {

    public static void main(String[] args) {

        CollectionBasics demo = new CollectionBasics();

        System.out.println("\n=== JAVA COLLECTIONS FRAMEWORK BASICS ===\n");

        demo.demonstrateCollectionHierarchy();
        demo.demonstrateBasicOperations();
        demo.demonstrateBulkOperations();
        demo.demonstrateConversionToArray();
        demo.demonstrateGenericCollections();
        demonstrateCollectionEquality();

    }

    /**
     * Shows the basic hierarchy and types of collections
     */
    private void demonstrateCollectionHierarchy(){
        System.out.println("1. COLLECTION HIERARCHY OVERVIEW");
        System.out.println("=====================================");

        //Collection interface is the root of the hierarchy
        //Collection<E> is a generic interface that contains elements of type E
        Collection<String> list = new ArrayList<>();
        Collection<Integer> set = new HashSet<>();
        Collection<Double> queue = new LinkedList<>();

        System.out.println("ArrayList implements Collection: " + (list instanceof Collection));
        System.out.println("HashSet implements Collection: " + (set instanceof Collection));
        System.out.println("LinkedList implements Collection: " + (queue instanceof Collection));

        //Map interface is separate from Collection interface
        //Map<K, V> is a generic interface that maps keys to values
        Map<String, Integer> map = new HashMap<>();

        System.out.println("HashMap implements Collection: " + (map instanceof Collection));
        System.out.println("(Maps are separate from Collection hierarchy)\n");

    }

    /**
     * Demonstrates basic Collection operations
     */
    private void demonstrateBasicOperations(){
        System.out.println("2. BASIC COLLECTION OPERATIONS");
        System.out.println("=====================================");

        //adding elements to a collection
        Collection<String> fruits = new ArrayList<>();
        System.out.println("Adding elements:");
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Pear");
        fruits.add("Pineapple");

        System.out.println("After adding elements: " + fruits);

        //size and emptiness of a collection
        System.out.println("Size: " + fruits.size());
        System.out.println("Is empty: " + fruits.isEmpty());

        //contains method
        System.out.println("Does collection contain \"Pear\": " + fruits.contains("Pear"));
        System.out.println("Does collection contain \"Watermelon\": " + fruits.contains("Watermelon"));

        //remove element
        System.out.println("Removing \"Pear\": " + fruits.remove("Pear"));
        System.out.println("After removing \"Pear\": " + fruits);

        //clear operation:
        Collection<String> temp = new ArrayList<>(fruits);
        temp.clear();
        System.out.println("After clearing collection: " + temp);
        System.out.println("Is collection empty: " + temp.isEmpty());
        System.out.println();

    }

    /**
     * Demonstrates bulk operations on collections
     */
    private void demonstrateBulkOperations(){
        System.out.println("3. BULK OPERATIONS");
        System.out.println("=====================================");

        //create two collections of numbers
        Collection<Integer> numbers1 = new ArrayList<>();
        numbers1.addAll(List.of(1, 2, 3, 4, 5));

        Collection<Integer> numbers2 = new ArrayList<>();
        numbers2.addAll(List.of(4, 5, 6, 7, 8));

        //initial state of the collections
        System.out.println("Numbers1: " + numbers1);
        System.out.println("Numbers2: " + numbers2);

        //addAll - Union of two collections
        Collection<Integer> union = new ArrayList<>(numbers1);
        union.addAll(numbers2);
        System.out.println("Union of numbers1 and numbers2: " + union);

        //retainAll - Intersection operation
        Collection<Integer> intersection = new ArrayList<>(numbers1);
        intersection.retainAll(numbers2);
        System.out.println("Intersection of numbers1 and numbers2: " + intersection);

        //removeAll - Difference operation
        Collection<Integer> difference = new ArrayList<>(numbers1);
        difference.removeAll(numbers2);
        System.out.println("Difference of numbers1 and numbers2: " + difference);

        // containsAll - Subset check
        Collection<Integer> subset = Arrays.asList(1, 2, 3);
        System.out.println("Collection 1 contains subset [1,2,3]: " + numbers1.containsAll(subset));
        System.out.println();

    }

    /**
     * Demonstrates conversion between Collection and Array
     */
    private void demonstrateConversionToArray(){
        System.out.println("4. CONVERSION TO ARRAY");
        System.out.println("=====================================");

        //create a collection with some elements:
        Collection<String> colors = new ArrayList<>();
        colors.addAll(Arrays.asList("Red", "Green", "Blue", "Yellow", "Orange"));

        System.out.println("Original collection (Strings): " + colors);

        // toArray() - returns Object[]
        Object[] objectArray = colors.toArray();
        System.out.println("Converted to Object[]: " + Arrays.toString(objectArray));

        // toArray(T[] array) - returns T[]
        String[] stringArray = colors.toArray(new String[0]);
        System.out.println("Converted to String[]: " + Arrays.toString(stringArray));

        // Converting array back to collection
        Collection<String> convertedCollection = Arrays.asList(stringArray);
        System.out.println("Converted back to collection: " + convertedCollection);
        System.out.println();

        //=======example with Collection<Integer> and Integer[] array=========
        Collection<Integer> collection = new ArrayList<>();
        collection.addAll(Arrays.asList(100, 200, 300, 400, 500));
        System.out.println("Original collection (Integers): " + collection);

        //toArray() - returns Object[]
        Object[] objectIntArray = collection.toArray();
        System.out.println("Converted to Object[]: " + Arrays.toString(objectIntArray));

        // toArray(T[] array) - returns T[]
        Integer[] intArray = collection.toArray(new Integer[0]);
        System.out.println("Converted to Integer[]: " + Arrays.toString(intArray));

        //convert back to collection
        Collection<Integer> convertedCollection2 = Arrays.asList(intArray);
        System.out.println("Converted back to collection: " + convertedCollection2);
        System.out.println();
    }

    /**
     * Demonstrates the importance of generics with collections
     */
    private void demonstrateGenericCollections(){
        System.out.println("5. GENERICS COLLECTIONS");
        System.out.println("=====================================");

        //without generics (raw type) - not recommended:
        Collection rawCollection = new ArrayList();
        rawCollection.add("Apple");
        rawCollection.add(123);
        rawCollection.add(45.67);
        System.out.println("Raw collection (mixed types): " + rawCollection);

        // With generics - type safe
        Collection<String> stringCollection = new ArrayList<>();
        stringCollection.add("Hello");
        stringCollection.add("World");
        // stringCollection.add(123); // This would cause compilation error
        stringCollection.add("!");
        System.out.println("Generic collection (String only): " + stringCollection);

        // Benefits of generics
        System.out.println("\nBenefits of generics:");
        System.out.println("- Compile-time type checking");
        System.out.println("- Elimination of type casting");
        System.out.println("- Better code readability");
        System.out.println("- Runtime type safety");

        // Example of type safety
        for (String s : stringCollection) {
            System.out.println("Processing string: " + s.toUpperCase());
        }
        System.out.println();

        //example with processing integers
        Collection<Integer> integerCollection = new ArrayList<>();
        integerCollection.add(1);
        integerCollection.add(2);
        integerCollection.add(3);
        integerCollection.add(4);
        integerCollection.add(5);

        for (Integer i : integerCollection) {
            System.out.println("Processing integer: " + i * 10);
        }
        System.out.println();
    }

    /**
     * Utility method to demonstrate collection equality
     */
    public static void demonstrateCollectionEquality(){
        System.out.println("6. COLLECTION EQUALITY");
        System.out.println("======================");

        Collection<String> list1 = new ArrayList<>();
        list1.addAll(Arrays.asList("A", "B", "C"));

        Collection<String> list2 = new ArrayList<>();
        list2.addAll(Arrays.asList("A", "B", "C"));

        Collection<String> set1 = new HashSet<>();
        set1.addAll(Arrays.asList("A", "B", "C"));

        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);
        System.out.println("Set1: " + set1);

        System.out.println("List1 equals List2: " + list1.equals(list2));
        System.out.println("List1 equals Set1 (different types): " + list1.equals(set1));
        System.out.println("Note: Different collection types are not equal even with same elements");
    }

}














