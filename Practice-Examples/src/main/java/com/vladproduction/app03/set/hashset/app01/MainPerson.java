package com.vladproduction.app03.set.hashset.app01;

import java.util.HashSet;
import java.util.Set;

public class MainPerson {
    public static void main(String[] args) {
        Set set = new HashSet<>();
        Person p1 = new Person(1,"Person-1");
        Person p2 = new Person(1,"Person-1");
        Person p3 = new Person(1,"Person-1");

        set.add(p1);
        set.add(p2);
        set.add(p3);


        System.out.println("hash1 = " + p1.hashCode());
        System.out.println("hash2 = " + p2.hashCode());
        System.out.println("hash3 = " + p3.hashCode());


        System.out.println(set);

        System.out.println("-----hashCode() changed--------");

        Person p4 = p1;
        System.out.println("p4 == p1 = " + (p4 == p1));
        System.out.println("p4.equals(p1) = " + (p4.equals(p1)));
        System.out.println("p4.equals(p1) = " + (p4.equals("Test")));

        System.out.println("p2.equals(p1) = " + (p2.equals(p1)));













    }
}
