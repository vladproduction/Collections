package com.vladproduction.collectionsframework.ch03_sets;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Comprehensive examples of LinkedHashSet usage, features, and best practices
 * Covers: Basic operations, capacity management, performance characteristics, and advanced features
 */
public class LinkedHashSetExamples {
    public static void main(String[] args) {

        System.out.println("LINKED HASHSET EXAMPLES\n");

        LinkedHashSetExamples demo = new LinkedHashSetExamples();

        demo.basicOperations();
        demo.capacityManagement();
        demo.performanceCharacteristic();
        demo.searchingAndOrdering();
        demo.advancedOperations();
        demo.comparisonsToOtherSets();
        demo.commonPitfalls();
        demo.bestPractices();
        demo.realWorldUseCases();

    }

    /**
     * Demonstrations:
     *  - Maintains insertion order
     *  - Supports typical Set operations like add(), remove(), contains(), size(), isEmpty()
     *  - Can be iterated using enhanced for-loop, Iterator, forEach in a predictable order (unlike HashSet)
     * */
    private void basicOperations() {
        System.out.println("\n1. BASIC OPERATIONS");
        System.out.println("=====================");

        LinkedHashSet<String> fruits = new LinkedHashSet<>();

        // Add elements
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Apple"); // duplicate ignored

        System.out.println("\nFruits after additions (in insertion order): " + fruits);

        // Remove an element
        fruits.remove("Orange");
        System.out.println("\nAfter removing 'Orange': " + fruits);

        // Check for an element
        System.out.println("\nContains 'Mango'? " + fruits.contains("Mango"));
        System.out.println("Contains 'Orange'? " + fruits.contains("Orange"));

        // Size and emptiness
        System.out.println("\nSize: " + fruits.size());
        System.out.println("Is empty? " + fruits.isEmpty());

        // --- Iteration techniques ---
        System.out.println("\nIterating with enhanced for-loop:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        System.out.println("\nIterating with Iterator:");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\nIterating with forEach:");
        fruits.forEach(System.out::println);
        System.out.println();

    }

    /**
     * Demonstrates:
     * - Internal structure: backed by LinkedHashMap
     * - Initial capacity & load factor
     * - Rehashing when threshold exceeded
     */
    private void capacityManagement(){
        System.out.println("2. CAPACITY MANAGEMENT");
        System.out.println("======================");

        // Default constructor (capacity 16, load factor 0.75)
        LinkedHashSet<Integer> defaultSet = new LinkedHashSet<>();
        System.out.println("\nDefault LinkedHashSet created.");

        // Add elements to exceed load factor threshold and trigger rehashing
        //After adding all 20 elements, the LinkedHashSet's capacity is likely 32 due to the rehashing process triggered
        // when the load factor threshold was exceeded.
        for (int i = 1; i <= 20; i++) {
            defaultSet.add(i);
        }
        System.out.println("Elements added to exceed initial threshold (16 * 0.75 = 12): " + defaultSet);

        // Custom initial capacity and load factor (optional)
        LinkedHashSet<String> customSet = new LinkedHashSet<>(32, 0.5f);
        System.out.println("\nCustom LinkedHashSet created with capacity=32, loadFactor=0.5");

        customSet.add("A");
        customSet.add("B");
        customSet.add("C");

        System.out.println("Custom set: " + customSet);

        // Note: We cannot directly inspect capacity or rehashing — handled internally.
        System.out.println("\nNotes: ");
        System.out.println(" - Capacity and rehashing are managed internally via LinkedHashMap.");
        System.out.println(" - Capacity is the number of buckets in the hash table.");
        System.out.println(" - Load Factor determines when to resize.");
        System.out.println(" - Rehashing is triggered when size > capacity * loadFactor.");
        System.out.println();

    }

    /**
     * Demonstrates:
     * - Time complexity of common operations
     * - Performance trade-offs compared to HashSet
     * - Cost of maintaining insertion order
     */
    private void performanceCharacteristic(){
        System.out.println("3. PERFORMANCE CHARACTERISTICS");
        System.out.println("================================");

        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

        int testSize = 1_000_000;

        //measure insertion time
        long startTime = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            linkedHashSet.add(i);
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("\nTime to insert " + testSize + " elements into LinkedHashSet: " + duration/1_000_000 + " ms");

        //measure look up
        startTime = System.nanoTime();
        boolean found = linkedHashSet.contains(testSize / 2);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("\nTime to find middle element (found =  " + found + "): " + duration + " nanoseconds" );

        //measure remove time
        startTime = System.nanoTime();
        linkedHashSet.remove(testSize / 2);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("\nTime to remove middle element : " + duration + " nanoseconds" );

        // Comparisons LinkedHashSet vs HashSet:
        System.out.println("\nComparisons LinkedHashSet vs HashSet:");
        System.out.println("- Both offer O(1) average time for add, remove, and contains.");
        System.out.println("- LinkedHashSet is slightly slower due to extra overhead (linked list for order).");
        System.out.println("- Prefer LinkedHashSet when you need predictable iteration order.");
        System.out.println();

    }

