package com.vladproduction.app05.map.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapRemove {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> users = new ConcurrentHashMap<>();
        users.put("u1", "Alice");
        users.put("u2", "Bob");

        users.remove("u2");

        System.out.println("Contains u2? " + users.containsKey("u2")); // false

        //also available to remove conditionally
        users.remove("u1", "Alice");
        System.out.println("Contains u1? " + users.containsKey("u1")); // false
    }
}
