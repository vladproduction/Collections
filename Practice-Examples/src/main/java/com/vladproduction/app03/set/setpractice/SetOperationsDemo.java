package com.vladproduction.app03.set.setpractice;

import java.util.HashSet;
import java.util.Set;

public class SetOperationsDemo {
    public static void main(String[] args) {

        Set<Integer> set1 = new HashSet<>();
        set1.add(0);
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        set2.add(7);


        //union operation for set
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println(union); //[0, 1, 2, 3, 4, 5, 6, 7]

        //intersection operation for set
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println(intersection); //[2, 3, 4]

        //difference operation for set
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println(difference); //[0, 1]


    }
}
