package com.vladproduction.ch05_map;

public interface MyMap<K, V> {

    int size();

    boolean isEmpty();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    void clear();

    boolean containsKey(K key);

}
