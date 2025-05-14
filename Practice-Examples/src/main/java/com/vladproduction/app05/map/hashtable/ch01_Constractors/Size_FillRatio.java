package com.vladproduction.app05.map.hashtable.ch01_Constractors;

import java.util.Hashtable;

/*      3. Hashtable(int size, float fillRatio): This version creates a hash table that
has an initial size specified by size and fill ratio specified by fillRatio. fill ratio:
Basically, it determines how full a hash table can be before it is resized upward and its
Value lies between 0.0 to 1.0.

        Hashtable<K, V> ht = new Hashtable<K, V>(int size, float fillRatio);*/
public class Size_FillRatio {
    public static void main(String[] args) {
        // No need to mention the
        // Generic type twice
        Hashtable<Integer, String> ht1
                = new Hashtable<>(4, 0.75f);

        // Initialization of a Hashtable
        // using Generics
        Hashtable<Integer, String> ht2
                = new Hashtable<Integer, String>(3, 0.5f);

        // Inserting the Elements
        // using put() method
        ht1.put(1, "one");
        ht1.put(2, "two");
        ht1.put(3, "three");

        ht2.put(4, "four");
        ht2.put(5, "five");
        ht2.put(6, "six");

        // Print mappings to the console
        System.out.println("Mappings of ht1 : " + ht1);
        System.out.println("Mappings of ht2 : " + ht2);

    }
}
