package com.vladproduction.app06.iterable_iterator_listIterator.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FailFastExampleIterator {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        System.out.println("Before remove: " + list);

        Iterator<String> stringIterator = list.iterator();
        while (stringIterator.hasNext()){
            String value = stringIterator.next();
            if(value.equals("c")){
//                list.remove(value); //ConcurrentModificationException
                stringIterator.remove(); //to avoid the exception need to remove from iterator not form the collection
            }
        }

        System.out.println("After remove" + list);


    }
}

