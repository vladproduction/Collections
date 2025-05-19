package com.vladproduction.app06.iterable_iterator_listIterator.listIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorDemo {
    public static void main(String[] args) {

        List<String> listString = new ArrayList();
        listString.add("a");
        listString.add("b");
        listString.add("c");
        listString.add("d");
        ListIterator<String> stringListIterator = listString.listIterator();
        while (stringListIterator.hasNext()){
            String item = stringListIterator.next();
            System.out.println(item);
        }

        System.out.println("-----------------");
        ListIterator<String> stringListIterator2 = listString.listIterator(listString.size());
        while (stringListIterator2.hasPrevious()){
            String item = stringListIterator2.previous();
            System.out.println(item);
        }

        System.out.println("-----------------");
        ListIterator<String> stringListIterator3 = listString.listIterator();
        while (stringListIterator3.hasNext()){
            String item = stringListIterator3.next();
            System.out.println(item);
            if("a".equals(item)||"b".equals(item)){
                stringListIterator3.add("add new text");
            }
        }
        System.out.println(listString);

        System.out.println("-----------------");
        //ListIterator<String> stringListIterator3 = listString.listIterator();
        while (stringListIterator3.hasPrevious()){
            String item = stringListIterator3.previous();
            System.out.println(item);
//            if("a".equals(item)||"b".equals(item)){
//                stringListIterator3.add("add new text");
//            }
        }

        System.out.println("-----------------");
        //ListIterator<String> stringListIterator3 = listString.listIterator();
        while (stringListIterator3.hasNext()){
            String item = stringListIterator3.next();
            System.out.println(item);
            if("add new text".equals(item)){
                stringListIterator3.remove();
            }
        }
        System.out.println(listString);

    }
}
