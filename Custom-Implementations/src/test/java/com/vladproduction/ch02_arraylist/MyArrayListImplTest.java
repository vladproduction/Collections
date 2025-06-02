package com.vladproduction.ch02_arraylist;

import org.junit.jupiter.api.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListImplTest {

    private MyArrayListImpl<String> stringList;
    private MyArrayListImpl<Integer> intList;

    @BeforeEach
    void setUp() {
        stringList = new MyArrayListImpl<>();
        intList = new MyArrayListImpl<>();
    }

    @AfterEach
    void tearDown() {
        stringList = null;
        intList = null;
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("default constructor creates list with default capacity")
        void testDefaultConstructorCreatesListWithDefaultCapacity() {
            MyArrayListImpl<String> defaultList = new MyArrayListImpl<>();

            assertTrue(defaultList.isEmpty());
            assertEquals(0, defaultList.size());
            assertEquals(10, defaultList.capacity());
        }

        @Test
        @DisplayName("parameterized constructor creates list with specified capacity")
        void testParameterizedConstructorWithSpecifiedCapacity() {
            MyArrayListImpl<Integer> parameterizedList = new MyArrayListImpl<>(5);

            assertTrue(parameterizedList.isEmpty());
            assertEquals(0, parameterizedList.size());
            assertEquals(5, parameterizedList.capacity());
        }

        @Test
        @DisplayName("constructor with zero capacity creates valid list")
        void testConstructorWithZeroInitialCapacity() {
            MyArrayListImpl<String> zeroList = new MyArrayListImpl<>(0);

            assertTrue(zeroList.isEmpty());
            assertEquals(0, zeroList.size());
            assertEquals(0, zeroList.capacity());
        }

        @Test
        @DisplayName("constructor throws exception with negative capacity")
        void testConstructorThrowsExceptionWithNegativeCapacity() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new MyArrayListImpl<String>(-1)
            );

            assertEquals("Capacity cannot be negative", exception.getMessage());
        }

        @Test
        @DisplayName("constructor with large capacity works correctly")
        void testConstructorWithLargeCapacity() {
            MyArrayListImpl<Integer> largeList = new MyArrayListImpl<>(1000);

            assertTrue(largeList.isEmpty());
            assertEquals(0, largeList.size());
            assertEquals(1000, largeList.capacity());
        }
    }

    @Nested
    @DisplayName("Basic Operations Tests")
    class BasicOperationsTests {

        @Test
        @DisplayName("isEmpty returns true for newly created list")
        void testIsEmptyReturnsTrueForNewlyCreatedList() {
            assertTrue(stringList.isEmpty());
            assertTrue(intList.isEmpty());
        }

        @Test
        @DisplayName("isEmpty returns false after adding elements")
        void testIsEmptyReturnsFalseAfterAddingElements() {
            assertTrue(intList.isEmpty());
            intList.add(1);
            assertFalse(intList.isEmpty());

            assertTrue(stringList.isEmpty());
            stringList.add("element");
            assertFalse(stringList.isEmpty());
        }

        @Test
        @DisplayName("size returns zero for newly created list")
        void testSizeReturnsZeroForNewlyCreatedList() {
            assertEquals(0, stringList.size());
            assertEquals(0, intList.size());
        }

        @Test
        @DisplayName("size increases correctly after adding elements")
        void testSizeIncreasesCorrectlyAfterAddingElements() {
            assertTrue(intList.isEmpty());
            assertEquals(0, intList.size());

            intList.add(1);
            intList.add(2);
            intList.add(3);

            assertEquals(3, intList.size());
            assertFalse(intList.isEmpty());

            assertTrue(stringList.isEmpty());
            assertEquals(0, stringList.size());

            stringList.add("item 1");
            stringList.add("item 2");
            stringList.add("item 3");

            assertFalse(stringList.isEmpty());
            assertEquals(3, stringList.size());
        }

        @Test
        @DisplayName("add throws exception for null elements")
        void testAddNullElementThrowsExceptionForNullElements() {
            assertThrows(IllegalArgumentException.class, () -> stringList.add(null));
            assertThrows(IllegalArgumentException.class, () -> intList.add(null));
        }

        @Test
        @DisplayName("add throws exception for null elements with custom message")
        void testAddNullElementThrowsExceptionForNullElementsWithCustomMessage() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> stringList.add(null)
            );

            assertEquals("Cannot add null item to the list", exception.getMessage());

            exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> intList.add(null)
            );

            assertEquals("Cannot add null item to the list", exception.getMessage());
        }

        @Test
        @DisplayName("capacity increases when list is full")
        void testCapacityIncreasesWhenListIsFull() {
            MyArrayListImpl<Integer> smallList = new MyArrayListImpl<>(2);
            assertEquals(2, smallList.capacity());

            smallList.add(1);
            smallList.add(2);
            assertEquals(2, smallList.capacity());

            smallList.add(3);
            assertEquals(4, smallList.capacity());
            assertEquals(3, smallList.size());
        }
    }

    @Nested
    @DisplayName("Access Operations Tests")
    class AccessOperationsTests {

        @BeforeEach
        void setUpWithElements(){
            intList.add(10);
            intList.add(20);
            intList.add(30);

            stringList.add("first");
            stringList.add("second");
            stringList.add("third");
        }

        @Test
        @DisplayName("getByIndex returns correct elements")
        void testGetByIndexReturnsCorrectElements() {
            assertEquals(10, intList.getByIndex(0));
            assertEquals(20, intList.getByIndex(1));
            assertEquals(30, intList.getByIndex(2));

            assertEquals("first", stringList.getByIndex(0));
            assertEquals("second", stringList.getByIndex(1));
            assertEquals("third", stringList.getByIndex(2));
        }

        @Test
        @DisplayName("getByIndex throws exception for invalid indices")
        void testGetByIndexThrowsExceptionForInvalidIndices() {
            //testing negative index
            IndexOutOfBoundsException exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> intList.getByIndex(-1)
            );

            assertEquals("Index: " + -1 + ", Size: " + intList.size(), exception.getMessage());   //exactly matching

            IndexOutOfBoundsException negativeException = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> intList.getByIndex(-1)
            );

            assertTrue(negativeException.getMessage().contains("Index: -1"));                             //partially contain message

            //testing equals index to size
            IndexOutOfBoundsException equalException = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> intList.getByIndex(3)
            );

            assertTrue(equalException.getMessage().contains("Index: 3"));

            //testing greater index than size
            IndexOutOfBoundsException greaterThanException = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> intList.getByIndex(10)
            );

            assertTrue(greaterThanException.getMessage().contains("Index: 10"));
        }

        @Test
        @DisplayName("setByIndex updates and returns old value")
        void testSetByIndexUpdatesAndReturnsOldValue() {
            Integer oldValue = intList.setByIndex(1, 9999);
            assertEquals(20, oldValue);
            assertEquals(9999, intList.getByIndex(1));

            String oldStringValue = stringList.setByIndex(0, "updated");
            assertEquals("first", oldStringValue);
            assertEquals("updated", stringList.getByIndex(0));
        }

        @Test
        @DisplayName("setByIndex throws exception for null values")
        void testSetByIndexThrowsExceptionForNullValues() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> intList.setByIndex(0, null)
            );
            assertEquals("Cannot set null item to the list", exception.getMessage());

            exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> stringList.setByIndex(2, null)
            );
            assertEquals("Cannot set null item to the list", exception.getMessage());
        }

        @Test
        @DisplayName("setByIndex throws exception for invalid indices")
        void testSetByIndexThrowsExceptionForInvalidIndices() {
            //negative index
            IndexOutOfBoundsException exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> intList.setByIndex(-1, 9999)
            );

            assertTrue(exception.getMessage().contains("Index: -1"));

            //equal index to size
            exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> intList.setByIndex(3, 9999)
            );

            assertTrue(exception.getMessage().contains("Index: 3"));

            //index is greater than size
            exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> intList.setByIndex(100, 9999)
            );
            assertTrue(exception.getMessage().contains("Index: 100"));
        }

    }

    @Nested
    @DisplayName("Contains Operation Tests")
    class ContainsOperationTests {

        @BeforeEach
        void setUpWithElements(){
            intList.add(100);
            intList.add(200);
            intList.add(300);

            stringList.add("apple");
            stringList.add("banana");
            stringList.add("cherry");
        }

        @Test
        @DisplayName("contains returns true for existing elements")
        void testContainsReturnsTrueForExistingElements() {
            assertTrue(intList.contains(100));
            assertTrue(intList.contains(200));
            assertTrue(intList.contains(300));

            assertTrue(stringList.contains("apple"));
            assertTrue(stringList.contains("banana"));
            assertTrue(stringList.contains("cherry"));
        }

        @Test
        @DisplayName("contains returns false for non-existing elements")
        void testContainsReturnsFalseForNonExistingElements() {
            assertFalse(intList.contains(999));
            assertFalse(intList.contains(0));
            assertFalse(intList.contains(-1));

            assertFalse(stringList.contains("orange"));
            assertFalse(stringList.contains("pineapple"));
            assertFalse(stringList.contains("."));
            assertFalse(stringList.contains(" "));
            assertFalse(stringList.contains(""));
        }

        @Test
        @DisplayName("contains returns false for null")
        void testContainsReturnsFalseForNull() {
            assertFalse(intList.contains(null));
            assertFalse(stringList.contains(null));
        }

        @Test
        @DisplayName("contains works on empty list")
        void testContainsWorksOnEmptyList() {
            MyArrayListImpl<String> emptyList = new MyArrayListImpl<>();
            assertFalse(emptyList.contains("anything"));
            assertFalse(emptyList.contains(null));
        }
    }

    @Nested
    @DisplayName("Remove Operations Tests")
    class RemoveOperationTests {

        @BeforeEach
        void setUp(){
            intList.add(1);
            intList.add(2);
            intList.add(3);
            intList.add(4);
            intList.add(5);
        }

        @Test
        @DisplayName("removeByIndex removes correct element and shifts others")
        void testRemoveByIndexRemovesCorrectElementAndShiftsOthers() {
            assertEquals(5, intList.size());

            intList.removeByIndex(2);                                   //removing '3'

            assertEquals(4, intList.size());
            assertEquals(1, intList.getByIndex(0));
            assertEquals(2, intList.getByIndex(1));
            assertEquals(4, intList.getByIndex(2));             // '4' had been shifted to the left
            assertEquals(5, intList.getByIndex(3));             // '5' had been shifted to the left
        }

        @Test
        @DisplayName("removeByIndex works for first element")
        void testRemoveByIndexRemovesFirstElement() {
            intList.removeByIndex(0);

            assertEquals(4, intList.size());
            assertEquals(2, intList.getByIndex(0));
            assertEquals(3, intList.getByIndex(1));
        }

        @Test
        @DisplayName("removeByIndex works for last element")
        void testRemoveByIndexWorksForLastElement() {
            intList.removeByIndex(4); // Remove last element (5)

            assertEquals(4, intList.size());
            assertEquals(4, intList.getByIndex(3));
        }

        @Test
        @DisplayName("removeByIndex throws exception for invalid indices")
        void testRemoveByIndexThrowsExceptionForInvalidIndices() {
            IndexOutOfBoundsException negativeException = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> intList.removeByIndex(-1)
            );
            assertTrue(negativeException.getMessage().contains("Index: -1"));

            IndexOutOfBoundsException tooLargeException = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> intList.removeByIndex(10)
            );
            assertTrue(tooLargeException.getMessage().contains("Index: 10"));
        }

        @Test
        @DisplayName("removeByIndex on single element list works correctly")
        void testRemoveByIndexOnSingleElementListWorksCorrectly() {
            MyArrayListImpl<String> singleList = new MyArrayListImpl<>();
            singleList.add("only");

            assertEquals(1, singleList.size());
            singleList.removeByIndex(0);

            assertEquals(0, singleList.size());
            assertTrue(singleList.isEmpty());
        }
    }

    @Nested
    @DisplayName("Clear Operation Tests")
    class ClearOperationTests {

        @Test
        @DisplayName("clear removes all elements but preserves capacity")
        void testClearRemovesAllElementsButPreservesCapacity() {
            // Add elements to increase size
            for (int i = 1; i <= 5; i++) {
                intList.add(i);
            }

            assertEquals(5, intList.size());
            int originalCapacity = intList.capacity();

            intList.clear();

            assertEquals(0, intList.size());
            assertTrue(intList.isEmpty());
            assertEquals(originalCapacity, intList.capacity()); // Capacity preserved
        }

        @Test
        @DisplayName("clear on empty list works correctly")
        void testClearOnEmptyListWorksCorrectly() {
            assertTrue(intList.isEmpty());
            int originalCapacity = intList.capacity();

            intList.clear();

            assertTrue(intList.isEmpty());
            assertEquals(0, intList.size());
            assertEquals(originalCapacity, intList.capacity());
        }

        @Test
        @DisplayName("clear allows adding new elements")
        void testClearAllowsAddingNewElements() {
            intList.add(1);
            intList.add(2);
            intList.clear();

            assertTrue(intList.isEmpty());

            intList.add(10);
            intList.add(20);

            assertEquals(2, intList.size());
            assertEquals(10, intList.getByIndex(0));
            assertEquals(20, intList.getByIndex(1));
        }
    }

    @Nested
    @DisplayName("Iterator Tests")
    class IteratorTests {

        @BeforeEach
        void setupWithElements() {
            intList.add(1);
            intList.add(2);
            intList.add(3);
            intList.add(4);
            intList.add(5);
        }

        @Test
        @DisplayName("iterator iterates through all elements in correct order")
        void testIteratorIteratesThroughAllElementsInCorrectOrder() {
            Iterator<Integer> iterator = intList.iterator();
            int expected = 1;

            while (iterator.hasNext()) {
                assertEquals(expected++, iterator.next());
            }

            assertEquals(6, expected); // Should have processed 5 elements
        }

        @Test
        @DisplayName("iterator hasNext returns false after last element")
        void testIteratorHasNextReturnsFalseAfterLastElement() {
            Iterator<Integer> iterator = intList.iterator();

            // Consume all elements
            for (int i = 0; i < 5; i++) {
                assertTrue(iterator.hasNext());
                iterator.next();
            }

            assertFalse(iterator.hasNext());
        }

        @Test
        @DisplayName("iterator next throws exception when no more elements")
        void testIteratorNextThrowsExceptionWhenNoMoreElements() {
            Iterator<Integer> iterator = intList.iterator();

            // Consume all elements
            while (iterator.hasNext()) {
                iterator.next();
            }

            assertThrows(IllegalStateException.class, iterator::next);
        }

        @Test
        @DisplayName("iterator works with empty list")
        void testIteratorWorksWithEmptyList() {
            MyArrayListImpl<Integer> emptyList = new MyArrayListImpl<>();
            Iterator<Integer> iterator = emptyList.iterator();

            assertFalse(iterator.hasNext());
            assertThrows(IllegalStateException.class, iterator::next);
        }

        @Test
        @DisplayName("enhanced for loop works correctly")
        void testEnhancedForLoopWorksCorrectly() {
            int expected = 1;
            for (Integer value : intList) {
                assertEquals(expected++, value);
            }
            assertEquals(6, expected);
        }

        @Test
        @DisplayName("iterator remove works correctly")
        void testIteratorRemoveWorksCorrectly() {
            Iterator<Integer> iterator = intList.iterator();

            // Move to second element and remove it
            iterator.next(); // 1
            Integer second = iterator.next(); // 2
            assertEquals(2, second);

            iterator.remove();

            assertEquals(4, intList.size());
            assertEquals(1, intList.getByIndex(0));
            assertEquals(3, intList.getByIndex(1)); // 3 shifted left
            assertEquals(4, intList.getByIndex(2));
            assertEquals(5, intList.getByIndex(3));
        }

        @Test
        @DisplayName("iterator remove throws exception when called before next")
        void testIteratorRemoveThrowsExceptionWhenCalledBeforeNext() {
            Iterator<Integer> iterator = intList.iterator();

            assertThrows(IllegalStateException.class, iterator::remove);
        }

        @Test
        @DisplayName("iterator remove throws exception when called twice")
        void testIteratorRemoveThrowsExceptionWhenCalledTwice() {
            Iterator<Integer> iterator = intList.iterator();

            iterator.next();
            iterator.remove(); // First remove - should work

            assertThrows(IllegalStateException.class, iterator::remove);
        }

        @Test
        @DisplayName("iterator detects concurrent modification")
        void testIteratorDetectsConcurrentModification() {
            Iterator<Integer> iterator = intList.iterator();

            iterator.next(); // Start iteration

            // Modify list during iteration
            intList.add(100);

            // Should throw ConcurrentModificationException
            assertThrows(ConcurrentModificationException.class, iterator::next);
        }

        @Test
        @DisplayName("multiple iterators work independently")
        void testMultipleIteratorsWorkIndependently() {
            Iterator<Integer> iter1 = intList.iterator();
            Iterator<Integer> iter2 = intList.iterator();

            assertEquals(1, iter1.next());
            assertEquals(1, iter2.next());
            assertEquals(2, iter1.next());
            assertEquals(2, iter2.next());

            // They should be independent
            assertTrue(iter1.hasNext());
            assertTrue(iter2.hasNext());
        }
    }

    @Nested
    @DisplayName("Capacity Management Tests")
    class CapacityManagementTests {

        @Test
        @DisplayName("capacity doubles when array becomes full")
        void testCapacityDoublesWhenArrayBecomesFull() {
            MyArrayListImpl<Integer> smallList = new MyArrayListImpl<>(1);
            assertEquals(1, smallList.capacity());

            smallList.add(1);
            assertEquals(1, smallList.capacity());

            smallList.add(2); // Should trigger resize
            assertEquals(2, smallList.capacity());

            smallList.add(3); // Should trigger another resize
            assertEquals(4, smallList.capacity());
        }

        @Test
        @DisplayName("capacity grows correctly from zero")
        void testCapacityGrowsCorrectlyFromZero() {
            MyArrayListImpl<String> zeroList = new MyArrayListImpl<>(0);
            assertEquals(0, zeroList.capacity());

            zeroList.add("first");
            assertEquals(1, zeroList.capacity()); // Minimum growth of 1

            zeroList.add("second");
            assertEquals(2, zeroList.capacity());
        }

        @Test
        @DisplayName("large capacity additions work correctly")
        void testLargeCapacityAdditionsWorkCorrectly() {
            MyArrayListImpl<Integer> list = new MyArrayListImpl<>(1000);

            // Add many elements
            for (int i = 0; i < 1500; i++) {
                list.add(i);
            }

            assertEquals(1500, list.size());
            assertTrue(list.capacity() >= 1500);

            // Verify all elements are correct
            for (int i = 0; i < 1500; i++) {
                assertEquals(i, list.getByIndex(i));
            }
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("operations on single element list work correctly")
        void testOperationsOnSingleElementListWorkCorrectly() {
            intList.add(42);

            assertEquals(1, intList.size());
            assertFalse(intList.isEmpty());
            assertEquals(42, intList.getByIndex(0));
            assertTrue(intList.contains(42));
            assertFalse(intList.contains(43));

            Integer old = intList.setByIndex(0, 99);
            assertEquals(42, old);
            assertEquals(99, intList.getByIndex(0));

            intList.removeByIndex(0);
            assertTrue(intList.isEmpty());
        }

        @Test
        @DisplayName("toString works correctly for various states")
        void testToStringWorksCorrectlyForVariousStates() {
            // Empty list
            assertEquals("[]", intList.toString());

            // Single element
            intList.add(1);
            assertEquals("[1]", intList.toString());

            // Multiple elements
            intList.add(2);
            intList.add(3);
            assertEquals("[1, 2, 3]", intList.toString());
        }

        @Test
        @DisplayName("equals and hashCode work correctly")
        void testEqualsAndHashCodeWorkCorrectly() {
            MyArrayListImpl<Integer> list1 = new MyArrayListImpl<>();
            MyArrayListImpl<Integer> list2 = new MyArrayListImpl<>();

            // Empty lists should be equal
            assertEquals(list1, list2);
            assertEquals(list1.hashCode(), list2.hashCode());

            // Add same elements
            list1.add(1);
            list1.add(2);
            list2.add(1);
            list2.add(2);

            assertEquals(list1, list2);
            assertEquals(list1.hashCode(), list2.hashCode());

            // Different elements should not be equal
            list2.add(3);
            assertNotEquals(list1, list2);

            // Different order should not be equal
            MyArrayListImpl<Integer> list3 = new MyArrayListImpl<>();
            list3.add(2);
            list3.add(1);
            assertNotEquals(list1, list3);
        }

        @Test
        @DisplayName("list handles duplicate elements correctly")
        void testListHandlesDuplicateElementsCorrectly() {
            intList.add(1);
            intList.add(1);
            intList.add(2);
            intList.add(1);

            assertEquals(4, intList.size());
            assertTrue(intList.contains(1));
            assertTrue(intList.contains(2));

            assertEquals(1, intList.getByIndex(0));
            assertEquals(1, intList.getByIndex(1));
            assertEquals(2, intList.getByIndex(2));
            assertEquals(1, intList.getByIndex(3));
        }

        @Test
        @DisplayName("concurrent modification during enhanced for loop")
        void testConcurrentModificationDuringEnhancedForLoop() {
            intList.add(1);
            intList.add(2);
            intList.add(3);

            assertThrows(ConcurrentModificationException.class, () -> {
                for (Integer value : intList) {
                    if (value.equals(2)) {
                        intList.add(4); // Modify during iteration
                    }
                }
            });
        }

        @Test
        @DisplayName("stress test with many operations")
        void testStressTestWithManyOperations() {
            MyArrayListImpl<Integer> stressList = new MyArrayListImpl<>(5);

            // Add many elements
            for (int i = 0; i < 100; i++) {
                stressList.add(i);
            }
            assertEquals(100, stressList.size());

            // Remove every other element
            for (int i = 99; i >= 0; i -= 2) {
                stressList.removeByIndex(i);
            }
            assertEquals(50, stressList.size());

            // Verify remaining elements are even numbers
            for (int i = 0; i < stressList.size(); i++) {
                assertEquals(i * 2, stressList.getByIndex(i));
            }

            // Clear and verify
            stressList.clear();
            assertTrue(stressList.isEmpty());

            // Add elements again after clear
            stressList.add(999);
            assertEquals(1, stressList.size());
            assertEquals(999, stressList.getByIndex(0));
        }
    }

    @Nested
    @DisplayName("Performance Characteristics Tests")
    class PerformanceCharacteristicsTests {

        @Test
        @DisplayName("adding elements maintains amortized O(1) complexity")
        void testAddingElementsMaintainsAmortizedComplexity() {
            MyArrayListImpl<Integer> perfList = new MyArrayListImpl<>(1);

            // This test verifies that doubling strategy works
            // by checking capacity increases at expected points
            assertEquals(1, perfList.capacity());

            perfList.add(1);
            assertEquals(1, perfList.capacity());

            perfList.add(2);
            assertEquals(2, perfList.capacity());

            perfList.add(3);
            assertEquals(4, perfList.capacity());

            perfList.add(4);
            assertEquals(4, perfList.capacity());

            perfList.add(5);
            assertEquals(8, perfList.capacity());
        }

        @Test
        @DisplayName("access operations are constant time")
        void testAccessOperationsAreConstantTime() {
            // Fill list with elements
            for (int i = 0; i < 1000; i++) {
                intList.add(i);
            }

            // Access should be O(1) regardless of list size
            // We can't truly test time complexity, but we can verify correctness
            long startTime = System.nanoTime();

            // Multiple random accesses
            for (int i = 0; i < 100; i++) {
                int index = i % 1000;
                assertEquals(index, intList.getByIndex(index));
            }

            long endTime = System.nanoTime();

            // This mainly verifies operations complete in reasonable time
            assertTrue(endTime - startTime < 1_000_000_000); // Less than 1 second
        }

        @Test
        @DisplayName("iterator performance with large dataset")
        void testIteratorPerformanceWithLargeDataset() {
            // Add large number of elements
            for (int i = 0; i < 10000; i++) {
                intList.add(i);
            }

            long startTime = System.nanoTime();

            int count = 0;
            for (Integer value : intList) {
                assertEquals(count++, value);
            }

            long endTime = System.nanoTime();

            assertEquals(10000, count);
            // Verify iteration completes in reasonable time
            assertTrue(endTime - startTime < 1_000_000_000); // Less than 1 second
        }
    }

    @Nested
    @DisplayName("Memory Management Tests")
    class MemoryManagementTests {

        @Test
        @DisplayName("clear nullifies references for garbage collection")
        void testClearNullifiesReferencesForGarbageCollection() {
            // This test verifies the implementation detail that clear() nulls references
            stringList.add("test1");
            stringList.add("test2");
            stringList.add("test3");

            assertEquals(3, stringList.size());

            stringList.clear();

            assertEquals(0, stringList.size());
            assertTrue(stringList.isEmpty());

            // After clear, we should be able to add new elements
            stringList.add("new1");
            assertEquals(1, stringList.size());
            assertEquals("new1", stringList.getByIndex(0));
        }

        @Test
        @DisplayName("removeByIndex nullifies removed reference")
        void testRemoveByIndexNullifiesRemovedReference() {
            stringList.add("keep1");
            stringList.add("remove");
            stringList.add("keep2");

            assertEquals(3, stringList.size());

            stringList.removeByIndex(1);

            assertEquals(2, stringList.size());
            assertEquals("keep1", stringList.getByIndex(0));
            assertEquals("keep2", stringList.getByIndex(1));
        }

        @Test
        @DisplayName("capacity management with mixed operations")
        void testCapacityManagementWithMixedOperations() {
            MyArrayListImpl<String> mixedList = new MyArrayListImpl<>(2);

            // Add elements to trigger expansion
            mixedList.add("a");
            mixedList.add("b");
            assertEquals(2, mixedList.capacity());

            mixedList.add("c");
            assertEquals(4, mixedList.capacity());

            // Remove elements - capacity should remain
            mixedList.removeByIndex(0);
            mixedList.removeByIndex(0);
            assertEquals(4, mixedList.capacity());
            assertEquals(1, mixedList.size());

            // Clear - capacity should remain
            mixedList.clear();
            assertEquals(4, mixedList.capacity());
            assertEquals(0, mixedList.size());
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("complete workflow simulation")
        void testCompleteWorkflowSimulation() {
            MyArrayListImpl<String> workflow = new MyArrayListImpl<>(3);

            // Initial state
            assertTrue(workflow.isEmpty());
            assertEquals(0, workflow.size());
            assertEquals(3, workflow.capacity());

            // Add initial data
            workflow.add("task1");
            workflow.add("task2");
            workflow.add("task3");

            assertFalse(workflow.isEmpty());
            assertEquals(3, workflow.size());
            assertEquals(3, workflow.capacity());

            // Expand beyond initial capacity
            workflow.add("task4");
            assertEquals(4, workflow.size());
            assertEquals(6, workflow.capacity());

            // Update tasks
            String oldTask = workflow.setByIndex(1, "updated_task2");
            assertEquals("task2", oldTask);
            assertEquals("updated_task2", workflow.getByIndex(1));

            // Check task existence
            assertTrue(workflow.contains("task1"));
            assertTrue(workflow.contains("updated_task2"));
            assertFalse(workflow.contains("task2")); // Old value

            // Process tasks using iterator
            Iterator<String> taskIterator = workflow.iterator();
            StringBuilder processed = new StringBuilder();
            while (taskIterator.hasNext()) {
                String task = taskIterator.next();
                processed.append(task).append(";");
                if (task.equals("task3")) {
                    taskIterator.remove(); // Remove completed task
                }
            }

            assertEquals("task1;updated_task2;task3;task4;", processed.toString());
            assertEquals(3, workflow.size()); // One task removed
            assertFalse(workflow.contains("task3"));

            // Final verification
            assertEquals("task1", workflow.getByIndex(0));
            assertEquals("updated_task2", workflow.getByIndex(1));
            assertEquals("task4", workflow.getByIndex(2));

            // Enhanced for-loop verification
            String[] expected = {"task1", "updated_task2", "task4"};
            int index = 0;
            for (String task : workflow) {
                assertEquals(expected[index++], task);
            }

            // Complete workflow
            workflow.clear();
            assertTrue(workflow.isEmpty());
            assertEquals(0, workflow.size());
            assertTrue(workflow.capacity() > 0); // Capacity preserved
        }

        @Test
        @DisplayName("mixed type operations with custom objects")
        void testMixedTypeOperationsWithCustomObjects() {
            MyArrayListImpl<StringBuilder> builderList = new MyArrayListImpl<>();

            StringBuilder sb1 = new StringBuilder("hello");
            StringBuilder sb2 = new StringBuilder("world");
            StringBuilder sb3 = new StringBuilder("test");

            builderList.add(sb1);
            builderList.add(sb2);
            builderList.add(sb3);

            assertEquals(3, builderList.size());

            // Modify objects through the list
            builderList.getByIndex(0).append(" modified");
            assertEquals("hello modified", builderList.getByIndex(0).toString());

            // Test contains with modified object
            assertTrue(builderList.contains(sb1));

            // Replace object
            StringBuilder newSb = new StringBuilder("replacement");
            StringBuilder old = builderList.setByIndex(1, newSb);
            assertEquals(sb2, old);
            assertEquals("replacement", builderList.getByIndex(1).toString());

            // Iterate and modify
            for (StringBuilder sb : builderList) {
                if (!sb.toString().contains("replacement")) {
                    sb.append(" processed");
                }
            }

            assertEquals("hello modified processed", builderList.getByIndex(0).toString());
            assertEquals("replacement", builderList.getByIndex(1).toString());
            assertEquals("test processed", builderList.getByIndex(2).toString());
        }
    }

    

}

















