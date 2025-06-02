# MyLinkedList - Custom Singly Linked List Implementation

A custom implementation of a singly linked list built from scratch to demonstrate fundamental data structure concepts, pointer manipulation, and iterator patterns in Java.

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
- [Key Fixes Applied](#key-fixes-applied)

## 🎯 Overview

This project implements a generic singly linked list data structure with dynamic node allocation. 
Unlike array-based structures, this implementation provides efficient insertion and deletion at any position without the need for element shifting, 
making it ideal for scenarios with frequent modifications.

## ✨ Key Features

### Core Functionality
- **Generic Type Support**: Works with any object type using Java generics
- **Dynamic Size**: No fixed capacity limitations, grows as needed
- **Fail-Fast Iterators**: Detects concurrent modifications during iteration
- **Memory Efficient**: Only allocates memory for actual elements
- **Node-Based Structure**: True linked list implementation with pointer traversal

### Advanced Features
- **Enhanced For-Loop Support**: Implements `Iterable<T>` interface
- **Iterator Remove**: Safe element removal during iteration
- **Concurrent Modification Detection**: Prevents data corruption during iteration
- **Null Safety**: Prevents null elements from being added
- **Comprehensive Equals/HashCode**: Proper object comparison and hashing

### Safety & Robustness
- **Exception Handling**: Comprehensive error handling for edge cases
- **Index Validation**: Prevents out-of-bounds access
- **Thread-Safety Awareness**: Implements modification counting for detection
- **Memory Management**: Proper cleanup to prevent memory leaks
- **Iterator State Management**: Proper state tracking for safe iteration

## ⏱️ Time Complexity

| Operation | Average Case | Worst Case | Notes |
|-----------|-------------|------------|-------|
| `add(item)` | O(n) | O(n) | Must traverse to end |
| `getByIndex(index)` | O(n) | O(n) | Sequential traversal required |
| `setByIndex(index, item)` | O(n) | O(n) | Must traverse to position |
| `removeByIndex(index)` | O(n) | O(n) | Traversal + pointer adjustment |
| `contains(item)` | O(n) | O(n) | Linear search through nodes |
| `clear()` | O(n) | O(n) | Must traverse and clean each node |
| `size()` | O(1) | O(1) | Maintained counter |
| `isEmpty()` | O(1) | O(1) | Simple null/size check |

### Space Complexity: O(n)
- Where n is the number of elements
- Additional space per element: O(1) for node overhead
- No wasted space for unused capacity (unlike arrays)

## 🏗️ Architecture

```
MyLinkedList<T> (Interface)
    ↓
MyLinkedListImpl<T> (Implementation)
    ├── Node<T> root (First node reference)
    ├── int size (Current number of elements)
    ├── int modCount (Modification counter)
    ├── MyLinkedListIterator (Inner class)
    └── Node<T> (Static inner class)
        ├── T value (Node data)
        └── Node<T> next (Reference to next node)
```

### Core Components

#### Interface (`MyLinkedList<T>`)
Defines the contract with all essential operations:
- Collection operations (add, remove, contains)
- Access operations (get, set)
- Utility operations (size, isEmpty, clear)
- Iterator support

#### Implementation (`MyLinkedListImpl<T>`)
Main class containing:
- Node reference management
- Sequential traversal logic
- Iterator implementation
- Modification tracking

#### Node (`Node<T>`)
Static inner class providing:
- Value storage
- Next pointer management
- Basic node operations

#### Iterator (`MyLinkedListIterator`)
Inner class providing:
- Fail-fast iteration
- Safe removal during iteration
- Concurrent modification detection

## 🚀 Usage Examples

### Basic Operations
```java
// Create new linked list
MyLinkedList<String> list = new MyLinkedListImpl<>();

// Add elements
list.add("Java");
list.add("Python");
list.add("JavaScript");

// Access elements
String first = list.getByIndex(0); // Returns "Java"
String old = list.setByIndex(1, "Kotlin"); // Returns "Python", sets position 1 to "Kotlin"

// Check contents
boolean hasJava = list.contains("Java"); // Returns true
int size = list.size(); // Returns 3

System.out.println(list); // Prints: [Java, Kotlin, JavaScript]
```

### Iteration Examples
```java
// Enhanced for-loop
for (String language : list) {
    System.out.println(language);
}

// Traditional iterator
Iterator<String> iter = list.iterator();
while (iter.hasNext()) {
    String language = iter.next();
    System.out.println(language);
}
```

### Safe Removal During Iteration
```java
MyLinkedList<Integer> numbers = new MyLinkedListImpl<>();
for (int i = 1; i <= 10; i++) {
    numbers.add(i);
}

// Remove even numbers safely
Iterator<Integer> iter = numbers.iterator();
while (iter.hasNext()) {
    Integer num = iter.next();
    if (num % 2 == 0) {
        iter.remove(); // Safe removal
    }
}
// Result: [1, 3, 5, 7, 9]
```

### Working with Custom Objects
```java
// Using with custom objects
MyLinkedList<Person> people = new MyLinkedListImpl<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));

// Access and modify
Person first = people.getByIndex(0);
Person oldPerson = people.setByIndex(0, new Person("Alice Smith", 31));
```

## 📚 API Documentation

### Constructors
- `MyLinkedListImpl()`: Creates empty linked list

### Core Methods
- `void add(T item)`: Adds item to end of list (O(n))
- `T getByIndex(int index)`: Returns element at index (O(n))
- `T setByIndex(int index, T newItem)`: Sets element at index, returns old value (O(n))
- `void removeByIndex(int index)`: Removes element at index (O(n))
- `boolean contains(T item)`: Checks if list contains item (O(n))
- `void clear()`: Removes all elements (O(n))

### Utility Methods
- `boolean isEmpty()`: Returns true if list is empty (O(1))
- `int size()`: Returns number of elements (O(1))
- `Iterator<T> iterator()`: Returns fail-fast iterator
- `Node<T> getRoot()`: Returns first node (for debugging)

### Object Methods
- `boolean equals(Object o)`: Deep comparison with another list
- `int hashCode()`: Hash code based on all elements
- `String toString()`: String representation: [item1, item2, ...]

## 🔧 Implementation Details

### Node Structure
- **Singly Linked**: Each node points to the next node only
- **Generic Support**: Nodes can store any object type
- **Null Termination**: Last node's next pointer is null
- **Static Inner Class**: Doesn't hold reference to outer instance

### Memory Management
- **Dynamic Allocation**: Nodes created only when needed
- **Reference Clearing**: Nulls node values and pointers when clearing
- **No Memory Waste**: No unused capacity like array-based structures
- **Garbage Collection Friendly**: Proper reference cleanup

### Traversal Strategy
- **Sequential Access**: Must traverse from root to reach any position
- **Optimization**: Size counter maintained to avoid counting nodes
- **Early Termination**: Stops traversal when target is found

### Concurrent Modification Detection
- **Modification Counter**: Tracks structural changes to the list
- **Fail-Fast Behavior**: Throws `ConcurrentModificationException`
- **Iterator Safety**: Each iterator maintains expected modification count
- **Thread Awareness**: Detects modifications from other sources

### Error Handling
- **Null Prevention**: Rejects null elements with clear error messages
- **Bounds Checking**: Validates all index parameters against current size
- **State Validation**: Ensures iterator operations are called in correct order
- **Descriptive Messages**: Clear error descriptions for debugging

## 🧪 Testing

Comprehensive test coverage should include:

### Basic Functionality Tests
```java
@Test
void testAddAndSize() {
    MyLinkedList<String> list = new MyLinkedListImpl<>();
    assertTrue(list.isEmpty());
    
    list.add("Test");
    assertEquals(1, list.size());
    assertFalse(list.isEmpty());
}

@Test
void testGetAndSet() {
    MyLinkedList<Integer> list = new MyLinkedListImpl<>();
    list.add(10);
    list.add(20);
    
    assertEquals(Integer.valueOf(10), list.getByIndex(0));
    assertEquals(Integer.valueOf(20), list.setByIndex(1, 30));
    assertEquals(Integer.valueOf(30), list.getByIndex(1));
}
```

### Iterator Tests
```java
@Test
void testIteratorRemove() {
    MyLinkedList<String> list = new MyLinkedListImpl<>();
    list.add("A");
    list.add("B");
    list.add("C");
    
    Iterator<String> iter = list.iterator();
    iter.next(); // "A"
    iter.remove();
    
    assertEquals(2, list.size());
    assertEquals("B", list.getByIndex(0));
}

@Test
void testConcurrentModificationException() {
    MyLinkedList<String> list = new MyLinkedListImpl<>();
    list.add("A");
    list.add("B");
    
    assertThrows(ConcurrentModificationException.class, () -> {
        for (String item : list) {
            list.add("C"); // Modifies during iteration
        }
    });
}
```

### Edge Cases Tests
```java
@Test
void testRemoveOnlyElement() {
    MyLinkedList<String> list = new MyLinkedListImpl<>();
    list.add("Only");
    
    list.removeByIndex(0);
    
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
}

@Test
void testIndexOutOfBounds() {
    MyLinkedList<String> list = new MyLinkedListImpl<>();
    
    assertThrows(IndexOutOfBoundsException.class, 
                () -> list.getByIndex(0));
    assertThrows(IndexOutOfBoundsException.class, 
                () -> list.removeByIndex(-1));
}
```

## ⚡ Performance Considerations

### Strengths
- **No Capacity Waste**: Only allocates memory for actual elements
- **Dynamic Growth**: No need to specify initial capacity
- **Efficient Clear**: O(n) but releases all memory immediately
- **Cache Considerations**: Good for sequential access patterns

### Limitations
- **No Random Access**: O(n) time for indexed operations
- **Memory Overhead**: Each node requires additional pointer storage
- **Cache Locality**: Poor for random access due to non-contiguous memory
- **Traversal Cost**: Must traverse from beginning for each access

### Best Use Cases
- **Frequent Insertions/Deletions**: Especially at the beginning
- **Unknown Size**: When final size is unpredictable
- **Memory Constraints**: When avoiding over-allocation is important
- **Sequential Processing**: When iterating through all elements

### Avoid When
- **Frequent Random Access**: Use ArrayList instead
- **Memory is Critical**: Node overhead may be significant for small objects
- **Cache Performance Matters**: Array-based structures provide better locality
- **Simple Operations**: Built-in ArrayList may be more suitable

---

## 🎓 Educational Value

This implementation demonstrates several important computer science concepts:

### Data Structure Concepts
- **Pointer Manipulation**: Understanding of reference-based structures
- **Dynamic Memory**: Allocation and deallocation patterns
- **Linear Data Structures**: Sequential organization principles
- **Node-Based Architecture**: Building blocks of more complex structures

### Algorithm Design Patterns
- **Traversal Algorithms**: Sequential access patterns
- **Iterator Pattern**: Safe iteration with modification detection
- **Fail-Fast Design**: Early error detection and reporting
- **Memory Management**: Proper cleanup and garbage collection

### Software Engineering Practices
- **Interface Design**: Clean separation of contract and implementation
- **Error Handling**: Comprehensive exception management
- **Documentation**: Clear API documentation and usage examples
- **Testing Strategy**: Edge case identification and handling

### Performance Analysis
- **Time Complexity**: Understanding O(n) vs O(1) operations
- **Space Complexity**: Memory overhead analysis
- **Trade-off Analysis**: When to use linked lists vs arrays
- **Benchmarking**: Performance measurement techniques

## 📊 Comparison with ArrayList

| Feature | LinkedList | ArrayList |
|---------|------------|-----------|
| **Random Access** | O(n) | O(1) |
| **Sequential Access** | O(1) per step | O(1) per step |
| **Insertion at End** | O(n) | O(1) amortized |
| **Insertion at Beginning** | O(1) | O(n) |
| **Deletion** | O(n) | O(n) |
| **Memory Overhead** | Higher (pointers) | Lower (continuous) |
| **Memory Allocation** | Dynamic | Pre-allocated chunks |
| **Cache Performance** | Poor | Good |
| **Best Use Case** | Frequent modifications | Random access |

## 🚀 Advanced Usage Patterns

### Building More Complex Structures
```java
// Stack implementation using LinkedList
public class LinkedStack<T> {
    private MyLinkedList<T> list = new MyLinkedListImpl<>();
    
    public void push(T item) {
        // Add to beginning for O(1) operation
        // Would need addFirst() method for true O(1)
        list.add(item);
    }
    
    public T pop() {
        if (list.isEmpty()) throw new EmptyStackException();
        T item = list.getByIndex(list.size() - 1);
        list.removeByIndex(list.size() - 1);
        return item;
    }
}

// Queue operations (less efficient without tail pointer)
public class LinkedQueue<T> {
    private MyLinkedList<T> list = new MyLinkedListImpl<>();
    
    public void enqueue(T item) {
        list.add(item); // Add to end
    }
    
    public T dequeue() {
        if (list.isEmpty()) throw new NoSuchElementException();
        T item = list.getByIndex(0);
        list.removeByIndex(0); // Remove from beginning
        return item;
    }
}
```

### Custom Iteration Patterns
```java
// Reverse iteration (requires additional logic)
public void printReverse(MyLinkedList<String> list) {
    // Convert to array for reverse iteration
    String[] items = new String[list.size()];
    int i = 0;
    for (String item : list) {
        items[i++] = item;
    }
    
    // Print in reverse
    for (int j = items.length - 1; j >= 0; j--) {
        System.out.println(items[j]);
    }
}

// Filtered iteration
public void printFiltered(MyLinkedList<Integer> list, Predicate<Integer> filter) {
    for (Integer num : list) {
        if (filter.test(num)) {
            System.out.println(num);
        }
    }
}
```

## 🔍 Debugging and Troubleshooting

### Common Issues and Solutions

1. **NullPointerException during traversal**
   ```java
   // Problem: Not checking for null nodes
   while (current.getNext() != null) { ... }
   
   // Solution: Always check current node
   while (current != null) { ... }
   ```

2. **Memory leaks in clear() method**
   ```java
   // Problem: Not nulling references
   current.setNext(next); // Wrong!
   
   // Solution: Null out references
   current.setNext(null);
   current.setValue(null);
   ```

3. **ConcurrentModificationException**
   ```java
   // Problem: Modifying during enhanced for-loop
   for (String item : list) {
       list.add("new"); // Throws exception
   }
   
   // Solution: Use iterator.remove() or collect changes
   Iterator<String> iter = list.iterator();
   while (iter.hasNext()) {
       if (shouldRemove(iter.next())) {
           iter.remove(); // Safe
       }
   }
   ```

### Debugging Utilities
```java
// Add to MyLinkedListImpl for debugging
public void printStructure() {
    System.out.println("List structure:");
    Node<T> current = root;
    int index = 0;
    while (current != null) {
        System.out.println("  [" + index + "] " + current.getValue() + 
                          " -> " + (current.getNext() != null ? "Node" : "null"));
        current = current.getNext();
        index++;
    }
    System.out.println("Size: " + size + ", ModCount: " + modCount);
}
```

## 📈 Future Enhancements

### Potential Improvements
1. **Doubly Linked Implementation**: Add previous pointers for O(1) backward traversal
2. **Circular Linked List**: Connect tail to head for circular operations
3. **Tail Pointer**: Maintain reference to last node for O(1) append operations
4. **Thread Safety**: Add synchronization for concurrent access
5. **Memory Pool**: Reuse node objects to reduce garbage collection
6. **Specialized Iterators**: Bidirectional, filtered, or parallel iterators

### Advanced Features
```java
// Example: Add tail pointer for efficient appends
private Node<T> tail; // Add to class fields

public void addLast(T item) { // O(1) operation
    Node<T> newNode = new Node<>(item);
    if (root == null) {
        root = tail = newNode;
    } else {
        tail.setNext(newNode);
        tail = newNode;
    }
    size++;
    modCount++;
}
```

---

## 📝 Conclusion

This MyLinkedList implementation serves as an excellent educational tool for understanding:
- Fundamental data structure concepts
- Pointer manipulation and memory management
- Iterator patterns and fail-fast design
- Performance trade-offs in data structure selection
- Object-oriented design principles

The implementation follows Java best practices while maintaining clarity for learning purposes. 
It provides a solid foundation for understanding more complex linked data structures like trees, graphs, and advanced list variations.

**Key Takeaways:**
- Linked lists excel at insertion/deletion but are poor for random access
- Proper memory management is crucial in pointer-based structures
- Iterator patterns provide safe and flexible data traversal
- Understanding trade-offs helps in choosing appropriate data structures
- Clean interface design separates concerns and improves maintainability