package com.vladproduction.app05.map.hashtable;

import java.util.Hashtable;
import java.util.Map;

public class HashtableIteration {
    public static void main(String[] args) {
        Hashtable<String, Integer> table = new Hashtable<>();
        table.put("A", 10);
        table.put("B", 20);
        table.put("C", 30);

        for (Map.Entry<String, Integer> entry : table.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}
