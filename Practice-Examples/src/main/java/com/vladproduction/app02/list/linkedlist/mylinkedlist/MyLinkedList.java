package com.vladproduction.app02.list.linkedlist.mylinkedlist;

import java.util.HashSet;
import java.util.Set;

public class MyLinkedList<T> {

    private Node<T> head;

    //add to end method
    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node<T> current = head;
        while (current.next != null){
            current = current.next;
        }
        current.next = newNode;
    }

    //print all elements:
    public void printList(){
        Node<T> current = head;
        while (current != null){
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println("null");
    }

    //remove by value
    public void remove(T data){
        if(head == null){
            return;
        }

        if(head.data.equals(data)){
            head = head.next;
            return;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)){
            current = current.next;
        }

        if(current.next != null){
            current.next = current.next.next;
        }
    }

    //size method:
    public int size(){
        int size = 0;
        Node<T> current = head;
        while (current != null){
            size++;
            current = current.next;
        }
        return size;
    }

    //get by index method:
    public T get(int index){
        int counter = 0;
        Node<T> current = head;
        while (current != null){
            if(counter == index){
                return current.data;
            }
            current = current.next;
            counter++;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
    }

    //reverse the list method:
    public void reverse(){
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    //find the middle element:
    public T getMiddle(){
        if(head == null){
            return null;
        }
        Node<T> slow = head;
        Node<T> fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    //detect loop method (Floyd`s Cycle detection)
    public boolean hasLoop(){
        Node<T> slow = head;
        Node<T> fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    //remove duplicates method (unordered list)
    public void removeDuplicates(){
        Set<T> seen = new HashSet<>();
        Node<T> current = head;
        Node<T> previous = null;
        while(current != null){
            if(seen.contains(current.data)){
                previous.next = current.next;
            }else{
                seen.add(current.data);
                previous = current;
            }
            current = current.next;
        }
    }

}
















