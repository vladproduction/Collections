package com.vladproduction.iterator.my_iterator03;

public interface MyListIterator3<T> extends Iterable<T> {
    public boolean isEmpty();
    public int size();
    public void add(T o);
    public T get(int index);
    public T set(int index, T newObject);
    public void clear();
    public void remove(int index);
}

//если убрать наследование от Iterable<T> , то нельзя использовать for each loop.
//т.к. нет доступа к методу iterator();
