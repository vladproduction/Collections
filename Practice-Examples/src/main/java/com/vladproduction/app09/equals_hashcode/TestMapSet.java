package com.vladproduction.app09.equals_hashcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestMapSet {
    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        map.put(1, "One");
        map.put(1, "Other");

        set.add(1);
        set.add(1);

        System.out.println(map);
        System.out.println(set);

        /*  {1=Other}
            [1]
        */
    }
}
