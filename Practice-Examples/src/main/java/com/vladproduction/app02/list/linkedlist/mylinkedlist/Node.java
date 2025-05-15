package com.vladproduction.app02.list.linkedlist.mylinkedlist;

public class Node<T> {

    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
