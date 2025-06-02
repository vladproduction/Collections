package com.vladproduction.ch05_map;

import com.vladproduction.ch05_map.hashtablemap.HashTableMap;
import com.vladproduction.ch05_map.linearsearchmap.LinearSearchMap;

import java.util.Random;

/**
 * Demonstration class showing how to use both LinearSearchMap and HashTableMap implementations.
 * Includes performance comparison and usage examples.
 */
public class MainMyMapApp {
    public static void main(String[] args) {

        System.out.println("=== MyMap Implementations Demo ===\n");

        // Basic usage examples
        demonstrateBasicUsage();

        // Performance comparison
        performanceComparison();

        // Edge cases
        demonstrateEdgeCases();

        // Generic type safety
        demonstrateGenerics();

    }

    /**
     * Demonstrates basic operations for both implementations.
     */
    private static void demonstrateBasicUsage() {
        System.out.println("1. BASIC USAGE DEMONSTRATION");
        System.out.println("=" .repeat(40));

        // LinearSearchMap example
        System.out.println("\n--- LinearSearchMap Example ---");
        MyMap<String, Integer> linearMap = new LinearSearchMap<>();

        linearMap.put("apple", 5);
        linearMap.put("banana", 3);
        linearMap.put("cherry", 8);
        linearMap.put("date", 2);

        System.out.println("Linear Map: " + linearMap);
        System.out.println("Size: " + linearMap.size());
        System.out.println("Get 'banana': " + linearMap.get("banana"));
        System.out.println("Contains 'cherry': " + linearMap.containsKey("cherry"));
        System.out.println("Remove 'date': " + linearMap.remove("date"));
        System.out.println("After removal: " + linearMap);

        // HashTableMap example
        System.out.println("\n--- HashTableMap Example ---");
        MyMap<String, Integer> hashMap = new HashTableMap<>();

        hashMap.put("apple", 5);
        hashMap.put("banana", 3);
        hashMap.put("cherry", 8);
        hashMap.put("date", 2);

        System.out.println("Hash Map: " + hashMap);
        System.out.println("Size: " + hashMap.size());
        System.out.println("Get 'banana': " + hashMap.get("banana"));
        System.out.println("Contains 'cherry': " + hashMap.containsKey("cherry"));
        System.out.println("Remove 'date': " + hashMap.remove("date"));
        System.out.println("After removal: " + hashMap);

        // Update existing key
        System.out.println("\n--- Update Operations ---");
        String oldAppleValue = String.valueOf(linearMap.put("apple", 10));
        System.out.println("Linear Map - Updated 'apple' from " + oldAppleValue + " to 10");

        Integer oldBananaValue = hashMap.put("banana", 7);
        System.out.println("Hash Map - Updated 'banana' from " + oldBananaValue + " to 7");

        System.out.println("Linear Map after update: " + linearMap);
        System.out.println("Hash Map after update: " + hashMap);
    }

    /**
     * Compares performance between the two implementations.
     */
    private static void performanceComparison() {
        System.out.println("\n\n2. PERFORMANCE COMPARISON");
        System.out.println("=" .repeat(40));

        int[] testSizes = {100, 1000, 5000};

        for (int size : testSizes) {
            System.out.println("\n--- Testing with " + size + " elements ---");

            // Test LinearSearchMap
            long startTime = System.nanoTime();
            MyMap<Integer, String> linearMap = new LinearSearchMap<>();
            for (int i = 0; i < size; i++) {
                linearMap.put(i, "value" + i);
            }

            // Random access test
            Random random = new Random(42); // Fixed seed for consistent results
            for (int i = 0; i < 100; i++) {
                linearMap.get(random.nextInt(size));
            }
            long linearTime = System.nanoTime() - startTime;

            // Test HashTableMap
            startTime = System.nanoTime();
            MyMap<Integer, String> hashMap = new HashTableMap<>();
            for (int i = 0; i < size; i++) {
                hashMap.put(i, "value" + i);
            }

            // Random access test
            random = new Random(42); // Same seed
            for (int i = 0; i < 100; i++) {
                hashMap.get(random.nextInt(size));
            }
            long hashTime = System.nanoTime() - startTime;

            System.out.printf("LinearSearchMap: %.2f ms%n", linearTime / 1_000_000.0);
            System.out.printf("HashTableMap:    %.2f ms%n", hashTime / 1_000_000.0);
            System.out.printf("Speedup:         %.2fx%n", (double) linearTime / hashTime);

            // Show hash table stats
            if (hashMap instanceof HashTableMap) {
                HashTableMap<Integer, String> htMap = (HashTableMap<Integer, String>) hashMap;
                System.out.printf("Hash table load factor: %.2f%n", htMap.getLoadFactor());
                System.out.printf("Hash table capacity: %d%n", htMap.getCapacity());
            }
        }
    }

