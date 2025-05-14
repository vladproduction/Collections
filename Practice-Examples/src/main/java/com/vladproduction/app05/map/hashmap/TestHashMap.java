package com.vladproduction.app05.map.hashmap;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();

        //put:
        map.put("Bob", 25);
        map.put("Frank", 28);
        map.put("Make", 35);
        map.put("Tom", 55);
        map.put("Katy", 15);

        //iterating:
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            String key = stringIntegerEntry.getKey();
            Integer value = stringIntegerEntry.getValue();
            System.out.println(key + " : " + value);
        }

        //get:
        Integer index_Bob = map.get("Bob");
        System.out.println("index_Bob = " + index_Bob);

        //---------------------------------
        System.out.println("------phoneBook----------");
        HashMap<String, Integer> phoneBook = new HashMap<>();

        phoneBook.put("Alice", 123456);
        phoneBook.put("Bob", 654321);
        phoneBook.put("Charlie", 789012);

        int hash_Alice = phoneBook.get("Alice").hashCode();
        int hash_Bob = phoneBook.get("Bob").hashCode();
        int hash_Charlie = phoneBook.get("Charlie").hashCode();
        System.out.println("hash_Alice = " + hash_Alice);
        System.out.println("hash_Bob = " + hash_Bob);
        System.out.println("hash_Charlie = " + hash_Charlie);


        // In this example:
        //  * Alice name hashes to index 0
        //  * Bob name hashes to index 0 (collision!)
        //  * Charlie's name hashes to index 1

        // HashMap stores entries based on hash codes:
        //  * bucket[0]:  (Bob, 654321) -> (Alice, 123456)  // Bob first as it was added later
        //  * bucket[1]:  (Charlie, 789012)
    }
}
