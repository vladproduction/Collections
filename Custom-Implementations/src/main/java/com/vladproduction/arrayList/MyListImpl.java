package com.vladproduction.arrayList;

import java.util.Arrays;

public class MyListImpl implements MyList {
    private int capacity;
    private int size;
    private Object[] data;

    public MyListImpl() {
        this(5);
    }
    public MyListImpl(int capacity) {
        this.data = new Object[capacity];
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
    public void add(Object o) {
        if (size>=data.length){
            Object [] tmp = new Object[data.length+5];
            System.arraycopy(data,0,tmp,0,data.length);
            data=tmp;
        }
        data[size]=o;
        size++;
    }
    @Override
    public Object get(int index) {
        return data[index];
    }
    @Override
    public Object set(int index, Object newObject) {
        Object value = get(index);
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
            Object rightValue = data[i+1];
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
        if (o.getClass()==MyListImpl.class){
            MyListImpl otherList = (MyListImpl)o;
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
