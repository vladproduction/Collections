package com.vladproduction.collectionsframework.ch03_sets;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Comprehensive examples of HashSet usage, features, and best practices
 * Covers: Basic operations, capacity management, performance characteristics, and advanced features
 */
public class HashSetExamples {
    public static void main(String[] args) {
        System.out.println("HASHSET EXAMPLES\n");

        HashSetExamples demo = new HashSetExamples();

        demo.basicOperations();
        demo.capacityManagement();
        demo.performanceCharacteristic();
        demo.searchingAndOrdering();
        demo.advancedOperations();
        demo.hashSetVsLinkedHashSetVsTreeSet();
        demo.commonPitfalls();
        demo.bestPractices();
        demo.realWorldUseCases();

    }

    /**
     * Demonstrate basic HashSet operations
     * */
    private void basicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("===================");

        Set<String> fruits = new HashSet<>();

        //adding elements to the set
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Pear");
        fruits.add("Apple"); //duplicate will be ignored

        System.out.println("Fruits Set: " + fruits); //unordered, unique values only

        //check if a set contain an element
        System.out.println("Contains 'Apple'? " + fruits.contains("Apple"));

        //removing element
        fruits.remove("Banana");
        System.out.println("Fruits Set after removing 'Banana': " + fruits);

        //check size
        System.out.println("Fruits Set size: " + fruits.size());

        //check if set is empty
        System.out.println("Fruits Set is empty?: " + fruits.isEmpty());

        //iterating:
        System.out.println("Iterating: enhanced for-loop");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        System.out.println();

        System.out.println("Iterating: using iterator");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();

        System.out.println("Iterating: for each method reference");
        fruits.forEach(System.out::println);
        System.out.println();

