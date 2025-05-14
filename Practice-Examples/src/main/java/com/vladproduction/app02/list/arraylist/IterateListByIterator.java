package com.vladproduction.app02.list.arraylist;

import java.util.Iterator;
import java.util.List;

public class IterateListByIterator {
    public static void main(String[] args) {

        List<String> list = List.of("A", "B", "C");

        System.out.println(list);

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

    }
}
