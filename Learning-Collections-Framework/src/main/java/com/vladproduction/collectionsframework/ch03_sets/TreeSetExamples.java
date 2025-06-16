package com.vladproduction.collectionsframework.ch03_sets;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TreeSet is a powerful Set implementation that maintains sorted order,
 * and it introduces unique characteristics thanks to its NavigableSet and SortedSet interfaces.
 * */
public class TreeSetExamples {
    public static void main(String[] args) {
        System.out.println("TREE SET EXAMPLES");
        System.out.println("================\n");

        TreeSetExamples demo = new TreeSetExamples();

        demo.basicOperations();
        demo.customOrderingWithComparator();
        demo.performanceCharacteristics();
        demo.navigableMethods();
        demo.handlingCustomObjects();
        demo.advancedOperations();
        demo.treeSetVsOtherSets();
        demo.commonPitfalls();
        demo.bestPractices();
        demo.realWorldUseCases();

    }
    /**Add, remove, contains, size, isEmpty
    Iteration (for-each, Iterator, forEach)
    Automatic sorting behavior*/
    private void basicOperations(){
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("===================");

        TreeSet<Integer> numbers = new TreeSet<>();

        // Adding elements (automatically sorted)
        System.out.println("Adding elements: 5, 2, 8, 1, 9, 3");
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        numbers.add(3);

        System.out.println("TreeSet content (automatically sorted): " + numbers);

        // Basic operations
        System.out.println("Contains 5: " + numbers.contains(5));
        System.out.println("Size: " + numbers.size());
        System.out.println("Is empty: " + numbers.isEmpty());

        // Remove operation
        numbers.remove(8);
        System.out.println("After removing 8: " + numbers);

        // Iteration methods
        System.out.print("For-each iteration: ");
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.print("forEach with lambda: ");
        numbers.forEach(num -> System.out.print(num + " "));
        System.out.println();

        // Iterator
        System.out.print("Iterator (descending): ");
        Iterator<Integer> descendingIter = numbers.descendingIterator();
        while (descendingIter.hasNext()) {
            System.out.print(descendingIter.next() + " ");
        }
        System.out.println("\n");

    }

    /**How to define natural vs custom ordering
    Use of Comparator
    Sorting objects by fields (e.g., name, age, ID)*/
    private void customOrderingWithComparator(){
        System.out.println("2. CUSTOM ORDERING WITH COMPARATOR");
        System.out.println("===================================");

        // Natural ordering (ascending)
        TreeSet<String> naturalOrder = new TreeSet<>();
        naturalOrder.addAll(Arrays.asList("banana", "apple", "cherry", "date"));
        System.out.println("Natural ordering: " + naturalOrder);

        // Custom ordering (descending)
        TreeSet<String> reverseOrder = new TreeSet<>(Collections.reverseOrder());
        reverseOrder.addAll(Arrays.asList("banana", "apple", "cherry", "date"));
        System.out.println("Reverse ordering: " + reverseOrder);

        // Custom ordering by length
        TreeSet<String> lengthOrder = new TreeSet<>(Comparator.comparing(String::length));
        lengthOrder.addAll(Arrays.asList("a", "hello", "hi", "world", "java", "!"));
        System.out.println("Length ordering: " + lengthOrder); //since it is a Set equal length will be not added

        // Complex custom ordering
        TreeSet<String> complexOrder = new TreeSet<>(
                Comparator.comparing(String::length)
                        .thenComparing(String::compareTo)
        );
        complexOrder.addAll(Arrays.asList("ab", "ba", "abc", "xyz", "a", "z"));
        System.out.println("Length then alphabetical: " + complexOrder);
        System.out.println();

    }


