package com.vladproduction.app05.map.hashmap;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main01 {

    public static void main(String[] args) {
        //Collection collection = new HashSet<>();
        Collection collection = new LinkedList();
        collection.size();
        collection.isEmpty();
        collection.iterator();
        collection.add("A");


        Map map = new HashMap();
        boolean isEmpty = map.isEmpty();
        int size = map.size();
        // set.add("A") - void
        Object oldValue = map.put("a", "Test-a");
        System.out.println("oldValue="+oldValue);
        System.out.println("map="+map);
        //map.put(1, true);
        System.out.println("-----------------------");
        oldValue = map.put("a", "Test2");
        System.out.println("oldValue="+oldValue);
        System.out.println("map="+map);

        map.put("a", "Test3");
    }

}
