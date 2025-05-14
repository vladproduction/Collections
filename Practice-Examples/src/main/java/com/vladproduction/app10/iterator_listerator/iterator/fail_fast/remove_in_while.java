package com.vladproduction.app10.iterator_listerator.iterator.fail_fast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class remove_in_while {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println("list:start "+list);

        Iterator<String> iteratorList = list.iterator();
        while (iteratorList.hasNext()){
            String item = iteratorList.next();
            if("c".equals(item)){
                iteratorList.remove();
                //list.remove(2); //--> ConcurrentModificationException
            }
            System.out.println("item: "+item);
        }
        System.out.println("list:finish "+list);
    }
}
