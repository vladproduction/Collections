package com.vladproduction.app05.map.hashtable;


import java.util.Hashtable;
import java.util.Map;

public class Traversal_of_Hashtable {
    public static void main(String[] args) {

        /*  4. Traversal of a Hashtable: To iterate the table, we can make use of an advanced for loop.
        Below is the example of iterating a hashtable*/

        // Create an instance of Hashtable
        Hashtable<String, Integer> ht = new Hashtable<>();

        // Adding elements using put method
        ht.put("Player1", 10);
        ht.put("Player2", 30);
        ht.put("Player3", 20);

        // Iterating using enhanced for loop
        for (Map.Entry<String, Integer> e : ht.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());


    }
}
