# MyMap Implementations

A Java project demonstrating two different approaches to implementing a custom Map data structure with generics support.

## Overview

This project provides two distinct implementations of a custom map interface:

1. **LinearSearchMap** - Simple array-based implementation with linear search
2. **HashTableMap** - Hash table implementation with separate chaining

## Project Structure

```
src/
├── com/vladproduction/ch05_map/
│   ├── hashtablemap (HashTableMap, Entry)
│   ├── linearsearchmap (LinearSearchMap)
│   ├── MyMap.java                                       # Generic map interface
│   └── MainMyMapApp.java                                # Usage examples
└── test/
    ├── LinearSearchMapTest.java
    └── HashTableMapTest.java
```

## Implementations

### LinearSearchMap<K, V>

A simple map implementation using two parallel ArrayLists for keys and values.

**Characteristics:**
- **Time Complexity**: O(n) for all operations (get, put, remove)
- **Space Complexity**: O(n) with minimal overhead
- **Best Use Case**: Small datasets (< 50 entries) or when insertion order preservation is important
- **Thread Safety**: Not thread-safe

**Features:**
- Maintains insertion order
- Simple and easy to understand
- Low memory overhead
- Suitable for educational purposes

### HashTableMap<K, V>

A hash table implementation using separate chaining for collision resolution.

**Characteristics:**
- **Time Complexity**: O(1) average case, O(n) worst case for all operations
- **Space Complexity**: O(n) with additional overhead for buckets
- **Best Use Case**: Large datasets where performance is critical
- **Thread Safety**: Not thread-safe

**Features:**
- Automatic resizing when load factor exceeds 0.75
- Separate chaining for collision handling
- Performance monitoring methods
- Robust hash distribution

## Interface: MyMap<K, V>

```java
public interface MyMap<K, V> {
    int size();
    boolean isEmpty();
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    void clear();
    boolean containsKey(K key);
}
```

## Performance Comparison

| Operation | LinearSearchMap | HashTableMap |
|-----------|----------------|--------------|
| `get(key)` | O(n) | O(1) avg, O(n) worst |
| `put(key, value)` | O(n) | O(1) avg, O(n) worst |
| `remove(key)` | O(n) | O(1) avg, O(n) worst |
| `containsKey(key)` | O(n) | O(1) avg, O(n) worst |
| Memory Usage | Low | Medium |
| Insertion Order | Preserved | Not preserved |

## Usage Example

```java
// Linear Search Map
MyMap<String, Integer> linearMap = new LinearSearchMap<>();
linearMap.put("apple", 5);
linearMap.put("banana", 3);
System.out.println(linearMap.get("apple")); // Output: 5

// Hash Table Map
MyMap<String, Integer> hashMap = new HashTableMap<>();
hashMap.put("apple", 5);
hashMap.put("banana", 3);
System.out.println(hashMap.get("apple")); // Output: 5
```

## When to Use Which Implementation

### Choose LinearSearchMap when:
- Working with small datasets (< 50 entries)
- Insertion order matters
- Memory usage is a concern
- Simple implementation is preferred
- Educational/learning purposes

### Choose HashTableMap when:
- Working with large datasets (> 100 entries)
- Performance is critical
- Frequent lookups are expected
- Order doesn't matter
- Production applications

## Generic Type Safety

Both implementations support full generic type safety:

```java
MyMap<String, Integer> stringIntMap = new HashTableMap<>();
MyMap<Integer, String> intStringMap = new LinearSearchMap<>();
MyMap<Person, Account> personAccountMap = new HashTableMap<>();
```

## Requirements

- Java 8 or higher
- JUnit 5 (for running tests)
