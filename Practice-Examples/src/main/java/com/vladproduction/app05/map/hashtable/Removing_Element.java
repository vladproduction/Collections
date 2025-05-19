package com.vladproduction.app05.map.hashtable;


import java.util.Hashtable;
import java.util.Map;

public class Removing_Element {
    public static void main(String[] args) {

        /*      3. Removing Element: In order to remove an element from the Map, we can use
        the remove() method. This method takes the key value and removes the mapping for a
        key from this map if it is present in the map.*/

        // Initialization of a Hashtable
        Map<Integer, String> ht = new Hashtable<Integer, String>();

        // Inserting the Elements
        // using put method
        ht.put(1, "Inter");
        ht.put(2, "Milan");
        ht.put(3, "Italy");
        ht.put(4, "Italy");

        // Initial HashMap
        System.out.println("Initial map : " + ht);

        // Remove the map entry with key 4
        ht.remove(4);

        // Final Hashtable
        System.out.println("Updated map : " + ht);

    }
}
