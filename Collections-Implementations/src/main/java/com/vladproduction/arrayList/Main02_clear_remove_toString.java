package com.vladproduction.arrayList;

/*
* working with methods:
    clear();
    remove(int index);
 */
public class Main02_clear_remove_toString {
    public static void main(String[] args) {
        MyList list = new MyListImpl();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println(list);
        list.clear();
        System.out.println(list);
        System.out.println(list.size());
        System.out.println("-----------");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println(list);
        System.out.println(list.size());
        list.remove(0);
        System.out.println(list);
        System.out.println(list.size());
    }
}
