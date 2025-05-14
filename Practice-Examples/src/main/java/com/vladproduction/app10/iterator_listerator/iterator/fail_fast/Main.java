package com.vladproduction.app10.iterator_listerator.iterator.fail_fast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        main.run4(list);
    }

    private void run(List<String> list) {
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()){
            String item = listIterator.next();
            if("c".equals(item)){
                listIterator.remove();
            }
            System.out.println(item);
        }
        System.out.println("----------------");
        for (String element: list) {
            if("c".equals(element)){
                list.remove(2);
            }
            System.out.println(element);
        }
    }

    //ConcurrentModificationException
    private void run2(List<String> list) {
        Iterator<String> iteratorList = list.iterator();
        while (iteratorList.hasNext()){
            String item = iteratorList.next();
            if("b".equals(item)){
               list.remove(2);
            }
            System.out.println(item);
        }
        System.out.println("----------------");
        for (String element: list) {
            if("c".equals(element)){
                list.remove(element);//it`s ConcurrentModificationException
                //versions are not same; iteratorVersion= 4 ;version=5
                System.out.println(element);
            }
        }
        System.out.println(list);
    }

    //ConcurrentModificationException
    private void run3(List<String> list) {
        Iterator<String> iteratorList = list.iterator();
        while (iteratorList.hasNext()){
            String item = iteratorList.next();
            System.out.println(item);
            if("b".equals(item)){
                list.remove(1);
            }
        }
        System.out.println(list);

    }

    //ConcurrentModificationException
    private void run4(List<String> list) {
        for (String item: list) {
            System.out.println(item);
            if ("b".equals(item)){
                list.remove(1);
            }
        }
        System.out.println(list);
    }
}
