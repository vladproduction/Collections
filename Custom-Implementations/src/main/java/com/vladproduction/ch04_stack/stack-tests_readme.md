# MyStack Test Suite Documentation

This document provides a comprehensive overview of the test coverage for the MyStack implementation project. The test suite uses JUnit 5 and ensures all four stack implementations maintain consistent behavior while validating their unique characteristics.

## Test Architecture

### Base Test Class (`MyStackTestBase`)
An abstract base class that defines common test scenarios that all stack implementations must pass. This ensures behavioral consistency across different underlying data structures.

**Purpose:** Guarantee that all implementations follow the same contract and exhibit identical stack behavior from the user's perspective.

## Test Coverage Overview

### ✅ Core Functionality Tests

| Test Category | Test Count | Description |
|--------------|------------|-------------|
| **Basic Operations** | 8 tests | push, pop, peek, isEmpty, size |
| **LIFO Behavior** | 3 tests | Last-In-First-Out ordering verification |
| **Error Handling** | 2 tests | Exception throwing for invalid operations |
| **Utility Methods** | 5 tests | contains, clear, toArray functionality |
| **Edge Cases** | 4 tests | Null elements, empty operations, complex sequences |
| **Implementation-Specific** | 12+ tests | Unique features per implementation |
| **Performance** | 4 tests | Timeout-based performance validation |

**Total Test Methods:** 38+ individual test methods across all classes

## Detailed Test Breakdown

### 1. Common Tests (Applied to All Implementations)

#### **Initialization Tests**
- ✅ `testNewStackIsEmpty()` - Verifies new stack starts empty
- ✅ `testNewStackSize()` - Confirms initial size is zero

#### **Push Operation Tests**
- ✅ `testPushSingleElement()` - Single element addition
- ✅ `testPushMultipleElements()` - Multiple element addition
- ✅ `testPushNullElement()` - Null element handling

#### **Pop Operation Tests**
- ✅ `testPopSingleElement()` - Single element removal
- ✅ `testPopLIFOOrder()` - Maintains Last-In-First-Out order
- ✅ `testPopFromEmptyStack()` - Exception on empty stack

#### **Peek Operation Tests**
- ✅ `testPeekDoesNotRemove()` - Non-destructive top access
- ✅ `testPeekAtEmptyStack()` - Exception on empty stack

#### **Contains Operation Tests**
- ✅ `testContainsExistingElement()` - Finds existing elements
- ✅ `testDoesNotContainNonExistingElement()` - Correctly identifies missing elements
- ✅ `testContainsOnEmptyStack()` - Handles empty stack searches

#### **Clear Operation Tests**
- ✅ `testClear()` - Removes all elements
- ✅ `testClearOnEmptyStack()` - Safe operation on empty stack

#### **Array Conversion Tests**
- ✅ `testToArray()` - Converts stack to array format
- ✅ `testToArrayOnEmptyStack()` - Handles empty stack conversion

#### **Complex Scenario Tests**
- ✅ `testComplexOperationsSequence()` - Mixed operations workflow

### 2. ArrayList Implementation Tests (`MyStackArrayListImplTest`)

#### **Scalability Tests**
- ✅ `testLargeNumberOfElements()` - Handles 1000+ elements
  - Tests dynamic resizing capability
  - Verifies performance with large datasets
  - Confirms memory management

#### **Formatting Tests**
- ✅ `testToStringFormat()` - Proper string representation
  - Contains implementation identifier
  - Shows stack contents
  - Indicates top element position

### 3. LinkedList Implementation Tests (`MyStackLinkedListImplTest`)

#### **Efficiency Tests**
- ✅ `testLargeNumberOfElements()` - Efficient large dataset handling
  - Tests 1000 push operations
  - Verifies 1000 pop operations in correct order
  - Confirms no memory leaks

#### **Formatting Tests**
- ✅ `testToStringFormat()` - LinkedList-specific string representation

### 4. Array Implementation Tests (`MyStackArrayImplTest`)