        System.out.println("Iterating: for each lambda");
        fruits.forEach(fruit -> System.out.println(fruit));
        System.out.println();

    }

    /**
     * Demonstrates how initial capacity and load factor affect HashSet.
     * NOTE: Capacity and load factor can only be set via constructor.
     * HashSet uses a backing HashMap which resizes based on these.
     */
    private void capacityManagement(){
        System.out.println("2. CAPACITY MANAGEMENT");
        System.out.println("======================");

        //default constructor: initial capacity = 16, load factor = 0.75
        Set<String> defaultCapacitySet = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
             defaultCapacitySet.add("" + i);
        }
        System.out.println("Default capacity set: " + defaultCapacitySet);
        System.out.println();

        //custom constructor: initial capacity = 32, load factor = 0.5
        Set<String> customCapacitySet = new HashSet<>(32, 0.5f);
        for (int i = 1; i <= 20; i++) {
            customCapacitySet.add("" + i);
        }
        System.out.println("Custom capacity set (capacity = 32, load factor = 0.5) with 20 elements: " + customCapacitySet);

        System.out.println("\n--- Explanation how does it work:---");
        System.out.println("Default capacity is 16 with load factor 0.75.");
        System.out.println("When size > capacity * loadFactor, HashSet resizes (rehashing).");
        System.out.println("Rehashing is costly, so tuning can help performance.");
        System.out.println("Higher initial capacity avoids early rehashing.");
        System.out.println("Lower load factor reduces collisions but increases memory usage.");
        System.out.println();

    }

    /**
     * Demonstrates time complexity (avg vs worst-case),
     * how hash collisions affect performance
     * */
    private void performanceCharacteristic(){
        System.out.println("3. PERFORMANCE CHARACTERISTIC");
        System.out.println("=============================");

        Set<Integer> numbers = new HashSet<>();

        //add performance
        long startTime = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
             numbers.add(i);
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 1_000_000 elements to the set: "
        + (endTime - startTime) / 1_000_000 + " ms");

        // searching time (contains)
        startTime = System.nanoTime();
        boolean found = numbers.contains(999_999);
        endTime = System.nanoTime();
        System.out.println("Contains 999_999? " + found);
        System.out.println("Search Time: " + (endTime - startTime) + " ns");

        //removing performance
        startTime = System.nanoTime();
        numbers.remove(500_000);
        endTime = System.nanoTime();
        System.out.println("Remove Time: " + (endTime - startTime) + " ns");

        System.out.println("\n--- Notes ---");
        System.out.println("HashSet offers average-case O(1) time for add, remove, and contains.");
        System.out.println("In worst-case (e.g. poor hashCode or too many collisions), time degrades to O(n).");
        System.out.println("Well-distributed hashCodes are crucial for performance.");
        System.out.println();

    }

    /**
     * Demonstrates:
     *  - Why HashSet is unordered
     *  - Cannot sort directly — how to transfer to List or TreeSet
     *  - Efficient contains checks
     * */
    private void searchingAndOrdering(){
        System.out.println("4. SEARCHING AND ORDERING");
        System.out.println("=========================");

        Set<String> animals = new HashSet<>();
        animals.add("Tiger");
        animals.add("Zebra");
        animals.add("Elephant");
        animals.add("Lion");
        animals.add("Giraffe");

        System.out.println("Original Set (unordered): " + animals);

        //searching:
        System.out.println("\nSearching:");
        System.out.println("Contain 'Lion'? ' " + animals.contains("Lion"));
        System.out.println("Contain 'Bear'? ' " + animals.contains("Bear"));

        //sort directly a set is incorrect, need to convert to list
        System.out.println("\nOrdering:");
        System.out.println("HashSet does not preserve order or support direct sorting");

        List<String> animalsSorted = new ArrayList<>(animals);
        Collections.sort(animalsSorted);
        System.out.println("Sorted List from HashSet: " + animalsSorted);

        //alternative: convert to TreeSet to have natural sorting
        System.out.println("\nOrdering as TreeSet (natural sorting):");
        Set<String> animalsSortedAsTreeSet = new TreeSet<>(animals);
        System.out.println(animalsSortedAsTreeSet);

        System.out.println("\nExplanation of sorting:");
        System.out.println(" - HashSet provides O(1) average-case search with contains().");
        System.out.println(" - It does not maintain any order of elements.");
        System.out.println(" - To sort or order elements, convert to a List or use TreeSet.");
        System.out.println();

    }

    /**
     * Demonstrates:
     *  - Bulk operations: addAll, removeAll, retainAll
     *  - Cloning a HashSet
     *  - Streams & functional-style operations
     *  - Custom objects with equals/hashCode
     * */
    private void advancedOperations(){
        System.out.println("5. ADVANCED OPERATIONS");
        System.out.println("======================");

        Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        Set<String> set2 = new HashSet<>(Arrays.asList("C", "D", "E", "F"));

        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);

        //addAll (union)
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union (after addAll()): " + union); //no duplicates as expected

        //retainAll (intersection)
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection (after retainAll()): " + intersection); //C, D is intersected in both sets

        //removeAll (differences between two sets)
        //1) Create a new set difference initialized with all elements of set1
        //2) Remove all elements that are also in set2
        //3) If set2 contains [C, D, E, F], then after this operation, difference will only have the elements from set1 that are not in set2, i.e., [A, B]
        //4) removeAll() mutates the difference set by removing elements that are present in set2
        //5) the final set contains elements unique to set1 relative to set2
        Set<String> difference = new HashSet<>(set1); //[A, B, C, D]
        difference.removeAll(set2); //[A, B, C, D] + [C, D, E, F]
        System.out.println("Difference (after removeAll()): " + difference); //[A, B]

        //Cloning
        Set<String> clone = new HashSet<>(set1);
        System.out.println("Cloned set1 (shallow copy): " + clone);

        //stream & functional style processing
        Set<String> filtered = union.stream()
                .filter(e -> e.compareTo("C") > 0)
                .collect(Collectors.toSet());
        System.out.println("Filtered (elements > 'C'): " + filtered);

        //custom object in HashSet
        Set<Person> people = new HashSet<>();
        people.add(new Person("Bob", 25));
        people.add(new Person("Tod", 26));
        people.add(new Person("Bob", 25)); //ignored

        System.out.println("\nCustom object in HashSet:");
        people.forEach(System.out::println);

        System.out.println("\nExplanation:");
        System.out.println(" - Bulk operations (addAll, retainAll, removeAll) enable set logic.");
        System.out.println(" - HashSet can be cloned via constructor or clone() (shallow).");
        System.out.println(" - Streams allow functional-style filtering and mapping.");
        System.out.println(" - For custom objects, equals() and hashCode() must be overridden for correct behavior.");
        System.out.println();


    }
    //custom class with overridden equals and hashCode
    record Person(String name, int age) {

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof HashSetExamples.Person)) return false;
            HashSetExamples.Person other = (HashSetExamples.Person) obj;
            return Objects.equals(this.name, other.name) && this.age == other.age;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }

    }

    /**
     * Demonstration:
     *  - Internal structure differences between HashSet, LinkedHashSet, TreeSet
     *  - Order guarantees
     *  - Performance tradeoffs
     *  - When to use each one
     * */
    private void hashSetVsLinkedHashSetVsTreeSet(){
        System.out.println("6. HASHSET VS LINKEDHASHSET VS TREESET");
        System.out.println("======================================");

        //creating sets:
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();

        //adding elements for each set
        List<String> elements = Arrays.asList("Apple", "Banana", "Pineapple", "Watermelon");
        for (String element : elements) {
            hashSet.add(element);
            linkedHashSet.add(element);
            treeSet.add(element);
        }

        System.out.println("Original input (List of elements):" + elements);
        System.out.println("HashSet (unordered): " + hashSet);
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);
        System.out.println("TreeSet (sorted order): " + treeSet);

        System.out.println("\nExplanation:");
        System.out.println(" - HashSet: Fastest (O(1) avg), no order guarantee.");
        System.out.println(" - LinkedHashSet: Slightly slower, maintains insertion order.");
        System.out.println(" - TreeSet: Slowest (O(log n)), maintains natural (or custom) sort order.");
        System.out.println(" - All sets eliminate duplicates automatically.");
        System.out.println();

    }

    /**
     * Demonstrates:
     *  - Mutable objects as elements
     *  - Forgetting to override equals() and hashCode()
     *  - Concurrent modification exceptions
     *  - Assuming order
     * */
    private void commonPitfalls(){
        System.out.println("7. COMMON PITFALLS");
        System.out.println("==================");

        // 1. Mutable elements in HashSet (dangerous!)
        System.out.println("\n1. Mutable elements in HashSet (dangerous!)");
        Set<MutableKey> set = new HashSet<>();
        MutableKey key = new MutableKey("key1");

        set.add(key);
        System.out.println("Set contains key? " + set.contains(key)); // true

        // Modify the key (affects hashCode)
        key.name = "modified";

        // Now the HashSet may not find it (corrupts internal structure)
        System.out.println("After modification, contains key? " + set.contains(key)); // false
        System.out.println("Current set: " + set);

        // 2. Missing equals() and hashCode()
        System.out.println("\n2. Missing equals() and hashCode()");
        Set<BadKey> badSet = new HashSet<>();
        badSet.add(new BadKey("foo"));
        badSet.add(new BadKey("foo")); // Treated as different because no equals/hashCode

        System.out.println("Without equals/hashCode, duplicates may exist:");
        System.out.println("Size of badSet: " + badSet.size()); // 2

        // 3. ConcurrentModificationException
        System.out.println("\n3. ConcurrentModificationException");
        Set<String> names = new HashSet<>(Arrays.asList("A", "B", "C"));
        System.out.println("Trying to modify while iterating (bad):");
        try {
            for (String name : names) {
                if (name.equals("B")) {
                    names.remove(name); // throws ConcurrentModificationException
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught: " + e);
        }

        // Correct way using Iterator
        System.out.println("Safe removal using Iterator:");
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            if (it.next().equals("B")) {
                it.remove(); //
            }
        }
        System.out.println("After safe removal: " + names);

        // 4. Assuming order
        System.out.println("\n4. Assuming order");
        Set<String> unordered = new HashSet<>();
        unordered.add("X");
        unordered.add("Y");
        unordered.add("Z");

        System.out.println("HashSet order is not guaranteed:");
        System.out.println(unordered); // Unpredictable order

        System.out.println("\n--- Explanations: ---");
        System.out.println("Avoid using mutable objects as keys in HashSet.");
        System.out.println("Always override equals() and hashCode() in custom classes.");
        System.out.println("Use Iterator.remove() when modifying while iterating.");
        System.out.println("Never assume HashSet has any predictable order.");
        System.out.println();

    }

    // Class with mutable state used as a HashSet key
    static class MutableKey {
        String name;
        public MutableKey(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof MutableKey)) return false;
            MutableKey other = (MutableKey) obj;
            return Objects.equals(this.name, other.name);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // Class with no equals() or hashCode() overrides
    static class BadKey {
        String value;
        public BadKey(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    /**
     * Demonstrates:
     *  - Use cases
     *  - Defensive copying
     *  - Choosing the right Set implementation
     *  - Prefer composition over inheritance if extending
     * */
    private void bestPractices(){
        System.out.println("8. BEST PRACTICES");
        System.out.println("=================");

        // 1. Use HashSet for fast lookups and uniqueness
        Set<String> emailSet = new HashSet<>();
        Collections.addAll(emailSet, "a@example.com", "b@example.com", "a@example.com"); // duplicate ignored
        System.out.println("Unique emails: " + emailSet);

        // 2. Defensive copying to avoid exposing internal mutable sets
        Set<String> exposed = getDefensiveCopy(emailSet);
        exposed.add("new@example.com"); // does NOT affect original
        System.out.println("Original emails (unchanged): " + emailSet);
        System.out.println("Copied emails (modified): " + exposed);

        // 3. Choose the right Set implementation
        System.out.println("\nChoosing right implementation:");
        Set<String> insertionOrder = new LinkedHashSet<>(List.of("X", "Y", "Z"));
        Set<String> sortedOrder = new TreeSet<>(List.of("X", "Y", "Z"));

        System.out.println("LinkedHashSet (insertion order): " + insertionOrder);
        System.out.println("TreeSet (sorted): " + sortedOrder);

        // 4. Prefer composition over inheritance
        System.out.println("\nAvoid extending HashSet directly — prefer wrapping it:");
        MyEmailStore store = new MyEmailStore();
        store.add("hello@example.com");
        store.add("hello@example.com"); // duplicate
        store.print();

        System.out.println("\n--- Explanations: ---");
        System.out.println(" - Use HashSet for fast membership checks and enforcing uniqueness.");
        System.out.println(" - Never expose internal sets directly — return unmodifiable or defensive copies.");
        System.out.println(" - Choose HashSet, LinkedHashSet, or TreeSet based on ordering and performance needs.");
        System.out.println(" - Prefer composition: wrap HashSet in your class instead of extending it.");

        System.out.println();
    }

    // Defensive copy method
    private Set<String> getDefensiveCopy(Set<String> input) {
        return new HashSet<>(input); // shallow copy
    }

    // Good practice: wrapping HashSet (composition)
    static class MyEmailStore {
        private final Set<String> emails = new HashSet<>();

        public void add(String email) {
            emails.add(email);
        }

        public void print() {
            emails.forEach(System.out::println);
        }
    }

    /**
     * Demonstrates:
     *  - Unique usernames
     *  - Caching unique elements
     *  - Deduplicating inputs in streams/files
     * */
    private void realWorldUseCases(){
        System.out.println("9. REAL WORLD USE CASES");
        System.out.println("=======================");

        // 1. Ensuring unique usernames in a system
        Set<String> usernames = new HashSet<>();
        System.out.println("\n--- Unique Usernames ---");
        addUsername(usernames, "alice");
        addUsername(usernames, "bob");
        addUsername(usernames, "alice"); // duplicate
        System.out.println("Current users: " + usernames);

        // 2. Caching unique search terms
        Set<String> searchCache = new HashSet<>();
        System.out.println("\n--- Caching Unique Search Terms ---");
        String[] searches = {"java", "spring", "java", "hashset"};
        for (String term : searches) {
            if (searchCache.add(term)) {
                System.out.println("New search cached: " + term);
            } else {
                System.out.println("Already searched: " + term);
            }
        }

        // 3. Deduplicating a list of emails (e.g., from a file)
        List<String> emailsFromFile = Arrays.asList(
                "a@example.com", "b@example.com", "a@example.com", "c@example.com"
        );
        Set<String> uniqueEmails = new HashSet<>(emailsFromFile);
        System.out.println("\n--- Deduplicated Emails ---");
        System.out.println("Original: " + emailsFromFile);
        System.out.println("Unique:   " + uniqueEmails);

        System.out.println("\n--- Explanations: ---");
        System.out.println(" - HashSet is ideal for enforcing uniqueness rules.");
        System.out.println(" - Often used in data validation, caching, and de-duplication.");
        System.out.println(" - Quick membership checks make it a strong fit for real-time systems.");

        System.out.println();
    }

    private void addUsername(Set<String> usernames, String username) {
        if (usernames.contains(username)) {
            System.out.println("Username already taken: " + username);
        } else {
            usernames.add(username);
            System.out.println("Username added: " + username);
        }
    }


}






















