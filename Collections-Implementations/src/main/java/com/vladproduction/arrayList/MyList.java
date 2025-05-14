package com.vladproduction.arrayList;

public interface MyList {
    public boolean isEmpty();
    public int size();
    public void add(Object o);
    public Object get(int index);
    public Object set(int index, Object newObject);
    public void clear();
    public void remove(int index);
}
