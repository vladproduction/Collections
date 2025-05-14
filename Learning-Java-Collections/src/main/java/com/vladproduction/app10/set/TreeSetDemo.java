package com.vladproduction.app10.set;

import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(500, 1500, 2500, 1000, 3000, 2000);

        NavigableSet<Integer> numberTree = new TreeSet<>(numbers);

        //**order** is asc by default
        numberTree
                .forEach(System.out::println);

        System.out.println("---descendingSet:---");
        //in case we need to have **order** by desc
        numberTree.descendingSet()
                .forEach(System.out::println);

        System.out.println("---headSet:---");
        //in case we need to have set that is lower of concrete integer:
        numberTree.headSet(1750)
                .forEach(System.out::println);

        System.out.println("---tailSet:---");
        //in case we need to have set that is higher of concrete integer:
        numberTree.tailSet(1750)
                .forEach(System.out::println);

        System.out.println("---subSet:---");
        //in case we need to have set that is between a concrete numbers:
        numberTree.subSet(1750, 2750)
                .forEach(System.out::println);

        System.out.println("---lower:---");
        //in case we need to have number just lower than a concrete number:
        System.out.println(numberTree.lower(750));

        System.out.println("---higher:---");
        //in case we need to have number just higher than a concrete number:
        System.out.println(numberTree.higher(750));

    }
}
