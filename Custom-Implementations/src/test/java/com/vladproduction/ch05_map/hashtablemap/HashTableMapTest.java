package com.vladproduction.ch05_map.hashtablemap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for HashTableMap implementation.
 * Tests all operations, edge cases, collision handling, and resizing.
 */
class HashTableMapTest {

    private HashTableMap<String, Integer> map;
    private HashTableMap<Integer, String> intMap;

    @BeforeEach
    void setUp() {
        map = new HashTableMap<>();
        intMap = new HashTableMap<>();
    }

    @Test
    @DisplayName("Empty map should have size 0 and be empty")
    void testEmptyMap() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.get("nonexistent"));
        assertFalse(map.containsKey("nonexistent"));
        assertEquals(16, map.getCapacity()); // Default capacity
        assertEquals(0.0, map.getLoadFactor(), 0.001);
    }

    @Test
    @DisplayName("Custom capacity constructor should work")
    void testCustomCapacity() {
        HashTableMap<String, Integer> customMap = new HashTableMap<>(8);
        assertEquals(8, customMap.getCapacity());
        assertEquals(0, customMap.size());

        // Test minimum capacity
        HashTableMap<String, Integer> minMap = new HashTableMap<>(0);
        assertEquals(1, minMap.getCapacity());
    }

    @Test
    @DisplayName("Put operation should add new entries and return null")
    void testPutNewEntries() {
        assertNull(map.put("apple", 5));
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertEquals(Integer.valueOf(5), map.get("apple"));
        assertTrue(map.containsKey("apple"));

        assertNull(map.put("banana", 3));
        assertEquals(2, map.size());
        assertEquals(Integer.valueOf(3), map.get("banana"));

        // Check a load factor
        assertTrue(map.getLoadFactor() > 0);
        assertTrue(map.getLoadFactor() < 1);
    }

    @Test
    @DisplayName("Put operation should update existing entries and return old value")
    void testPutUpdateExisting() {
        map.put("apple", 5);
        Integer oldValue = map.put("apple", 10);

        assertEquals(Integer.valueOf(5), oldValue);
        assertEquals(1, map.size()); // Size shouldn't change
        assertEquals(Integer.valueOf(10), map.get("apple"));
    }

    @Test
    @DisplayName("Get operation should return correct values or null")
    void testGetOperation() {
        map.put("apple", 5);
        map.put("banana", null); // Test null value

        assertEquals(Integer.valueOf(5), map.get("apple"));
        assertNull(map.get("banana")); // Null value
        assertNull(map.get("nonexistent")); // Non-existent key
    }

    @Test
    @DisplayName("Remove operation should remove entries and return old values")
    void testRemoveOperation() {
        map.put("apple", 5);
        map.put("banana", 3);

        Integer removed = map.remove("apple");
        assertEquals(Integer.valueOf(5), removed);
        assertEquals(1, map.size());
        assertNull(map.get("apple"));
        assertFalse(map.containsKey("apple"));

        // Remove a non-existent key
        assertNull(map.remove("nonexistent"));
        assertEquals(1, map.size()); // Size unchanged
    }

    @Test
    @DisplayName("Clear operation should remove all entries")
    void testClearOperation() {
        map.put("apple", 5);
        map.put("banana", 3);
        map.put("cherry", 8);

        assertEquals(3, map.size());

        map.clear();

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.get("apple"));
        assertFalse(map.containsKey("apple"));
        assertEquals(0.0, map.getLoadFactor(), 0.001);
    }

    @Test
    @DisplayName("ContainsKey should work correctly with null values")
    void testContainsKeyWithNullValues() {
        map.put("nullValue", null);
        map.put("normalValue", 42);

        assertTrue(map.containsKey("nullValue"));
        assertTrue(map.containsKey("normalValue"));
        assertFalse(map.containsKey("nonexistent"));
    }

    @Test
    @DisplayName("Map should handle null keys correctly")
    void testNullKeys() {
        assertNull(map.put(null, 42));
        assertEquals(Integer.valueOf(42), map.get(null));
        assertTrue(map.containsKey(null));

        Integer oldValue = map.put(null, 100);
        assertEquals(Integer.valueOf(42), oldValue);
        assertEquals(Integer.valueOf(100), map.get(null));
    }

    @Test
    @DisplayName("Hash collisions should be handled correctly")
    void testHashCollisions() {
        // Create objects that will likely collide
        HashTableMap<CollisionKey, String> collisionMap = new HashTableMap<>(4); // Small capacity

        CollisionKey key1 = new CollisionKey("a", 1);
        CollisionKey key2 = new CollisionKey("b", 1); // Same hash code
        CollisionKey key3 = new CollisionKey("c", 1); // Same hash code

        collisionMap.put(key1, "value1");
        collisionMap.put(key2, "value2");
        collisionMap.put(key3, "value3");

        assertEquals("value1", collisionMap.get(key1));
        assertEquals("value2", collisionMap.get(key2));
        assertEquals("value3", collisionMap.get(key3));
        assertEquals(3, collisionMap.size());

        // Test removal with collisions
        assertEquals("value2", collisionMap.remove(key2));
        assertNull(collisionMap.get(key2));
        assertEquals("value1", collisionMap.get(key1));
        assertEquals("value3", collisionMap.get(key3));
        assertEquals(2, collisionMap.size());
    }

    @Test
    @DisplayName("Map should resize when load factor threshold is exceeded")
    void testAutomaticResizing() {
        HashTableMap<Integer, String> resizeMap = new HashTableMap<>(4); // Small initial capacity
        int initialCapacity = resizeMap.getCapacity();

        // Add enough elements to trigger resize
        for (int i = 0; i < 10; i++) {
            resizeMap.put(i, "value" + i);
        }

        // Should have resized
        assertTrue(resizeMap.getCapacity() > initialCapacity);
        assertEquals(10, resizeMap.size());

        // All elements should still be accessible
        for (int i = 0; i < 10; i++) {
            assertEquals("value" + i, resizeMap.get(i));
            assertTrue(resizeMap.containsKey(i));
        }

        // Load factor should be reasonable
        assertTrue(resizeMap.getLoadFactor() < 0.75);
    }

    @Test
    @DisplayName("Equals method should work correctly")
    void testEqualsMethod() {
        HashTableMap<String, Integer> map1 = new HashTableMap<>();
        HashTableMap<String, Integer> map2 = new HashTableMap<>();

        // Empty maps should be equal
        assertEquals(map1, map2);

        // Maps with the same content should be equal
        map1.put("apple", 5);
        map1.put("banana", 3);
        map2.put("apple", 5);
        map2.put("banana", 3);
        assertEquals(map1, map2);

        // Order shouldn't matter in the hash table
        HashTableMap<String, Integer> map3 = new HashTableMap<>();
        map3.put("banana", 3);
        map3.put("apple", 5);
        assertEquals(map1, map3);

        // Maps with different content should not be equal
        map2.put("cherry", 8);
        assertNotEquals(map1, map2);

        // Test with null values
        map1.put("nullKey", null);
        map2.remove("cherry");
        map2.put("nullKey", null);
        assertEquals(map1, map2);
    }

    @Test
    @DisplayName("HashCode should be consistent with equals")
    void testHashCodeConsistency() {
        HashTableMap<String, Integer> map1 = new HashTableMap<>();
        HashTableMap<String, Integer> map2 = new HashTableMap<>();

        map1.put("apple", 5);
        map1.put("banana", 3);
        map2.put("banana", 3);
        map2.put("apple", 5);

        assertEquals(map1, map2);
        assertEquals(map1.hashCode(), map2.hashCode());
    }

    @Test
    @DisplayName("Map should handle different generic types")
    void testDifferentGenericTypes() {
        HashTableMap<Integer, String> intStringMap = new HashTableMap<>();
        intStringMap.put(1, "one");
        intStringMap.put(2, "two");

        assertEquals("one", intStringMap.get(1));
        assertEquals("two", intStringMap.get(2));
        assertEquals(2, intStringMap.size());

        HashTableMap<String, Boolean> stringBoolMap = new HashTableMap<>();
        stringBoolMap.put("true", Boolean.TRUE);
        stringBoolMap.put("false", Boolean.FALSE);

        assertEquals(Boolean.TRUE, stringBoolMap.get("true"));
        assertEquals(Boolean.FALSE, stringBoolMap.get("false"));
    }

    @Test
    @DisplayName("Large dataset performance and correctness test")
    void testLargeDataset() {
        final int LARGE_SIZE = 10000;

        // Add many entries
        for (int i = 0; i < LARGE_SIZE; i++) {
            intMap.put(i, "value" + i);
        }

        assertEquals(LARGE_SIZE, intMap.size());

        // Should have resized multiple times
        assertTrue(intMap.getCapacity() > 16); // Default capacity
        assertTrue(intMap.getLoadFactor() < 0.75);

        // Verify all entries
        for (int i = 0; i < LARGE_SIZE; i++) {
            assertEquals("value" + i, intMap.get(i));
            assertTrue(intMap.containsKey(i));
        }

        // Remove half the entries
        for (int i = 0; i < LARGE_SIZE / 2; i++) {
            assertEquals("value" + i, intMap.remove(i));
        }

        assertEquals(LARGE_SIZE / 2, intMap.size());

        // Verify removals
        for (int i = 0; i < LARGE_SIZE / 2; i++) {
            assertNull(intMap.get(i));
            assertFalse(intMap.containsKey(i));
        }

        // Verify remaining entries
        for (int i = LARGE_SIZE / 2; i < LARGE_SIZE; i++) {
            assertEquals("value" + i, intMap.get(i));
            assertTrue(intMap.containsKey(i));
        }
    }

    @Test
    @DisplayName("toString should produce readable output")
    void testToString() {
        String emptyString = map.toString();
        assertEquals("{}", emptyString);

        map.put("apple", 5);
        String singleEntryString = map.toString();
        assertEquals("{apple=5}", singleEntryString);

        map.put("banana", 3);
        String multiEntryString = map.toString();
        assertTrue(multiEntryString.contains("apple=5"));
        assertTrue(multiEntryString.contains("banana=3"));
        assertTrue(multiEntryString.startsWith("{"));
        assertTrue(multiEntryString.endsWith("}"));
    }

    @Test
    @DisplayName("Edge case: Multiple operations on same key")
    void testRepeatedOperations() {
        // Put, update, remove, put again
        assertNull(map.put("key", 1));
        assertEquals(Integer.valueOf(1), map.put("key", 2));
        assertEquals(Integer.valueOf(2), map.put("key", 3));
        assertEquals(Integer.valueOf(3), map.remove("key"));
        assertNull(map.get("key"));
        assertNull(map.put("key", 4));
        assertEquals(Integer.valueOf(4), map.get("key"));
    }

    @Test
    @DisplayName("Reference equality vs value equality")
    void testReferenceVsValueEquality() {
        String key1 = new String("key");
        String key2 = new String("key");

        // Keys should be equal by value, not reference
        assertNotSame(key1, key2);
        assertEquals(key1, key2);

        map.put(key1, 42);
        assertEquals(Integer.valueOf(42), map.get(key2)); // Should find by value equality
        assertTrue(map.containsKey(key2));
    }

    @Test
    @DisplayName("Bucket distribution should be reasonable")
    void testBucketDistribution() {
        HashTableMap<String, Integer> testMap = new HashTableMap<>(8);

        // Add many different strings
        String[] keys = {"apple", "banana", "cherry", "date", "elderberry",
                "fig", "grape", "honeydew", "kiwi", "lemon",
                "mango", "nectarine", "orange", "papaya"};

        for (int i = 0; i < keys.length; i++) {
            testMap.put(keys[i], i);
        }

        // All should be retrievable
        for (int i = 0; i < keys.length; i++) {
            assertEquals(Integer.valueOf(i), testMap.get(keys[i]));
        }

        assertEquals(keys.length, testMap.size());
    }

    @Test
    @DisplayName("Load factor should be calculated correctly")
    void testLoadFactorCalculation() {
        HashTableMap<Integer, String> testMap = new HashTableMap<>(10);
        assertEquals(0.0, testMap.getLoadFactor(), 0.001);

        testMap.put(1, "one");
        assertEquals(0.1, testMap.getLoadFactor(), 0.001);

        for (int i = 2; i <= 7; i++) {
            testMap.put(i, "value" + i);
        }
        assertEquals(0.7, testMap.getLoadFactor(), 0.001);

        // Should resize after the next insertion
        int oldCapacity = testMap.getCapacity();
        System.out.println("oldCapacity = " + oldCapacity);                 //oldCapacity = 10
        testMap.put(8, "eight");
        int newCapacity = testMap.getCapacity();
        System.out.println("newCapacity = " + newCapacity);                 //newCapacity = 20 (should increase by 10 (10 * 2))

        assertTrue(newCapacity > oldCapacity);                      //true

        double currentLoadFactor = testMap.getLoadFactor();
        System.out.println("currentLoadFactor = " + currentLoadFactor);      //currentLoadFactor = 0.4

        // after resizing, the load factor should indeed be lower
        // than before the resize operation
        assertTrue(currentLoadFactor < 0.5);                         // Should be lower after resize (0.4 < 0.5)  so no issue here
    }

    /**
     * Load Factor Explanation
     * Load Factor = Number of Elements / Table Capacity
     * Initial state: 0 elements in capacity 10 → Load factor = 0/10 = 0.0
     * After adding 7 elements: 7 elements in capacity 10 → Load factor = 7/10 = 0.7
     * After adding 8th element: The hash table detects that adding another element would exceed the load factor threshold (typically 0.75), so it:
     * Resizes from capacity 10 to 20 (doubles)
     * Rehashes all existing elements into the new larger table
     * Now has 8 elements in capacity 20 → Load factor = 8/20 = 0.4
     * The load factor decreases after resizing because we're spreading the same number of elements across a larger table.
     * This is the intended behavior to maintain good performance by keeping the load factor low.
     * */

    @Test
    @DisplayName("Load factor behavior during resize operations")
    void testLoadFactorBehaviorDuringResize() {
        // Start with a small capacity
        HashTableMap<Integer, String> map = new HashTableMap<>(4);

        // Empty table
        assertEquals(0.0, map.getLoadFactor(), 0.001, "Empty table should have load factor 0.0");
        assertEquals(4, map.getCapacity(), "Initial capacity should be 4");

        // 1 element has been added
        map.put(1, "one");
        assertEquals(0.25, map.getLoadFactor(), 0.001, "1 element in capacity 4 should give load factor 0.25");

        // 2 elements have been added
        map.put(2, "two");
        assertEquals(0.5, map.getLoadFactor(), 0.001, "2 elements in capacity 4 should give load factor 0.5");

        // 3 elements have been added
        //triggering resize #1 is starting here after the put third element
        System.out.println("Before resize #1 - Elements: " + map.size() +
                ", Capacity: " + map.getCapacity() +
                ", Load Factor: " + map.getLoadFactor());
        map.put(3, "three");
        assertEquals(0.375, map.getLoadFactor(), 0.001, "3 elements in capacity 8 should give load factor 0.375");
        assertEquals(8, map.getCapacity(), "Capacity should resize to '8'");

        System.out.println("After resize #1 occur (since '3' elem was added) - Elements: " + map.size() +
                ", Capacity: " + map.getCapacity() +
                ", Load Factor: " + map.getLoadFactor());

        // 4 elements have been added
        map.put(4, "four");
        assertEquals(8, map.getCapacity(), "Capacity should is still 8");
        assertEquals(0.5, map.getLoadFactor(), 0.001, "4 elements in capacity 8 should give load factor 0.5");

        // 5 elements have been added
        map.put(5, "five");
        assertEquals(0.625, map.getLoadFactor(), 0.001, "5 elements in capacity 8 should give load factor 0.625");
        assertEquals(8, map.getCapacity(), "Capacity should is still 8");

        // 6 elements have been added
        //triggering resize #2 is starting here after the put sixth element
        System.out.println("Before resize #2 - Elements: " + map.size() +
                ", Capacity: " + map.getCapacity() +
                ", Load Factor: " + map.getLoadFactor());
        map.put(6, "six");
        assertEquals(0.375, map.getLoadFactor(), 0.001, "6 elements in capacity 16 should give load factor 0.375");
        assertEquals(16, map.getCapacity(), "Capacity should resize to '16'");

        //final check load factor and capacity after resizing
        System.out.println("After resize #2 (since '6' elem was added) - Elements: " + map.size() +
                ", Capacity: " + map.getCapacity() +
                ", Load Factor: " + map.getLoadFactor());
        assertTrue(map.getLoadFactor() < 0.5, "Load factor (0.375 < 0.5) should decrease significantly after resize");
    }

    @Test
    @DisplayName("Load factor calculation with remove operations")
    void testLoadFactorWithRemoval() {
        HashTableMap<Integer, String> map = new HashTableMap<>(8); //initial capacity is 8

        // Fill the map
        for (int i = 1; i <= 6; i++) {
            map.put(i, "value" + i);
        }
        assertEquals(16, map.getCapacity(), "6 elements should be in the map with capacity 16");
        assertEquals(0.375, map.getLoadFactor(), 0.001, "6 elements in capacity 16 should give load factor 0.375");

        // Remove some elements
        map.remove(1);
        assertEquals(0.3125, map.getLoadFactor(), 0.001, "5 elements in capacity 16 should give load factor 0.3125");
        assertEquals(5, map.size(), "5 elements should be in the map");

        map.remove(2);
        map.remove(3);
        assertEquals(3, map.size(), "3 elements should be in the map");
        assertEquals(0.1875, map.getLoadFactor(), 0.001, "3 elements in capacity 16 should give load factor 0.1875");

        // Note: Most hash table implementations don't shrink capacity when removing elements
        // The capacity typically remains the same to avoid frequent resizing
        assertEquals(16, map.getCapacity(), "Capacity should remain 16 even after removals");
    }

    /**
     * Helper class to test hash collisions
     */
    static class CollisionKey {
        private final String name;
        private final int hash;

        public CollisionKey(String name, int hash) {
            this.name = name;
            this.hash = hash;
        }

        @Override
        public int hashCode() {
            return hash; // assuming intentionally return fixed hash to create collisions
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CollisionKey that = (CollisionKey) obj;
            return hash == that.hash && name.equals(that.name);
        }

        @Override
        public String toString() {
            return name + "(" + hash + ")";
        }

    }
}