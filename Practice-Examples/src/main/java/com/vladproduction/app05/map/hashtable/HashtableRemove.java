package com.vladproduction.app05.map.hashtable;

import java.util.Hashtable;

public class HashtableRemove {
    public static void main(String[] args) {
        Hashtable<String, String> data = new Hashtable<>();
        data.put("user1", "John");
        data.put("user2", "Jane");

        data.remove("user1");

        System.out.println(data.containsKey("user1")); // false
    }
}
