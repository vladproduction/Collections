package com.vladproduction.iterator.my_iterator02;

import java.util.Iterator;

public class MyIterator_2<T> implements Iterator<T>{

    private MyListIteratorImpl_2 myListIterator;

    private int size;
    private int iteratorStep;

    public MyIterator_2(MyListIteratorImpl_2 myListIterator) {
        this.myListIterator = myListIterator;
        this.size = myListIterator.size();
    }

//    public MyIterator_2() {
//
//    }
//
//    public void setMyListIterator(MyListIteratorImpl_2 m){
//        myListIterator=m;
//
//    }

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
