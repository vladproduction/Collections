package com.vladproduction.app02.list.linkedlist.mylinkedlist2;


public class Test_MyLinkedList_Impl {
    public static void main(String[] args) {
        LinkedListImplementation list = new LinkedListImplementation();
        list.add(1);
        list.add(3);
        list.add(5);

        System.out.println(list);

//        System.out.println(list.get(1));
//        list.remove(1);
//        System.out.println(list);
//        list.remove(1);
//        System.out.println(list);

        list.remove(0);
        System.out.println(list);
    }
}
