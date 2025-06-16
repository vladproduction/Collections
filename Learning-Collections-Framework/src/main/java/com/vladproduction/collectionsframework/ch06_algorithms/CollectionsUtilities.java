package com.vladproduction.collectionsframework.ch06_algorithms;

import java.util.*;

/**
 * Demonstrates Collections and Arrays utility methods
 * Covers common operations like: shuffling, reversing, copying, finding min/max, etc.
 */
public class CollectionsUtilities {
    public static void main(String[] args) {

        CollectionsUtilities utilities = new CollectionsUtilities();

        System.out.println("=== COLLECTIONS & ARRAYS UTILITIES ===");
        System.out.println("=======================================\n");

        utilities.demonstrateCollectionsUtilities();
        utilities.demonstrateArraysUtilities();
        utilities.demonstrateCollectionsFactoryMethods();
        utilities.demonstrateUnmodifiableCollections();
        utilities.demonstrateSynchronizedCollections();
        utilities.demonstrateFrequencyAndDisjoint();
        utilities.demonstrateAdditionalUtilities();

    }

    /**
     * Demonstrates Collections utility methods
     */
    public void demonstrateCollectionsUtilities() {
        System.out.println("1. Collections Utility Methods:");
        System.out.println("-------------------------------");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6));
        System.out.println("Original list: " + numbers);

        // Sorting
        Collections.sort(numbers);
        System.out.println("After sort: " + numbers);

        // Reverse
        Collections.reverse(numbers);
        System.out.println("After reverse: " + numbers);

        // Shuffle
        Collections.shuffle(numbers);
        System.out.println("After shuffle: " + numbers);

        // Min and Max
        int min = Collections.min(numbers);
        int max = Collections.max(numbers);
        System.out.println("Min: " + min + ", Max: " + max);

        // Rotate - shifts elements by specified distance
        List<String> words = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println("Before rotate: " + words);
        Collections.rotate(words, 2);
        System.out.println("After rotate in distance (2): " + words);

        // Swap elements
        Collections.swap(words, 0, 4);
        System.out.println("After swap(0,4): " + words);

        // Fill - replaces all elements
        List<String> fillList = new ArrayList<>(Arrays.asList("X", "Y", "Z"));
        System.out.println("Before fill: " + fillList);
        Collections.fill(fillList, "FILLED");
        System.out.println("After fill: " + fillList);

        // Copy
        List<Integer> source = Arrays.asList(10, 20, 30);
        List<Integer> dest = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Destination before copy: " + dest);
        Collections.copy(dest, source);
        System.out.println("Destination after copy: " + dest);

        // Replace all occurrences
        List<String> replaceList = new ArrayList<>(Arrays.asList("a", "b", "a", "c", "a"));
        System.out.println("Before replaceAll: " + replaceList);
        Collections.replaceAll(replaceList, "a", "X");
        System.out.println("After replaceAll('a'->'X'): " + replaceList);

        System.out.println();
    }

    /**
     * Demonstrates Arrays utility methods
     */
    public void demonstrateArraysUtilities() {
        System.out.println("2. Arrays Utility Methods:");
        System.out.println("--------------------------");

        // Array creation and initialization
        int[] numbers = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        System.out.println("Original array: " + Arrays.toString(numbers));

        // Sort
        int[] sortedCopy = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sortedCopy);
        System.out.println("Sorted copy: " + Arrays.toString(sortedCopy));

        // Fill array
        int[] fillArray = new int[5];
        Arrays.fill(fillArray, 42);
        System.out.println("Filled array: " + Arrays.toString(fillArray));

        // Fill range
        int[] rangeArray = new int[10];
        Arrays.fill(rangeArray, 2, 7, 100);
        System.out.println("Range filled (2-6): " + Arrays.toString(rangeArray));

        // Copy arrays
        int[] copied = Arrays.copyOf(numbers, numbers.length);
        System.out.println("Full copy: " + Arrays.toString(copied));

        int[] partialCopy = Arrays.copyOfRange(numbers, 2, 6);
        System.out.println("Range copy (2-5): " + Arrays.toString(partialCopy));

        // Equals comparison
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        int[] array3 = {1, 2, 4};
        System.out.println("array1 equals array2: " + Arrays.equals(array1, array2));
        System.out.println("array1 equals array3: " + Arrays.equals(array1, array3));

        // Deep equals for multidimensional arrays
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{1, 2}, {3, 4}};
        System.out.println("Deep equals matrices: " + Arrays.deepEquals(matrix1, matrix2));

        // Hash code
        System.out.println("Array hashCode: " + Arrays.hashCode(array1));
        System.out.println("Deep hashCode: " + Arrays.deepHashCode(matrix1));

        // Convert to List
        String[] stringArray = {"apple", "banana", "cherry"};
        List<String> stringList = Arrays.asList(stringArray);
        System.out.println("Array to List: " + stringList);

        // Stream from array (Java 8+)
        int sum = Arrays.stream(numbers).sum();
        System.out.println("Sum using stream: " + sum);

        System.out.println();
    }

    /**
     * Demonstrates Collections factory methods (Java 9+)
     */
    public void demonstrateCollectionsFactoryMethods() {
        System.out.println("3. Collections Factory Methods:");
        System.out.println("-------------------------------");

        // Create singleton collections
        Set<String> singletonSet = Collections.singleton("unique");
        List<String> singletonList = Collections.singletonList("single");
        Map<String, String> singletonMap = Collections.singletonMap("key", "value");

        System.out.println("Singleton set: " + singletonSet);
        System.out.println("Singleton list: " + singletonList);
        System.out.println("Singleton map: " + singletonMap);

        // Create empty collections
        List<String> emptyList = Collections.emptyList();
        Set<String> emptySet = Collections.emptySet();
        Map<String, String> emptyMap = Collections.emptyMap();

        System.out.println("Empty collections created (size): " +
                emptyList.size() + ", " + emptySet.size() + ", " + emptyMap.size());

        // N copies of an element
        List<String> nCopies = Collections.nCopies(5, "repeat");
        System.out.println("5 copies: " + nCopies);

        System.out.println();
    }

    /**
     * Demonstrates unmodifiable collections
     */
    public void demonstrateUnmodifiableCollections() {
        System.out.println("4. Unmodifiable Collections:");
        System.out.println("----------------------------");

        List<String> mutableList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> unmodifiableList = Collections.unmodifiableList(mutableList);

        System.out.println("Original list: " + mutableList);
        System.out.println("Unmodifiable view: " + unmodifiableList);

        // Modify original - affects unmodifiable view
        mutableList.add("D");
        System.out.println("After adding to original: " + unmodifiableList);

        // Attempting to modify unmodifiable list would throw UnsupportedOperationException
        try {
            unmodifiableList.add("E");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify unmodifiable list: " + e.getClass().getSimpleName());
        }

        // Unmodifiable Set and Map
        Set<Integer> mutableSet = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> unmodifiableSet = Collections.unmodifiableSet(mutableSet);
        System.out.println("Unmodifiable set: " + unmodifiableSet);

        Map<String, Integer> mutableMap = new HashMap<>();
        mutableMap.put("one", 1);
        mutableMap.put("two", 2);
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(mutableMap);
        System.out.println("Unmodifiable map: " + unmodifiableMap);

        System.out.println();
    }

    /**
     * Demonstrates synchronized collections
     */
    public void demonstrateSynchronizedCollections() {
        System.out.println("5. Synchronized Collections:");
        System.out.println("----------------------------");

        List<String> normalList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> syncList = Collections.synchronizedList(normalList);

        System.out.println("Synchronized list created: " + syncList);
        System.out.println("Class: " + syncList.getClass().getSimpleName());

        // Synchronized Set
        Set<Integer> normalSet = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> syncSet = Collections.synchronizedSet(normalSet);
        System.out.println("Synchronized set: " + syncSet);

        // Synchronized Map
        Map<String, String> normalMap = new HashMap<>();
        normalMap.put("key1", "value1");
        normalMap.put("key2", "value2");
        normalMap.put("key3", "value3");
        Map<String, String> syncMap = Collections.synchronizedMap(normalMap);
        System.out.println("Synchronized map: " + syncMap);

        // Note: Even with synchronized collections, iteration needs external synchronization
        synchronized (syncList) {
            for (String item : syncList) {
                System.out.println("Safe iteration: " + item);
            }
        }

        System.out.println();
    }

    /**
     * Demonstrates frequency counting and disjoint checking
     */
    public void demonstrateFrequencyAndDisjoint() {
        System.out.println("6. Frequency and Disjoint Operations:");
        System.out.println("-------------------------------------");

        List<String> letters = Arrays.asList("a", "b", "a", "c", "b", "a", "d");
        System.out.println("Letter list: " + letters);

        // Count frequency of elements
        int frequencyA = Collections.frequency(letters, "a");
        int frequencyB = Collections.frequency(letters, "b");
        System.out.println("Frequency of 'a': " + frequencyA);
        System.out.println("Frequency of 'b': " + frequencyB);

        // Check if collections are disjoint (no common elements)
        List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");
        List<String> consonants = Arrays.asList("b", "c", "d", "f", "g");
        List<String> mixedList = Arrays.asList("a", "b", "c");

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Mixed: " + mixedList);

        boolean disjointVC = Collections.disjoint(vowels, consonants);
        boolean disjointVM = Collections.disjoint(vowels, mixedList);

        System.out.println("Vowels and consonants are disjoint: " + disjointVC);
        System.out.println("Vowels and mixed are disjoint: " + disjointVM);

        // Index of sublist
        List<Integer> mainList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> subList = Arrays.asList(4, 5, 6);
        List<Integer> notSubList = Arrays.asList(4, 6, 5);

        int indexOfSub = Collections.indexOfSubList(mainList, subList);
        int indexOfNotSub = Collections.indexOfSubList(mainList, notSubList);

        System.out.println("Main list: " + mainList);
        System.out.println("Sublist " + subList + " found at index: " + indexOfSub);
        System.out.println("Sublist " + notSubList + " found at index: " + indexOfNotSub);

        // Last index of sublist
        List<Integer> repeatingList = Arrays.asList(1, 2, 3, 1, 2, 3, 1, 2, 3);
        List<Integer> pattern = Arrays.asList(1, 2);

        int firstIndex = Collections.indexOfSubList(repeatingList, pattern);
        int lastIndex = Collections.lastIndexOfSubList(repeatingList, pattern);

        System.out.println("Repeating list: " + repeatingList);
        System.out.println("Pattern " + pattern + " first at: " + firstIndex + ", last at: " + lastIndex);

        System.out.println();

    }

    /**
     * Demonstrates additional utility operations
     */
    private void demonstrateAdditionalUtilities() {
        System.out.println("7. Additional Utility Operations:");
        System.out.println("---------------------------------");

        // Creating lists with different characteristics
        List<String> arrayList = new ArrayList<>(Arrays.asList("ArrayList", "implementation"));
        List<String> linkedList = new LinkedList<>(Arrays.asList("LinkedList", "implementation"));
        List<String> vector = new Vector<>(Arrays.asList("Vector", "implementation"));

        System.out.println("ArrayList: " + arrayList + " (Class: " + arrayList.getClass().getSimpleName() + ")");
        System.out.println("LinkedList: " + linkedList + " (Class: " + linkedList.getClass().getSimpleName() + ")");
        System.out.println("Vector: " + vector + " (Class: " + vector.getClass().getSimpleName() + ")");

        // Demonstrate different Set implementations
        Set<String> hashSet = new HashSet<>(Arrays.asList("c", "a", "b"));
        Set<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList("c", "a", "b"));
        Set<String> treeSet = new TreeSet<>(Arrays.asList("c", "a", "b"));

        System.out.println("HashSet (unordered): " + hashSet);
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);
        System.out.println("TreeSet (sorted): " + treeSet);

        // Demonstrate Map implementations
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();

        // Add elements in specific order
        String[] keys = {"charlie", "alpha", "beta"};
        for (int i = 0; i < keys.length; i++) {
            hashMap.put(keys[i], i);
            linkedHashMap.put(keys[i], i);
            treeMap.put(keys[i], i);
        }

        System.out.println("HashMap (no guaranteed order): " + hashMap);
        System.out.println("LinkedHashMap (insertion order): " + linkedHashMap);
        System.out.println("TreeMap (sorted by key): " + treeMap);

        // Binary search insertion point demonstration
        List<Integer> sortedNumbers = Arrays.asList(1, 3, 5, 7, 9);
        int insertionPoint = Collections.binarySearch(sortedNumbers, 6);
        System.out.println("Sorted list: " + sortedNumbers);
        System.out.println("Insertion point for 6: " + (-insertionPoint - 1));

        // Demonstrate addAll utility
        Collection<String> collection1 = new ArrayList<>(Arrays.asList("A", "B"));
        Collection<String> collection2 = Arrays.asList("C", "D", "E");

        System.out.println("Collection1 before addAll: " + collection1);
        Collections.addAll(collection1, "F", "G", "H");
        System.out.println("Collection1 after addAll: " + collection1);

        boolean changed = collection1.addAll(collection2);
        System.out.println("Collection1 after addAll(collection2): " + collection1);
        System.out.println("Collection changed: " + changed);

        System.out.println();
    }

}