    /**
     Time complexity (O(log n) for add, remove, contains)
     Backed by Red-Black Tree (balanced BST)
     Comparison to HashSet and LinkedHashSet
     * */
    private void performanceCharacteristics(){
        System.out.println("3. PERFORMANCE CHARACTERISTICS");
        System.out.println("===============================");

        System.out.println("TreeSet Performance:");
        System.out.println("- Add: O(log n)");
        System.out.println("- Remove: O(log n)");
        System.out.println("- Contains: O(log n)");
        System.out.println("- Backed by Red-Black Tree (balanced BST)");
        System.out.println("- Maintains sorted order automatically");

        // Performance demonstration
        TreeSet<Integer> treeSet = new TreeSet<>();
        long startTime = System.nanoTime();

        // Add 10,000 random numbers
        for (int i = 0; i < 10_000; i++) {
            treeSet.add(ThreadLocalRandom.current().nextInt(50_000));
        }

        long endTime = System.nanoTime();
        System.out.println("Time to add 10,000 elements: " +
                (endTime - startTime) / 1_000_000 + " ms");
        System.out.println("Final size (duplicates removed): " + treeSet.size());
        System.out.println();

    }

    /**TreeSet-specific methods:
    first(), last()
    higher(), lower()
    ceiling(), floor()
    headSet(), tailSet(), subSet()
    Demonstrate range views and safe iteration*/
    private void navigableMethods(){
        System.out.println("4. NAVIGABLE METHODS");
        System.out.println("====================");

        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.addAll(Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90));
        System.out.println("TreeSet: " + numbers);

        // First and last
        System.out.println("First element: " + numbers.first());
        System.out.println("Last element: " + numbers.last());

        // Navigation methods
        System.out.println("Higher than 45: " + numbers.higher(45));
        System.out.println("Lower than 45: " + numbers.lower(45));
        System.out.println("Ceiling of 45: " + numbers.ceiling(45));
        System.out.println("Floor of 45: " + numbers.floor(45));

        // Poll methods (remove and return)
        TreeSet<Integer> copy = new TreeSet<>(numbers);
        System.out.println("Poll first: " + copy.pollFirst());
        System.out.println("Poll last: " + copy.pollLast());
        System.out.println("After polling: " + copy);

        // Range views
        System.out.println("Head set (< 50): " + numbers.headSet(50));
        System.out.println("Tail set (>= 50): " + numbers.tailSet(50));
        System.out.println("Sub set (30, 70): " + numbers.subSet(30, 70));
        System.out.println("Sub set (30, 70): " + numbers.subSet(30, true, 70, true));

