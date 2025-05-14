package com.vladproduction.iterator.my_iterator03;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyListIteratorImpl3<T> implements MyListIterator3<T> {
    private int capacity;
    private int size;
    private T[] data;
    private long version;

    public MyListIteratorImpl3() {
        this(5);
    }
    public MyListIteratorImpl3(int capacity) {
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
        version++;
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
        version++;
    }
    @Override
    public void remove(int index) {
        for(int i = index; i<size-1;i++){
            T rightValue = data[i+1];
            data[i] = rightValue;
        }
        data[size-1] = null;
        size--;
        version++;
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
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int iteratorStep;
            private long iteratorVersion = version;
            private int iteratorSize = size;
            @Override
            public boolean hasNext() {
                return iteratorStep<iteratorSize;
            }

            @Override
            public T next() {
                if (iteratorVersion!=version){
                    throw new ConcurrentModificationException("versions are not same; " +
                            "iteratorVersion= "+iteratorVersion+" ;version= "+version);
                }
                T item = get(iteratorStep);
                iteratorStep++;
                return item;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }
        if(o==this){
            return true;
        }
        if (o.getClass()== MyListIteratorImpl3.class){
            MyListIteratorImpl3 otherList = (MyListIteratorImpl3)o;
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
