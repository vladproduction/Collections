package com.vladproduction.app10.iterator_listerator.iterator.fail_fast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class remove_in_for_each {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println("list:start "+list);

        Iterator<String> valueList = list.iterator();
        for (Iterator<String> it = valueList; it.hasNext(); ) {
            String value = it.next();
            if("b".equals(value)){
                valueList.remove();
                //list.remove(2);     //--> ConcurrentModificationException
            }
        }
        System.out.println("list:finish "+list);
    }
}
