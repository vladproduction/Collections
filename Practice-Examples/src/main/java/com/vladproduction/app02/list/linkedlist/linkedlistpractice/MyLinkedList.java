package com.vladproduction.app02.list.linkedlist.linkedlistpractice;


import java.util.Arrays;

/**
 * Created by vladproduction on 24-Mar-24
 */

public class MyLinkedList {

    private Node head;
    private int size;

    public void add(int value){
        //first adding to the list
        if(head == null){
            this.head = new Node(value);
        }else {
            Node tmp = head;
            while (tmp.getNext() != null){
                tmp = tmp.getNext();
            }
            tmp.setNext(new Node(value));
            // head[1] -> [2] -> [3] -> add [4]
        }
        size++;
    }

    public String toString(){
        int [] result = new int [size];
        int index = 0;
        Node tmp = head;
        while (tmp != null){
            result[index++] = tmp.getValue();
            tmp = tmp.getNext();
        }

        return Arrays.toString(result);
    }

    public void remove(int index){
        //1-2-3 need remove index1
        //have to change reference for 1 to 3
        if(index == 0){
            head = head.getNext();
            size--;
            return;
        }
        int currentIndex = 0;
        Node tmp = head;
        while (tmp != null){
            if (currentIndex == index - 1){
                tmp.setNext(tmp.getNext().getNext());
                size--;
                return;
            }else {
                tmp = tmp.getNext();
                currentIndex++;
            }
        }
    }

    public int get(int index){
        int currentIndex = 0;
        Node tmp = head;
        while (tmp != null){
            if(currentIndex == index){
                return tmp.getValue();
            }else {
                tmp = tmp.getNext();
                currentIndex++;
            }
        }
        // 1-2-3
        //need 1
        throw new IllegalArgumentException();
    }


    private static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
