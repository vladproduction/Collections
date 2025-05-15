package com.vladproduction.app03.set.hashset.demohashset;

import java.util.HashSet;
import java.util.Iterator;

public class Main01 {

    /*
    Set:
    1) unique (no same items)
    2) no index access // set.get(i) - no such method; use iterator, and for each
    3) no order guarantee// you can add (a,b,c), but read (b,a,c)

    Details:
    2) What is unique ? equals() and hashCode() between its items;
    When HashSet for number, String - it is natural.

    When HashSet for Dog.
    What is  unique for Dog ? --> Dog hashCode() and equals();
     */
    public static void main(String[] args) {
        HashSet set = new HashSet();
        set.add("a");
        set.add("b");
        set.add("c");
        System.out.println(set);
        int size = set.size();
        boolean isEmpty = set.isEmpty();
        System.out.println("size="+size);
        System.out.println("isEmpty="+isEmpty);
        set.add("b");
        System.out.println(set);
        set.add("d");
        System.out.println(set);
        set.remove("c");
        System.out.println(set);
        set.clear();
        System.out.println(set);
        boolean contains = set.contains("A");
        System.out.println("contains A="+contains);
        System.out.println("----------------iterator-----------");
        //set has no index access
        //set.get(0);
        set.add("A");
        set.add("B");
        set.add("C");
        System.out.println("set="+set);
        Iterator iterator = set.iterator();
        //says set has more items
        //boolean hasNext = iterator.hasNext();
        //gives you next item
        //Object item = iterator.next();

        while(iterator.hasNext()){
            Object nextItem = iterator.next();
            System.out.println("nextItem="+nextItem);
        }
        System.out.println("---------for each----------------");
        for(Object item:set){
            System.out.println("item="+item);
        }
        System.out.println("---------for each with step----------------");
        int step=0;
        for(Object item:set){
            System.out.println(step+"; item="+item);
            step++;
        }
        System.out.println("------------------Dog example---------------------");
        HashSet dogSet = new HashSet();

        Dog dogA = new Dog();
        dogA.age=1;
        dogA.name="DogA";
        dogSet.add(dogA);

        Dog dogB = new Dog();
        dogB.age=2;
        dogB.name="DogB";
        dogSet.add(dogB);

        Dog dogC = new Dog();
        dogC.age=3;
        dogC.name="DogC";
        dogSet.add(dogC);

        System.out.println("dogSet.size()="+dogSet.size());
        System.out.println(dogSet);

        String text = "[";
        for(Object item: dogSet){
            text+=item.toString();
        }
        text+="]";
        System.out.println("text="+text);
        System.out.println("--------add another Dog");
        Dog dogD = new Dog();
        dogD.age=3;
        dogD.name="DogC";
        dogSet.add(dogD);
        System.out.println(dogSet);

        //what is equal in this set ?

    }

}
