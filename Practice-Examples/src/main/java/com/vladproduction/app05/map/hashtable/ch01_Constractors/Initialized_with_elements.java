package com.vladproduction.app05.map.hashtable.ch01_Constractors;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/*      4. Hashtable(Map<? extends K,? extends V> m): This creates a hash table that is
initialized with the elements in m.

        Hashtable<K, V> ht = new Hashtable<K, V>(Map m);*/
public class Initialized_with_elements {
    public static void main(String[] args) {
        // No need to mention the
        // Generic type twice
        Map<Integer, String> hm = new HashMap<>();

        // Inserting the Elements
        // using put() method
        hm.put(1, "one");
        hm.put(2, "two");
        hm.put(3, "three");

        // Initialization of a Hashtable
        // using Generics
        Hashtable<Integer, String> ht2
                = new Hashtable<Integer, String>(hm);

        // Print mappings to the console

        System.out.println("Mappings of ht2 : " + ht2);

    }
}
