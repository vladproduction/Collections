package com.vladproduction.generic;

import java.util.Arrays;

public class MyListGenericImpl<T> implements MyListGeneric<T> {
    private int capacity;
    private int size;
    private T[] data;

    public MyListGenericImpl() {
        this(5);
    }
    public MyListGenericImpl(int capacity) {
        this.data = (T[])new Object[capacity];
        this.capacity = capacity;
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    @Override
    public int size() {
        return this.size;
    }
    @Override
    public void add(T o) {
        if (size>=data.length){
            T [] tmp = (T[])new Object[data.length+5];
            System.arraycopy(data,0,tmp,0,data.length);
            data=tmp;
        }
        data[size]=o;
        size++;
    }
    @Override
    public T get(int index) {
        return data[index];
    }
    @Override
    public T set(int index, T newObject) {
        T value = get(index);
        data[index] = newObject;
        return value;
    }
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size=0;
    }
    @Override
    public void remove(int index) {
        for(int i = index; i<size-1;i++){
            T rightValue = data[i+1];
            data[i] = rightValue;
        }
        data[size-1] = null;
        size--;
    }
    @Override
    public String toString() {
        String text = "[";
        for (int i = 0; i<size;i++){
            text = text+data[i];
            if (i!=size-1){
                text=text+",";
            }
        }
        text = text+"]";
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
        if (o.getClass()== MyListGenericImpl.class){
            MyListGenericImpl otherList = (MyListGenericImpl)o;
            Object [] otherData = otherList.data;
            return Arrays.equals(data,otherData);
        }
        return false;

    }
    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