    /**
     * Demonstrates:
     * - Insertion order is preserved
     * - Efficient searching using contains()
     * - Sorting via conversion to List or TreeSet
     */
    private void searchingAndOrdering(){
        System.out.println("4. SEARCHING AND ORDERING");
        System.out.println("==============================");

        LinkedHashSet<String> countries = new LinkedHashSet<>();
        countries.add("Germany");
        countries.add("Canada");
        countries.add("Brazil");
        countries.add("Australia");
        countries.add("Denmark");

        System.out.println("\nOriginal countries LinkedHashSet (insertion order): ");
        System.out.println(countries);

        // Searching
        System.out.println("\nContains 'Canada'? " + countries.contains("Canada"));
        System.out.println("Contains 'Japan'? " + countries.contains("Japan"));

        // Sorting using TreeSet (alphabetical order)
        TreeSet<String> sortedCountries = new TreeSet<>(countries);
        System.out.println("\nSorted countries (via TreeSet in alphabetical order): ");
        System.out.println(sortedCountries);

        // Sorting using List + custom comparator
        System.out.println("\nSorted using List (reverse alphabetical):");
        List<String> countriesList = new ArrayList<>(countries);
        countriesList.sort(Comparator.reverseOrder());
        System.out.println(countriesList);

        // Iterating in original insertion order (key feature)
        System.out.println("\nIterating in insertion order (LinkedHashSet):");
        for (String country : countries) {
            System.out.print(country + " ");
        }
        System.out.println();

        System.out.println();

    }

    /**
     * Demonstrates:
     * - Bulk operations: addAll, removeAll, retainAll
     * - Cloning a LinkedHashSet
     * - Streams & functional-style operations
     * - Custom objects with equals/hashCode
     */
    private void advancedOperations(){
        System.out.println("5. ADVANCED OPERATIONS");
        System.out.println("======================");

        //bulk operations:
        LinkedHashSet<Integer> linkedHashSet1 = new LinkedHashSet<>(List.of(1, 2, 3, 4, 5));
        LinkedHashSet<Integer> linkedHashSet2 = new LinkedHashSet<>(List.of(4, 5, 6, 7, 8));

        //add all (union operation for two sets)
        LinkedHashSet<Integer> union = new LinkedHashSet<>(linkedHashSet1);
        union.addAll(linkedHashSet2);
        System.out.println("\n Union (addAll): ");
        System.out.println(union);  //[1, 2, 3, 4, 5, 6, 7, 8]

        //retainAll (intersection between two sets)
        LinkedHashSet<Integer> intersection = new LinkedHashSet<>(linkedHashSet1);
        intersection.retainAll(linkedHashSet2);
        System.out.println("\n Intersection (retainAll): ");
        System.out.println(intersection);  //[4, 5]

        //removeAll (show difference between two sets based on first by constructor)
        LinkedHashSet<Integer> difference = new LinkedHashSet<>(linkedHashSet1);
        difference.removeAll(linkedHashSet2);
        System.out.println("\n Difference (removeAll): ");
        System.out.println(difference);  //[1, 2, 3]

        // --- Cloning ---
        @SuppressWarnings("unchecked")
        LinkedHashSet<String> cloned = (LinkedHashSet<String>) linkedHashSet1.clone();
        System.out.println("\nCloned set1: " + cloned);

        // --- Streams and functional-style filtering ---
        Set<Integer> filtered = union.stream()
                .filter(elem -> elem % 2 == 0)
                .collect(Collectors.toSet());
        System.out.println("\nFiltered set (contain only even elements): " + filtered);

        //insert and iterate custom objects with equals/hashCode
        System.out.println("\nInsert and Iterate custom objects (Cars) with equals/hashCode");
        LinkedHashSet<Car> carsSet = new LinkedHashSet<>();
        //add
        carsSet.add(new Car(100, "BMW", 45_000.00));
        carsSet.add(new Car(101, "Audi", 47_000.00));
        carsSet.add(new Car(102, "Mercedes", 55_000.00));
        carsSet.add(new Car(101, "Audi", 47_000.00)); //duplicate will be not added

        //iterate by enhanced for-each loop
        for (Car car : carsSet) {
            System.out.println(car);
        }
        System.out.println();

    }

