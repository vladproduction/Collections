package com.vladproduction.app02.list.linkedlist.mylinkedlist;

public class MainApp {
    public static void main(String[] args) {

        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(4);
        System.out.print("Original list: ");
        list.printList();

        list.remove(2);
        System.out.print("After removing 2 (first occurrence): ");
        list.printList();

        System.out.println("Size: " + list.size());
        System.out.println("Element at index 2: " + list.get(2));
        System.out.println("Middle element: " + list.getMiddle());

        list.reverse();
        System.out.print("After reversing: ");
        list.printList();

        list.removeDuplicates();
        System.out.print("After removing duplicates: ");
        list.printList();

        System.out.println("Contains loop? " + list.hasLoop());

    }
}
