package com.vladproduction.bag;

import java.util.Arrays;

public class BagImpl implements Bag {
    private Object [] data;
    private int size;
    private int capacity;

    public BagImpl() {
        //this.data = new Object[3];
         this(3);
    }

    public BagImpl(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return this.size;
    }

    //1) version: new Object [data.length*2]
    /*@Override
    public void add(Object o) {
        if(size==data.length){
            Object [] tmp = new Object[data.length*2];
            for (int i = 0; i<data.length; i++){
                tmp [i] = data [i];
            }
            data = tmp;
        }
        data[size] = o;
        size++;
    }*/

    //2) version: Arrays.copyOf
    /*@Override
    public void add(Object o) {
        if(size<data.length){
            Object [] tmp = Arrays.copyOf(data,10);
            data = tmp;
        }
        data [size] = o;
        size++;
    }*/


    //3) version: System.arraycopy
    @Override
    public void add(Object o) {
        if(size>=data.length){
            Object [] tmp = new Object[10];
            System.arraycopy(data,0,tmp,0,data.length);
            data = tmp;
        }
        data [size] = o;
        size++;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
