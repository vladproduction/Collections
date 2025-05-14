package com.vladproduction.app03.set.hashset.app01;

import java.util.*;

public class SetSimpleHashSet {
    public static void main(String[] args) {

        Set data = new HashSet();
        System.out.println("data="+data);
        data.add("A");
        data.add("B");
        data.add("C");
        System.out.println("data="+data);
        data.add("D");
        boolean isEmpty = data.isEmpty();
        System.out.println("isEmpty = " + isEmpty);
        int dataSize = data.size();
        System.out.println("dataSize = " + dataSize);
        System.out.println("data="+data);

        System.out.println("---------iterator-------");
        Iterator itr = data.iterator();
        while(itr.hasNext()){
            Object item =  itr.next();
            System.out.println("item="+item);
        }

        System.out.println("---------for each-------");
        for (Object item: data) {
            System.out.println(item);
        }

        Collection myData = new ArrayList();
        Iterator itr2 = myData.iterator();
        while(itr2.hasNext()){
            itr2.next();
        }

    }
}
