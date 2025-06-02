package com.vladproduction.ch07_generic.p04_bounds;

import java.util.List;

public class LowerBoundedContainer {

    // Accepts Integer or any superclass of Number (like Object)
    public static void addNumber(List<? super Integer> list){
        list.add(100);   // ok: Integer is a subclass of Number
    }

    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

}
