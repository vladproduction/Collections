package com.vladproduction.collectionsframework.ch04_maps;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * HashMap is the most commonly used Map implementation in Java.
 * It provides O(1) average time complexity for basic operations
 * and uses hash table for storage with separate chaining for collision resolution.
 */
public class HashMapExamples {
    public static void main(String[] args) {

        System.out.println("HASHMAP EXAMPLES");
        System.out.println("================\n");

        HashMapExamples demo = new HashMapExamples();

        demo.basicOperations();
        demo.iterationMethods();
        demo.handlingCollisions();
        demo.performanceCharacteristics();
        demo.nullHandling();
        demo.customHashCodeAndEquals();
        demo.capacityAndLoadFactor();
        demo.bulkOperations();
        demo.java8Enhancements();
        demo.commonPatterns();
        demo.bestPractices();
        demo.realWorldUseCases();

    }

    private void basicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("===================");

        HashMap<String, Integer> ages = new HashMap<>();

        // Put operations
        ages.put("Alice", 30);
        ages.put("Bob", 25);
        ages.put("Charlie", 35);
        ages.put("Diana", 28);

        System.out.println("Initial map: " + ages);
        System.out.println("Size: " + ages.size());

        // Get operations
        System.out.println("Alice's age: " + ages.get("Alice"));
        System.out.println("Eve's age: " + ages.get("Eve")); // null
        System.out.println("Eve's age (with default): " + ages.getOrDefault("Eve", 0));

        // Check operations
        System.out.println("Contains key 'Bob': " + ages.containsKey("Bob"));
        System.out.println("Contains value 30: " + ages.containsValue(30));

        // Update operation
        ages.put("Alice", 31); // Overwrites existing value
        System.out.println("After updating Alice: " + ages);

        // Remove operations
        Integer removedAge = ages.remove("Charlie");
        System.out.println("Removed Charlie's age: " + removedAge);
        System.out.println("After removal: " + ages);

