# MyArrayList - Custom Dynamic Array Implementation

A custom implementation of a dynamic array (similar to Java's ArrayList) built from scratch to demonstrate fundamental data structure concepts and algorithms.

## 📋 Table of Contents
- [Overview](#overview)
- [Key Features](#key-features)
- [Time Complexity](#time-complexity)
- [Architecture](#architecture)
- [Usage Examples](#usage-examples)
- [API Documentation](#api-documentation)
- [Implementation Details](#implementation-details)
- [Testing](#testing)
- [Performance Considerations](#performance-considerations)

## 🎯 Overview

This project implements a generic, resizable array data structure that automatically grows when needed. The implementation follows Java's Collection Framework conventions and provides fail-fast iterators with concurrent modification detection.

## ✨ Key Features

### Core Functionality
- **Generic Type Support**: Works with any object type using Java generics
- **Dynamic Resizing**: Automatically doubles capacity when array becomes full
- **Fail-Fast Iterators**: Detects concurrent modifications during iteration
- **Memory Efficient**: Nulls out references to help garbage collection
- **Bounds Checking**: Comprehensive index validation with descriptive error messages

### Advanced Features
- **Enhanced For-Loop Support**: Implements `Iterable<T>` interface
- **Iterator Remove**: Safe element removal during iteration
- **Concurrent Modification Detection**: Prevents data corruption during iteration
- **Custom Capacity**: Supports both default and custom initial capacities
- **Null Safety**: Prevents null elements from being added

### Safety & Robustness
- **Exception Handling**: Comprehensive error handling for edge cases
- **Index Validation**: Prevents out-of-bounds access
- **Thread-Safety Awareness**: Implements modification counting for detection
- **Memory Management**: Proper cleanup to prevent memory leaks

## ⏱️ Time Complexity

| Operation | Average Case | Worst Case | Notes |
|-----------|-------------|------------|-------|
| `add(item)` | O(1) | O(n) | Amortized O(1), O(n) when resizing |
| `getByIndex(index)` | O(1) | O(1) | Direct array access |
| `setByIndex(index, item)` | O(1) | O(1) | Direct array access |
| `removeByIndex(index)` | O(n) | O(n) | Must shift elements |
| `contains(item)` | O(n) | O(n) | Linear search |
| `clear()` | O(n) | O(n) | Must null all references |
| `size()` | O(1) | O(1) | Field access |
| `isEmpty()` | O(1) | O(1) | Simple size check |
| `capacity()` | O(1) | O(1) | Array length access |

### Space Complexity: O(n)
- Where n is the number of elements
- Additional space used for resizing operations: O(n) temporarily

## 🏗️ Architecture

```
MyArrayList<T> (Interface)
    ↓
MyArrayListImpl<T> (Implementation)
    ├── T[] data (Internal array)
    ├── int size (Current number of elements)
    ├── int modCount (Modification counter)
    └── MyArrayListIterator (Inner class)
```

### Core Components

#### Interface (`MyArrayList<T>`)
Defines the contract with all essential operations:
- Collection operations (add, remove, contains)
- Access operations (get, set)
- Utility operations (size, isEmpty, clear)
- Iterator support

#### Implementation (`MyArrayListImpl<T>`)
Main class containing:
- Internal array management
- Dynamic resizing logic
- Iterator implementation
- Thread-safety mechanisms

#### Iterator (`MyArrayListIterator`)
Inner class providing:
- Fail-fast iteration
- Safe removal during iteration
- Concurrent modification detection

## 🚀 Usage Examples

### Basic Operations
```java
// Create new list
MyArrayList<Integer> list = new MyArrayListImpl<>();

// Add elements
list.add(1);
list.add(2);
list.add(3);

// Access elements
Integer first = list.getByIndex(0); // Returns 1
Integer old = list.setByIndex(1, 99); // Returns 2, sets position 1 to 99

// Check contents
boolean hasThree = list.contains(3); // Returns true
int size = list.size(); // Returns 3
```

### Custom Capacity
```java
// Create list with specific initial capacity
MyArrayList<String> list = new MyArrayListImpl<>(5);
```

### Iteration
```java
// Enhanced for-loop
for (Integer value : list) {
    System.out.println(value);
}

// Traditional iterator
Iterator<Integer> iter = list.iterator();
while (iter.hasNext()) {
    Integer value = iter.next();
    if (value.equals(99)) {
        iter.remove(); // Safe removal during iteration
    }
}
```

### Removal
```java
// Remove by index
list.removeByIndex(0); // Removes first element

// Clear all elements
list.clear();
```

## 📚 API Documentation

### Constructors
- `MyArrayListImpl()`: Creates list with default capacity (10)
- `MyArrayListImpl(int capacity)`: Creates list with specified capacity

### Core Methods
- `void add(T item)`: Adds item to end of list
- `T getByIndex(int index)`: Returns element at index
- `T setByIndex(int index, T newItem)`: Sets element at index, returns old value
- `void removeByIndex(int index)`: Removes element at index
- `boolean contains(T item)`: Checks if list contains item
- `void clear()`: Removes all elements

### Utility Methods
- `boolean isEmpty()`: Returns true if list is empty
- `int size()`: Returns number of elements
- `int capacity()`: Returns current array capacity
- `Iterator<T> iterator()`: Returns fail-fast iterator

## 🔧 Implementation Details

### Dynamic Resizing Strategy
- **Initial Capacity**: 10 elements (default)
- **Growth Factor**: 2x (doubles when full)
- **Minimum Growth**: At least 1 element for edge cases
- **Timing**: Occurs before adding element that would exceed capacity

### Memory Management
- **Reference Clearing**: Nulls removed elements for GC
- **Array Copying**: Uses `System.arraycopy()` for efficiency
- **Capacity Preservation**: Maintains capacity after clearing

### Concurrent Modification Detection
- **Modification Counter**: Tracks structural changes
- **Fail-Fast Behavior**: Throws `ConcurrentModificationException`
- **Iterator Safety**: Validates expected modification count

### Error Handling
- **Null Prevention**: Rejects null elements
- **Bounds Checking**: Validates all index parameters
- **Descriptive Messages**: Clear error descriptions

## 🧪 Testing

The implementation includes comprehensive unit tests covering:
- Constructor variations and edge cases
- Basic operations (add, get, set, remove)
- Iterator functionality and fail-fast behavior
- Error conditions and exception handling
- Capacity management and resizing
- Edge cases (empty lists, single elements, etc.)

### Test Structure
```java
@Nested
@DisplayName("Constructor Tests")
class ConstructorTests { ... }

@Nested
@DisplayName("Basic Operations Tests")
class BasicOperationsTests { ... }

@Nested
@DisplayName("Iterator Tests")
class IteratorTests { ... }
```

## ⚡ Performance Considerations

### Strengths
- **Amortized O(1) Addition**: Fast appends in typical cases
- **Direct Access**: O(1) random access by index
- **Cache Friendly**: Contiguous memory layout
- **Low Overhead**: Minimal per-element overhead

### Limitations
- **Expensive Insertion/Deletion**: O(n) for middle operations
- **Memory Overhead**: May have unused capacity
- **Resizing Cost**: Occasional O(n) operations
- **No Thread Safety**: Requires external synchronization

### Best Use Cases
- Frequent appends and random access
- Iteration over all elements
- Known approximate size in advance
- Single-threaded environments

### Avoid When
- Frequent insertions/deletions in middle
- Highly concurrent access required
- Memory usage must be minimized
- Frequent size changes with unknown patterns

---

## 📝 Notes

This implementation serves as an educational tool to understand:
- Dynamic array management
- Generic programming in Java
- Iterator patterns and fail-fast behavior
- Memory management and garbage collection
- Algorithm complexity analysis

The code follows Java conventions and best practices while maintaining simplicity for learning purposes.