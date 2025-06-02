package com.vladproduction.ch01_bag.typesafety;

import org.junit.jupiter.api.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BagResizingImplTest {

    private BagResizingImpl<String> stringBag;
    private BagResizingImpl<Integer> intBag;

    @BeforeEach
    void setUp() {
        stringBag = new BagResizingImpl<>();
        intBag = new BagResizingImpl<>();
    }

    @AfterEach
    void tearDown() {
        stringBag = null;
        intBag = null;
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests{

        @Test
        @DisplayName("default constructor creates bag with default capacity")
        void testDefaultConstructorCreatesBagWithDefaultCapacity(){
            BagResizingImpl<String> defBag = new BagResizingImpl<>();

            assertTrue(defBag.isEmpty());
            assertEquals(0, defBag.size());
            assertEquals(10, defBag.capacity());
        }

        @Test
        @DisplayName("parameterized constructor creates bag with specified capacity")
        void testParameterizedConstructorWithSpecifiedCapacity(){
            BagResizingImpl<Integer> parameterizedBag = new BagResizingImpl<>(5);

            assertTrue(parameterizedBag.isEmpty());
            assertEquals(0, parameterizedBag.size());
            assertEquals(5, parameterizedBag.capacity());
        }

        @Test
        @DisplayName("constructor with zero capacity creates valid bag")
        void testConstructorWithZeroInitialCapacity(){
            BagResizingImpl<String> zeroBag = new BagResizingImpl<>(0);

            assertTrue(zeroBag.isEmpty());
            assertEquals(0, zeroBag.size());
            assertEquals(0, zeroBag.capacity());
        }

        @Test
        @DisplayName("constructor throwing exception with negative capacity")
        void testConstructorThrowingExceptionWithNegativeCapacity(){
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new BagResizingImpl<String>(-1)
            );

            assertEquals("Capacity cannot be negative", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Basic Operations Tests")
    class BasicOperationsTests{

        @Test
        @DisplayName("isEmpty returns true for newly created bag")
        void testIsEmptyReturnTrueForNewBag(){
            assertTrue(intBag.isEmpty());
            assertTrue(stringBag.isEmpty());
        }

        @Test
        @DisplayName("isEmpty returns false after adding elements")
        void testReturnFalseAfterAddingElements(){
            assertTrue(intBag.isEmpty());
            intBag.add(1);
            assertFalse(intBag.isEmpty());

            assertTrue(stringBag.isEmpty());
            stringBag.add("element");
            assertFalse(stringBag.isEmpty());
        }

        @Test
        @DisplayName("size returns zero for newly created bag")
        void testReturnZeroSizeForNewlyCreatedBag(){
            assertEquals(0, intBag.size());
            assertEquals(0, stringBag.size());
        }

        @Test
        @DisplayName("size increases correctly after adding elements")
        void testCorrectSizeAfterAddingElements(){
            assertTrue(intBag.isEmpty());
            assertEquals(0, intBag.size());

            intBag.add(1);
            intBag.add(2);
            intBag.add(3);

            assertEquals(3, intBag.size());
            assertFalse(intBag.isEmpty());

            assertTrue(stringBag.isEmpty());
            assertEquals(0, stringBag.size());

            stringBag.add("item 1");
            stringBag.add("item 2");
            stringBag.add("item 3");

            assertFalse(stringBag.isEmpty());
            assertEquals(3, stringBag.size());
        }
    }

    @Nested
    @DisplayName("Add Operation Tests")
    class AddOperationTests{

        @Test
        @DisplayName("add single element works correctly")
        void testAddSingleElementWorksCorrectly(){
            BagResizingImpl<String> singleElemBag = new BagResizingImpl<>();

            singleElemBag.add("test");

            assertFalse(singleElemBag.isEmpty());
            assertEquals(1, singleElemBag.size());
            assertEquals("[test]", singleElemBag.toString());
            assertTrue(singleElemBag.contains("test"));
        }

        @Test
        @DisplayName("add multiple elements within initial capacity")
        void testAddMultipleElementsWithinInitCapacity(){
            intBag.add(1);
            intBag.add(2);
            intBag.add(3);

            assertEquals(3, intBag.size());
            assertEquals("[1, 2, 3]", intBag.toString());
            assertEquals(10, intBag.capacity()); //should still be default capacity
        }

        @Test
        @DisplayName("add throws exception for null element")
        void testThrowingExceptionDuringAddNullElement(){
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> stringBag.add(null)
            );

            assertEquals("Cannot add null item to the bag", exception.getMessage());
            assertEquals(0, stringBag.size());
            assertTrue(stringBag.isEmpty());
        }

        @Test
        @DisplayName("add different types maintains type safety")
        void testAddDifferentTypesMaintainsTypeSafety() {
            intBag.add(1);
            intBag.add(42);
            intBag.add(-10);
            //intBag.add("-10"); //type safe, cannot add different types

            assertEquals(3, intBag.size());
            assertEquals("[1, 42, -10]", intBag.toString());
            assertTrue(intBag.contains(42));
        }

        @Test
        @DisplayName("add duplicate elements is allowed")
        void testAddDuplicateElementsIsAllowed() {
            stringBag.add("duplicate");
            stringBag.add("duplicate");
            stringBag.add("duplicate");

            assertEquals(3, stringBag.size());
            assertEquals("[duplicate, duplicate, duplicate]", stringBag.toString());
            assertTrue(stringBag.contains("duplicate"));
        }
    }

    @Nested
    @DisplayName("Dynamic Resizing Tests")
    class DynamicResizingTests{

        @Test
        @DisplayName("bag resizes when exceeding initial capacity")
        void testBagResizingWithInitialCapacity(){
            //fill up to capacity
            for (int i = 0; i < 10; i++) {
                 intBag.add(i);
            }

            assertEquals(10, intBag.size());
            assertFalse(intBag.isEmpty());

            //add the element and check increasing
            intBag.add(11);

            assertFalse(intBag.isEmpty());
            assertEquals(11, intBag.size());
            assertEquals(20, intBag.capacity()); //doubling capacity expected
            assertTrue(intBag.contains(11));
        }

        @Test
        @DisplayName("bag with small initial capacity resizes correctly")
        void testSmallBagResizingCorrectly(){
            BagResizingImpl<String> smallBag = new BagResizingImpl<>(2);

            smallBag.add("elem 1");
            smallBag.add("elem 2");

            assertEquals(2, smallBag.capacity());
            assertEquals(2, smallBag.size());

            //add one more element and check how capacity increased
            smallBag.add("elem 3");

            assertEquals(3, smallBag.size());
            assertEquals(4, smallBag.capacity()); //should double from 2 to 4
            assertEquals("[elem 1, elem 2, elem 3]", smallBag.toString());
        }

        @Test
        @DisplayName("bag with zero capacity handles first addition")
        void testHandlingFirstAdditionWithZeroCapacity(){
            BagResizingImpl<String> zeroBag = new BagResizingImpl<>(0);

            zeroBag.add("elem");

            assertEquals(1, zeroBag.size());
            assertEquals(1, zeroBag.capacity()); //due to Math.max(1, 0 * 2)
        }

        @Test
        @DisplayName("multiple resizes maintain data integrity")
        void testMultipleResizingMaintainDataIntegrity(){
            BagResizingImpl<Integer> smallBag = new BagResizingImpl<>(1);

            // adding elements to force multiple resizes: 1 -> 2 -> 4 -> 8
            for (int i = 0; i < 8; i++) {
                smallBag.add(i);
            }

            assertEquals(8, smallBag.size());
            assertEquals(8, smallBag.capacity());

            //verifying that all elements are preserved in the bag
            for (int i = 0; i < 8; i++) {
                assertTrue(smallBag.contains(i));
            }
        }
    }

    @Nested
    @DisplayName("Remove Operation Tests")
    class RemoveOperationTests{

        @Test
        @DisplayName("remove existing element decreases size")
        void testRemoveExistingElementDecreasingSize(){
            stringBag.add("a");
            stringBag.add("b");
            stringBag.add("c");

            assertEquals(3, stringBag.size());

            stringBag.remove("b");

            //should decrease size
            assertEquals(2, stringBag.size());
            assertEquals("[a, c]", stringBag.toString());
            assertFalse(stringBag.contains("b"));
        }

        @Test
        @DisplayName("remove first element works correctly")
        void testRemoveFirstElementWorksCorrectly() {
            stringBag.add("first");
            stringBag.add("second");
            stringBag.add("third");

            stringBag.remove("first");

            assertEquals("[second, third]", stringBag.toString());
            assertEquals(2, stringBag.size());
        }

        @Test
        @DisplayName("remove last element works correctly")
        void testRemoveLastElementWorksCorrectly() {
            stringBag.add("first");
            stringBag.add("second");
            stringBag.add("third");

            stringBag.remove("third");

            assertEquals("[first, second]", stringBag.toString());
            assertEquals(2, stringBag.size());
        }

        @Test
        @DisplayName("remove non-existing element does nothing")
        void testRemoveNonExistingElementDoesNothing() {
            stringBag.add("a");
            stringBag.add("b");
            int originalSize = stringBag.size();

            stringBag.remove("nonexistent");

            assertEquals(originalSize, stringBag.size());
            assertEquals("[a, b]", stringBag.toString());
        }

        @Test
        @DisplayName("remove only first occurrence of duplicate elements")
        void testRemoveOnlyFirstOccurrenceOfDuplicateElements() {
            stringBag.add("duplicate");
            stringBag.add("other");
            stringBag.add("duplicate");
            stringBag.add("duplicate");

            stringBag.remove("duplicate");

            assertEquals(3, stringBag.size());
            assertEquals("[other, duplicate, duplicate]", stringBag.toString());
        }

        @Test
        @DisplayName("remove throws exception for null element")
        void testRemoveThrowsExceptionForNullElement() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> stringBag.remove(null)
            );

            assertEquals("Cannot remove null item from the bag", exception.getMessage());
        }

        @Test
        @DisplayName("remove from empty bag does nothing")
        void testRemoveFromEmptyBagDoesNothing() {
            assertTrue(stringBag.isEmpty());

            stringBag.remove("nonexistent");

            assertTrue(stringBag.isEmpty());
            assertEquals(0, stringBag.size());
        }
    }

    @Nested
    @DisplayName("Contains Method Tests")
    class ContainsMethodTests{

        @Test
        @DisplayName("contains returns false for empty bag")
        void testContainsReturnsFalseForEmptyBag() {
            assertFalse(stringBag.contains("anything"));
            assertFalse(intBag.contains(42));
        }

        @Test
        @DisplayName("contains returns true for existing elements")
        void testContainsReturnsTrueForExistingElements() {
            stringBag.add("test");
            stringBag.add("another");

            assertTrue(stringBag.contains("test"));
            assertTrue(stringBag.contains("another"));
        }

        @Test
        @DisplayName("contains returns false for non-existing elements")
        void testContainsReturnsFalseForNonExistingElements() {
            stringBag.add("test");

            assertFalse(stringBag.contains("nonexistent"));
        }

        @Test
        @DisplayName("contains returns false for null")
        void testContainsReturnsFalseForNull() {
            stringBag.add("test");

            assertFalse(stringBag.contains(null));
        }

        @Test
        @DisplayName("contains works with different types")
        void testContainsWorksWithDifferentTypes() {
            intBag.add(1);
            intBag.add(42);
            intBag.add(-5);

            assertTrue(intBag.contains(42));
            assertTrue(intBag.contains(-5));
            assertFalse(intBag.contains(100));
        }
    }

    @Nested
    @DisplayName("Clear Method Tests")
    class ClearMethodTests{

        @Test
        @DisplayName("clear remove all elements and resets size to 0 and default capacity")
        void testClearRemovesAllElementsAndResetsSize() {
            stringBag.add("a");
            stringBag.add("b");
            stringBag.add("c");
            assertEquals(3, stringBag.size());

            stringBag.clear();

            assertEquals(0, stringBag.size());
            assertTrue(stringBag.isEmpty());
            assertEquals("[]", stringBag.toString());
            assertEquals(10, stringBag.capacity()); //capacity should remain unchanged
        }

        @Test
        @DisplayName("clear works on empty bag")
        void testClearWorksOnEmptyBag() {
            stringBag.clear();

            assertEquals(0, stringBag.size());
            assertTrue(stringBag.isEmpty());
            assertEquals("[]", stringBag.toString());
        }

        @Test
        @DisplayName("clear allows adding new elements after clearing")
        void testClearAllowsAddingNewElementsAfterClearing() {
            stringBag.add("before clear");
            stringBag.clear();
            stringBag.add("after clear");

            assertEquals(1, stringBag.size());
            assertEquals("[after clear]", stringBag.toString());
            assertTrue(stringBag.contains("after clear"));
            assertFalse(stringBag.contains("before clear"));
        }
    }

    @Nested
    @DisplayName("Iterator Tests")
    class IteratorTests{

        @Test
        @DisplayName("iterator returns all elements in order")
        void testIteratorReturningElementsInSameOrder(){
            String[] elements = {"first", "second", "third"};
            for (String element : elements) {
                stringBag.add(element);
            }

            Iterator<String> iterator = stringBag.iterator();
            int index = 0;
            while (iterator.hasNext()){
                assertEquals(elements[index++], iterator.next());
            }

            //expected the same numbers of elements in our bag and index after iterating
            assertEquals(elements.length, index);
        }

        @Test
        @DisplayName("iterator hasNext returns false for empty bag")
        void testIteratorReturnsFalseForEmptyBag(){
            Iterator<String> iterator = stringBag.iterator();
            assertFalse(iterator.hasNext());
        }

        @Test
        @DisplayName("iterator next throws exception when no more elements")
        void testIteratorNextThrowsExceptionWhenNoMoreElements(){

            Iterator<String> iterator = stringBag.iterator();

            NoSuchElementException exception = assertThrows(
                    NoSuchElementException.class,
                    iterator::next
            );

            assertEquals("No more elements in the bag", exception.getMessage());
        }

        @Test
        @DisplayName("enhanced for loop works correctly")
        void testEnhancedForLoopWorksCorrectly(){
            String[] elements = {"a", "b", "c", "d"};
            for (String element : elements) {
                stringBag.add(element);
            }

            int index = 0;
            for (String element : stringBag) {
                assertEquals(elements[index++], element); //for loop is returning correct elements in order were added
            }
            assertEquals(elements.length, index);
        }

        @Test
        @DisplayName("multiple iterators work independently")
        void testMultipleIteratorsWorksCorrectIndependently(){

            stringBag.add("one");
            stringBag.add("two");

            Iterator<String> iterator1 = stringBag.iterator();
            Iterator<String> iterator2 = stringBag.iterator();

            assertEquals("one", iterator1.next());
            assertEquals("two", iterator1.next());

            assertEquals("one", iterator2.next());
            assertEquals("two", iterator2.next());
        }

    }

    @Nested
    @DisplayName("Iterator Remove Tests")
    class IteratorRemoveTests{

        @Test
        @DisplayName("iterator remove works correctly")
        void testIteratorRemoveWorksCorrectly(){
            stringBag.add("first");
            stringBag.add("second");
            stringBag.add("third");

            Iterator<String> iterator = stringBag.iterator();

            //need to call next() first than remove()
            iterator.next();        //move to first
            iterator.remove();      //remove first

            assertEquals(2, stringBag.size());
            assertEquals("[second, third]", stringBag.toString());
        }

        @Test
        @DisplayName("iterator remove throws exception without next call")
        void testIteratorRemoveThrowExceptionWithoutNext(){
            stringBag.add("test");

            Iterator<String> iterator = stringBag.iterator();

            IllegalStateException exception = assertThrows(
                    IllegalStateException.class,
                    iterator::remove
            );

            assertEquals("next() must be called before remove()", exception.getMessage());
        }

        @Test
        @DisplayName("iterator remove throws exception for consecutive calls")
        void testIteratorRemoveThrowsExceptionForConsecutiveCalls(){
            stringBag.add("test");
            Iterator<String> iterator = stringBag.iterator();
            iterator.next();
            iterator.remove(); //first removing is OK

            IllegalStateException exception = assertThrows(
                    IllegalStateException.class,
                    iterator::remove //the second consecutive remove should fail and throw an exception
            );

            assertEquals("next() must be called before remove()", exception.getMessage());
        }

        @Test
        @DisplayName("iterator remove adjusts current position correctly")
        void testIteratorRemoveAdjustsCurrentPositionCorrectly(){
            stringBag.add("a");
            stringBag.add("b");
            stringBag.add("c");
            stringBag.add("d");

            Iterator<String> iterator = stringBag.iterator();
            iterator.next();        // a
            iterator.next();        // b
            iterator.remove();      // remove b

            assertEquals("c", iterator.next()); //should be c, not d;
            assertEquals("d", iterator.next()); //should be d
            assertFalse(iterator.hasNext());
        }
    }

    @Nested
    @DisplayName("Concurrent Modification Tests")
    class ConcurrentModificationTests{

        @Test
        @DisplayName("concurrent modification during iteration throws exception")
        void testThrowingExceptionDuringConcurrentModification(){
            stringBag.add("a");
            stringBag.add("b");
            stringBag.add("c");

            Iterator<String> iterator = stringBag.iterator();
            iterator.next();           //move iterator

            //trying to add an element
            stringBag.add("d");

            ConcurrentModificationException exception = assertThrows(
                    ConcurrentModificationException.class,
                    iterator::next
            );

            assertEquals("Bag was modified during modification", exception.getMessage());
        }

        @Test
        @DisplayName("concurrent modification with remove throws exception")
        void testConcurrentModificationWithRemoveThrowingException(){
            stringBag.add("a");
            stringBag.add("b");
            stringBag.add("c");

            Iterator<String> iterator = stringBag.iterator();
            iterator.next();

            stringBag.remove("b");  //modify bag during iteration

            assertThrows(ConcurrentModificationException.class, iterator::next);
        }

        @Test
        @DisplayName("concurrent modification with clear throws exception")
        void testConcurrentModificationWithClearThrowsException() {
            stringBag.add("a");
            stringBag.add("b");

            Iterator<String> iterator = stringBag.iterator();
            iterator.next();

            stringBag.clear(); //modify bag during iteration

            assertThrows(ConcurrentModificationException.class, iterator::next);
        }

        @Test
        @DisplayName("iterator remove does not cause concurrent modification")
        void testIteratorDoesNotCauseConcurrentModificationException(){
            stringBag.add("a");
            stringBag.add("b");
            stringBag.add("c");

            Iterator<String> iterator = stringBag.iterator();
            iterator.next();
            iterator.remove(); //here should not throw ConcurrentModificationException

            assertDoesNotThrow(iterator::next);
        }

    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests{

        @Test
        @DisplayName("toString returns empty array for new bag")
        void testToStringReturnsEmptyArrayForNewBag() {
            assertEquals("[]", stringBag.toString());
            assertEquals("[]", intBag.toString());
        }

        @Test
        @DisplayName("toString shows only actual elements")
        void toStringShowsOnlyActualElements() {
            stringBag.add("visible");

            assertEquals("[visible]", stringBag.toString());
            // Should not show internal nulls
            assertFalse(stringBag.toString().contains("null"));
        }

        @Test
        @DisplayName("toString handles various data types correctly")
        void testToStringHandlesVariousDataTypesCorrectly() {
            intBag.add(123);
            intBag.add(-456);
            intBag.add(0);

            String result = intBag.toString();
            assertTrue(result.contains("123"));
            assertTrue(result.contains("-456"));
            assertTrue(result.contains("0"));
        }

        @Test
        @DisplayName("toString updates after modifications")
        void testToStringUpdatesAfterModifications() {
            stringBag.add("a");
            stringBag.add("b");
            assertEquals("[a, b]", stringBag.toString());

            stringBag.remove("a");
            assertEquals("[b]", stringBag.toString());

            stringBag.clear();
            assertEquals("[]", stringBag.toString());
        }

    }

    @Nested
    @DisplayName("Generic Type-Safety Tests")
    class GenericTypeSafetyTests{

        @Test
        @DisplayName("string bag only accepts strings")
        void testStringBagOnlyAcceptsStrings() {
            Bag<String> typedBag = stringBag;

            typedBag.add("string");
            assertTrue(typedBag.contains("string"));
            //compiler prevents: typedBag.add(123);
        }

        @Test
        @DisplayName("integer bag only accepts integers")
        void testIntegerBagOnlyAcceptsIntegers() {
            Bag<Integer> typedBag = intBag;

            typedBag.add(42);
            assertTrue(typedBag.contains(42));
            //compiler prevents: typedBag.add("string");
        }

        @Test
        @DisplayName("custom object bag works correctly")
        void testCustomObjectBagWorksCorrectly() {
            BagResizingImpl<StringBuilder> builderBag = new BagResizingImpl<>();
            StringBuilder sb1 = new StringBuilder("test1");
            StringBuilder sb2 = new StringBuilder(123);

            builderBag.add(sb1);
            builderBag.add(sb2);

            assertEquals(2, builderBag.size());
            assertTrue(builderBag.contains(sb1));
            assertTrue(builderBag.contains(sb2));
        }
    }

    @Nested
    @DisplayName("Edge Cases and Stress Tests")
    class EdgeCasesAndStressTests {

        @Test
        @DisplayName("adding large number of elements works correctly")
        void testAddingLargeNumberOfElementsWorksCorrectly() {
            int numberOfElements = 1000;

            for (int i = 0; i < numberOfElements; i++) {
                intBag.add(i);
            }

            assertEquals(numberOfElements, intBag.size());
            assertFalse(intBag.isEmpty());

            //verify capacity grew appropriately
            assertTrue(intBag.capacity() >= numberOfElements);

            //verify elements are preserved
            for (int i = 0; i < numberOfElements; i++) {
                assertTrue(intBag.contains(i));
            }
        }

        @Test
        @DisplayName("operations after multiple clear and add cycles")
        void testOperationsAfterMultipleClearAndAddCycles() {
            for (int cycle = 0; cycle < 5; cycle++) {
                for (int i = 0; i < 10; i++) {
                    stringBag.add("cycle" + cycle + "item" + i);
                }
                assertEquals(10, stringBag.size());
                stringBag.clear();
                assertEquals(0, stringBag.size());
                assertTrue(stringBag.isEmpty());
            }

            //final verification
            stringBag.add("final");
            assertEquals(1, stringBag.size());
            assertEquals("[final]", stringBag.toString());
        }

        @Test
        @DisplayName("iterator works correctly with large datasets")
        void testIteratorWorksCorrectlyWithLargeDatasets() {
            int numberOfElements = 500;
            for (int i = 0; i < numberOfElements; i++) {
                intBag.add(i);
            }

            int count = 0;
            int sum = 0;
            for (Integer value : intBag) {
                count++;
                sum += value;
            }

            assertEquals(numberOfElements, count);
            assertEquals((numberOfElements - 1) * numberOfElements / 2, sum); // Sum of 0 to n-1
        }
    }

    @Nested
    @DisplayName("Interface Compliance Tests")
    class InterfaceComplianceTests {

        @Test
        @DisplayName("bag implements all interface methods correctly")
        void testBagImplementsAllInterfaceMethodsCorrectly() {
            Bag<String> interfaceRef = stringBag;

            // Test all interface methods
            assertTrue(interfaceRef.isEmpty());
            assertEquals(0, interfaceRef.size());
            assertEquals(10, interfaceRef.capacity());

            interfaceRef.add("interface test");

            assertFalse(interfaceRef.isEmpty());
            assertEquals(1, interfaceRef.size());
            assertTrue(interfaceRef.contains("interface test"));

            interfaceRef.remove("interface test");
            assertTrue(interfaceRef.isEmpty());

            interfaceRef.add("clear test");
            interfaceRef.clear();
            assertTrue(interfaceRef.isEmpty());
        }

        @Test
        @DisplayName("bag is iterable through interface")
        void testBagIsIterableThroughInterface() {
            Bag<String> interfaceRef = stringBag;
            interfaceRef.add("iterable");
            interfaceRef.add("test");

            int count = 0;
            for (String item : interfaceRef) {
                count++;
                assertTrue(item.equals("iterable") || item.equals("test"));
            }
            assertEquals(2, count);
        }
    }

}























