# MyStack - Custom Stack Implementations

A comprehensive collection of stack data structure implementations in Java, demonstrating different underlying data structures and their trade-offs.

## Overview

This project provides four different implementations of a stack data structure, each using a different underlying storage mechanism. 
All implementations follow the same `MyStack<T>` interface, making them interchangeable while showcasing various approaches to stack implementation.

## Implementations

### 1. ArrayList-based Stack (`MyStackArrayListImpl`)
**Underlying Structure:** `ArrayList<T>`

```java
MyStack<Integer> stack = new MyStackArrayListImpl<>();
```

**Characteristics:**
- Dynamic resizing
- Random access to elements
- Automatic memory management
- Good general-purpose performance

**Time Complexity:**
- Push: O(1) amortized
- Pop: O(1)
- Peek: O(1)
- Contains: O(n)

**Best for:** General-purpose applications where you need a reliable, well-performing stack.

### 2. LinkedList-based Stack (`MyStackLinkedListImpl`)
**Underlying Structure:** `LinkedList<T>`

```java
MyStack<String> stack = new MyStackLinkedListImpl<>();
```

**Characteristics:**
- Dynamic size with no capacity limit
- Efficient insertion/deletion at ends
- Extra memory overhead for node pointers
- No random access

**Time Complexity:**
- Push: O(1)
- Pop: O(1)
- Peek: O(1)
- Contains: O(n)

**Best for:** Applications with frequent push/pop operations and unknown maximum size.

### 3. Array-based Stack (`MyStackArrayImpl`)
**Underlying Structure:** Fixed-size array

```java
MyStack<Double> stack = new MyStackArrayImpl<>(100); // capacity = 100
MyStack<Double> defaultStack = new MyStackArrayImpl<>(); // default capacity = 10
```

**Characteristics:**
- Fixed capacity (prevents stack overflow)
- Minimal memory overhead
- Fast access and operations
- Stack overflow protection

**Time Complexity:**
- Push: O(1)
- Pop: O(1)
- Peek: O(1)
- Contains: O(n)

**Additional Methods:**
- `getCapacity()` - returns maximum capacity
- `isFull()` - checks if stack is at capacity

**Best for:** Memory-constrained environments or when you need guaranteed capacity limits.

### 4. Queue-based Stack (`MyStackQueueImpl`)
**Underlying Structure:** Two `ArrayDeque` queues

```java
MyStack<Character> stack = new MyStackQueueImpl<>();
```

**Characteristics:**
- Implements stack using FIFO queues
- Demonstrates algorithmic problem-solving
- Higher memory usage (two queues)
- Slower push operations

**Time Complexity:**
- Push: O(n) - needs to rearrange elements
- Pop: O(1)
- Peek: O(1)
- Contains: O(n)

**Best for:** Educational purposes, technical interviews, or when you need to implement a stack using only queue operations.

## Interface Methods

All implementations support the following operations:

| Method | Description | Returns |
|--------|-------------|---------|
| `push(T element)` | Adds element to top of stack | void |
| `pop()` | Removes and returns top element | T |
| `peek()` | Returns top element without removing | T |
| `isEmpty()` | Checks if stack is empty | boolean |
| `size()` | Returns number of elements | int |
| `clear()` | Removes all elements | void |
| `contains(T element)` | Checks if element exists in stack | boolean |
| `toArray()` | Returns array representation | Object[] |

## Usage Examples

### Basic Operations
```java
MyStack<Integer> stack = new MyStackArrayListImpl<>();

// Push elements
stack.push(10);
stack.push(20);
stack.push(30);

// Check top element
System.out.println(stack.peek()); // Output: 30

// Pop element
int top = stack.pop(); // Returns 30
System.out.println(stack.size()); // Output: 2

// Check if contains element
boolean hasElement = stack.contains(20); // true

// Clear all elements
stack.clear();
System.out.println(stack.isEmpty()); // true
```

### Choosing Implementation by Use Case
```java
// General purpose - good balance of performance and features
MyStack<String> generalStack = new MyStackArrayListImpl<>();

// Memory constrained - fixed capacity
MyStack<Integer> limitedStack = new MyStackArrayImpl<>(1000);

// Frequent operations - efficient linked structure
MyStack<Object> dynamicStack = new MyStackLinkedListImpl<>();

// Academic/Interview - algorithmic demonstration
MyStack<Double> academicStack = new MyStackQueueImpl<>();
```

## Performance Comparison

| Implementation | Push | Pop | Peek | Memory | Use Case |
|---------------|------|-----|------|--------|----------|
| ArrayList | O(1)* | O(1) | O(1) | Medium | General purpose |
| LinkedList | O(1) | O(1) | O(1) | High | Dynamic size |
| Array | O(1) | O(1) | O(1) | Low | Fixed capacity |
| Queue | O(n) | O(1) | O(1) | High | Educational |

*Amortized - occasionally O(n) during resize

## Error Handling

All implementations throw `RuntimeException` for:
- `pop()` on empty stack
- `peek()` on empty stack
- `push()` on full stack (Array implementation only)

## Thread Safety

⚠️ **Important:** None of these implementations are thread-safe. For concurrent access, consider:
- Using `Collections.synchronizedList()` wrapper
- Implementing your own synchronization
- Using `java.util.concurrent.ConcurrentLinkedDeque`

---

## Requirements
- Java 8 or higher
- No external dependencies

---

## Future Enhancements

Potential improvements and additions:
- Thread-safe implementations
- Generic capacity management for array implementation
- Performance benchmarking suite
- Memory usage analysis tools
- Additional utility methods (e.g., `swap()`, `duplicate()`)
- Iterator support for stack traversal

---

## License

This project is provided as-is for educational and development purposes.