#### **Capacity Management Tests**
- ✅ `testDefaultCapacityConstructor()` - Default capacity (10 elements)
- ✅ `testCustomCapacityConstructor()` - User-defined capacity
- ✅ `testIsFullFunctionality()` - Capacity limit detection
- ✅ `testStackOverflowException()` - Proper overflow handling
- ✅ `testCapacityManagement()` - Full capacity scenarios

#### **Resource Management Tests**
- ✅ `testClearAndReuse()` - Memory cleanup and reusability
- ✅ `testToStringWithCapacity()` - Shows current/max capacity ratio

#### **Unique Features Tests**
- ✅ `getCapacity()` method validation
- ✅ `isFull()` method validation
- ✅ Stack overflow prevention

### 5. Queue Implementation Tests (`MyStackQueueImplTest`)

#### **Algorithm Correctness Tests**
- ✅ `testLIFOBehaviorWithQueueInternals()` - LIFO despite FIFO internals
- ✅ `testMixedOperations()` - Alternating push/pop operations
- ✅ `testClearBothInternalQueues()` - Proper internal state management

#### **Performance Awareness Tests**
- ✅ `testPerformanceWithMultiplePushes()` - O(n) push operation handling
  - Limited to 100 elements due to O(n) complexity
  - Verifies correctness despite performance cost

### 6. Performance Comparison Tests (`StackPerformanceTest`)

#### **Timeout-Based Tests**
- ✅ `testArrayListPerformance()` - 5-second timeout, 10,000 operations
- ✅ `testLinkedListPerformance()` - 5-second timeout, 10,000 operations  
- ✅ `testArrayPerformance()` - 5-second timeout, 10,000 operations
- ✅ `testQueuePerformance()` - 30-second timeout, 1,000 operations

#### **Performance Metrics**
- Measures push/pop operation speed
- Compares implementation efficiency
- Validates timeout compliance
- Reports execution time for analysis

## Error Handling Validation

### Exception Testing Coverage
| Exception Scenario | Expected Behavior | Test Coverage |
|-------------------|------------------|---------------|
| Pop from empty stack | `RuntimeException` | ✅ All implementations |
| Peek at empty stack | `RuntimeException` | ✅ All implementations |
| Push to full array stack | `RuntimeException` with "Stack overflow" | ✅ Array implementation |
| Invalid capacity | Constructor validation | ✅ Array implementation |

### Error Message Validation
- ✅ Meaningful error messages
- ✅ Proper exception types
- ✅ Consistent error handling across implementations

## Test Data Coverage

### Data Types Tested
- ✅ **Integer values** - Primary test data type
- ✅ **Null values** - Edge case handling
- ✅ **Large datasets** - Scalability verification
- ✅ **Mixed operations** - Real-world usage patterns

### Test Scenarios
- ✅ **Empty stack operations**
- ✅ **Single element operations**
- ✅ **Multiple element operations**
- ✅ **Capacity limit scenarios** (Array implementation)
- ✅ **Performance stress tests**

## Running the Tests

### Prerequisites
```xml
<!-- Maven dependency -->
<dependencies>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.1</version>
    <scope>test</scope>
  </dependency>

  <dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-suite</artifactId>
    <version>1.10.1</version>
    <scope>test</scope>
  </dependency>

  <dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-suite-engine</artifactId>
    <version>1.10.1</version>
    <scope>test</scope>
  </dependency>
</dependencies>
```

## Continuous Integration Considerations

### Test Performance
| Implementation | Test Execution Time | Memory Usage |
|---------------|-------------------|--------------|
| ArrayList | ~100ms | Low |
| LinkedList | ~150ms | Medium |
| Array | ~50ms | Lowest |
| Queue | ~2000ms | Medium-High |

### CI/CD Integration
- All tests complete within reasonable timeframes
- Memory usage is bounded and predictable
- No external dependencies required
- Cross-platform compatible

### Debug Information
- All test classes include detailed assertion messages
- `toString()` methods provide stack state visibility
- Performance tests output timing information
- Exception tests validate specific error messages

This comprehensive test suite ensures the reliability, correctness, and performance characteristics of all MyStack implementations 
while maintaining consistency across different underlying data structures.