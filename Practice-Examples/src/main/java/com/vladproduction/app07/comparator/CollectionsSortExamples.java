package com.vladproduction.app07.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsSortExamples {
    public static void main(String[] args) {

        List<String> animals = new ArrayList<>();
        animals.add("cat");
        animals.add("dog");
        animals.add("bird");
        animals.add("frog");
        animals.add("tiger");
        animals.add("turtle");

        Collections.sort(animals);
        System.out.println(animals); //[bird, cat, dog, frog, tiger, turtle]

        List<Integer> numbs = new ArrayList<>();
        numbs.add(23);
        numbs.add(20);
        numbs.add(100);
        numbs.add(15);
        numbs.add(0);

        Collections.sort(numbs);
        System.out.println(numbs); //[0, 15, 20, 23, 100]

    }
}
