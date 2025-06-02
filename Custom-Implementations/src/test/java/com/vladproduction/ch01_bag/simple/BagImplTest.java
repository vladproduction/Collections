package com.vladproduction.ch01_bag.simple;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BagImplTest {

    private BagImpl bag;

    @BeforeEach
    void setUp() {
        bag = new BagImpl();
    }

    @AfterEach
    void tearDown() {
        bag = null;
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests{

        @Test
        @DisplayName("Default Constructor creates bag with default capacity")
        void testDefaultConstructorCreateEmptyBagWithDefaultCapacity(){
            BagImpl defaultBag = new BagImpl();

            assertTrue(defaultBag.isEmpty());
            assertEquals(0, defaultBag.size());
            assertEquals(10, defaultBag.capacity());
        }

        @Test
        @DisplayName("Parameterized constructor creates bag with specified capacity")
        void testParameterizedConstructorCreatesEmptyBagWithSpecifiedCapacity(){
            BagImpl defaultBag = new BagImpl(5);

            assertTrue(defaultBag.isEmpty());
            assertEquals(0, defaultBag.size());
            assertEquals(5, defaultBag.capacity());
        }

        @Test
        @DisplayName("Constructor with zero capacity creates valid bag")
        void testConstructorWithZeroCapacityCreatesValidBag(){
            BagImpl defaultBag = new BagImpl(0);

            assertTrue(defaultBag.isEmpty());
            assertEquals(0, defaultBag.size());
            assertEquals(0, defaultBag.capacity());
        }

        @Test
        @DisplayName("Constructor throws exception for negative capacity")
        void testConstructorThrowingExceptionWithNegativeCapacity(){
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new BagImpl(-1)
            );

            assertEquals("Capacity cannot be negative.", exception.getMessage());
        }

    }

    @Nested
    @DisplayName("Basic Operation Tests")
    class BasicOperationTests{

        @Test
        @DisplayName("isEmpty returns true for newly created bag")
        void testIsEmptyReturnTrueForNewCreatedBag(){

            assertTrue(bag.isEmpty());
        }

        @Test
        @DisplayName("isEmpty returns false after add element into the bag")
        void testIsEmptyReturnFalseAfterAddElementIntoBag(){
            bag.add("element was added");

            assertFalse(bag.isEmpty());
        }

        @Test
        @DisplayName("size return zero for new created bag")
        void testSizeReturnZeroForNewBag(){

            assertEquals(0, bag.size());
        }

        @Test
        @DisplayName("size increases correctly after adding elements")
        void testSizeIncreasingCorrectlyAfterAddingElements(){

            assertEquals(0, bag.size());

            bag.add("First element");
            assertEquals(1, bag.size());

            bag.add("Second element");
            assertEquals(2, bag.size());

            bag.add("Third element");
            assertEquals(3, bag.size());
        }

    }

    @Nested
    @DisplayName("Add Operation Tests")
    class AddOperationTests{

        @Test
        @DisplayName("add single element works correctly")
        void testAddSingleElementWorkCorrectly(){
            bag.add("test");

            assertFalse(bag.isEmpty());
            assertEquals(1, bag.size());
            assertEquals("[test]", bag.toString());
        }

        @Test
        @DisplayName("add multiple elements within initial capacity")
        void testAddMultipleElementsWithingInitialCapacity(){
            bag.add("a");
            bag.add("b");
            bag.add("c");

            assertFalse(bag.isEmpty());
            assertEquals("[a, b, c]", bag.toString());
            assertEquals(10, bag.capacity());                    //should be default capacity
        }

        @Test
        @DisplayName("add null element is allowed")
        void testAddNullElementIsNotForbidden(){
            bag.add(null);
            bag.add(null);
            bag.add("after null");

            assertEquals(3, bag.size());
            assertEquals("[null, null, after null]", bag.toString());
        }

        @Test
        @DisplayName("add different types of objects")
        void testAddDifferentTypesOfObjects(){
            bag.add("String");
            bag.add(1);
            bag.add(3.1415);
            bag.add(500.00055F);
            bag.add(true);

            assertEquals(5, bag.size());
            assertEquals("[String, 1, 3.1415, 500.00055, true]", bag.toString());
        }

        @Test
        @DisplayName("add duplicate elements is allowed")
        void testDuplicatesElementsAllowed(){
            bag.add("element");
            bag.add("element");
            bag.add("element");

            assertEquals(3, bag.size());
            assertEquals("[element, element, element]", bag.toString());
        }

    }

    @Nested
    @DisplayName("Dynamic Resizing Tests")
    class DynamicResizingTests{

        @Test
        @DisplayName("bag resizes when exceeding initial capacity")
        void testResizingWhenInitialCapacityExceed(){
            //fill the bag through loop
            for (int i = 0; i < 10; i++) {
                 bag.add(i);
            }

            assertFalse(bag.isEmpty());
            assertEquals(10, bag.size());
            assertEquals(10, bag.capacity());

            // adding one element more to trigger resize
            bag.add("overflow");

            assertEquals(11, bag.size());
            assertEquals(20, bag.capacity());
            assertTrue(bag.toString().contains("overflow"));
        }

        @Test
        @DisplayName("bag with small initial capacity resizes correctly")
        void testResizingCorrectlyWhenSmallInitialCapacity(){
            BagImpl smallBag = new BagImpl(2); //initial capacity is set to 2
            smallBag.add(1);
            smallBag.add(2);

            assertEquals(2, smallBag.capacity());

            //triggering resize:
            smallBag.add(3); //now it should have capacity as 4

            assertEquals(3, smallBag.size());
            assertEquals(4, smallBag.capacity());
            assertEquals("[1, 2, 3]", smallBag.toString());
        }

        @Test
        @DisplayName("bag with zero capacity handles first addition")
        void testHandleFirstAdditionWithZeroCapacity(){
            BagImpl zeroBag = new BagImpl(0);

            zeroBag.add("first");

            assertEquals(1, zeroBag.size());
            assertEquals(1, zeroBag.capacity()); //due to Math.max(1, 0 * 2)
        }

        @Test
        @DisplayName("multiple resizes maintain data integrity")
        void testMultipleResizingMaintainDataIntegrity(){
            BagImpl smallBag = new BagImpl(1);

            //adding elements to force multiple resizes
            for (int i = 0; i < 8; i++) {
                smallBag.add("item " + i);
            }
            assertEquals(8, smallBag.size());
            assertEquals(8, smallBag.capacity());

            //verify all elements are preserved
            String result = smallBag.toString();
            for (int i = 0; i < 8; i++) {
                 assertTrue(result.contains("item " + i));
            }

        }

    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests{

        @Test
        @DisplayName("toString returns empty array for new bag")
        void testReturnEmptyArrayForNewBag(){
            BagImpl emptyBag = new BagImpl();

            assertEquals("[]", emptyBag.toString());
        }

        @Test
        @DisplayName("toString shows only actual elements, not internal array")
        void testDisplayingActualElementsOnly(){
            bag.add("visible");

            assertEquals("[visible]", bag.toString());

            //should not show internal null elements
            assertFalse(bag.toString().contains("null"));
        }

        @Test
        @DisplayName("toString handles various data types correctly")
        void testHandlingVariousDataTypesCorrectly(){
            bag.add(1);
            bag.add("String");
            bag.add(true);
            bag.add(null);

            String result = bag.toString();
            assertTrue(result.contains("1"));
            assertTrue(result.contains("String"));
            assertTrue(result.contains("true"));
            assertTrue(result.contains("null"));
        }

        @Test
        @DisplayName("toString handles whitespace correctly")
        void testHandlingWhitespaceCorrectly(){
            bag.add(" "); //just in educational case

            String result = bag.toString();
            assertTrue(result.contains(" "));
        }

    }

    @Nested
    @DisplayName("Additional Methods Tests")
    class AdditionalMethodsTests{

        @Test
        @DisplayName("capacity returns correct initial capacity")
        void testReturnCorrectInitialCapacity(){
            assertEquals(10, bag.capacity());

            BagImpl customBag = new BagImpl(5);
            assertEquals(5, customBag.capacity());
        }

        @Test
        @DisplayName("capacity updates after resize")
        void testCapacityUpdatesAfterResize() {
            BagImpl smallBag = new BagImpl(2);
            assertEquals(2, smallBag.capacity());

            smallBag.add("a");
            smallBag.add("b");
            smallBag.add("c"); // resize occur here

            assertEquals(4, smallBag.capacity());
        }

        @Test
        @DisplayName("clear removes all elements and resets size")
        void testClearRemovesAllElementsAndResetsSize() {
            bag.add("a");
            bag.add("b");
            bag.add("c");
            assertEquals(3, bag.size());

            bag.clear();

            assertEquals(0, bag.size());
            assertTrue(bag.isEmpty());
            assertEquals("[]", bag.toString());
            assertEquals(10, bag.capacity()); //capacity should remain unchanged
        }

        @Test
        @DisplayName("clear works on empty bag")
        void testClearWorksOnEmptyBag() {
            bag.clear();

            assertEquals(0, bag.size());
            assertTrue(bag.isEmpty());
            assertEquals("[]", bag.toString());
        }

        @Test
        @DisplayName("clear allows adding new elements after clearing")
        void testClearAllowsAddingNewElementsAfterClearing() {
            bag.add("before clear");
            bag.clear();
            bag.add("after clear 1");
            bag.add("after clear 2");

            assertEquals(2, bag.size());
            assertEquals("[after clear 1, after clear 2]", bag.toString());
        }

    }

    @Nested
    @DisplayName("Edge Cases and Stress Tests")
    class EdgeCasesAndStressTests{

        @Test
        @DisplayName("adding large number of elements works correctly")
        void testAddingLargeNumberOfElementsWorksCorrectly(){
            int numberOfElements = 1000;

            for (int i = 0; i < numberOfElements; i++) {
                bag.add("element " + i);
            }

            assertEquals(numberOfElements, bag.size());
            assertFalse(bag.isEmpty());

            // Verify capacity grew appropriately (should be power of 2 >= 1000)
            assertTrue(bag.capacity() >= numberOfElements);

            // Verify first and last elements are preserved
            String result = bag.toString();
            assertTrue(result.contains("element 0"));
            assertTrue(result.contains("element 999"));

        }

        @Test
        @DisplayName("operations after multiple clear and add cycles")
        void testOperationsAfterMultipleClearAndAddCycles(){

            //multiple cycle of add and clear
            for (int cycle = 0; cycle < 5; cycle++) {
                for (int i = 0; i < 10; i++) {
                    bag.add("cycle" + cycle + "item" + i);
                    //System.out.println(bag);  //to see logging
                }
                assertEquals(10, bag.size());
                bag.clear();
                assertEquals(0, bag.size());
                assertTrue(bag.isEmpty());
            }

            // Final verification
            bag.add("final");
            assertEquals(1, bag.size());
            assertEquals("[final]", bag.toString());
        }

    }

    @Nested
    @DisplayName("Interface methods compliance  Tests")
    class InterfaceComplianceTests{

        @Test
        @DisplayName("bag instance implements Bag interface correctly")
        void testBagInstanceImplementsBagInterfaceCorrectly(){
            Bag interfaceReference = new BagImpl();

            assertEquals(0, interfaceReference.size());
            assertTrue(interfaceReference.isEmpty());

            interfaceReference.add(1);
            assertEquals(1, interfaceReference.size());
            assertFalse(interfaceReference.isEmpty());
        }

        @Test
        @DisplayName("all interface methods work through interface reference")
        void testAllInterfaceMethodsWorkThroughInterfaceReference() {
            Bag interfaceRef = bag;

            // Test all interface methods
            assertTrue(interfaceRef.isEmpty());
            assertEquals(0, interfaceRef.size());

            interfaceRef.add("interface test");

            assertFalse(interfaceRef.isEmpty());
            assertEquals(1, interfaceRef.size());

            // toString should work (inherited from Object)
            assertEquals("[interface test]", interfaceRef.toString());
        }

    }


}
























