package com.vladproduction.arrayList;
    /*
* working with methods:
*   isEmpty();
*   size();
*   add(Object o);
*
* how constructor creating:
*   -capacity in bounds (5)
*   -capacity out of bounds, so if bench settings start
*
* modify toString, so it dsn`t print "null"   */



public class Main02_add_size_isEmpty {
    public static void main(String[] args) {
        MyList list = new MyListImpl();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println(list.isEmpty());
        System.out.println(list.size());
        System.out.println(list);
//        false
//        5
//        [A,B,C,D,E]

        System.out.println("------");
        list.add("F");
        System.out.println(list.isEmpty());
        System.out.println(list.size());
        System.out.println(list);
//        false
//        6
//        [A,B,C,D,E,F]
    }
}
