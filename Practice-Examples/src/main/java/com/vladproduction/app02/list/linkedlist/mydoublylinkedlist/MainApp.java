package com.vladproduction.app02.list.linkedlist.mydoublylinkedlist;

public class MainApp {
    public static void main(String[] args) {

        MyDoublyLinkedList<String> dll = new MyDoublyLinkedList<>();

        dll.add("A");
        dll.add("B");
        dll.add("C");
        dll.addFirst("Start");
        dll.add("D");

        System.out.print("Forward: ");
        dll.printForward();

        System.out.print("Backward: ");
        dll.printBackward();

        dll.remove("B");
        System.out.print("After removing 'B': ");
        dll.printForward();

        System.out.println("Size: " + dll.size());
        System.out.println("Element at index 2: " + dll.get(2));

        // Using forward iterator
        System.out.print("Iterating forward: ");
        var it = dll.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        // Using reverse iterator
        System.out.print("\nIterating backward: ");
        var revIt = dll.reverseIterator();
        while (revIt.hasNext()) {
            System.out.print(revIt.next() + " ");
        }

        System.out.println("\nClearing list...");
        dll.clear();
        System.out.println("Is empty? " + dll.isEmpty());

    }
}
