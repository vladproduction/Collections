package com.vladproduction.app02.list.arraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArrayList03 {
    public static void main(String[] args) {
        ArrayList03 arrayList03 = new ArrayList03();
        arrayList03.run();
    }

    private void run() {
        System.out.println("-------create list----------");

        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("four");
        list.add(2,"three");
        System.out.println(list); //[one, two, three, four]
        System.out.println("------for each-----------");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("----itco-------------");
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            String next =  iterator.next();
            System.out.println(next);
        }
        System.out.println("----itco (listIterator)-------------"); //iterate both sides
        for (Iterator<String> iterator = list.listIterator(); iterator.hasNext(); ) {
            String next =  iterator.next();
            System.out.println(next);
        }
        System.out.println("----System.out::println-------------");
        list.forEach(System.out::println);

        System.out.println("-------------List.of()----------------------");
        List<Integer> list1 = new ArrayList<>(List.of(1,2,4,22,7,7,7,5,6,7,8,9));
        System.out.println("list1(start) = "+list1);

//        for (int i = 0; i < list1.size(); i++) {
//            if(list1.get(i) % 2 != 0){
//                list1.remove(i); //not correct work
//            }
//        }
//        System.out.println(list1);

//        for (int i = list1.size()-1; i >= 0 ;i--) { //going from the end of list to begin
//             if (list1.get(i) % 2 != 0){
//                 list1.remove(i); // correct work
//             }
//        }
        //alternative way:
//        for (Iterator<Integer> iterator = list1.iterator(); iterator.hasNext(); ) {
//            Integer next =  iterator.next();
//            if(next % 2 != 0){
//                iterator.remove();
//            }
//        }
        //alternative way2 (modern with lambda):
        list1.removeIf(x -> x % 2 != 0);
        System.out.println("list1(if->remove) = "+list1); //[2, 4, 22, 6, 8]

        System.out.println("-----------list1.toArray()---------------");
//        var arr = list1.toArray(); //can create array from list elements
//        System.out.println(arr);//[Ljava.lang.Object;@7b23ec81

//        Integer [] list1Arr = (Integer[]) list1.toArray();
//        System.out.println(list1Arr);//ClassCastException

//        Object [] list1Array = list1.toArray();
//        System.out.println(list1Array);//[Ljava.lang.Object;@6acbcfc0
        // now we do it right:
//        Integer [] arr = new Integer[list1.size()];
//        list1.toArray(arr);
//        for (Integer item : arr) {
//            System.out.println(item);
//        }
        //if we are creating through
//        Integer [] arr = new Integer[5]; //we have to know exactly size of list
//        list1.toArray(arr);             //otherwise if size less than list are, elements stands as null
//        for (Integer item : arr) {
//            System.out.println(item);
//        }

        //but right way could be so:
        System.out.println("-----list1.toArray(new Integer[0])------");
        Integer [] arr = list1.toArray(new Integer[0]); //
        //here creating new [] (inside the method toArray) by type Integer size 0, then set elements in
        for (Integer item : arr) {
            System.out.println(item);
        }
        System.out.println("---subList----");
//        List<Integer> list2 = list1.subList(1, 10); //IndexOutOfBoundsException: toIndex = 10
//        System.out.println(list2);
//        List<Integer> list2 = list1.subList(1, 5);//[4, 22, 6, 8]
//        System.out.println(list2);
        List<Integer> list2 = list1.subList(1, 3);//[4, 22]
        list2.set(0, 15);
        System.out.println(list2);//[15, 22]
        System.out.println(list1);//[2, 15, 22, 6, 8]
        /* it happens because subList (list2) creating his data from main list(list1),
        * but as list view (from el, to el exclusive); and it has his oun indexes;
        * elements changeable till the main list1 as well, we see it by set '15' on 0 index*/
        System.out.println("-----list3.add(0, 15);-------");
        List<Integer> list3 = list1.subList(1, 3);//[4, 22]
        list3.add(0, 15);
        System.out.println(list3);//[15, 15, 22]
        System.out.println(list1);//[2, 15, 15, 22, 6, 8]
        /* element were added by index, and the others are moved to the right positions indexes */

    }
}
