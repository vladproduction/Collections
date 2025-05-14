package com.vladproduction.app03.set.hashset.app02;

import java.util.*;


public class MainSet {
    public static void main(String[] args) {
        MainSet mainSet = new MainSet();
        mainSet.run();
    }

    private void run() {
        Set<Integer> set = new HashSet<>(List.of(1,16,23,0,14,50,2,9,15,17));
        System.out.println(set + " size() = " + set.size());
        set.add(10);
        System.out.println(set + " size() = " + set.size());
        set.add(11);
        System.out.println(set + " size() = " + set.size());
        set.add(80);

        System.out.println(set + " size() = " + set.size());

        Set<Integer> set1 = new HashSet<>();/* achievements of HashSet:
            access to elements of HashSet based on constantly time, independently of size;
            doesn`t matter what method in action (add, remove)*/
        /*
        Candidates for new HashSet() are:
        HashSet()--> example 2 ('set1'); it has 16 baskets by default
        HashSet(Collection<? extends E> c)--> example 1 ('set'); it has 16 baskets by default as well
        HashSet(int initialCapacity, float loadFactor); if size come close to 16 loadFactor
            automatically change size (size*0.75)
        HashSet(int initialCapacity)
        HashSet(int initialCapacity, float loadFactor, boolean dummy)*/

    }
}
