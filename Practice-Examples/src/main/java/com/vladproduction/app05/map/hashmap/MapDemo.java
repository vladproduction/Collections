package com.vladproduction.app05.map.hashmap;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    /**
     * 1) no duplicates (by key);
     * 2) no order guarantee;
     * 3) iterating by map.entrySet() with each pair (key, value)
     * */
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "First");
        map.put(2, "Second");
        map.put(3, "Third");
        map.put(4, "Third");
        System.out.println(map);

        map.put(3, "New-Third");
        System.out.println(map);

        System.out.println(map.get(1));
        System.out.println(map.get(10)); //null

        System.out.println("----------forEach---------");
        for (Map.Entry<Integer, String> integerStringEntry : map.entrySet()) {
            Integer key = integerStringEntry.getKey();
            String value = integerStringEntry.getValue();
            System.out.println(key + " = " + value);
        }

        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1, 100);
        map1.put(2, 200);
        map1.put(3, 200);

        for(Map.Entry<Integer, Integer> entry: map1.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }




    }
}
