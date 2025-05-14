package com.vladproduction.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyMapImpl implements MyMap{
    private List keys = new ArrayList();
    private List values = new ArrayList();

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public Object put(Object key, Object value) {
        if(keys.contains(key)){
            int index = findIndexOfKey(key);
            Object oldValue = values.set(index,value);
            return oldValue;
        }
        keys.add(key);
        values.add(value);
        return null;
    }

    private int findIndexOfKey(Object key){
        for (int i = 0; i < keys.size(); i++) {
            if(keys.get(i).equals(key)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object get(Object key) {
        int index = findIndexOfKey(key);
        if (index==-1){
            return null;
        }
        return values.get(index);
    }

    @Override
    public Object remove(Object key) {
        int index = findIndexOfKey(key);
        if (index!=-1){ //that means we have such key
            keys.remove(index);
            Object oldValue = values.remove(index);
            return oldValue;
        }
        return null;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();

    }

    @Override
    public String toString() {
        return "MyMapImpl{" +
                "keys=" + keys +
                ", values=" + values +
                '}';
    }


    public boolean equalsDefault(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMapImpl myMap = (MyMapImpl) o;
        return Objects.equals(keys, myMap.keys) && Objects.equals(values, myMap.values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (o.getClass() == this.getClass()) {
            MyMapImpl otherMap = (MyMapImpl) o;
            if (keys.size() != otherMap.keys.size()
                    && values.size() != otherMap.values.size()) {
                return false;
            }
            for (int i = 0; i < keys.size(); i++) {
                Object keysValue = keys.get(i);
                Object otherKeyValue = otherMap.keys.get(i);
                if (!keysValue.equals(otherKeyValue)){
                    return false;
                }
            }
            for (int i = 0; i < values.size(); i++) {
                Object valuesValue = values.get(i);
                Object otherValue = otherMap.values.get(i);
                if(!valuesValue.equals(otherValue)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(keys, values);
    }
}
