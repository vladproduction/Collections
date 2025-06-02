package com.vladproduction.ch07_generic.p08_generic_cache_with_expiration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Generic Cache class that can store any type with expiration time
 * */
public class GenericCache<K, V> {

    private final Map<K, CacheEntry<V>> cache = new HashMap<>();
    private final long defaultExpirationTime;

    public GenericCache(long defaultExpirationTime) {
        this.defaultExpirationTime = defaultExpirationTime;
    }

    public void put(K key, V value) {
        put(key, value, defaultExpirationTime);
    }

    public void put(K key, V value, long expirationTime) {

        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null");
        }

        long expirationTimeMillis = System.currentTimeMillis() + expirationTime;
        cache.put(key, new CacheEntry<>(value, expirationTimeMillis));

    }

    public Optional<V> get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        CacheEntry<V> entry = cache.get(key);
        if (entry == null) {
            return Optional.empty();
        }
        if (entry.isExpired()) {
            cache.remove(key);
            return Optional.empty();
        }
        return Optional.of(entry.value);
    }

    private static class CacheEntry<V> {

        final V value;
        final long expirationTime;

        public CacheEntry(V value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }

}
