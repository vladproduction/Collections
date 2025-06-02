# Testing Documentation for MyMap Implementations

This document provides comprehensive information about testing the LinearSearchMap and HashTableMap implementations.

## Test Structure

The test suite is organized into two main test classes:
- `LinearSearchMapTest.java` - Tests for the array-based implementation
- `HashTableMapTest.java` - Tests for the hash table implementation

## Test Categories

### 1. Basic Operations Tests
Tests the fundamental map operations:
- **Empty Map Behavior**: Verifies correct behavior on newly created maps
- **Put Operations**: Tests adding new entries and updating existing ones
- **Get Operations**: Tests value retrieval for existing and non-existing keys
- **Remove Operations**: Tests entry removal and return values
- **Clear Operations**: Tests complete map clearing
- **Size and isEmpty**: Tests size tracking and empty state detection

### 2. Edge Cases Tests
Tests boundary conditions and special scenarios:
- **Null Values**: Handling of null values as map values
- **Null Keys**: Handling of null keys (where supported)
- **Non-existent Keys**: Behavior when accessing keys that don't exist
- **Repeated Operations**: Multiple operations on the same key
- **Reference vs Value Equality**: Proper key comparison using equals()

### 3. Contract Compliance Tests
Ensures the implementations follow Java object contracts:
- **equals() Method**: Proper equality comparison between maps
- **hashCode() Method**: Consistent hash code calculation
- **toString() Method**: Readable string representation

### 4. Performance and Scalability Tests
Tests behavior with larger datasets:
- **Large Dataset Handling**: Performance with thousands of entries
- **Memory Usage**: Efficient memory utilization
- **Hash Distribution**: (HashTableMap only) Even distribution across buckets

### 5. Implementation-Specific Tests

#### LinearSearchMap Specific:
- **Insertion Order Preservation**: Maintains order of insertion
- **Linear Search Behavior**: Correct sequential search implementation

#### HashTableMap Specific:
- **Hash Collision Handling**: Proper collision resolution
- **Automatic Resizing**: Dynamic capacity adjustment
- **Load Factor Management**: Maintains optimal load factor
- **Bucket Distribution**: Even distribution of entries

## Running the Tests

### Prerequisites
- Java 8 or higher
- JUnit 5 (Jupiter)
- Your IDE or build tool configured for JUnit 5

## Test Coverage

### LinearSearchMapTest Coverage
| Test Method | Coverage Area | Expected Behavior |
|-------------|---------------|-------------------|
| `testEmptyMap()` | Initial state | Size=0, isEmpty=true |
| `testPutNewEntries()` | Adding entries | Returns null, increases size |
| `testPutUpdateExisting()` | Updating entries | Returns old value, size unchanged |
| `testGetOperation()` | Value retrieval | Returns correct values or null |
| `testRemoveOperation()` | Entry removal | Returns old value, decreases size |
| `testClearOperation()` | Complete clearing | Size=0, all entries removed |
| `testContainsKeyWithNullValues()` | Null value handling | Correct containsKey behavior |
| `testNullKeys()` | Null key handling | Proper null key support |
| `testInsertionOrder()` | Order preservation | Maintains insertion order |
| `testEqualsMethod()` | Equality comparison | Proper equals implementation |
| `testHashCodeConsistency()` | Hash code contract | Consistent with equals |
| `testDifferentGenericTypes()` | Type safety | Works with various types |
| `testLargeDataset()` | Scalability | Handles 1000+ entries |
| `testToString()` | String representation | Readable output format |
| `testRepeatedOperations()` | Multiple operations | Consistent behavior |
| `testReferenceVsValueEquality()` | Key comparison | Uses equals(), not == |

