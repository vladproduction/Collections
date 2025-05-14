package com.vladproduction.app02.list.linkedlist.linkedlistpractice;

/**
 * Created by vladproduction on 25-Mar-24
 */

public class MyPracticeLinkedList_1 {
    public static void main(String[] args) {

        class Node {
            int data;
            Node next;

            public Node(int data) {
                this.data = data;
                this.next = null;
            }
        }
        class MyLinkedListLocal {
            Node head;

            //3-exercise:
            public void deleting(int value){
                if(head == null){ //Handle case if list is empty
                    return;
                }
                if(head.data == value){ // Check if the head node needs to be deleted
                    head = head.next; // Simply update head to point to the next node
                    return;
                }
                // Traverse the list to find the node to be deleted
                Node current = head; //start
                while (current.next != null){
                    if(current.next.data == value){ //if it`s found
                        current.next = current.next.next; //skipping node to deleting (update current.next)
                        return;
                    }
                    current = current.next;
                }
                // Value not found (no node to delete)
                System.out.println("Value " + value + " not found in the list.");
            }

            // 2-exercise:
            public void insertAtBeginning(int data){
                Node newNode = new Node(data);
                newNode.next = head; //Make the new node point to the current head
                head = newNode; //Update head to point of the new node
            }
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

            // 1-exercise:
            public void printList(){
                Node current = head;
                while (current != null){
                    System.out.print(current.data + " ");
                    current = current.next;
                }
                System.out.println();
            }
        }

        /**Basic Traversal and Manipulation:

         1)Print the elements of a linked list: Write a function that iterates through a linked list and prints the
         data of each node.*/

        MyLinkedListLocal myLinkedListLocal = new MyLinkedListLocal();
        System.out.println("1):");
        myLinkedListLocal.head = new Node(5);
        myLinkedListLocal.head.next = new Node(15);
        myLinkedListLocal.head.next.next = new Node(25);
        myLinkedListLocal.printList(); //5 15 25



         /**2)Insert a node at the beginning/end of a linked list: Implement functions to insert a new node with a given
         value at the beginning or the end of a linked list.*/
        System.out.println("2):");
        myLinkedListLocal.insertAtBeginning(30);
        myLinkedListLocal.insertAtEnd(40);
        myLinkedListLocal.printList();
        System.out.println("insert one more to beginning: data = 0");
        myLinkedListLocal.insertAtBeginning(0);
        myLinkedListLocal.printList(); //0 30 5 15 25 40



         /**3)Delete a node with a specific value: Write a function that traverses the linked list and deletes the first
         node containing a specific value.*/
        System.out.println("3):");
        myLinkedListLocal.insertAtEnd(50);
        myLinkedListLocal.insertAtEnd(60);
        myLinkedListLocal.insertAtEnd(70);
        myLinkedListLocal.insertAtEnd(80);
        myLinkedListLocal.printList();//0 30 5 15 25 40 50 60 70 80
        myLinkedListLocal.deleting(5);
        myLinkedListLocal.printList();//0 30 15 25 40 50 60 70 80
        myLinkedListLocal.deleting(15);
        myLinkedListLocal.printList();//0 30 25 40 50 60 70 80
        myLinkedListLocal.deleting(25);
        myLinkedListLocal.printList();//0 30 40 50 60 70 80
    }
}
