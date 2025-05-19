package com.vladproduction.app06.iterable_iterator_listIterator.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FailFastExampleForEach {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("Before remove: ");
        //iterating to see list values by iterator:
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer value = iterator.next();
            System.out.print(value + " ");
        }
        System.out.println();

        // a fail-fast case is occurring during for-each loop and modification: ConcurrentModificationException
        for (Integer i : list) {
            System.out.print(i + " ");
            if (i == 3) {
                list.remove(i);//ConcurrentModificationException
            }
        }

        //never happen while an exception occurs
        System.out.println("After remove: " + list);



    }
}
