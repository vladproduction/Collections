package com.vladproduction.app03.generics;

import com.vladproduction.app01.demo.Room;

import java.util.ArrayList;
import java.util.Collection;

public class MultiTypesContains {
    public static void main(String[] args) {

        //without generic could hold any type
        Collection c = new ArrayList();
        c.add("Cambridge");
        c.add(1L);
        c.add(new Room("Cambridge", "Suite", 5, 125));

        c.stream().forEach(elem -> System.out.println(elem.getClass()));
        //class java.lang.String
        //class java.lang.Long


        Collection c2 = new ArrayList();
        c2.add(new Room("Cambridge", "Suite", 5, 125));

    }
}
