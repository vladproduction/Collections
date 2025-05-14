package com.vladproduction.app05.map.linkedhashmap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class LinkedHashMapDemo {

    public static void main(String[] args) {

        //no order guarantee (in case of input-output)
        Map<Integer, String> hashMap = new HashMap<>();

        //order guarantee (in case of input-output)
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();

        //pairs (key-value) sorting by keys for output order (natural order)
        Map<Integer, String> treeMap = new TreeMap<>();

//        testMap(hashMap);
//        testMap(linkedHashMap);
        testMap(treeMap);



    }

    //method testing our objects
    public static void testMap(Map<Integer, String> map){
        map.put(39, "Bob");
        map.put(16, "Ted");
        map.put(69, "Frank");
        map.put(0, "Tom");
        map.put(1500, "Bob");
        map.put(7, "Stacy");

        for (Map.Entry<Integer, String> integerStringEntry : map.entrySet()) {
            Integer key = integerStringEntry.getKey();
            String value = integerStringEntry.getValue();
            System.out.println(key + " : " + value);

        }

    }
}
