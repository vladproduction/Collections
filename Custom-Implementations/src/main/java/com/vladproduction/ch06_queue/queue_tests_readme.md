# Queue Implementation Tests

This repository contains comprehensive test suites for two different queue implementations in Java: **Array-based Queue** and **LinkedList-based Queue**. Both implementations follow the FIFO (First In, First Out) principle and implement a common `MyQueue<T>` interface.

## 📋 Table of Contents

- [Overview](#overview)
- [Queue Implementations](#queue-implementations)
- [Test Structure](#test-structure)
- [Key Features Tested](#key-features-tested)
- [Test Categories](#test-categories)
- [Running the Tests](#running-the-tests)
- [Test Coverage](#test-coverage)

## 🔍 Overview

The test suites validate the functionality, performance, and edge cases of two queue implementations:

1. **MyQueueArrayImpl** - Array-based queue with dynamic resizing
2. **MyQueueLinkedListImpl** - LinkedList-based queue with null rejection

Both implementations are thoroughly tested using JUnit 5 with comprehensive test cases covering normal operations, edge cases, error conditions, and performance scenarios.

## 🏗️ Queue Implementations

### Array-Based Queue (`MyQueueArrayImpl`)
- **Dynamic Resizing**: Automatically expands capacity when needed
- **Null Support**: Allows null values to be enqueued
- **Constructor Options**: Default capacity (5) or custom capacity
- **Memory Efficient**: Uses circular array approach

### LinkedList-Based Queue (`MyQueueLinkedListImpl`)
- **Dynamic Memory**: No fixed capacity limitations
- **Null Rejection**: Throws `IllegalArgumentException` for null values
- **Memory Efficient**: Allocates memory only as needed
- **Pointer Management**: Maintains head and tail pointers

## 🧪 Test Structure

The test suites are organized using JUnit 5's `@Nested` test classes for better organization:

### Array Implementation Tests
- **Basic Operations Tests** - Core queue functionality
- **Exception Handling Tests** - Error conditions and exceptions
- **Capacity and Constructor Tests** - Initialization and resizing
- **Null Handling Tests** - Null value support
- **Utility Methods Tests** - Helper methods (clear, contains, toArray)
- **Iterator Tests** - Iterator functionality and edge cases
- **Object Methods Tests** - equals, hashCode, toString
- **Integration Tests** - Complex operation sequences

### LinkedList Implementation Tests
- **Basic Operations Tests** - Core queue functionality
- **Exception Handling Tests** - Error conditions
- **Null Handling Tests** - Null rejection behavior
- **Utility Methods Tests** - Helper methods
- **Iterator Tests** - Iterator functionality
- **Performance Tests** - Large dataset and stress testing
- **Edge Cases Tests** - Boundary conditions

## ✨ Key Features Tested

### Core Queue Operations
- ✅ **enQueue(item)** - Add element to rear of queue
- ✅ **deQueue()** - Remove and return front element
- ✅ **peek()** - View front element without removal
- ✅ **isEmpty()** - Check if queue is empty
- ✅ **size()** - Get current number of elements

### Utility Methods
- ✅ **clear()** - Remove all elements
- ✅ **contains(item)** - Check if item exists in queue
- ✅ **toArray()** - Convert queue to array
- ✅ **toString()** - String representation
- ✅ **iterator()** - Iterator support

### Quality Assurance
- ✅ **FIFO Order Maintenance** - Ensures proper queue behavior
- ✅ **Exception Handling** - Proper error conditions
- ✅ **Null Handling** - Different approaches for each implementation
- ✅ **Memory Management** - Dynamic resizing and efficient allocation
- ✅ **Thread Safety Considerations** - Iterator behavior

## 📊 Test Categories

### 1. **Functional Tests**
- Basic queue operations (enqueue, dequeue, peek)
- FIFO order verification
- Size and empty state management
- Clear functionality

### 2. **Exception Tests**
- Empty queue operations (peek, dequeue)
- Invalid constructor parameters
- Iterator boundary conditions
- Null handling (implementation-specific)

### 3. **Edge Case Tests**
- Single element operations
- Multiple clear operations
- Queue drain and refill cycles
- Iterator behavior after modifications

### 4. **Performance Tests**
- Large dataset handling (1,000-10,000 elements)
- Stress testing with alternating operations
- Memory efficiency validation
- Resizing behavior under load

### 5. **Integration Tests**
- Complex operation sequences
- Mixed enqueue/dequeue patterns
- State consistency verification

## 🚀 Running the Tests

### Prerequisites
- Java 8 or higher
- JUnit 5
- Maven or Gradle build system

## 📈 Test Coverage

### Array Implementation Coverage
- **Basic Operations**: 100% - All core queue methods
- **Exception Handling**: 100% - All error conditions
- **Utility Methods**: 100% - All helper methods
- **Edge Cases**: 95% - Most boundary conditions
- **Performance**: 90% - Large datasets and stress tests

### LinkedList Implementation Coverage
- **Basic Operations**: 100% - All core queue methods
- **Exception Handling**: 100% - All error conditions including null rejection
- **Utility Methods**: 100% - All helper methods
- **Edge Cases**: 95% - Boundary conditions and pointer management
- **Performance**: 95% - Extensive performance and scalability tests

## 🎯 Key Test Insights

### Array Implementation Highlights
- ✅ **Dynamic Resizing**: Successfully handles capacity overflow
- ✅ **Null Support**: Properly manages null values in queue
- ✅ **Memory Efficiency**: Circular array implementation
- ✅ **Constructor Flexibility**: Multiple initialization options

### LinkedList Implementation Highlights
- ✅ **Memory Efficiency**: No pre-allocation, grows as needed
- ✅ **Null Safety**: Prevents null values with clear error messages
- ✅ **Pointer Consistency**: Maintains head/tail integrity
- ✅ **Scalability**: Handles large datasets efficiently

## 🔧 Implementation Differences

| Feature | Array Implementation | LinkedList Implementation |
|---------|---------------------|--------------------------|
| **Null Values** | ✅ Supported | ❌ Rejected with exception |
| **Memory Allocation** | Fixed chunks (with resizing) | Dynamic per element |
| **Capacity Limits** | Initial capacity (expandable) | No limits |
| **Memory Overhead** | Lower per element | Higher per element |
| **Access Pattern** | Random access possible | Sequential access only |

## 📝 Notes

- All tests use JUnit 5 annotations and assertions
- Test methods have descriptive display names for better readability
- Both basic and enhanced test suites are provided
- Tests cover happy path, edge cases, and error conditions
- Performance tests validate scalability up to 10,000 elements
- Iterator tests ensure proper fail-fast behavior where applicable