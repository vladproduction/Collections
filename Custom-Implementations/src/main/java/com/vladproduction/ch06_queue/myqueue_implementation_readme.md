# Queue Implementation Review

This project provides two distinct implementations of a custom Queue data structure in Java, demonstrating different approaches to achieve the same functionality with varying performance characteristics and memory usage patterns.

## Overview

The project consists of three main components:
- `MyQueue<T>` - The interface defining queue operations
- `MyQueueArrayImpl<T>` - Array-based implementation
- `MyQueueLinkedListImpl<T>` - Linked list-based implementation

## Interface Design

The `MyQueue<T>` interface extends `Iterable<T>` and provides comprehensive queue functionality:

```java
public interface MyQueue<T> extends Iterable<T> {
    int size();                    // Get number of elements
    boolean isEmpty();             // Quick check for empty state
    void enQueue(T item);          // Add to the end of the queue
    T deQueue();                   // Remove from front
    T peek();                      // View front without removing
    void clear();                  // Reset the queue
    boolean contains(T item);      // Search support
    Object[] toArray();            // Export contents to array
    Iterator<T> iterator();        // For-each loop compatibility
}
```

## Implementation Comparison

### Array-Based Implementation (`MyQueueArrayImpl<T>`)

**Advantages:**
- **Memory Efficiency**: Elements are stored contiguously in memory, providing better cache locality
- **Random Access**: Direct array indexing allows for efficient access patterns
- **Predictable Memory Usage**: Fixed capacity with controlled growth

**Disadvantages:**
- **Dynamic Resizing Overhead**: When capacity is exceeded, a new array must be allocated and elements copied
- **Inefficient Dequeue**: O(n) time complexity due to shifting all remaining elements
- **Memory Waste**: May have unused capacity after resizing

**Key Features:**
- Default capacity of 5 elements, expandable by 5 when full
- Automatic array resizing with `System.arraycopy()` for efficiency
- Comprehensive null handling in search operations
- Custom iterator with bounds checking

**Time Complexities:**
- `enQueue()`: O(1) amortized, O(n) worst case (during resize)
- `deQueue()`: O(n) - requires shifting all elements
- `peek()`: O(1)
- `contains()`: O(n)

### Linked List-Based Implementation (`MyQueueLinkedListImpl<T>`)

**Advantages:**
- **Efficient Operations**: Both enqueue and dequeue operations are O(1)
- **Dynamic Memory**: No pre-allocated capacity, grows and shrinks as needed
- **No Memory Waste**: Only allocates memory for actual elements

**Disadvantages:**
- **Memory Overhead**: Each node requires additional memory for storing the next pointer
- **Cache Performance**: Non-contiguous memory allocation may result in cache misses
- **Pointer Traversal**: Sequential access only, no random access capability

**Key Features:**
- Head and tail pointers for efficient front and rear operations
- Null item validation on enqueue
- Proper memory cleanup on clear operations
- Custom iterator with node traversal

**Time Complexities:**
- `enQueue()`: O(1)
- `deQueue()`: O(1)
- `peek()`: O(1)
- `contains()`: O(n)

## Implementation Details

### Array Implementation Highlights

```java
// Dynamic resizing strategy
if(size >= capacity){
    T[] tmp = (T[]) new Object[capacity + QUEUE_CAPACITY];
    System.arraycopy(elements, 0, tmp, 0, capacity);
    elements = tmp;
}
```

The array implementation uses a growth strategy that increases capacity by a fixed amount (5 elements) rather than doubling, which may be more memory-efficient for smaller queues but less efficient for rapidly growing queues.

### Linked List Implementation Highlights

```java
// Efficient enqueue with tail pointer
Node<T> newNode = new Node<>(item);
if(isEmpty()){
    head = newNode;
} else {
    tail.next = newNode;
}
tail = newNode;
```

The linked list maintains both head and tail references, enabling O(1) enqueue operations without traversing the entire list.

## Common Features

Both implementations provide:
- **Iterator Support**: Full `Iterator<T>` implementation with proper exception handling
- **Null Safety**: Appropriate handling of null values in contains operations
- **Exception Handling**: Consistent error states with meaningful exception messages
- **Array Export**: `toArray()` method for integration with other Java collections
- **String Representation**: Informative `toString()` methods showing queue state

## Performance Summary

| Operation | Array Implementation | Linked List Implementation |
|-----------|---------------------|----------------------------|
| enQueue   | O(1) amortized*     | O(1)                      |
| deQueue   | O(n)                | O(1)                      |
| peek      | O(1)                | O(1)                      |
| contains  | O(n)                | O(n)                      |
| Memory    | Contiguous, may waste space | Non-contiguous, exact fit |

*O(n) during resize operations

## Usage Recommendations

**Choose Array Implementation when:**
- Memory usage patterns are predictable
- Cache performance is critical
- Queue size remains relatively stable
- Dequeue operations are infrequent

**Choose Linked List Implementation when:**
- Frequent enqueue/dequeue operations
- Queue size varies significantly
- Memory efficiency is paramount
- O(1) performance for core operations is required

## Code Quality Features

Both implementations demonstrate good software engineering practices:
- **Generic Type Safety**: Full parameterized type support
- **Defensive Programming**: Input validation and boundary checking
- **Resource Management**: Proper cleanup in clear operations
- **Documentation**: Clear method documentation and inline comments
- **Testing Support**: Comprehensive `equals()`, `hashCode()`, and `toString()` implementations

This dual implementation approach provides flexibility for different use cases while maintaining a consistent interface, allowing for easy swapping between implementations based on performance requirements.