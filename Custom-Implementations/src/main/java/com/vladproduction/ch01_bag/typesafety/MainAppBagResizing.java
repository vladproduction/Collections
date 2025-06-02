package com.vladproduction.ch01_bag.typesafety;

import java.util.Iterator;

public class MainAppBagResizing {
    public static void main(String[] args) {

        System.out.println("=== Generic Type-Safe Bag Implementation Demo ===\n");

        // Creating a type-safe bag
        Bag<String> stringBag = new BagResizingImpl<>();

        // Check methods before adding elements
        System.out.println("1. Initial State:");
        System.out.println("   Empty bag: " + stringBag);
        System.out.println("   Size: " + stringBag.size());
        System.out.println("   Is empty: " + stringBag.isEmpty());

        // Add some elements
        System.out.println("\n2. Adding elements (a, b, c):");
        stringBag.add("a");
        stringBag.add("b");
        stringBag.add("c");
        System.out.println("   Bag contents: " + stringBag);
        System.out.println("   Size: " + stringBag.size());
        System.out.println("   Is empty: " + stringBag.isEmpty());

        // Add more elements to trigger resizing
        System.out.println("\n3. Adding more elements (d, e, f, g, h, i, j, k):");
        String[] moreElements = {"d", "e", "f", "g", "h", "i", "j", "k"};
        for (String element : moreElements) {
            stringBag.add(element);
        }
        System.out.println("   Bag contents: " + stringBag);
        System.out.println("   Size: " + stringBag.size());
        if (stringBag instanceof BagResizingImpl) {
            System.out.println("   Capacity: " + ((BagResizingImpl<String>) stringBag).capacity());
        }

        // Test iterator functionality
        System.out.println("\n4. Iterator Testing:");
        System.out.println("   Iterating with enhanced for-loop:");
        System.out.print("   ");
        for (String item : stringBag) {
            System.out.print(item + " ");
        }

        System.out.println("\n   Iterating with traditional iterator:");
        System.out.print("   ");
        Iterator<String> iterator = stringBag.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println("\n   Using forEachRemaining:");
        System.out.print("   ");
        stringBag.iterator().forEachRemaining(element -> System.out.print(element + " "));

        // Test remove functionality
        System.out.println("\n\n5. Remove Functionality:");
        System.out.println("   Before removal: " + stringBag);
        stringBag.remove("e");
        System.out.println("   After removing 'e': " + stringBag);
        System.out.println("   Size: " + stringBag.size());

        // Test iterator remove
        System.out.println("\n6. Iterator Remove:");
        System.out.println("   Removing elements that come after 'd' using iterator:");
        Iterator<String> removeIterator = stringBag.iterator();
        boolean foundD = false;
        while (removeIterator.hasNext()) {
            String current = removeIterator.next();
            if (foundD) {
                System.out.println("   Removing: " + current);
                removeIterator.remove();
            }
            if ("d".equals(current)) {
                foundD = true;
            }
        }
        System.out.println("   After iterator removal: " + stringBag);
        System.out.println("   Size: " + stringBag.size());

        // Test contains method
        System.out.println("\n7. Contains Method:");
        if (stringBag instanceof BagResizingImpl) {
            BagResizingImpl<String> impl = (BagResizingImpl<String>) stringBag;
            System.out.println("   Contains 'a': " + impl.contains("a"));
            System.out.println("   Contains 'z': " + impl.contains("z"));
        }

        // Test with different types
        System.out.println("\n8. Different Type Demo (Integer Bag):");
        Bag<Integer> intBag = new BagResizingImpl<>();
        intBag.add(1);
        intBag.add(2);
        intBag.add(3);
        System.out.println("   Integer bag: " + intBag);
        System.out.println("   Size: " + intBag.size());

        // Test error handling
        System.out.println("\n9. Error Handling:");
        try {
            stringBag.add(null);
        } catch (IllegalArgumentException e) {
            System.out.println("   Adding null: " + e.getMessage());
        }

        try {
            stringBag.remove(null);
        } catch (IllegalArgumentException e) {
            System.out.println("   Removing null: " + e.getMessage());
        }

        // Test clear functionality
        System.out.println("\n10. Clear Functionality:");
        if (stringBag instanceof BagResizingImpl) {
            BagResizingImpl<String> impl = (BagResizingImpl<String>) stringBag;
            System.out.println("   Before clear: " + stringBag + " (size: " + stringBag.size() + ")");
            impl.clear();
            System.out.println("   After clear: " + stringBag + " (size: " + stringBag.size() + ")");
            System.out.println("   Is empty: " + stringBag.isEmpty());
        }

        // Test concurrent modification detection
        System.out.println("\n11. Concurrent Modification Detection:");
        stringBag.add("test1");
        stringBag.add("test2");
        stringBag.add("test3");

        try {
            System.out.println("   Attempting to modify bag during iteration...");
            Iterator<String> concurrentIterator = stringBag.iterator();
            concurrentIterator.next();
            stringBag.add("concurrent");    // This should cause ConcurrentModificationException on next iterator call
            concurrentIterator.next();      // This should throw exception
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("   Caught expected exception: " + e.getClass().getSimpleName());
        }

        System.out.println("\n=== Demo Complete ===");


    }
}
