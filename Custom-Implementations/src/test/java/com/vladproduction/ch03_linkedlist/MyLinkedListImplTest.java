package com.vladproduction.ch03_linkedlist;

import org.junit.jupiter.api.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListImplTest {

    private MyLinkedList<String> stringList;
    private MyLinkedList<Integer> intList;

    @BeforeEach
    void setUp() {
        stringList = new MyLinkedListImpl<>();
        intList = new MyLinkedListImpl<>();
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
        @DisplayName("default constructor creates empty list")
        void testDefaultConstructorCreatesEmptyList() {
            MyLinkedList<String> newList = new MyLinkedListImpl<>();

            assertTrue(newList.isEmpty());
            assertEquals(0, newList.size());
        }

        @Test
        @DisplayName("multiple instances are independent")
        void testMultipleInstancesAreIndependent() {
            MyLinkedList<String> list1 = new MyLinkedListImpl<>();
            MyLinkedList<String> list2 = new MyLinkedListImpl<>();

            list1.add("Test1");
            list2.add("Test2");

            assertEquals(1, list1.size());
            assertEquals(1, list2.size());
            assertEquals("Test1", list1.getByIndex(0));
            assertEquals("Test2", list2.getByIndex(0));
        }

        @Test
        @DisplayName("parameterized constructor with different types")
        void testParameterizedConstructorWithDifferentTypes() {
            MyLinkedList<Integer> intList = new MyLinkedListImpl<>();
            MyLinkedList<String> stringList = new MyLinkedListImpl<>();
            MyLinkedList<Double> doubleList = new MyLinkedListImpl<>();

            assertTrue(intList.isEmpty());
            assertTrue(stringList.isEmpty());
            assertTrue(doubleList.isEmpty());
        }
    }

    @Nested
    @DisplayName("Basic Operation Tests")
    class BasicOperationTests {

        @Test
        @DisplayName("add single element increases size")
        void testAddSingleElementIncreasesSize() {
            stringList.add("Test");

            assertFalse(stringList.isEmpty());
            assertEquals(1, stringList.size());
        }

        @Test
        @DisplayName("add multiple elements maintains order")
        void testAddMultipleElementsMaintainsOrder() {
            stringList.add("Test1");
            stringList.add("Test2");
            stringList.add("Test3");

            assertEquals(3, stringList.size());
            assertEquals("Test1", stringList.getByIndex(0));
            assertEquals("Test2", stringList.getByIndex(1));
            assertEquals("Test3", stringList.getByIndex(2));
        }

        @Test
        @DisplayName("add null element throws exception")
        void testAddNullElementThrowsException() {

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> stringList.add(null)
            );

            assertEquals("Can not add null to the list", exception.getMessage());
        }

        @Test
        @DisplayName("isEmpty returns true for new list")
        void testIsEmptyReturnTrueForNewList(){
            assertTrue(stringList.isEmpty());
            assertTrue(intList.isEmpty());
        }

        @Test
        @DisplayName("isEmpty return false after add element into list")
        void testIsEmptyReturnFalseAfterAddElementIntoList(){
            stringList.add("Test");
            intList.add(100);

            assertFalse(stringList.isEmpty());
            assertFalse(intList.isEmpty());
        }

        @Test
        @DisplayName("size returns zero for new list")
        void testSizeReturnZeroForNewList(){
            assertEquals(0, stringList.size());
            assertEquals(0, intList.size());
        }

        @Test
        @DisplayName("size increases correctly with multiple additions")
        void testSizeIncreasesCorrectlyWithMultipleAdditions(){
            for (int i = 1; i <= 5; i++) {
                intList.add(i * 1000);
                assertEquals(i, intList.size());
            }
        }
    }

    @Nested
    @DisplayName("Access Operations Tests")
    class AccessOperationsTests {

        @BeforeEach
        void setUpWithElements() {
            stringList.add("Zero");
            stringList.add("First");
            stringList.add("Second");
            stringList.add("Third");
        }

        @Test
        @DisplayName("getByIndex retrieves correct elements")
        void testGetByIndexRetrievesCorrectElements() {
            assertEquals("Zero", stringList.getByIndex(0));
            assertEquals("First", stringList.getByIndex(1));
            assertEquals("Second", stringList.getByIndex(2));
            assertEquals("Third", stringList.getByIndex(3));
        }

        @Test
        @DisplayName("getByIndex throws exception for negative index")
        void testGetByIndexThrowsExceptionForNegativeIndex() {
            IndexOutOfBoundsException exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> stringList.getByIndex(-1)
            );

            assertEquals("Index: " + -1 + ", Size: " + stringList.size(), exception.getMessage());
        }

        @Test
        @DisplayName("getByIndex throws exception for index greater than size")
        void testGetByIndexThrowsExceptionForIndexGreaterThanSize() {
            IndexOutOfBoundsException exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> stringList.getByIndex(4)
            );
            assertEquals("Index: " + 4 + ", Size: " + stringList.size(), exception.getMessage());
        }

        @Test
        @DisplayName("getByIndex throws exception for index equal to size")
        void testGetByIndexThrowsExceptionForIndexEqualToSize() {
            IndexOutOfBoundsException exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> stringList.getByIndex(4)
            );
            assertEquals("Index: " + 4 + ", Size: " + stringList.size(), exception.getMessage());
        }

        @Test
        @DisplayName("setByIndex updates and returns old value")
        void testSetByIndexUpdatesAndReturnsOldValue() {
            String oldValue = stringList.setByIndex(1, "Updated");
            assertEquals("First", oldValue);
            assertEquals("Updated", stringList.getByIndex(1));
            assertEquals(4, stringList.size());                       //should remain same
        }

        @Test
        @DisplayName("setByIndex throws exception for invalid indices")
        void testThrowingExceptionForSetInvalidIndexes(){
            //negative index
            IndexOutOfBoundsException exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> stringList.setByIndex(-1, "Updated")
            );
            assertEquals("Index: " + -1 + ", Size: " + stringList.size(), exception.getMessage());

            //index is greater than size
            IndexOutOfBoundsException greaterException = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> stringList.setByIndex(5, "Updated")
            );
            assertEquals("Index: " + 5 + ", Size: " + stringList.size(), greaterException.getMessage());
        }

        @Test
        @DisplayName("setByIndex throws exception for null values")
        void testThrowingExceptionForSetNullValues(){
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> stringList.setByIndex(0, null)
            );
            assertEquals("Cannot set null item to the list", exception.getMessage());
        }

        @Test
        @DisplayName("getByIndex from empty list throws exception")
        void testGetByIndexFromEmptyListThrowsException(){
            MyLinkedList<String> emptyList = new MyLinkedListImpl<>();
            IndexOutOfBoundsException exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> emptyList.getByIndex(0)
            );
            assertEquals("Index: " + 0 + ", Size: " + emptyList.size(), exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Contains Operation Tests")
    class ContainsOperationTests {

        @BeforeEach
        void setupContainsTests() {
            stringList.add("Apple");
            stringList.add("Banana");
            stringList.add("Cherry");
            stringList.add("Date");
        }

        @Test
        @DisplayName("contains returns true for existing elements")
        void testContainsReturnsTrueForExistingElements() {
            assertTrue(stringList.contains("Apple"));
            assertTrue(stringList.contains("Banana"));
            assertTrue(stringList.contains("Cherry"));
            assertTrue(stringList.contains("Date"));
        }

        @Test
        @DisplayName("contains returns false for non-existing elements")
        void testContainsReturnsFalseForNonExistingElements() {
            assertFalse(stringList.contains("Pear"));
            assertFalse(stringList.contains("Orange"));
            assertFalse(stringList.contains("Grape"));
            assertFalse(stringList.contains("Strawberry"));
            assertFalse(stringList.contains(""));
            assertFalse(stringList.contains(" - "));
            assertFalse(stringList.contains(" . "));
        }

        @Test
        @DisplayName("contains returns false for null elements")
        void testContainsReturnsFalseForNullElements() {
            assertFalse(stringList.contains(null));
        }

        @Test
        @DisplayName("contains returns false for empty list")
        void testContainsReturnsFalseForEmptyList(){
            MyLinkedList<String> emptyList = new MyLinkedListImpl<>();
            assertFalse(emptyList.contains("Nothing in the list"));
        }

        @Test
        @DisplayName("contains work with different data types")
        void testContainsWorkWithDifferentDataTypes(){
            intList.add(100);
            intList.add(200);
            intList.add(300);

            assertTrue(intList.contains(100));
            assertFalse(intList.contains(150));
        }

        @Test
        @DisplayName("contains work with duplicates elements in the list")
        void testContainsWorkWithDuplicatesElementsInTheList(){
            stringList.add("Apple");
            stringList.add("Apple");
            stringList.add("Apple");

            assertTrue(stringList.contains("Apple"));
            assertEquals(7, stringList.size());             //it should be '7' elements in the list now
        }
    }

    @Nested
    @DisplayName("Remove Operations Tests")
    class RemoveOperationTests {

        @BeforeEach
        void setupRemoveTests() {
            stringList.add("First");
            stringList.add("Second");
            stringList.add("Third");
            stringList.add("Fourth");
            stringList.add("Fifth");
        }

        @Test
        @DisplayName("removeByIndex removes element and shifts remaining elements")
        void testRemoveByIndexRemovesElementAndShiftsRemainingElements() {

            stringList.removeByIndex(2); // removing "Third" element

            assertEquals(4, stringList.size());
            assertEquals("First", stringList.getByIndex(0));
            assertEquals("Second", stringList.getByIndex(1));
            assertEquals("Fourth", stringList.getByIndex(2)); // shifted
            assertEquals("Fifth", stringList.getByIndex(3));  // shifted
        }

        @Test
        @DisplayName("removeByIndex removes first element correctly")
        void testRemoveByIndexRemovesFirstElementCorrectly() {
            stringList.removeByIndex(0);

            assertEquals(4, stringList.size());
            assertEquals("Second", stringList.getByIndex(0));
        }

        @Test
        @DisplayName("removeByIndex removes last element correctly")
        void testRemoveByIndexRemovesLastElementCorrectly() {
            int lastIndex = stringList.size() - 1;
            stringList.removeByIndex(lastIndex);

            assertEquals(4, stringList.size());
            assertEquals("Fourth", stringList.getByIndex(stringList.size() - 1));
        }

        @Test
        @DisplayName("removeByIndex throws exception for invalid indices")
        void testThrowingExceptionWhileRemovingByInvalidIndex(){
            //negative index
            IndexOutOfBoundsException negativeException = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> stringList.removeByIndex(-1)
            );
            assertEquals("Index: " + -1 + ", Size: " + stringList.size(), negativeException.getMessage());

            //index is greater than size
            IndexOutOfBoundsException greaterException = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> stringList.removeByIndex(7)
            );
            assertEquals("Index: " + 7 + ", Size: " + stringList.size(), greaterException.getMessage());
        }

        @Test
        @DisplayName("removeByIndex from single element list makes it empty")
        void testRemoveByIndexFromSingleElementListMakesItEmpty() {
            MyLinkedList<String> singleElementList = new MyLinkedListImpl<>();
            singleElementList.add("Test");

            singleElementList.removeByIndex(0);
            assertTrue(singleElementList.isEmpty());
            assertEquals(0, singleElementList.size());
        }

        @Test
        @DisplayName("removeByIndex from empty list throws exception")
        void testRemoveByIndexFromEmptyListThrowsException(){
            MyLinkedList<String> emptyList = new MyLinkedListImpl<>();
            IndexOutOfBoundsException exception = assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> emptyList.removeByIndex(0)
            );

            assertEquals("Index: " + 0 + ", Size: " + emptyList.size(), exception.getMessage());
        }

        @Test
        @DisplayName("multiple removes work correctly")
        void testMultipleRemovesWorkCorrectly(){

            //remove elements from the end to avoid index shifting issues
            stringList.removeByIndex(4);
            stringList.removeByIndex(3);
            stringList.removeByIndex(2);

            assertEquals(2, stringList.size());
            assertEquals("First", stringList.getByIndex(0));
            assertEquals("Second", stringList.getByIndex(1));
        }
    }

    @Nested
    @DisplayName("Clear Operation Tests")
    class ClearOperationTests {

        @Test
        @DisplayName("clear removes all elements from populated list")
        void testClearRemovesAllElementsFromPopulatedList() {
            stringList.add("First");
            stringList.add("Second");
            stringList.add("Third");

            stringList.clear();

            assertTrue(stringList.isEmpty());
            assertEquals(0, stringList.size());
        }

        @Test
        @DisplayName("clear on empty list works without issues")
        void testClearOnEmptyListWorksWithoutIssues(){
            MyLinkedList<String> emptyList = new MyLinkedListImpl<>();
            emptyList.clear();

            assertEquals(0, stringList.size());
            assertTrue(emptyList.isEmpty());
        }

        @Test
        @DisplayName("clear followed by add works correctly")
        void testClearFollowedByAddWorksCorrectly(){
            stringList.add("element1");
            stringList.clear();
            stringList.add("element2");

            assertFalse(stringList.isEmpty());
            assertEquals(1, stringList.size());
            assertEquals("element2", stringList.getByIndex(0));
        }

        @Test
        @DisplayName("clear works with large lists")
        void testClearWorksWithLargeLists(){
            for (int i = 1; i <= 10_000; i++) {
                stringList.add("element" + i);
            }

            stringList.clear();

            assertEquals(0, stringList.size());
            assertTrue(stringList.isEmpty());
        }
    }

    @Nested
    @DisplayName("Iterator Tests")
    class IteratorTests {

        @BeforeEach
        void setupIteratorTests() {
            stringList.add("A");
            stringList.add("B");
            stringList.add("C");
            stringList.add("D");
        }

        @Test
        @DisplayName("iterator traverse elements and return in correct order")
        void testIteratorTraverseElementsAndReturnInCorrectOrder() {
            Iterator<String> iterator = stringList.iterator();
            String[] expected = {"A", "B", "C", "D"};
            int index = 0;

            while (iterator.hasNext()) {
                assertEquals(expected[index], iterator.next());
                index++;
            }

            assertEquals(4, index);
            assertEquals(expected.length, index);
        }

        @Test
        @DisplayName("hasNext returns false for empty list")
        void testHasNextReturnsFalseForEmptyList(){
            MyLinkedList<String> emptyList = new MyLinkedListImpl<>();
            Iterator<String> iterator = emptyList.iterator();

            assertFalse(iterator.hasNext());
        }

        @Test
        @DisplayName("next throws exception when no more elements")
        void testNextThrowsExceptionWhenNoMoreElements(){
            Iterator<String> iterator = stringList.iterator();
            //consuming all elements
            while (iterator.hasNext()) {
                iterator.next();
            }

            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        @DisplayName("iterator remove works correctly")
        void testIteratorRemoveWorksCorrectly(){
            Iterator<String> iterator = stringList.iterator();
            //move to the second element and remove it
            iterator.next(); //A
            iterator.next(); //B

            iterator.remove(); //removing B

            //rest elements should shift to the left correctly
            assertEquals(3, stringList.size());
            assertEquals("A", stringList.getByIndex(0));
            assertEquals("C", stringList.getByIndex(1));
            assertEquals("D", stringList.getByIndex(2));
        }

        @Test
        @DisplayName("iterator remove throws exception when called before next")
        void testIteratorRemoveThrowsExceptionWhenCalledBeforeNext(){
            Iterator<String> iterator = stringList.iterator();
            assertThrows(IllegalStateException.class, iterator::remove);
        }

        @Test
        @DisplayName("iterator remove throws exception when called twice")
        void testThrowingExceptionForIteratorRemoveCalledTwice(){
            Iterator<String> iterator = stringList.iterator();
            iterator.next();
            iterator.remove();          //the first remove should be successful

            assertThrows(IllegalStateException.class, iterator::remove);   //second remove should throw exception as expected
        }

        @Test
        @DisplayName("enhanced for loop works correctly")
        void testEnhancedForLoopWorksCorrectly(){
            String[] expected = {"A", "B", "C", "D"};
            int index = 0;

            for (String string : stringList) {
                assertEquals(expected[index], string);
                index++;
            }

            assertEquals(4, index);
        }

        @Test
        @DisplayName("multiple iterators work independently")
        void testMultipleIteratorsWorkIndependently(){
            Iterator<String> iterator1 = stringList.iterator();
            Iterator<String> iterator2 = stringList.iterator();

            assertEquals("A", iterator1.next());
            assertEquals("B", iterator1.next());
            assertEquals("A", iterator2.next());
            assertEquals("B", iterator2.next());
        }

        @Test
        @DisplayName("concurrent modification throws exception")
        void testConcurrentModificationThrowsException(){
            Iterator<String> iterator = stringList.iterator();
            iterator.next();                                            //positioning iterator
            stringList.add("E");                                        //modifying the list

            assertThrows(ConcurrentModificationException.class, iterator::hasNext);
        }

        @Test
        @DisplayName("concurrent modification with remove throws exception")
        void testConcurrentModificationWithRemoveThrowsException(){
            Iterator<String> iterator = stringList.iterator();

            iterator.next();                                           //position iterator
            stringList.removeByIndex(0);                               //modifying the list

            assertThrows(ConcurrentModificationException.class, iterator::next);
        }


    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("single element operations work correctly")
        void testSingleElementOperationsWorkCorrectly(){
            MyLinkedList<String> singleList = new MyLinkedListImpl<>();
            singleList.add("Only");

            assertEquals(1, singleList.size());
            assertEquals("Only", singleList.getByIndex(0));
            assertTrue(singleList.contains("Only"));
            assertEquals("Only", singleList.setByIndex(0, "Changed"));
            assertEquals("Changed", singleList.getByIndex(0));
        }

        @Test
        @DisplayName("duplicate elements are handled correctly")
        void testDuplicateElementsAreHandledCorrectly() {
            stringList.add("Duplicate");
            stringList.add("Duplicate");
            stringList.add("Duplicate");

            assertEquals(3, stringList.size());
            assertTrue(stringList.contains("Duplicate"));
            assertEquals("Duplicate", stringList.getByIndex(0));
            assertEquals("Duplicate", stringList.getByIndex(1));
            assertEquals("Duplicate", stringList.getByIndex(2));

            //-------investigation for the following test-------
            assertEquals(stringList.getByIndex(0).hashCode(), "Duplicate".hashCode());
            assertEquals(stringList.getByIndex(1).hashCode(), "Duplicate".hashCode());
            assertEquals(stringList.getByIndex(2).hashCode(), "Duplicate".hashCode());
        }

        @Test
        @DisplayName("toString returns appropriate representation")
        void testToStringReturnsAppropriateRepresentation() {
            stringList.add("First");
            stringList.add("Second");

            String result = stringList.toString();

            assertNotNull(result);
            assertTrue(result.contains("First"));
            assertTrue(result.contains("Second"));

            assertEquals(result, stringList.toString());
            assertEquals("[First, Second]", stringList.toString());
        }

        @Test
        @DisplayName("empty list toString works")
        void testEmptyListToStringWorks() {
            String result = stringList.toString();

            assertNotNull(result);
            // Should not throw exception
        }

        @Test
        @DisplayName("operations work with custom objects")
        void testOperationsWorkWithCustomObjects(){
            MyLinkedList<StringBuilder> builderList = new MyLinkedListImpl<>();

            StringBuilder builder1 = new StringBuilder("Hello");
            StringBuilder builder2 = new StringBuilder("World");
            StringBuilder builder3 = new StringBuilder("!");

            builderList.add(builder1);
            builderList.add(builder2);
            builderList.add(builder3);

            assertEquals(3, builderList.size());
            assertEquals(builder1, builderList.getByIndex(0));
            assertEquals("World", builderList.getByIndex(1).toString());
            assertEquals("!", builderList.getByIndex(2).toString());
            assertTrue(builderList.contains(builder3));
        }
    }

    @Nested
    @DisplayName("Performance Characteristic Tests")
    class PerformanceCharacteristicTests {

        @Test
        @DisplayName("add operation performance")
        void testAddOperationPerformance() {
            long start = System.nanoTime();
            for (int i = 0; i < 10_000; i++) {
                intList.add(i);
            }
            long end = System.nanoTime();
            long duration = end - start;

            assertEquals(10_000, intList.size());
            // Add should be O(1) for linked list, so this should complete quickly
            assertTrue(duration < 1_000_000_000, "Add operations took too long: " + duration + " ns");
        }

        @Test
        @DisplayName("get operation performance characteristics")
        void testGetOperationPerformanceCharacteristics() {

            //add 1000 elements to the list
            for (int i = 0; i < 10_000; i++) {
                intList.add(i);
            }

            //check timing
            long start = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                intList.getByIndex(i);
            }
            long end = System.nanoTime();
            long duration = end - start;

            //get operation should be O(1) for linked list, so this should complete quickly
            //no timeout expected
            assertTrue(duration > 1000);
        }

        @Test
        @DisplayName("clear operation performance")
        void testClearOperationPerformance() {
            // Add many elements
            for (int i = 0; i < 10_000; i++) {
                intList.add(i);
            }

            long startTime = System.nanoTime();
            intList.clear();
            long endTime = System.nanoTime();

            assertEquals(0, intList.size());
            assertTrue(intList.isEmpty());

            long duration = endTime - startTime;
            assertTrue(duration < 100_000_000, "Clear operation took too long: " + duration + " ns");
        }
    }

    @Nested
    @DisplayName("Memory Managements Tests")
    class MemoryManagementsTests {

        @Test
        @DisplayName("clear operation releases references")
        void testClearOperationReleasesReferences(){

            // adding elements
            for (int i = 0; i < 100; i++) {
                stringList.add("Element" + i);
            }

            // clear and verify
            stringList.clear();

            assertTrue(stringList.isEmpty());
            assertEquals(0, stringList.size());

            // trying to add new elements to ensure the list is still functional
            stringList.add("New Element");
            assertEquals(1, stringList.size());
        }

        @Test
        @DisplayName("remove operations release references")
        void testRemoveOperationsReleaseReferences() {
            stringList.add("First");
            stringList.add("Second");
            stringList.add("Third");

            stringList.removeByIndex(1); // removing in the middle of the list element

            assertEquals(2, stringList.size());
            assertEquals("First", stringList.getByIndex(0));
            assertEquals("Third", stringList.getByIndex(1));
        }

        @Test
        @DisplayName("iterator remove releases references")
        void testIteratorRemoveReleasesReferences(){
            stringList.add("First");
            stringList.add("Second");
            stringList.add("Third");

            Iterator<String> iterator = stringList.iterator();
            iterator.next();                                                //move to the first element
            iterator.remove();                                              //removes the first element

            assertEquals(2, stringList.size());
            assertEquals("Second", stringList.getByIndex(0));
            assertEquals("Third", stringList.getByIndex(1));
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("mixed operations workflow simulation")
        void testMixedOperationsWorkflowSimulation() {

            MyLinkedList<String> taskList = new MyLinkedListImpl<>();

            taskList.add("Buy milk");
            taskList.add("Buy eggs");
            taskList.add("Buy bread");

            assertEquals(3, taskList.size());

            //update task
            String oldTask = taskList.setByIndex(0, "Buy milk - DONE!");
            assertEquals("Buy milk", oldTask);
            assertEquals("Buy milk - DONE!", taskList.getByIndex(0));

            //check if specific tasks are existing
            assertTrue(taskList.contains("Buy milk - DONE!"));
            assertFalse(taskList.contains("Buy milk"));
            assertTrue(taskList.contains("Buy eggs"));
            assertTrue(taskList.contains("Buy bread"));

            //removing a completed task
            taskList.removeByIndex(0);
            assertEquals(2, taskList.size());
            assertFalse(taskList.contains("Buy milk - DONE!"));

            //adding new tasks
            taskList.add("Cook dinner");
            taskList.add("Prepare cakes");
            assertEquals(4, taskList.size());

            // iterating through all tasks
            int count = 0;
            for (String task : taskList) {
                assertNotNull(task);
                count++;
            }
            assertEquals(4, count);

            // clear all tasks
            taskList.clear();
            assertTrue(taskList.isEmpty());
        }

        @Test
        @DisplayName("concurrent iterator usage simulation")
        void testConcurrentIteratorUsageSimulation() {
            for (int i = 1; i <= 10; i++) {
                intList.add(i);
            }

            //use iterator to remove even numbers
            Iterator<Integer> iterator = intList.iterator();
            while (iterator.hasNext()) {
                int current = iterator.next();
                if (current % 2 == 0) {
                    iterator.remove();
                }
            }

            //verify only odd numbers remain
            assertEquals(5, intList.size());
            for (int i = 0; i < intList.size(); i++) {
                assertEquals(1, intList.getByIndex(i) % 2);
            }

            //add more elements and verify integrity
            intList.add(11);
            intList.add(17);

            assertEquals(7, intList.size());
            assertEquals(Integer.valueOf(11), intList.getByIndex(5));
            assertEquals(Integer.valueOf(17), intList.getByIndex(6));
        }
    }

}














