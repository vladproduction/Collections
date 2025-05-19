package com.vladproduction.app05.map.hashtable;

import java.util.Hashtable;

/**
 * Hashtable is a legacy class introduced in Java 1.0 that implements a synchronized hash table.
 * It was part of the original Java Collections, later retrofitted to implement the Map interface.
 * */
public class HashtableBasics {
    public static void main(String[] args) {
        Hashtable<String, String> capitals = new Hashtable<>();
        
        capitals.put("USA", "Washington");
        capitals.put("France", "Paris");
        capitals.put("Germany", "Berlin");

        System.out.println("Capital of France: " + capitals.get("France"));
    }
}
