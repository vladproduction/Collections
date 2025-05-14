package com.vladproduction.linkedList;

public class Main03_clear_remove {
    public static void main(String[] args) {
        MyList list = new MyLinkedListImpl();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        System.out.println("toString: "+list);
        System.out.println("size: "+list.size());
        list.clear();
        System.out.println("toString: "+list);
        System.out.println("size: "+list.size());


        System.out.println("------create list: -----------");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        System.out.println("toString: "+list);
        System.out.println("size: "+list.size());

        System.out.println("------remove first element: -----------");
        list.remove(0);
        System.out.println("toString: "+list);
        System.out.println("size: "+list.size());

        System.out.println("------remove last element: -----------");
        list.remove(5);
        System.out.println("toString: "+list);
        System.out.println("size: "+list.size());

        System.out.println("------remove middle element: -----------");
        list.remove(2);
        System.out.println("toString: "+list);
        System.out.println("size: "+list.size());

        //-------------------------
        System.out.println("===========");
        System.out.println("example with tricky remove: ");
        MyList list3 = new MyLinkedListImpl();
        list3.add("a");
        list3.add("b");
        list3.add("c");

        System.out.println("toString: "+list3);
        System.out.println("size: "+list3.size());

        list3.remove(0);
        list3.remove(1);

        System.out.println("toString: "+list3); //[b]
        System.out.println("size: "+list3.size());// 1
    }
}
