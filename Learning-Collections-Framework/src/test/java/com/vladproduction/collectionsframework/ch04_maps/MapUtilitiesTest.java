package com.vladproduction.collectionsframework.ch04_maps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MapUtilitiesTest {

    private Map<String, Integer> map1;
    private Map<String, Integer> map2;
    private List<String> fruits;
    private Map<String, Object> mixedMap;

    @BeforeEach
    void setUp() {
        map1 = new LinkedHashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        map1.put("C", 3);

        map2 = new LinkedHashMap<>();
        map2.put("B", 20);
        map2.put("D", 4);
        map2.put("E", 5);

        fruits = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

        mixedMap = new LinkedHashMap<>();
        mixedMap.put("key1", "hello");
        mixedMap.put("key2", 123);
    }

    @Test
    void testGetOrDefault() {
        assertEquals(1, MapUtilities.getOrDefault(map1, "A", -1));
        assertEquals(-1, MapUtilities.getOrDefault(map1, "Z", -1));
    }

    @Test
    void testMergeMaps() {
        Map<String, Integer> merged = MapUtilities.mergeMaps(map1, map2, Integer::sum);
        assertEquals(1, merged.get("A"));
        assertEquals(22, merged.get("B")); // 2 + 20
        assertEquals(3, merged.get("C"));
        assertEquals(4, merged.get("D"));
        assertEquals(5, merged.get("E"));
    }

    @Test
    void testFilter() {
        Map<String, Integer> merged = MapUtilities.mergeMaps(map1, map2, Integer::sum);
        Map<String, Integer> filtered = MapUtilities.filter(merged, e -> e.getValue() > 2);
        assertFalse(filtered.containsKey("A"));
        assertTrue(filtered.containsKey("B"));
        assertTrue(filtered.containsKey("C"));
        assertTrue(filtered.containsKey("D"));
        assertTrue(filtered.containsKey("E"));
    }

    @Test
    void testMapValues() {
        Map<String, Integer> mapped = MapUtilities.mapValues(map1, v -> v * 10);
        assertEquals(10, mapped.get("A"));
        assertEquals(20, mapped.get("B"));
        assertEquals(30, mapped.get("C"));
    }

    @Test
    void testGetAsType() {
        assertEquals("hello", MapUtilities.getAsType(mixedMap, "key1", String.class));
        assertEquals(123, MapUtilities.getAsType(mixedMap, "key2", Integer.class));
        assertNull(MapUtilities.getAsType(mixedMap, "key2", Double.class));
        assertNull(MapUtilities.getAsType(mixedMap, "missing", String.class));
    }

    @Test
    void testImmutableCopy() {
        Map<String, Integer> immutable = MapUtilities.immutableCopy(map1);
        assertEquals(map1, immutable);
        assertThrows(UnsupportedOperationException.class, () -> immutable.put("Z", 999));
    }

    @Test
    void testInvert() {
        Map<Integer, String> inverted = MapUtilities.invert(map1);
        assertEquals("A", inverted.get(1));
        assertEquals("B", inverted.get(2));
        assertEquals("C", inverted.get(3));
    }

    @Test
    void testFromCollection() {
        Map<String, String> fromColl = MapUtilities.fromCollection(fruits, s -> s.substring(0, 1));
        assertEquals("apple", fromColl.get("a"));
        assertEquals("banana", fromColl.get("b"));
        assertEquals("orange", fromColl.get("o"));
    }

    @Test
    void testFrequencyMap() {
        Map<String, Integer> freqMap = MapUtilities.frequencyMap(fruits);
        assertEquals(3, freqMap.get("apple"));
        assertEquals(2, freqMap.get("banana"));
        assertEquals(1, freqMap.get("orange"));
    }

    @Test
    void testSafeClear() {
        Map<String, Integer> toClear = new HashMap<>(map1);
        MapUtilities.safeClear(toClear);
        assertTrue(toClear.isEmpty());
        // Should not throw on null
        assertDoesNotThrow(() -> MapUtilities.safeClear(null));
    }

    @Test
    void testContainsEntry() {
        assertTrue(MapUtilities.containsEntry(map1, "A", 1));
        assertFalse(MapUtilities.containsEntry(map1, "B", 3));
        assertFalse(MapUtilities.containsEntry(map1, "Z", 100));
    }
}