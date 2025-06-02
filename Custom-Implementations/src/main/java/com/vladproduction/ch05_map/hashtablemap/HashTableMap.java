package com.vladproduction.ch05_map.hashtablemap;

import com.vladproduction.ch05_map.MyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Hash table implementation using separate chaining for collision resolution.
 * Average case O(1) for all operations, O(n) worst case.
 * Good for larger datasets where performance matters.
 * */
public class HashTableMap<K, V> implements MyMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private List<Entry<K, V>>[] buckets;
    private int capacity;
    private int size;

    public HashTableMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashTableMap(int capacity) {
        this.capacity = Math.max(capacity, 1);
        this.buckets = new ArrayList[this.capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        //get the bucket index first
        int bucketIndex = getBucketIndex(key);
        List<Entry<K, V>> bucket = buckets[bucketIndex];

        //check if the key already exists - if so, just update, no resize needed
        for (Entry<K, V> entry : bucket) {
            if(Objects.equals(key, entry.getKey())){
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        //check if resize is needed before adding new entry
        //resize if adding this element would reach or exceed the load factor
        if ((double)(size + 1) / capacity >= DEFAULT_LOAD_FACTOR) {
            resize();
            // Recalculate bucket index after resize
            bucketIndex = getBucketIndex(key);
            bucket = buckets[bucketIndex];
        }

        //add a new key-value pair (new Entry) to the bucket
        bucket.add(new Entry<>(key, value));
        size++;

        return null;
    }

    @Override
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        for (Entry<K, V> entry : buckets[bucketIndex]) {
            if(Objects.equals(key, entry.getKey())){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        List<Entry<K, V>> bucket = buckets[bucketIndex];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if(Objects.equals(key, entry.getKey())){
                V oldValue = entry.getValue();
                bucket.remove(i);
                size--;
                return oldValue;
            }
        }

        return null;
    }

    @Override
    public void clear() {
        for (List<Entry<K, V>> bucket : buckets) {
            bucket.clear();
        }
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null || keyExistsWithNullValue(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        @SuppressWarnings("unchecked")
        HashTableMap<K, V> that = (HashTableMap<K, V>) o;

        if (size != that.size) return false;

        // Check that all entries in this map exist in the other map
        for (List<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                V otherValue = that.get(entry.getKey());
                if (!Objects.equals(entry.getValue(), otherValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (List<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                hash += entry.hashCode();
            }
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (List<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                if (!first) sb.append(", ");
                sb.append(entry);
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // helpers methods
    /**
     * Get the bucket index for a given key.
     */
    private int getBucketIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % capacity);
    }

    /**
     * Check if a key exists with a null value (for containsKey method).
     */
    private boolean keyExistsWithNullValue(K key) {
        int bucketIndex = getBucketIndex(key);
        List<Entry<K, V>> bucket = buckets[bucketIndex];

        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(key, entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Resize the hash table when a load factor threshold is exceeded.
     * Capacity must be doubling.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        List<Entry<K, V>>[] oldBuckets = buckets;
        capacity *= 2;
        buckets = new ArrayList[capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        size = 0;

        // Rehash all entries
        for (List<Entry<K, V>> bucket : oldBuckets) {
            for (Entry<K, V> entry : bucket) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Get the current load factor for debugging/monitoring.
     */
    public double getLoadFactor() {
        return (double) size / capacity;
    }

    /**
     * Get a number of buckets for debugging/monitoring.
     */
    public int getCapacity() {
        return capacity;
    }

}

