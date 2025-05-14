package com.vladproduction.app02.list.linkedlist.linkedlistpractice;

/**
 * Created by vladproduction on 25-Mar-24
 */

public class MyPracticeLinkedList_3_remove_duplicates {
    public static void main(String[] args) {
        /**Advanced Exercises:

         1)Remove duplicates from a linked list: Implement a function that removes duplicate elements from an
         unsorted linked list.*/

        class Node {
            int data;
            Node next;

            public Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        class LinkedList {
            Node head;

            public void insertAtEnd(int data){
                Node newNode = new Node(data);
                if(head == null){ // If the list is empty, make the new node the head
                    head = newNode;
                    return;
                }
                Node current = head;
                while (current.next != null){ // Traverse to the last node
                    current = current.next;
                }
                current.next = newNode; // Make the last node point to the new node
            }

            // Function to remove duplicates from an unsorted linked list
            public void removeDuplicates() {
                Node current = head;

                while (current != null) {
                    Node runner = current;
                    while (runner.next != null) {
                        if (runner.next.data == current.data) {
                            runner.next = runner.next.next; // Skip the duplicate node
                        } else {
                            runner = runner.next;
                        }
                    }
                    current = current.next;
                }
            }

            // Function to print the linked list
            public void printList() {
                Node current = head;
                while (current != null){
                    System.out.print(current.data + " ");
                    current = current.next;
                }
                System.out.println();
            }
        }

        LinkedList list = new LinkedList();
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtEnd(2);
        list.insertAtEnd(3);
        list.insertAtEnd(3);
        list.insertAtEnd(4);

        System.out.println("Original list:");
        list.printList(); // Output: 1 2 2 3 3 4

        list.removeDuplicates();

        System.out.println("List after removing duplicates:");
        list.printList(); // Output: 1 2 3 4


    }
}
