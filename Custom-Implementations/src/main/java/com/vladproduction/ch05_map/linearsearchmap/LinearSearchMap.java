package com.vladproduction.ch05_map.linearsearchmap;

import com.vladproduction.ch05_map.MyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Array-based implementation with linear search
 * Simple map implementation using two parallel ArrayLists for keys and values.
 * All operations are O(n) due to linear search.
 * Good for small datasets or when insertion order matters.
 * */
public class LinearSearchMap<K, V> implements MyMap<K, V> {

    private final List<K> keys = new ArrayList<>();
    private final List<V> values = new ArrayList<>();

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public V put(K key, V value) {
        int index = findIndexOfKey(key);
        if(index != -1){
            // Key exists, update value and return old value
            return values.set(index, value);
        }

        //a new key with value adds to both lists respectively
        keys.add(key);
        values.add(value);
        return null;
    }

    @Override
    public V get(K key) {
        int index = findIndexOfKey(key);
        if (index == -1){
            return null;
        }
        return values.get(index);
    }

    @Override
    public V remove(K key) {
        int index = findIndexOfKey(key);
        if(index != -1){
            keys.remove(index);
            return values.remove(index);
        }
        return null;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public boolean containsKey(K key) {
        return findIndexOfKey(key) != -1 ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinearSearchMap<?, ?> that = (LinearSearchMap<?, ?>) o;

        if (keys.size() != that.keys.size() || values.size() != that.values.size()) {
            return false;
        }

        // Compare all key-value pairs (order matters in this implementation)
        for (int i = 0; i < keys.size(); i++) {
            if (!Objects.equals(keys.get(i), that.keys.get(i)) ||
                    !Objects.equals(values.get(i), that.values.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(keys, values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (int i = 0; i < keys.size(); i++) {
            if(i > 0){
                sb.append(", ");
            }
            sb.append(keys.get(i)).append("=").append(values.get(i));
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * helper method: method to find the index of a key using linear search.
     * @param key the key to search for
     * @return index of the key, or -1 if not found
     * */
    private int findIndexOfKey(K key){
        for (int i = 0; i < keys.size(); i++) {
            // Use Objects.equals to handle null keys properly
            if(Objects.equals(keys.get(i), key)){
                return i;
            }
        }
        return -1;
    }
}

