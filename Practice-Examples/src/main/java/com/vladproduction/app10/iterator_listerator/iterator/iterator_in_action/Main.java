package com.vladproduction.app10.iterator_listerator.iterator.iterator_in_action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        main.run5(list);
    }

    private void run(List<String> list) {

        Iterator<String> stringIterator = list.iterator();
        while (stringIterator.hasNext()){
            String value = stringIterator.next();
            System.out.println(value);
        }
        System.out.println("-------------------------------");
        for (String item: list) {
            System.out.println(item);
        }
    }

    private void run2(List<String> list){
        //ConcurrentModificationException

        Iterator<String> stringIterator = list.iterator();
        while (stringIterator.hasNext()){
            String value = stringIterator.next();
            System.out.println(value);
        }
        System.out.println("-------------------------------");
        for (String item: list) {
            System.out.println(item);
            if("c".equals(item)){
                list.remove("c");
            }
        }
    }

    private void run3(List<String> list){
        //remove work as expected

        Iterator<String> stringIterator = list.iterator();
        while (stringIterator.hasNext()){
            String value = stringIterator.next();
            System.out.println(value);
            if("c".equals(value)){
                stringIterator.remove();
            }
        }
        System.out.println("-------------------------------");
        for (String item: list) {
            System.out.println(item);
        }
    }

    private void run4(List<String> list){
        //ConcurrentModificationException

        Iterator<String> stringIterator = list.iterator();
        while (stringIterator.hasNext()){
            String value = stringIterator.next();
            System.out.println(value);
            if("c".equals(value)){
                list.remove(2);
            }
        }
        System.out.println("-------------------------------");
        for (String item: list) {
            System.out.println(item);
        }
    }

    private void run5(List<String> list){
        //remove work if we return next element first

        Iterator<String> stringIterator = list.iterator();
        while (stringIterator.hasNext()){
            //stringIterator.remove(); // --> IllegalStateException
            //remove()-delete returned element, so we have to have 'value' by method next();
            String value = stringIterator.next();
            System.out.println(value);
            stringIterator.remove();
        }
        System.out.println("-------------------------------");
        System.out.println(list);
    }
}
