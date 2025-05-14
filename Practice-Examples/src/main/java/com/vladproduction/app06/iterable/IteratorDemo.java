package com.vladproduction.app06.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list); //[1, 2, 3]

        //before Java 5
        Iterator<Integer> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            if(index == 1) iterator.remove(); //want to remove '2'
            index ++;
            System.out.println("i: " + index);
        }
        System.out.println(list); //[1, 3]

        //since Java 5
        /*for (Integer integer : list) {
//            list.remove(1); //ConcurrentModificationException
            System.out.println(integer);
        }*/
    }
}
