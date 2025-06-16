package com.vladproduction.collectionsframework.ch04_maps;

import java.util.*;

public class MainUtilityTesting {

    public static void main(String[] args) {
        System.out.println("=== MapUtilities Demo ===\n");

        // Setup demo maps and collections
        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        map1.put("C", 3);

        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put("B", 20);
        map2.put("D", 4);
        map2.put("E", 5);

        List<String> fruits = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

        // 1. getOrDefault
        System.out.println("1) getOrDefault:");
        System.out.println("Key 'A': " + MapUtilities.getOrDefault(map1, "A", -1));
        System.out.println("Key 'Z' (missing): " + MapUtilities.getOrDefault(map1, "Z", -1));
        System.out.println();

        // 2. mergeMaps with sum as conflict resolver
        System.out.println("2) mergeMaps (sum conflict resolver):");
        Map<String, Integer> merged = MapUtilities.mergeMaps(map1, map2, Integer::sum);
        System.out.println(merged);
        System.out.println();

        // 3. filter (values > 2)
        System.out.println("3) filter (values > 2):");
        Map<String, Integer> filtered = MapUtilities.filter(merged, e -> e.getValue() > 2);
        System.out.println(filtered);
        System.out.println();

        // 4. mapValues (multiply by 10)
        System.out.println("4) mapValues (*10):");
        Map<String, Integer> mappedValues = MapUtilities.mapValues(filtered, v -> v * 10);
        System.out.println(mappedValues);
        System.out.println();

        // 5. getAsType
        System.out.println("5) getAsType:");
        Object someObj = "hello";
        Map<String, Object> mixedMap = new LinkedHashMap<>();
        mixedMap.put("key1", someObj);
        mixedMap.put("key2", 123);
        String casted = MapUtilities.getAsType(mixedMap, "key1", String.class);
        Integer castedInt = MapUtilities.getAsType(mixedMap, "key2", Integer.class);
        Double castedFail = MapUtilities.getAsType(mixedMap, "key2", Double.class);
        System.out.println("Casted String: " + casted);
        System.out.println("Casted Integer: " + castedInt);
        System.out.println("Failed cast (should be null): " + castedFail);
        System.out.println();

        // 6. immutableCopy
        System.out.println("6) immutableCopy:");
        Map<String, Integer> immutable = MapUtilities.immutableCopy(map1);
        System.out.println(immutable);
        try {
            immutable.put("Z", 999);
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught expected exception on modification attempt");
        }
        System.out.println();

        // 7. invert
        System.out.println("7) invert:");
        Map<Integer, String> inverted = MapUtilities.invert(map1);
        System.out.println(inverted);
        System.out.println();

        // 8. fromCollection
        System.out.println("8) fromCollection:");
        Map<String, String> fromColl = MapUtilities.fromCollection(fruits, s -> s.substring(0, 1));
        System.out.println(fromColl);
        System.out.println();

        // 9. frequencyMap
        System.out.println("9) frequencyMap:");
        Map<String, Integer> freqMap = MapUtilities.frequencyMap(fruits);
        System.out.println(freqMap);
        System.out.println();

        // 10. safeClear
        System.out.println("10) safeClear:");
        Map<String, Integer> toClear = new LinkedHashMap<>(map1);
        System.out.println("Before clear: " + toClear);
        MapUtilities.safeClear(toClear);
        System.out.println("After clear: " + toClear);
        MapUtilities.safeClear(null);  // Should be safe, no error
        System.out.println("Safe clear on null map didn't crash.");
        System.out.println();

        // 11. containsEntry
        System.out.println("11) containsEntry:");
        System.out.println("Contains (A,1): " + MapUtilities.containsEntry(map1, "A", 1));
        System.out.println("Contains (B,3): " + MapUtilities.containsEntry(map1, "B", 3));
        System.out.println("Contains (Z,100): " + MapUtilities.containsEntry(map1, "Z", 100));
    }
}
