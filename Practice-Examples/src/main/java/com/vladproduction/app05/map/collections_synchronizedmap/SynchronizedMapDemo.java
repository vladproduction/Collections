package com.vladproduction.app05.map.collections_synchronizedmap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SynchronizedMapDemo {
    public static void main(String[] args) {

        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());

        map.put("Java", 1);
        map.put("Python", 2);
        map.put("C++", 3);

        synchronized (map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " => " + entry.getValue());
            }
        }

        //Iteration and Removal Example
        synchronized (map) {
            Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Integer> entry = it.next();
                if (entry.getKey().startsWith("J")) {
                    it.remove();
                }
            }
        }

    }
}
