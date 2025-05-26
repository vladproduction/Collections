package com.vladproduction.map;

import java.util.ArrayList;
import java.util.List;

public class MyMapImpl2 implements MyMap {

    private List<Entry>[] data;
    private int capacity;
    private int size;

    public MyMapImpl2() {
        this(6);
    }

    public MyMapImpl2(int capacity) {
        this.capacity = capacity;
        data = new ArrayList[capacity];
        for (int i = 0; i < data.length; i++) {
            data[i] = new ArrayList();
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object put(Object key, Object value) {
        int numberBucket = findIndexOfBucket(key);
        List<Entry> bucketList = data[numberBucket];
        for (Entry entry : bucketList) {
            if (key.equals(entry.getKey())) {
                Object oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        Entry newItem = new Entry(key, value);
        bucketList.add(newItem);
        size++;
        return null;
    }

    private int findIndexOfBucket(Object key) {
        int hashKey = key.hashCode();
        int bucketIndex = hashKey % data.length;
        return Math.abs(bucketIndex);
    }

    @Override
    public Object get(Object key) {
        int indexBuket = findIndexOfBucket(key);
        List<Entry> bucketList = data[indexBuket];
        for (Entry entry : bucketList) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        int indexBuket = findIndexOfBucket(key);
        List<Entry> bucketList = data[indexBuket];
        for (Entry entry : bucketList) {
            if (key.equals(entry.getKey())) {
                Object oldValue = entry.getValue();
                bucketList.remove(entry);
                size--;
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i].clear();
        }
        size=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMapImpl2 that = (MyMapImpl2) o;
        if(size!= that.size){
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            List<Entry> entryList = data[i];
            List<Entry> thatList = that.data[i];
            if(entryList.size()!=thatList.size()){
                return false;
            }
            for(int j = 0;j<entryList.size();j++){
                Entry myEntry = entryList.get(j);
                Entry myThat = thatList.get(j);
                if(!myEntry.getKey().equals(myThat.getKey())){
                    return false;
                }
                if (!myEntry.getValue().equals(myThat.getValue())){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = size;
        for(int i = 0; i < data.length; i++){
            List<Entry> entryList = data[i];
            hash = hash+entryList.hashCode();
        }
        return hash;
    }
}