### HashTableMapTest Coverage
| Test Method | Coverage Area | Expected Behavior |
|-------------|---------------|-------------------|
| `testEmptyMap()` | Initial state | Size=0, default capacity=16 |
| `testCustomCapacity()` | Constructor | Custom capacity setting |
| `testPutNewEntries()` | Adding entries | Returns null, load factor updates |
| `testPutUpdateExisting()` | Updating entries | Returns old value, size unchanged |
| `testGetOperation()` | Value retrieval | O(1) average performance |
| `testRemoveOperation()` | Entry removal | Returns old value, updates load factor |
| `testClearOperation()` | Complete clearing | All buckets cleared |
| `testContainsKeyWithNullValues()` | Null value handling | Distinguishes null values from missing keys |
| `testNullKeys()` | Null key handling | Null keys handled in bucket 0 |
| `testHashCollisions()` | Collision handling | Separate chaining works correctly |
| `testAutomaticResizing()` | Dynamic resizing | Resizes when load factor > 0.75 |
| `testEqualsMethod()` | Equality comparison | Order-independent equality |
| `testHashCodeConsistency()` | Hash code contract | Consistent with equals |
| `testDifferentGenericTypes()` | Type safety | Works with various types |
| `testLargeDataset()` | Scalability | Handles 10,000+ entries efficiently |
| `testToString()` | String representation | Readable output format |
| `testRepeatedOperations()` | Multiple operations | Consistent behavior |
| `testReferenceVsValueEquality()` | Key comparison | Uses equals(), not == |
| `testBucketDistribution()` | Hash distribution | Reasonable bucket usage |
| `testLoadFactorCalculation()` | Load factor tracking | Accurate calculation |

## Test Data and Scenarios

### Test Data Types Used
- **Strings**: Common keys like "apple", "banana", "cherry"
- **Integers**: Sequential numbers for large dataset tests
- **Null Values**: Both null keys and null values
- **Custom Objects**: Person class and CollisionKey class for advanced testing

### Collision Testing (HashTableMap)
The `CollisionKey` helper class intentionally creates hash collisions to test:
- Separate chaining implementation
- Correct value retrieval with collisions
- Proper removal with collisions
- Performance under high collision scenarios

### Performance Benchmarks
Tests include performance verification for:
- **LinearSearchMap**: Acceptable performance up to ~1,000 entries
- **HashTableMap**: Efficient performance up to 10,000+ entries
- **Resizing Overhead**: Minimal impact during automatic resizing

## Common Test Patterns

### Assertion Patterns
```java
// Verify operation return values
assertEquals(expectedValue, actualValue);
assertNull(map.get("nonexistent"));

// Verify state changes
assertEquals(expectedSize, map.size());
assertTrue/assertFalse(map.isEmpty());

// Verify object contracts
assertEquals(map1, map2);
assertEquals(map1.hashCode(), map2.hashCode());
```

### Test Setup Pattern
```java
@BeforeEach
void setUp() {
    map = new LinearSearchMap<>(); // or HashTableMap
    // Additional setup as needed
}
```

### Performance Test Pattern
```java
final int LARGE_SIZE = 1000;
for (int i = 0; i < LARGE_SIZE; i++) {
    map.put(i, "value" + i);
}
// Verify all operations still work correctly
```

## Debugging Test Failures

### Common Issues and Solutions

1. **NullPointerException**
   - Check null key/value handling
   - Verify proper initialization

2. **AssertionError in equals() tests**
   - Check equals() method implementation
   - Verify all fields are compared

3. **Performance test timeouts**
   - May indicate O(n²) behavior
   - Check algorithm implementation

4. **Hash collision test failures**
   - Verify separate chaining implementation
   - Check bucket index calculation

### Debug Information Available
- **HashTableMap**: `getLoadFactor()`, `getCapacity()` methods
- **Both implementations**: `toString()` for state inspection
- **Test output**: Detailed assertion messages

## Best Practices for Adding Tests

1. **Test one thing at a time**: Each test method should focus on a single aspect
2. **Use descriptive names**: Test method names should clearly indicate what's being tested
3. **Include edge cases**: Test boundary conditions and error scenarios
4. **Verify state changes**: Check that operations have the expected side effects
5. **Test contracts**: Ensure equals(), hashCode(), and toString() work correctly
6. **Performance awareness**: Include tests that verify expected performance characteristics

## Continuous Integration

For CI/CD pipelines, consider:
- Running tests automatically on code changes
- Generating test coverage reports
- Performance regression testing
- Memory usage monitoring

The test suite is designed to be fast and reliable for continuous integration environments.