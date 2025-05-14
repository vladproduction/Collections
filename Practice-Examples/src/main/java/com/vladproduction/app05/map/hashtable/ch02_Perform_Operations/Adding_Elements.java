package com.vladproduction.app05.map.hashtable.ch02_Perform_Operations;


/*  1. Adding Elements: In order to add an element to the hashtable, we can use the put() method.
    However, the insertion order is not retained in the hashtable. Internally, for every element,
    a separate hash is generated and the elements are indexed based on this hash to make
    it more efficient.   */

import java.util.Hashtable;

public class Adding_Elements {
    public static void main(String[] args) {
        // No need to mention the
        // Generic type twice
        Hashtable<Integer, String> ht1 = new Hashtable<>();

        // Initialization of a Hashtable
        // using Generics
        Hashtable<Integer, String> ht2
                = new Hashtable<Integer, String>();

        // Inserting the Elements
        // using put() method
        ht1.put(1, "Geeks");
        ht1.put(2, "For");
        ht1.put(3, "Geeks");

        ht2.put(1, "Geeks");
        ht2.put(2, "For");
        ht2.put(3, "Geeks");

        // Print mappings to the console
        System.out.println("Mappings of ht1 : " + ht1);
        System.out.println("Mappings of ht2 : " + ht2);

    }
}