        // Descending set
        System.out.println("Descending set: " + numbers.descendingSet());
        System.out.println();

    }


    /**How to store custom objects (e.g., Person)
    Importance of implementing Comparable or using Comparator
    Pitfall: Inconsistent comparison logic*/
    private void handlingCustomObjects(){
        System.out.println("5. HANDLING CUSTOM OBJECTS");
        System.out.println("===========================");

        // Using Comparable
        TreeSet<Person> peopleByAge = new TreeSet<>();
        peopleByAge.add(new Person("Alice", 30));
        peopleByAge.add(new Person("Bob", 25));
        peopleByAge.add(new Person("Charlie", 35));
        peopleByAge.add(new Person("Diana", 28));

        System.out.println("People sorted by age (Comparable):");
        peopleByAge.forEach(System.out::println);

        // Using custom Comparator
        TreeSet<Person> peopleByName = new TreeSet<>(Comparator.comparing(Person::getName));
        peopleByName.addAll(peopleByAge);

        System.out.println("\nPeople sorted by name (Comparator):");
        peopleByName.forEach(System.out::println);

        // Complex sorting
        TreeSet<Person> complexSort = new TreeSet<>(
                Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getName)
        );
        complexSort.add(new Person("Alice", 30));
        complexSort.add(new Person("Bob", 30));
        complexSort.add(new Person("Charlie", 25));

        System.out.println("\nPeople sorted by age then name:");
        //expected output:
        /*Charlie (age: 25)
        Alice (age: 30)
        Bob (age: 30)*/
        complexSort.forEach(System.out::println);
        System.out.println();

    }

    //helper class
    static class Person implements Comparable<Person> {

        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return name + " (age: " + age + ")";
        }
    }


    /**Cloning a TreeSe
    addAll(), retainAll(), removeAll()
    Conversion to other collections (List, Array)*/
    private void advancedOperations(){
        System.out.println("6. ADVANCED OPERATIONS");
        System.out.println("=======================");

        TreeSet<String> set1 = new TreeSet<>(Arrays.asList("apple", "banana", "cherry", "date"));
        TreeSet<String> set2 = new TreeSet<>(Arrays.asList("banana", "date", "elderberry", "fig"));

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        // Clone
        TreeSet<String> cloned = (TreeSet<String>) set1.clone();
        System.out.println("Cloned set: " + cloned);

        // Set operations (union, intersection, difference)
        TreeSet<String> union = new TreeSet<>(set1);
        union.addAll(set2);
        System.out.println("Union (addAll): " + union);

        TreeSet<String> intersection = new TreeSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection (retainAll): " + intersection);

        TreeSet<String> difference = new TreeSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (removeAll): " + difference);

        // Conversion to other collections
        List<String> list = new ArrayList<>(set1);
        System.out.println("Converted to List: " + list);

        String[] array = set1.toArray(new String[0]);
        System.out.println("Converted to Array: " + Arrays.toString(array));
        System.out.println();

    }


    /**Compare with HashSet and LinkedHashSet:
    Ordering
    Performance
    Null-handling
    Use cases*/
    private void treeSetVsOtherSets(){
        System.out.println("7. TREESET VS OTHER SETS");
        System.out.println("=========================");

        Set<Integer> hashSet = new HashSet<>(Arrays.asList(5, 2, 8, 1, 9));
        Set<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.asList(5, 2, 8, 1, 9));
        Set<Integer> treeSet = new TreeSet<>(Arrays.asList(5, 2, 8, 1, 9));

        System.out.println("HashSet (no order): " + hashSet);
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);
        System.out.println("TreeSet (sorted order): " + treeSet);

        System.out.println("\nComparison:");
        System.out.println("HashSet: O(1) operations, no ordering, allows null");
        System.out.println("LinkedHashSet: O(1) operations, insertion order, allows null");
        System.out.println("TreeSet: O(log n) operations, sorted order, no null");

        // Null handling demonstration
        try {
            hashSet.add(null);
            System.out.println("HashSet with null: " + hashSet);
        } catch (Exception e) {
            System.out.println("HashSet null error: " + e.getMessage());
        }

        try {
            treeSet.add(null);
        } catch (NullPointerException e) {
            System.out.println("TreeSet doesn't allow null values");
        }
        System.out.println();

    }


    /**Using null elements (TreeSet does not allow null)
    ClassCastException due to inconsistent comparisons
    Mutation of objects that affect ordering
    Unintended sorting if compareTo() is wrong*/
    private void commonPitfalls(){
        System.out.println("8. COMMON PITFALLS");
        System.out.println("===================");

        System.out.println("Pitfall 1: Null elements");
        TreeSet<String> set = new TreeSet<>();
        try {
            set.add(null);
        } catch (NullPointerException e) {
            System.out.println("Cannot add null to TreeSet");
        }

        System.out.println("\nPitfall 2: Inconsistent comparison");
        try {
            @SuppressWarnings("rawtypes")
            TreeSet mixedSet = new TreeSet();
            mixedSet.add("string");
            mixedSet.add(42); // This will cause ClassCastException
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage());
        }

        System.out.println("\nPitfall 3: Mutating objects affects ordering");
        TreeSet<MutablePerson> mutableSet = new TreeSet<>();
        MutablePerson person = new MutablePerson("Alice", 25);
        mutableSet.add(person);
        mutableSet.add(new MutablePerson("Bob", 30));

        System.out.println("Before mutation: " + mutableSet);
        person.setAge(35); // Mutating after insertion
        System.out.println("After mutation: " + mutableSet);
        System.out.println("Set ordering is now broken! (comparing by age does not work)");

        System.out.println("\nPitfall 4: Incorrect compareTo implementation");
        System.out.println("Always ensure compareTo is consistent with equals");
        System.out.println();

    }

    //helper class
    static class MutablePerson implements Comparable<MutablePerson> {

        private String name;
        private int age;

        public MutablePerson(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }

        @Override
        public int compareTo(MutablePerson other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public String toString() {
            return name + " (age: " + age + ")";
        }
    }


    /**Use correct comparison strategy
    Be aware of ordering rules
    Defensive copying if exposing TreeSets
    Avoid storing mutable keys*/
    private void bestPractices(){
        System.out.println("9. BEST PRACTICES");
        System.out.println("==================");

        System.out.println("✓ 1. Use immutable objects when possible");
        TreeSet<String> strings = new TreeSet<>(Arrays.asList("apple", "banana", "cherry"));

        System.out.println("✓ 2. Consistent comparison logic");
        // Ensure compareTo is consistent with equals

        System.out.println("✓ 3. Defensive copying when exposing TreeSets");
        TreeSet<String> defensiveCopy = new TreeSet<>(strings);

        System.out.println("✓ 4. Choose appropriate Comparator");
        TreeSet<String> caseInsensitive = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitive.addAll(Arrays.asList("Apple", "banana", "Cherry"));
        System.out.println("Case insensitive: " + caseInsensitive);

        System.out.println("✓ 5. Consider performance implications");
        System.out.println("   - Use TreeSet when you need sorted order");
        System.out.println("   - Use HashSet for better performance without ordering");

        System.out.println("✓ 6. Handle edge cases");
        System.out.println("   - Check for null before adding");
        System.out.println("   - Use isEmpty() before calling first()/last()");
        System.out.println();

    }


    /**Sorted leaderboards
    Auto-sorted dropdown menus
    Range-based lookups
    Scheduling systems with priority/time ordering
    Deduplication with sorting*/
    private void realWorldUseCases(){
        System.out.println("10. REAL WORLD USE CASES");
        System.out.println("=========================");

        // Use case 1: Leaderboard
        System.out.println("Use Case 1: Gaming Leaderboard");
        TreeSet<Player> leaderboard = new TreeSet<>(
                Comparator.comparing(Player::getScore).reversed()
                        .thenComparing(Player::getName)
        );

        leaderboard.add(new Player("Alice", 1500));
        leaderboard.add(new Player("Bob", 1200));
        leaderboard.add(new Player("Charlie", 1500));
        leaderboard.add(new Player("Diana", 1800));

        System.out.println("Top players:");
        leaderboard.forEach(System.out::println);

        // Use case 2: Scheduled tasks
        System.out.println("\nUse Case 2: Task Scheduler");
        TreeSet<Task> scheduler = new TreeSet<>(Comparator.comparing(Task::getScheduledTime));

        scheduler.add(new Task("Backup", System.currentTimeMillis() + 3600000)); // +1 hour
        scheduler.add(new Task("Cleanup", System.currentTimeMillis() + 1800000)); // +30 min
        scheduler.add(new Task("Report", System.currentTimeMillis() + 7200000));  // +2 hours

        System.out.println("Next task: " + scheduler.first());

        // Use case 3: Range queries
        System.out.println("\nUse Case 3: Price Range Filtering");
        TreeSet<Product> products = new TreeSet<>(Comparator.comparing(Product::getPrice));
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 29.99));
        products.add(new Product("Keyboard", 79.99));
        products.add(new Product("Monitor", 299.99));

        // Find products in price range $50-$500
        NavigableSet<Product> priceRange = products.subSet(
                new Product("", 50.0), true,
                new Product("", 500.0), true
        );

        System.out.println("Products in $50-$500 range:");
        priceRange.forEach(System.out::println);

        System.out.println("\nTreeSet is ideal for:");
        System.out.println("- Maintaining sorted collections");
        System.out.println("- Range-based queries");
        System.out.println("- Finding min/max efficiently");
        System.out.println("- Ordered iteration");
        System.out.println("- Deduplication with sorting");

    }

    //helper classes
    static class Player {
        private String name;
        private int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() { return name; }
        public int getScore() { return score; }

        @Override
        public String toString() {
            return name + ": " + score + " points";
        }
    }

    static class Task {
        private String name;
        private long scheduledTime;

        public Task(String name, long scheduledTime) {
            this.name = name;
            this.scheduledTime = scheduledTime;
        }

        public String getName() { return name; }
        public long getScheduledTime() { return scheduledTime; }

        @Override
        public String toString() {
            return name + " at " + new Date(scheduledTime);
        }
    }

    static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }

        @Override
        public String toString() {
            return name + ": $" + price;
        }
    }

}
