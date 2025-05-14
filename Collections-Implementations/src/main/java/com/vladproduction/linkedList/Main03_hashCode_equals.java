package com.vladproduction.linkedList;

public class Main03_hashCode_equals {
    public static void main(String[] args) {
        MyList list = new MyLinkedListImpl();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        MyList list2 = new MyLinkedListImpl();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.add("d");
        list2.add("e");

        System.out.println(list.hashCode()==list2.hashCode());


        System.out.println("--------------");
        System.out.println(list.equals(list2));
        System.out.println(list2.equals(list));

        System.out.println("--------------");
        list2.set(0,"A");
        System.out.println(list.hashCode()==list2.hashCode());
        System.out.println(list.equals(list2));
        System.out.println(list2.equals(list));




    }
}