    // Helper record with proper equals()/hashCode() and toString()
    record Car(long id, String name, double price){}

    /**
     * Demonstrates:
     * - Internal structure and behavior of HashSet vs LinkedHashSet vs TreeSet
     * - Ordering guarantees
     * - Performance and use case comparisons
     */
    private void comparisonsToOtherSets(){
        System.out.println("6. COMPARISONS LINKEDHASHSET TO OTHER SETS");
        System.out.println("==========================================");

        Set<Phone> hashSet = new HashSet<>();
        Set<Phone> linkedHashSet = new LinkedHashSet<>();
        Set<Phone> treeSet = new TreeSet<>(Comparator.comparingDouble(o -> o.price));

        for (int i = 1; i <= 5; i++) {
            hashSet.add(new Phone(i, "PhoneHashSet: " + i + 10, i * 100));
            linkedHashSet.add(new Phone(i, "PhoneLinkedHashSet: " + i + 10, i * 100));
            treeSet.add(new Phone(i, "PhoneTreeSet: " + i + 10, i * 100));
        }

        System.out.println("\nOriginal hashSet (no order): ");
        System.out.println(hashSet);
        System.out.println("\nOriginal linkedHashSet (insertion order): ");
        System.out.println(linkedHashSet);
        System.out.println("\nOriginal treeSet (sorted by price): "); //must have Comparator for custom objects
        System.out.println(treeSet);

        System.out.println("\nPerformance & Use Case Summary:");
        System.out.println("- HashSet: Fastest (no order), O(1) operations, best for pure membership checks.");
        System.out.println("- LinkedHashSet: Maintains insertion order, slight performance overhead.");
        System.out.println("- TreeSet: Keeps sorted order, slower O(log n) performance, no nulls allowed.");
        System.out.println();

    }

    //helper record
    record Phone(int id, String name, double price){}

    /**
     * Demonstrates:
     * - Pitfalls like mutating elements, missing equals/hashCode, and concurrent modification
     */
    private void commonPitfalls(){
        System.out.println("\n7. COMMON PITFALLS");
        System.out.println("===================");

        // 1. Modifying elements after insertion
        Set<Person> people = new LinkedHashSet<>();
        Person alice = new Person("Alice", 30);
        people.add(alice);
        System.out.println("Original: " + people);

        // Changing internal state after insertion can break Set behavior
        alice.age = 31; // This breaks the hashCode contract
        System.out.println("After modifying Alice's age:");
        System.out.println("Contains modified Alice? " + people.contains(alice)); // false or unreliable!
        people.remove(alice); // Might silently fail
        System.out.println("After trying to remove modified Alice: " + people);

        // 2. Missing equals() / hashCode()
        Set<BadPerson> badPeople = new LinkedHashSet<>();
        badPeople.add(new BadPerson("Bob", 25));
        badPeople.add(new BadPerson("Bob", 25)); // duplicate but will be added
        System.out.println("\nWithout proper equals/hashCode:");
        System.out.println("badPeople contains duplicates: " + badPeople);

        // 3. Re-inserting an existing element doesn’t reorder it
        Set<String> ordered = new LinkedHashSet<>(List.of("A", "B", "C"));
        ordered.add("B"); // Does nothing!
        System.out.println("\nRe-inserting an existing element doesn’t reorder:");
        System.out.println("Ordered set: " + ordered);

        // 4. ConcurrentModificationException during iteration
        Set<String> items = new LinkedHashSet<>(List.of("X", "Y", "Z"));
        try {
            for (String item : items) {
                if (item.equals("Y")) {
                    items.remove(item); // This will throw ConcurrentModificationException
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("\nCaught ConcurrentModificationException while modifying during iteration");
        }

    }

    // Custom class with proper equals/hashCode
    static class Person {

        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person p)) return false;
            return age == p.age && Objects.equals(name, p.name);
        }

