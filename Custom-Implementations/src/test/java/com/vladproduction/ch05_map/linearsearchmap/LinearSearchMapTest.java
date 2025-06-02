package com.vladproduction.ch05_map.linearsearchmap;

import com.vladproduction.ch05_map.MyMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for LinearSearchMap implementation.
 * Tests all operations, edge cases, and contract compliance.
 */
class LinearSearchMapTest {

    private MyMap<String, Integer> map;
    private MyMap<Integer, String> intMap;

    @BeforeEach
    void setUp() {
        map = new LinearSearchMap<>();
        intMap = new LinearSearchMap<>();
    }

    @Test
    @DisplayName("Empty map should have size 0 and be empty")
    void testEmptyMap() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.get("nonexistent"));
        assertFalse(map.containsKey("nonexistent"));
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

        // Remove non-existent key
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
    @DisplayName("Map should preserve insertion order")
    void testInsertionOrder() {
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);

        String mapString = map.toString();
        assertTrue(mapString.contains("first=1"));
        assertTrue(mapString.contains("second=2"));
        assertTrue(mapString.contains("third=3"));

        // Verify order is preserved (first should appear before second, etc.)
        int firstPos = mapString.indexOf("first=1");
        int secondPos = mapString.indexOf("second=2");
        int thirdPos = mapString.indexOf("third=3");

        assertTrue(firstPos < secondPos);
        assertTrue(secondPos < thirdPos);
    }

    @Test
    @DisplayName("Equals method should work correctly")
    void testEqualsMethod() {
        MyMap<String, Integer> map1 = new LinearSearchMap<>();
        MyMap<String, Integer> map2 = new LinearSearchMap<>();

        // Empty maps should be equal
        assertEquals(map1, map2);

        // Maps with same content should be equal
        map1.put("apple", 5);
        map1.put("banana", 3);
        map2.put("apple", 5);
        map2.put("banana", 3);
        assertEquals(map1, map2);

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
        MyMap<String, Integer> map1 = new LinearSearchMap<>();
        MyMap<String, Integer> map2 = new LinearSearchMap<>();

        map1.put("apple", 5);
        map1.put("banana", 3);
        map2.put("apple", 5);
        map2.put("banana", 3);

        assertEquals(map1, map2);
        assertEquals(map1.hashCode(), map2.hashCode());
    }

    @Test
    @DisplayName("Map should handle different generic types")
    void testDifferentGenericTypes() {
        MyMap<Integer, String> intStringMap = new LinearSearchMap<>();
        intStringMap.put(1, "one");
        intStringMap.put(2, "two");

        assertEquals("one", intStringMap.get(1));
        assertEquals("two", intStringMap.get(2));
        assertEquals(2, intStringMap.size());

        MyMap<String, Boolean> stringBoolMap = new LinearSearchMap<>();
        stringBoolMap.put("true", Boolean.TRUE);
        stringBoolMap.put("false", Boolean.FALSE);

        assertEquals(Boolean.TRUE, stringBoolMap.get("true"));
        assertEquals(Boolean.FALSE, stringBoolMap.get("false"));
    }

    @Test
    @DisplayName("Large dataset performance test")
    void testLargeDataset() {
        final int LARGE_SIZE = 1000;

        // Add many entries
        for (int i = 0; i < LARGE_SIZE; i++) {
            intMap.put(i, "value" + i);
        }

        assertEquals(LARGE_SIZE, intMap.size());

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

}