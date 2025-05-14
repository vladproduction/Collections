package com.vladproduction.app03.set.hashset.app05;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main03 {
    public static void main(String[] args) {
        Set set1 = new HashSet();
        set1.add("A");
        set1.add("B");
        set1.add("C");
        set1.add("a");
        System.out.println("set1="+set1);
        System.out.println("------------------------");
        Set set2 = new LinkedHashSet();
        set2.add("A");
        set2.add("B");
        set2.add("C");
        set2.add("a");
        System.out.println("set2="+set2);
    }
}
