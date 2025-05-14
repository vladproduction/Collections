package com.vladproduction.arrayList;


/*
* working with methods:
    get();
    set(int index);
 */
public class Main02_get_set {
    public static void main(String[] args) {
        MyList list = new MyListImpl();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println(list);//[A,B,C,D,E]
        System.out.println(list.get(0));//A


        System.out.println("---------");
        list.set(0,"AA");
        System.out.println(list);//[AA,B,C,D,E]

    }
}
