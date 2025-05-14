package com.vladproduction.app03.set.hashset.app03;

import java.util.HashSet;
import java.util.Set;

public class Main01 {
    public static void main(String[] args) {

        Set set = new HashSet();
        Person p1 = new Person(1, "Name1");
        Person p2 = new Person(1, "Name1");
        Person p3 = new Person(1, "Name1");

        set.add(p1);
        set.add(p2);
        set.add(p3);

        System.out.println("hash1="+p1.hashCode());
        System.out.println("hash2="+p2.hashCode());
        System.out.println("hash3="+p3.hashCode());

        System.out.println(set);

        Person p4 = p1;
        System.out.println("p4==p1 --> "+(p4==p1));
        System.out.println("p4.equals(p1) --> "+(p4.equals(p1)));
        System.out.println("p4.equals('TEST') --> "+(p4.equals("TEST")));

        System.out.println("p2.equals(p1) --> "+(p2.equals(p1)));

    }
}
