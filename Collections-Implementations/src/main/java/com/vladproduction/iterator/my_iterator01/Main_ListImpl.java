package com.vladproduction.iterator.my_iterator01;

import java.util.Iterator;

public class Main_ListImpl {
    public static void main(String[] args) {
        MyListIterator<String> list = new MyListIteratorImpl<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        Iterator<String> iteratorDefault = list.iterator();
        while (iteratorDefault.hasNext()){
            String value = iteratorDefault.next();
            System.out.println(value);
        }
        System.out.println("-------------------");
        for (String item: list) {
            System.out.println(item);
        }
    }
}
