package com.vladproduction.app03.set.hashset.app04;

import java.util.*;

public class Main02 {
    public static void main(String[] args) {

        Set data = new HashSet(300);
        System.out.println("data="+data);
        data.add("A");
        boolean isEmpty = data.isEmpty();
        System.out.println("isEmpty="+isEmpty);
        int size = data.size();
        System.out.println("size="+size);
        System.out.println("data="+data);
        data.add("B");
        data.add("C");
        System.out.println("data="+data);
        data.add("A");
        System.out.println("data="+data);
        data.add("a");
        System.out.println("data="+data);
        System.out.println("---------iterator-------");
        Iterator itr = data.iterator();
        while(itr.hasNext()){
            Object item =  itr.next();
            System.out.println("item="+item);
        }
        System.out.println("---------for each-------");
        for(Object item: data){
            System.out.println("item="+item);
        }


        Collection myData = new ArrayList();
        Iterator itr2 = myData.iterator();
        while(itr2.hasNext()){
            itr2.next();
        }
    }
}
