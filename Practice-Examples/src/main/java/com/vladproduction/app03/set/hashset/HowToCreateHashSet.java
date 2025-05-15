package com.vladproduction.app03.set.hashset;

import java.util.HashSet;
import java.util.Set;

public class HowToCreateHashSet {
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");

        System.out.println("Set: " + set);

    }
}