    /**
     * Demonstrates handling of edge cases.
     */
    private static void demonstrateEdgeCases() {
        System.out.println("\n\n3. EDGE CASES DEMONSTRATION");
        System.out.println("=" .repeat(40));

        MyMap<String, String> map1 = new LinearSearchMap<>();
        MyMap<String, String> map2 = new HashTableMap<>();

        System.out.println("\n--- Null Values ---");
        map1.put("nullKey", null);
        map2.put("nullKey", null);
        System.out.println("Linear map null value: " + map1.get("nullKey"));
        System.out.println("Hash map null value: " + map2.get("nullKey"));
        System.out.println("Linear map contains null key: " + map1.containsKey("nullKey"));
        System.out.println("Hash map contains null key: " + map2.containsKey("nullKey"));

        System.out.println("\n--- Non-existent Keys ---");
        System.out.println("Linear map get non-existent: " + map1.get("notExists"));
        System.out.println("Hash map get non-existent: " + map2.get("notExists"));
        System.out.println("Linear map remove non-existent: " + map1.remove("notExists"));
        System.out.println("Hash map remove non-existent: " + map2.remove("notExists"));

        System.out.println("\n--- Empty Maps ---");
        MyMap<String, String> emptyLinear = new LinearSearchMap<>();
        MyMap<String, String> emptyHash = new HashTableMap<>();
        System.out.println("Empty linear map size: " + emptyLinear.size());
        System.out.println("Empty hash map size: " + emptyHash.size());
        System.out.println("Empty linear map isEmpty: " + emptyLinear.isEmpty());
        System.out.println("Empty hash map isEmpty: " + emptyHash.isEmpty());

        System.out.println("\n--- Clear Operation ---");
        map1.clear();
        map2.clear();
        System.out.println("After clear - Linear map size: " + map1.size());
        System.out.println("After clear - Hash map size: " + map2.size());
    }

    /**
     * Demonstrates generic type safety with different types.
     */
    private static void demonstrateGenerics() {
        System.out.println("\n\n4. GENERIC TYPE SAFETY DEMONSTRATION");
        System.out.println("=" .repeat(40));

        // String to Integer mapping
        System.out.println("\n--- String to Integer ---");
        MyMap<String, Integer> scores = new HashTableMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        System.out.println("Scores: " + scores);

        // Integer to String mapping
        System.out.println("\n--- Integer to String ---");
        MyMap<Integer, String> idToName = new LinearSearchMap<>();
        idToName.put(1001, "John Doe");
        idToName.put(1002, "Jane Smith");
        idToName.put(1003, "Mike Johnson");
        System.out.println("ID to Name: " + idToName);

        // Custom object mapping
        System.out.println("\n--- Custom Object Mapping ---");
        MyMap<Person, String> personToCity = new HashTableMap<>();
        personToCity.put(new Person("Alice", 30), "New York");
        personToCity.put(new Person("Bob", 25), "San Francisco");
        System.out.println("Person to City: " + personToCity);

        // Demonstrate equals and hashCode
        System.out.println("\n--- Equals and HashCode ---");
        MyMap<String, Integer> map1 = new LinearSearchMap<>();
        MyMap<String, Integer> map2 = new LinearSearchMap<>();
        map1.put("test", 42);
        map2.put("test", 42);
        System.out.println("Two maps with same content equal: " + map1.equals(map2));
        System.out.println("Map1 hashCode: " + map1.hashCode());
        System.out.println("Map2 hashCode: " + map2.hashCode());
    }

    /**
     * Simple Person class for demonstration.
     */
    static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode() * 31 + age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

}
