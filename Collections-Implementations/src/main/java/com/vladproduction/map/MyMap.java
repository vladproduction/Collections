package com.vladproduction.map;

public interface MyMap {
    public int size();
    public boolean isEmpty();
    public Object put(Object key, Object value);
    public Object get(Object key);
    public Object remove(Object key);
    public void clear();
}
