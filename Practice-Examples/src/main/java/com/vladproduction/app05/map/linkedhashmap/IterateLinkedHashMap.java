package com.vladproduction.app05.map.linkedhashmap;

import java.util.LinkedHashMap;
import java.util.Map;

public class IterateLinkedHashMap {
    public static void main(String[] args) {

        Map<String, Integer> map = new LinkedHashMap<>();

        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Orange", 30);
        map.put("Mango", 40);

        System.out.println("1. Using entrySet (for-each):");
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("\n2. Using keySet (for-each):");
        for (String s : map.keySet()) {
            System.out.println(s + " : " + map.get(s));
        }

        System.out.println("\n3. Using values() only:");
        for (Integer value : map.values()) {
            System.out.println("Value: " + value);
        }

        System.out.println("\n4. Using Iterator over entrySet:");
        var iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("\n5. Using forEach + lambda (Java 8+):");
        map.forEach((key, value) -> {
            String name = Thread.currentThread().getName();
            System.out.println("Execution by thread: " + name + " [" + key + " : " + value + "]");
        });

    }
}