        // Clear
        ages.clear();
        System.out.println("After clear - isEmpty: " + ages.isEmpty());
        System.out.println();
    }

    private void iterationMethods() {
        System.out.println("2. ITERATION METHODS");
        System.out.println("====================");

        HashMap<String, String> capitals = new HashMap<>();
        capitals.put("USA", "Washington D.C.");
        capitals.put("France", "Paris");
        capitals.put("Japan", "Tokyo");
        capitals.put("Germany", "Berlin");

        System.out.println("Original map: " + capitals);

        // Iterate through keys
        System.out.print("Keys: ");
        for (String country : capitals.keySet()) {
            System.out.print(country + " ");
        }
        System.out.println();

        // Iterate through values
        System.out.print("Values: ");
        for (String capital : capitals.values()) {
            System.out.print(capital + " ");
        }
        System.out.println();

        // Iterate through entries
        System.out.println("Entries (traditional):");
        for (Map.Entry<String, String> entry : capitals.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // Java 8 forEach
        System.out.println("Entries (Java 8 forEach):");
        capitals.forEach((country, capital) ->
                System.out.println("  " + country + " -> " + capital)
        );

        // Iterator with modification
        System.out.println("Removing entries with key length > 5:");
        Iterator<Map.Entry<String, String>> iterator = capitals.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (entry.getKey().length() > 5) {
                System.out.println("  Removing: " + entry.getKey());
                iterator.remove();
            }
        }
        System.out.println("After removal: " + capitals);
        System.out.println();
    }

    private void handlingCollisions() {
        System.out.println("3. HANDLING COLLISIONS");
        System.out.println("=======================");

        // Create objects with same hash code to demonstrate collision handling
        HashMap<BadHashKey, String> collisionMap = new HashMap<>();

        BadHashKey key1 = new BadHashKey("A");
        BadHashKey key2 = new BadHashKey("B");
        BadHashKey key3 = new BadHashKey("C");

        collisionMap.put(key1, "Value A");
        collisionMap.put(key2, "Value B");
        collisionMap.put(key3, "Value C");

        System.out.println("Keys with same hash code:");
        System.out.println("Key1 hash: " + key1.hashCode());
        System.out.println("Key2 hash: " + key2.hashCode());
        System.out.println("Key3 hash: " + key3.hashCode());

        System.out.println("Map with collisions: " + collisionMap);
        System.out.println("HashMap handles collisions using separate chaining");
        System.out.println("Java 8+: Uses balanced trees for large collision chains");
        System.out.println();
    }

    private void performanceCharacteristics() {
        System.out.println("4. PERFORMANCE CHARACTERISTICS");
        System.out.println("===============================");

        System.out.println("HashMap Performance:");
        System.out.println("- Average case: O(1) for get, put, remove");
        System.out.println("- Worst case: O(n) if all keys hash to same bucket");
        System.out.println("- Java 8+: Worst case O(log n) with tree structure");

        // Performance test
        HashMap<Integer, String> perfMap = new HashMap<>();
        long startTime = System.nanoTime();

        // Add 100,000 elements
        for (int i = 0; i < 100_000; i++) {
            perfMap.put(i, "Value" + i);
        }

        long endTime = System.nanoTime();
        System.out.println("Time to add 100,000 elements: " +
                (endTime - startTime) / 1_000_000 + " ms");

        // Lookup performance
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            perfMap.get(ThreadLocalRandom.current().nextInt(100_000));
        }
        endTime = System.nanoTime();
        System.out.println("Time for 1,000 random lookups: " +
                (endTime - startTime) / 1_000_000 + " ms");

        System.out.println("Final size: " + perfMap.size());
        System.out.println();
    }

    private void nullHandling() {
        System.out.println("5. NULL HANDLING");
        System.out.println("================");

        HashMap<String, String> nullMap = new HashMap<>();

        // Null key
        nullMap.put(null, "Null key value");
        System.out.println("Map with null key: " + nullMap);

        // Null values
        nullMap.put("key1", null);
        nullMap.put("key2", "value2");
        nullMap.put("key3", null);

        System.out.println("Map with null values: " + nullMap);

        // Retrieving null values
        System.out.println("Value for null key: " + nullMap.get(null));
        System.out.println("Value for key1 (null): " + nullMap.get("key1"));
        System.out.println("Contains null key: " + nullMap.containsKey(null));
        System.out.println("Contains null value: " + nullMap.containsValue(null));

        // Distinguishing between null value and missing key
        System.out.println("key1 exists: " + nullMap.containsKey("key1"));
        System.out.println("key4 exists: " + nullMap.containsKey("key4"));
        System.out.println("key1 value: " + nullMap.get("key1"));
        System.out.println("key4 value: " + nullMap.get("key4"));
        System.out.println();
    }

    private void customHashCodeAndEquals() {
        System.out.println("6. CUSTOM HASHCODE AND EQUALS");
        System.out.println("==============================");

        HashMap<Person, String> personMap = new HashMap<>();

        Person person1 = new Person("John", 30);
        Person person2 = new Person("John", 30); // Same content, different object
        Person person3 = new Person("Jane", 25);

        personMap.put(person1, "Engineer");
        personMap.put(person3, "Doctor");

        System.out.println("Person1 equals Person2: " + person1.equals(person2));
        System.out.println("Person1 hashCode: " + person1.hashCode());
        System.out.println("Person2 hashCode: " + person2.hashCode());

        System.out.println("Map contents: " + personMap);
        System.out.println("Get with person1: " + personMap.get(person1));
        System.out.println("Get with person2: " + personMap.get(person2)); // Should work due to equals/hashCode

        // Demonstrate bad hashCode/equals
        HashMap<BadPerson, String> badMap = new HashMap<>();
        BadPerson bad1 = new BadPerson("Alice", 28);
        BadPerson bad2 = new BadPerson("Alice", 28);

        badMap.put(bad1, "Designer");
        System.out.println("Bad map with bad1: " + badMap);
        System.out.println("Get with bad2 (should be same): " + badMap.get(bad2)); // Will be null!
        System.out.println("This demonstrates why proper hashCode/equals is crucial");
        System.out.println();
    }

    private void capacityAndLoadFactor() {
        System.out.println("7. CAPACITY AND LOAD FACTOR");
        System.out.println("============================");

        // Default HashMap
        HashMap<Integer, String> defaultMap = new HashMap<>();
        System.out.println("Default initial capacity: 16, load factor: 0.75");

        // Custom capacity and load factor
        HashMap<Integer, String> customMap = new HashMap<>(32, 0.6f);
        System.out.println("Custom capacity: 32, load factor: 0.6");

        // Demonstrating resize
        HashMap<Integer, String> resizeMap = new HashMap<>(4); // Small initial capacity
        System.out.println("Initial capacity: 4");

        for (int i = 0; i < 10; i++) {
            resizeMap.put(i, "Value" + i);
            if (i == 3 || i == 7) {
                System.out.println("After adding " + (i + 1) + " elements, map will resize");
            }
        }

        System.out.println("Final map size: " + resizeMap.size());
        System.out.println("Resize happens when size > capacity * loadFactor");
        System.out.println("Each resize doubles the capacity");
        System.out.println();
    }

    private void bulkOperations() {
        System.out.println("8. BULK OPERATIONS");
        System.out.println("==================");

        HashMap<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        map1.put("C", 3);

        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("B", 20); // Overlapping key with different value
        map2.put("D", 4);
        map2.put("E", 5);

        System.out.println("Map1: " + map1);
        System.out.println("Map2: " + map2);

        // putAll
        HashMap<String, Integer> merged = new HashMap<>(map1);
        merged.putAll(map2);
        System.out.println("After putAll: " + merged);

        // putIfAbsent
        merged.putIfAbsent("F", 6);
        merged.putIfAbsent("A", 100); // Won't overwrite existing
        System.out.println("After putIfAbsent: " + merged);

        // replace operations
        merged.replace("A", 10);
        merged.replace("G", 7); // Won't add new key-value
        System.out.println("After replace: " + merged);

        boolean replaced = merged.replace("B", 20, 200);
        System.out.println("Conditional replace success: " + replaced);
        System.out.println("After conditional replace: " + merged);

        // replaceAll
        merged.replaceAll((key, value) -> value * 10);
        System.out.println("After replaceAll (* 10): " + merged);
        System.out.println();
    }

    private void java8Enhancements() {
        System.out.println("9. JAVA 8 ENHANCEMENTS");
        System.out.println("=======================");

        HashMap<String, List<String>> groupMap = new HashMap<>();

        // computeIfAbsent - create list if key doesn't exist
        groupMap.computeIfAbsent("fruits", k -> new ArrayList<>()).add("apple");
        groupMap.computeIfAbsent("fruits", k -> new ArrayList<>()).add("banana");
        groupMap.computeIfAbsent("vegetables", k -> new ArrayList<>()).add("carrot");

        System.out.println("After computeIfAbsent: " + groupMap);

        // computeIfPresent - modify existing value
        groupMap.computeIfPresent("fruits", (k, v) -> {
            v.add("cherry");
            return v;
        });

        System.out.println("After computeIfPresent: " + groupMap);

        // compute - always compute new value
        HashMap<String, Integer> countMap = new HashMap<>();
        String[] words = {"apple", "banana", "apple", "cherry", "banana", "apple"};

        for (String word : words) {
            countMap.compute(word, (k, v) -> (v == null) ? 1 : v + 1);
        }

        System.out.println("Word counts: " + countMap);

        // merge - combine values
        HashMap<String, Integer> sales1 = new HashMap<>();
        sales1.put("Product A", 100);
        sales1.put("Product B", 150);

        HashMap<String, Integer> sales2 = new HashMap<>();
        sales2.put("Product B", 75);
        sales2.put("Product C", 200);

        System.out.println("Sales1: " + sales1);
        System.out.println("Sales2: " + sales2);

        // Merge sales2 into sales1
        sales2.forEach((product, amount) ->
                sales1.merge(product, amount, Integer::sum)
        );

        System.out.println("Merged sales: " + sales1);
        System.out.println();
    }

    private void commonPatterns() {
        System.out.println("10. COMMON PATTERNS");
        System.out.println("===================");

        System.out.println("Pattern 1: Caching/Memoization");
        HashMap<Integer, Integer> fibCache = new HashMap<>();
        System.out.println("Fibonacci(10) = " + fibonacciMemo(10, fibCache));
        System.out.println("Cache after computation: " + fibCache);

        System.out.println("\nPattern 2: Frequency Counting");
        String text = "hello world hello";
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (c != ' ') {
                charCount.merge(c, 1, Integer::sum);
            }
        }
        System.out.println("Character frequencies: " + charCount);

        System.out.println("\nPattern 3: Grouping");
        List<String> names = Arrays.asList("Alice", "Bob", "Anna", "Charlie", "Andrew");
        HashMap<Character, List<String>> groupedByFirstLetter = new HashMap<>();

        for (String name : names) {
            char firstLetter = name.charAt(0);
            groupedByFirstLetter.computeIfAbsent(firstLetter, k -> new ArrayList<>()).add(name);
        }
        System.out.println("Grouped by first letter: " + groupedByFirstLetter);

        System.out.println("\nPattern 4: Configuration/Properties");
        HashMap<String, String> config = new HashMap<>();
        config.put("database.url", "jdbc:mysql://localhost:3306/mydb");
        config.put("database.username", "admin");
        config.put("cache.enabled", "true");
        config.put("logging.level", "INFO");

        System.out.println("Configuration:");
        config.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println("  " + entry.getKey() + " = " + entry.getValue()));
        System.out.println();
    }

    private void bestPractices() {
        System.out.println("11. BEST PRACTICES");
        System.out.println("==================");

        System.out.println("✓ 1. Always implement proper hashCode() and equals()");
        System.out.println("✓ 2. Use immutable keys when possible");

        System.out.println("✓ 3. Initialize with appropriate capacity for large maps");
        HashMap<Integer, String> efficientMap = new HashMap<>(1000); // Avoid resizing

        System.out.println("✓ 4. Use computeIfAbsent instead of get/put pattern");
        HashMap<String, List<String>> grouping = new HashMap<>();
        // Good: grouping.computeIfAbsent("key", k -> new ArrayList<>()).add("value");
        // Bad: if (!grouping.containsKey("key")) grouping.put("key", new ArrayList<>());

        System.out.println("✓ 5. Be careful with null keys and values");
        System.out.println("✓ 6. Use merge() for combining values");
        System.out.println("✓ 7. Consider thread safety - HashMap is not thread-safe");
        System.out.println("✓ 8. Use entrySet() for iteration over key-value pairs");

        System.out.println("\n✗ Common Mistakes:");
        System.out.println("- Using mutable objects as keys");
        System.out.println("- Ignoring equals/hashCode contract");
        System.out.println("- Using HashMap in multi-threaded environment without synchronization");
        System.out.println("- Not initializing capacity for large datasets");
        System.out.println();
    }

    private void realWorldUseCases() {
        System.out.println("12. REAL WORLD USE CASES");
        System.out.println("=========================");

        System.out.println("Use Case 1: User Session Management");
        HashMap<String, UserSession> sessions = new HashMap<>();
        sessions.put("session123", new UserSession("Alice", System.currentTimeMillis()));
        sessions.put("session456", new UserSession("Bob", System.currentTimeMillis()));
        System.out.println("Active sessions: " + sessions.size());

        System.out.println("\nUse Case 2: Database Connection Pool");
        HashMap<String, DatabaseConnection> connectionPool = new HashMap<>();
        connectionPool.put("read-only", new DatabaseConnection("readonly", true));
        connectionPool.put("read-write", new DatabaseConnection("readwrite", false));
        System.out.println("Available connections: " + connectionPool.keySet());

        System.out.println("\nUse Case 3: HTTP Request Parameters");
        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("userId", "12345");
        requestParams.put("action", "update");
        requestParams.put("format", "json");
        System.out.println("Request parameters: " + requestParams);

        System.out.println("\nUse Case 4: Application Configuration");
        HashMap<String, Object> appConfig = new HashMap<>();
        appConfig.put("app.name", "MyApplication");
        appConfig.put("app.version", "2.1.0");
        appConfig.put("server.port", 8080);
        appConfig.put("debug.enabled", true);
        System.out.println("Application config loaded: " + appConfig.size() + " properties");

        System.out.println("\nUse Case 5: Inventory Management");
        HashMap<String, Product> inventory = new HashMap<>();
        inventory.put("SKU001", new Product("Laptop", 999.99, 50));
        inventory.put("SKU002", new Product("Mouse", 29.99, 200));
        inventory.put("SKU003", new Product("Keyboard", 79.99, 100));

        System.out.println("Inventory items:");
        inventory.forEach((sku, product) ->
                System.out.println("  " + sku + ": " + product)
        );

        System.out.println("\nHashMap is ideal for:");
        System.out.println("- Fast lookups by key");
        System.out.println("- Caching computed results");
        System.out.println("- Storing configuration data");
        System.out.println("- Implementing associative arrays");
        System.out.println("- Frequency counting");
        System.out.println("- Grouping operations");
    }

    // Helper method for memoization example
    private int fibonacciMemo(int n, HashMap<Integer, Integer> cache) {
        if (n <= 1) return n;

        return cache.computeIfAbsent(n, k ->
                fibonacciMemo(k - 1, cache) + fibonacciMemo(k - 2, cache)
        );
    }

    // Helper classes
    static class BadHashKey {
        private String value;

        public BadHashKey(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 1; // Intentionally bad - all instances have same hash
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            BadHashKey that = (BadHashKey) obj;
            return Objects.equals(value, that.value);
        }

        @Override
        public String toString() {
            return "BadHashKey{" + value + "}";
        }
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
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
            return name + "(" + age + ")";
        }
    }

    static class BadPerson {
        private String name;
        private int age;

        public BadPerson(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Intentionally not overriding equals and hashCode
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    static class UserSession {
        private String username;
        private long timestamp;

        public UserSession(String username, long timestamp) {
            this.username = username;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return username + "@" + new Date(timestamp);
        }
    }

    static class DatabaseConnection {
        private String connectionString;
        private boolean isReadOnly;

        public DatabaseConnection(String connectionString, boolean isReadOnly) {
            this.connectionString = connectionString;
            this.isReadOnly = isReadOnly;
        }

        @Override
        public String toString() {
            return connectionString + (isReadOnly ? "(RO)" : "(RW)");
        }
    }

    static class Product {
        private String name;
        private double price;
        private int quantity;

        public Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return name + " ($" + price + ", qty: " + quantity + ")";
        }
    }
}
