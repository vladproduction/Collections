package com.vladproduction.queue;

import java.util.Arrays;

public class MyQueueImpl implements MyQueue{

    private Object [] data;
    private int size;
    private int capacity;

    public MyQueueImpl() {
        this(5);
    }

    public MyQueueImpl(int capacity) {
        this.data = new Object [capacity];
        this.capacity = capacity;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void enQueue(Object o) {
        if (size>=data.length){
            Object [] tmp = new Object[data.length+capacity];
            System.arraycopy(data,0,tmp,0,data.length);
            data=tmp;
        }
        data[size]=o;
        size++;

    }

    @Override
    public Object deQueue() {
        Object rez = data[0];
        for (int i = 0; i < size-1; i++) {
            Object rightValue = data[i+1];
            data[i]=rightValue;
        }
        data[size-1]=null;
        size--;
        return rez;
    }

    @Override
    public String toString() {
        String text = "[";
        for (int i = 0; i < size; i++) {
            text = text+data[i];
            if(i!=size-1){
                text = text+", ";
            }
        }
        text = text + "]";
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }
        if(o==this){
            return true;
        }
        if (o.getClass()==MyQueueImpl.class){
            MyQueueImpl otherQueue = (MyQueueImpl)o;
            Object [] otherData = otherQueue.data;
            return Arrays.equals(data,otherData);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
//toString, hashCode, equals, test, -->as Array
