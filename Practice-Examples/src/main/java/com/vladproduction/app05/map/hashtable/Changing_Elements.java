package com.vladproduction.app05.map.hashtable;


import java.util.Hashtable;

public class Changing_Elements {
    public static void main(String[] args) {

        /*      2. Changing Elements: After adding the elements if we wish to change the element,
        it can be done by again adding the element with the put() method. Since the elements in
         the hashtable are indexed using the keys, the value of the key can be changed by simply
         inserting the updated value for the key for which we wish to change.*/

        // Initialization of a Hashtable
        Hashtable<Integer, String> ht = new Hashtable<Integer, String>();

        // Inserting the Elements
        // using put method
        ht.put(1, "Player");
        ht.put(2, "--insert--");
        ht.put(3, "Year");

        // print initial map to the console
        System.out.println("Initial Map " + ht);

        // Update the value at key 2
        ht.put(2, "Of the");

        // print the updated map
        System.out.println("Updated Map " + ht);

    }
}
