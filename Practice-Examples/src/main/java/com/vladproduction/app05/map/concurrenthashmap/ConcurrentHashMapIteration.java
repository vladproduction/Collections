package com.vladproduction.app05.map.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapIteration {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        for (String key : map.keySet()) {
            System.out.println(key + " → " + map.get(key));
        }
    }
}