        @Override public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override public String toString() {
            return name + " (" + age + ")";
        }
    }

    // Bad class with default equals/hashCode
    static class BadPerson {

        String name;
        int age;

        public BadPerson(String name, int age) {
            this.name = name; this.age = age;
        }

        @Override public String toString() {
            return name + " (" + age + ")";
        }
    }

    /**
     * Demonstrates:
     * - Choosing the right Set implementation
     * - Defensive copying when exposing sets
     * - Avoiding mutation of objects in the set
     * - Using factory methods for immutability
     * - Composition over inheritance
     */
    private void bestPractices(){
        System.out.println("\n8. BEST PRACTICES");
        System.out.println("===================");

        // 1. Choose the right Set type
        System.out.println("\nChoose the right Set type:");
        Set<String> unordered = new HashSet<>();
        Set<String> ordered = new LinkedHashSet<>();
        Set<String> sorted = new TreeSet<>();

        System.out.println(
                """
                1. Choose HashSet for speed.\s
                2. LinkedHashSet for order.\s
                3. TreeSet for sorted data.\s
               """
        );

        // 2. Defensive copying
        System.out.println("Defensive copying:");
        Set<String> internalSet = new LinkedHashSet<>(List.of("a", "b", "c"));
        Set<String> exposedCopy = new LinkedHashSet<>(internalSet); // Defensive copy
        System.out.println("Defensive copy of internal set: " + exposedCopy);

        // 3. Avoid mutating objects used in sets
        System.out.println("\nAvoid mutating objects used in sets:");
        Set<Person> people = new LinkedHashSet<>();
        Person p = new Person("Eva", 35);
        people.add(p);

        System.out.println(" - Before mutation: " + people);
        p.age = 40; // Mutates the object → breaks hashCode/equals contract
        System.out.println(" - After mutation: " + people);

        // 4. Use factory methods for read-only views (Java 9+)
        System.out.println("\nUse factory methods for read-only views (Java 9+):");
        Set<String> immutableSet = Set.of("X", "Y", "Z");
        System.out.println("Immutable set (Java 9+): " + immutableSet);

        // 5. Prefer composition over inheritance
        System.out.println("\nPrefer composition over inheritance");
        CustomSetWrapper customSet = new CustomSetWrapper();
        customSet.add("data-1");
        customSet.add("data-2");
        customSet.add("data-3");
        System.out.println("CustomSetWrapper contents: " + customSet.getElements());

    }

    // Example of composition over inheritance
    static class CustomSetWrapper {

        private final LinkedHashSet<String> elements = new LinkedHashSet<>();

        public void add(String value) {
            elements.add(value);
        }

        public Set<String> getElements() {
            return new LinkedHashSet<>(elements); // return copy
        }
    }

    /**
     * Demonstrates:
     * - Real-life scenarios where LinkedHashSet is particularly useful
     */
    private void realWorldUseCases() {
        System.out.println("\n9. REAL-WORLD USE CASES");
        System.out.println("=========================");

        // 1. Deduplicate and preserve order (e.g. user input, CSV import)
        List<String> rawInput = List.of("Java", "Python", "Java", "C++", "Python", "Go");
        Set<String> uniqueOrderedLanguages = new LinkedHashSet<>(rawInput);
        System.out.println("Unique ordered languages from input: " + uniqueOrderedLanguages);

        // 2. Recently viewed items (most recent at the end)
        LinkedHashSet<String> recentItems = new LinkedHashSet<>();
        addRecentItem(recentItems, "Home");
        addRecentItem(recentItems, "Profile");
        addRecentItem(recentItems, "Settings");
        addRecentItem(recentItems, "Profile"); // re-inserting doesn't reorder!
        System.out.println("Recently viewed pages: " + recentItems);

        // 3. Navigation history / breadcrumbs
        Set<String> navigationPath = new LinkedHashSet<>(List.of("Dashboard", "Reports", "2024", "Sales"));
        System.out.println("Navigation path: " + navigationPath);

        // 4. Caching last N unique queries (could combine with size limit)
        LinkedHashSet<String> searchQueries = new LinkedHashSet<>();
        for (String query : List.of("apple", "banana", "apple", "date", "banana", "elderberry")) {
            searchQueries.add(query);
        }
        System.out.println("Cached unique search queries: " + searchQueries);

        // 5. Menu options with uniqueness + ordering guaranteed
        LinkedHashSet<String> menuOptions = new LinkedHashSet<>(List.of("Start", "Load", "Settings", "Exit", "Start"));
        System.out.println("Menu options: " + menuOptions);
    }

    private void addRecentItem(LinkedHashSet<String> recent, String item) {
        recent.remove(item);  // Ensure no duplicate
        recent.add(item);     // Re-add to move to end
    }


}
