package com.vladproduction.iterator.my_iterator01;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T>{

    private MyListIteratorImpl myListIterator;

    private int size;
    private int iteratorStep;

    public MyIterator(MyListIteratorImpl myListIterator) {
        this.myListIterator = myListIterator;
        this.size = myListIterator.size();
    }

    @Override
    public boolean hasNext() {
        if(iteratorStep<size){
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        //Object item = myListIterator.get(iteratorStep);// variant1
        Object item = myListIterator.get((size-1)-iteratorStep);// variant1 - revers show
        iteratorStep++;
        return (T)item;
    }
}
