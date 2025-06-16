package com.vladproduction.collectionsframework.ch02_lists;

import java.util.*;

/**
 * Comprehensive examples of LinkedList usage, features, and best practices
 * Covers: Basic operations, deque operations, performance characteristics, and advanced features
 */
public class LinkedListExamples {
    public static void main(String[] args) {

        LinkedListExamples demo = new LinkedListExamples();

        System.out.println("\n=== LINKED LIST EXAMPLES ===");

        demo.basicOperations();
        demo.dequeOperations();
        demo.performanceCharacteristics();
        demo.searchingAndSorting();
        demo.advancedOperations();
        demo.linkedListVsArrayList();
        demo.commonPitfalls();
        demo.bestPractices();
        demo.demoDiscoveryExamples();

    }

    /**
     * Demonstrates basic LinkedList operations
     */
    private void basicOperations() {
        System.out.println("\n1. BASIC LINKEDLIST OPERATIONS");
        System.out.println("===============================");

        List<String> list = new ArrayList<>();
        System.out.println("\nInitial elements: " + list);
        System.out.println("Size: " + list.size());
        System.out.println("Is Empty: " + list.isEmpty());

        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println("\nList after added elements: " + list);
        System.out.println("Size: " + list.size());
        System.out.println("Is Empty: " + list.isEmpty());

        //add by specific index
        list.add(2, "B1");
        System.out.println("\nList after added element by index: " + list);

        //add first, add last (need to have LinkedList as type, not as common interface List)
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.addFirst(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.addLast(4);
        System.out.println("\nLinkedList addFirst & addLast: " + list2);

        //accessing elements
        System.out.println("\nAccessing elements: get(), getFirst(), getLast()");
        System.out.println(list2.get(0));
        System.out.println(list2.get(1));
        System.out.println(list2.get(4));
        System.out.println(list2.getFirst());
        System.out.println(list2.getLast());
        System.out.println(list2.peekFirst());  //no remove
        System.out.println(list2.peekLast());   //no remove

        System.out.println("\nSearching elements: indexOf(), lastIndex(), contains() ");
        System.out.println(list2.indexOf(3));
        System.out.println(list2.lastIndexOf(3));
        System.out.println(list2.contains("A")); //false
        System.out.println(list2.contains(4));

        System.out.println("\nRemoving by index and element itself: ");
        LinkedList<String> list3 = new LinkedList<>();
        list3.addAll(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println(list3.removeFirst());
        System.out.println(list3.removeLast());
        System.out.println(list3.remove(2)); //"D" became at index 2
        System.out.println(list3.remove("C"));
        System.out.println(list3); // [B]

        System.out.println("Size: " + list3.size());
        System.out.println("Is Empty: " + list3.isEmpty());

        //clear all elements in the list
        System.out.println("\nClear all elements in the list:");
        list3.clear();
        System.out.println("Size: " + list3.size());
        System.out.println("Is Empty: " + list3.isEmpty());
        System.out.println();
    }

    /**
     * Demonstrates LinkedList as Deque (Double-ended queue) operations
     */
    private void dequeOperations() {
        System.out.println("2. DEQUE OPERATIONS");
        System.out.println("===================");

        LinkedList<Integer> deque = new LinkedList<>();
        System.out.println("LinkedList implements Deque interface");

        // Stack operations (LIFO - Last In, First Out)
        System.out.println("\nStack operations (using as Stack):");
        deque.push(10);  // equivalent to addFirst()
        deque.push(20);
        deque.push(30);
        System.out.println("After pushing 10, 20, 30: " + deque);

        Integer popped = deque.pop();  // equivalent to removeFirst()
        System.out.println("Popped element: " + popped + ", remaining: " + deque);

        // Queue operations (FIFO - First In, First Out)
        System.out.println("\nQueue operations (using as Queue):");
        LinkedList<String> queue = new LinkedList<>();
        queue.offer("First");   // equivalent to addLast()
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("After offering elements: " + queue);

        String polled = queue.poll();  // equivalent to removeFirst()
        System.out.println("Polled element: " + polled + ", remaining: " + queue);

        System.out.println("\nLIFO - last in first out - as stack (push() and pop())");
        LinkedList<Integer> lifo = new LinkedList<>();
        lifo.push(1);
        lifo.push(2);
        lifo.push(3);
        lifo.push(4);

        System.out.println("Stack after push (added) elements: " + lifo);

        Integer integer1 = lifo.pop();//remove first
        System.out.println("Popped : " + integer1);
        System.out.println(lifo);

        System.out.println("\nFIFO - first in first out - as queue (offer() and poll())");
        LinkedList<Integer> fifo = new LinkedList<>();
        fifo.offer(100);
        fifo.offer(200);
        fifo.offer(300);
        fifo.offer(400);
        fifo.offer(500);

        System.out.println("Queue after offer (added) elements: " + fifo);
        Integer polledElem = fifo.poll();//remove first
        System.out.println("Polled : " + polledElem);
        System.out.println(fifo);

        // Deque operations (double-ended)
        System.out.println("\nDouble-ended queue operations:");
        LinkedList<String> doubleQueue = new LinkedList<>();

        // Adding to both ends
        doubleQueue.offerFirst("Middle");
        doubleQueue.offerFirst("First");
        doubleQueue.offerLast("Last");
        System.out.println("After adding to both ends: " + doubleQueue);

        // Peeking both ends
        System.out.println("Peek first: " + doubleQueue.peekFirst());
        System.out.println("Peek last: " + doubleQueue.peekLast());

        // Removing from both ends
        System.out.println("Poll first: " + doubleQueue.pollFirst());
        System.out.println("Poll last: " + doubleQueue.pollLast());
        System.out.println("Remaining: " + doubleQueue);

        System.out.println("\nDeque method summary:");
        System.out.println("- addFirst/offerFirst: Add to head");
        System.out.println("- addLast/offerLast: Add to tail");
        System.out.println("- removeFirst/pollFirst: Remove from head");
        System.out.println("- removeLast/pollLast: Remove from tail");
        System.out.println("- peekFirst/getFirst: View head element");
        System.out.println("- peekLast/getLast: View tail element");
        System.out.println();
    }

    /**
     * Demonstrates performance characteristics
     */
    private void performanceCharacteristics() {
        System.out.println("3. PERFORMANCE CHARACTERISTICS");
        System.out.println("===============================");

        LinkedList<Integer> list = new LinkedList<>();

        // Adding at beginning - O(1)
        System.out.println("\nAdding at beginning (O(1)):");
        long start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.addFirst(i);
        }
        long end = System.nanoTime();
        System.out.println("Added 10,000 elements at beginning in: " + (end - start) / 1_000_000 + "ms");

        // Adding at end - O(1)
        System.out.println("\nAdding at end (O(1)):");
        start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.addLast(i);
        }
        end = System.nanoTime();
        System.out.println("Added 10,000 elements at end in: " + (end - start) / 1_000_000 + "ms");

        // Random access - O(n)
        System.out.println("\nRandom access (O(n)):");
        start = System.nanoTime();
        Random random = new Random(42);
        for (int i = 0; i < 1000; i++) {
            list.get(random.nextInt(Math.min(list.size(), 1000)));
        }
        end = System.nanoTime();
        System.out.println("1,000 random access operations in: " + (end - start) / 1_000_000 + "ms");

        // Sequential access - much faster
        System.out.println("\nSequential access using iterator:");
        start = System.nanoTime();
        Iterator<Integer> iterator = list.iterator();
        int count = 0;
        while (iterator.hasNext() && count < 1000) {
            iterator.next();
            count++;
        }
        end = System.nanoTime();
        System.out.println("1,000 sequential access operations in: " + (end - start) / 1_000 + " μs");

        // Insertion in middle - O(n) for finding position + O(1) for insertion
        System.out.println("\nInsertion in middle:");
        LinkedList<Integer> insertDemo = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            insertDemo.add(i);
        }
        start = System.nanoTime();
        insertDemo.add(500, -1);  // Insert at middle
        end = System.nanoTime();
        System.out.println("Insertion at middle took: " + (end - start) / 1_000 + " μs");

        // Search operation - O(n)
        System.out.println("\nLinear search (O(n)):");
        start = System.nanoTime();
        list.contains(9999);
        end = System.nanoTime();
        System.out.println("Linear search took: " + (end - start) / 1_000_000 + "ms");

        System.out.println("\nPerformance Summary:");
        System.out.println("- Access by index: O(n) - must traverse from head/tail");
        System.out.println("- Add at beginning/end: O(1)");
        System.out.println("- Add at middle: O(n) to find position + O(1) to insert");
        System.out.println("- Remove from beginning/end: O(1)");
        System.out.println("- Remove from middle: O(n) to find + O(1) to remove");
        System.out.println("- Search: O(n)");
        System.out.println("- Sequential iteration: Very efficient");
        System.out.println();
    }

    /**
     * Demonstrates searching and sorting operations
     */
    private void searchingAndSorting() {
        System.out.println("4. SEARCHING AND SORTING");
        System.out.println("========================");

        LinkedList<String> names = new LinkedList<>();
        names.addAll(Arrays.asList("Charlie", "Alice", "Bob", "David", "Eve"));
        System.out.println("Original names: " + names);

        // Linear search
        System.out.println("\nLinear search:");
        System.out.println("Index of 'Bob': " + names.indexOf("Bob"));
        System.out.println("Last index of 'Alice': " + names.lastIndexOf("Alice"));

        // Sorting operations
        System.out.println("\nSorting operations:");
        LinkedList<String> sortingNames = new LinkedList<>(names);
        Collections.sort(sortingNames);
        System.out.println("Natural sort: " + sortingNames);

        Collections.sort(sortingNames, Collections.reverseOrder());
        System.out.println("Reverse sort: " + sortingNames);

        // Custom sorting
        Collections.sort(sortingNames, Comparator.comparing(String::length));
        System.out.println("Sort by length: " + sortingNames);

        // Binary search (requires sorted list)
        Collections.sort(names);
        System.out.println("\nBinary search on sorted names: " + names);
        int charlieIndex = Collections.binarySearch(names, "Charlie");
        System.out.println("Index of 'Charlie': " + charlieIndex);

        // Not found case
        int frankIndex = Collections.binarySearch(names, "Frank");
        System.out.println("Binary search for 'Frank' (not found): " + frankIndex);
        System.out.println("Insertion point would be: " + (-frankIndex - 1));

        // Shuffling
        Collections.shuffle(names);
        System.out.println("\nShuffled names: " + names);

        // Note about sorting performance
        System.out.println("\nNote: LinkedList sorting performance is generally worse than ArrayList");
        System.out.println("due to lack of random access. Consider converting to ArrayList for sorting.");
        System.out.println();
    }

    /**
     * Demonstrates advanced LinkedList operations
     */
    private void advancedOperations() {
        System.out.println("5. ADVANCED OPERATIONS");
        System.out.println("======================");

        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original numbers: " + numbers);

        // Sublist operations (note: less efficient than ArrayList)
        System.out.println("\nSublist operations:");
        List<Integer> subList = numbers.subList(2, 7);
        System.out.println("SubList (index 2-6): " + subList);

        // Modifying sublist affects original
        subList.set(0, 99);
        System.out.println("After modifying sublist (it affects original):");
        System.out.println("Original list: " + numbers);

        // Bulk operations
        System.out.println("\nBulk operations:");
        LinkedList<Integer> evenNumbers = new LinkedList<>(Arrays.asList(2, 4, 6, 8, 10, 12));
        System.out.println("Even numbers: " + evenNumbers);

        LinkedList<Integer> copyForRetain = new LinkedList<>(numbers);
        copyForRetain.retainAll(evenNumbers);
        System.out.println("After retainAll (intersection): " + copyForRetain);

        // Efficient list iteration patterns
        System.out.println("\nEfficient iteration patterns:");
        System.out.println("Using ListIterator for bidirectional traversal:");

        ListIterator<Integer> listIterator = numbers.listIterator(numbers.size());
        System.out.print("Reverse iteration: ");
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();

        // Java 8+ Stream operations
        System.out.println("\nStream operations:");
        LinkedList<String> words = new LinkedList<>();
        words.addAll(Arrays.asList("apple", "banana", "cherry", "date", "elderberry"));
        System.out.println("Original words: " + words);

        LinkedList<String> longWords = words.stream()
                .filter(word -> word.length() > 5)
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        System.out.println("Words with length > 5: " + longWords);

        LinkedList<String> uppercaseWords = words.stream()
                .map(String::toUpperCase)
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        System.out.println("Uppercase words: " + uppercaseWords);

        // Interview challenge task
        System.out.println("\nInterview challenge task:");
        List<String> notes = Arrays.asList("car", "place", "Dog", "Product", "anagram", "Gear");
        System.out.println("Notes before: " + notes);
        LinkedList<String> result = notes.stream()
                .filter(note -> !note.toLowerCase().startsWith("p"))
                .map(note -> note.substring(0, 1).toUpperCase() + note.substring(1))
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        System.out.println("Processed Notes: " + result);

        // removeIf (Java 8+)
        LinkedList<Integer> numbersToFilter = new LinkedList<>();
        numbersToFilter.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        numbersToFilter.removeIf(n -> n % 2 == 0);
        System.out.println("After removing even numbers: " + numbersToFilter);

        // Demonstrating descendingIterator
        System.out.println("\nUsing descendingIterator:");
        System.out.print("Descending order: ");
        Iterator<Integer> descendingIter = numbersToFilter.descendingIterator();
        while (descendingIter.hasNext()) {
            System.out.print(descendingIter.next() + " ");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Compares LinkedList with ArrayList
     */
    private void linkedListVsArrayList() {
        System.out.println("6. LINKEDLIST VS ARRAYLIST");
        System.out.println("===========================");

        // Performance comparison
        System.out.println("Performance comparison:");

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        // Adding elements
        System.out.println("\nAdding 10,000 elements at the beginning:");

        // ArrayList - slow for beginning insertion
        long start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            arrayList.add(0, i);
        }
        long end = System.nanoTime();
        System.out.println("ArrayList: " + (end - start) / 1_000_000 + "ms");

        // LinkedList - fast for beginning insertion
        start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            linkedList.addFirst(i);
        }
        end = System.nanoTime();
        System.out.println("LinkedList: " + (end - start) / 1_000_000 + "ms");

        // Random access comparison
        System.out.println("\nRandom access (1000 operations):");
        Random random = new Random(42);

        // ArrayList - fast random access
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.get(random.nextInt(arrayList.size()));
        }
        end = System.nanoTime();
        System.out.println("ArrayList: " + (end - start) / 1_000 + " μs");

        // LinkedList - slow random access
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.get(random.nextInt(linkedList.size()));
        }
        end = System.nanoTime();
        System.out.println("LinkedList: " + (end - start) / 1_000 + " μs");

        System.out.println("\nKey differences:");
        System.out.println("ArrayList:");
        System.out.println("  + Fast random access O(1)");
        System.out.println("  + Better memory efficiency (array-based)");
        System.out.println("  + Cache-friendly for iteration");
        System.out.println("  + Better for sorting operations");
        System.out.println("  - Slow insertion/deletion at beginning O(n)");
        System.out.println("  - Capacity management overhead");

        System.out.println("\nLinkedList:");
        System.out.println("  + Fast insertion/deletion at beginning/end O(1)");
        System.out.println("  + No capacity management");
        System.out.println("  + Implements Deque interface");
        System.out.println("  + Good for frequent insertions/deletions");
        System.out.println("  - Slow random access O(n)");
        System.out.println("  - Higher memory overhead (node objects)");
        System.out.println("  - Poor cache locality");

        System.out.println("\nWhen to use LinkedList:");
        System.out.println("- Frequent insertions/deletions at beginning/end");
        System.out.println("- Using as Stack, Queue, or Deque");
        System.out.println("- Sequential access patterns");
        System.out.println("- Unknown or highly variable size");

        System.out.println("\nWhen to use ArrayList:");
        System.out.println("- Frequent random access by index");
        System.out.println("- More reading than writing");
        System.out.println("- Sorting operations");
        System.out.println("- Memory-conscious applications");
        System.out.println();
    }

    /**
     * Demonstrates common pitfalls and how to avoid them
     */
    private void commonPitfalls() {
        System.out.println("7. COMMON PITFALLS");
        System.out.println("==================");

        // Pitfall 1: Using get(index) for iteration
        System.out.println("Pitfall 1: Using get(index) for iteration");
        LinkedList<String> items = new LinkedList<>();
        items.addAll(Arrays.asList("A", "B", "C", "D", "E"));

        System.out.println("WRONG: Using indexed for-loop (O(n²) complexity)");
        long start = System.nanoTime();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i); // Each get() is O(n)
        }
        long end = System.nanoTime();
        System.out.println("Indexed loop time: " + (end - start) / 1_000 + " μs");

        System.out.println("CORRECT: Using enhanced for-loop or iterator");
        start = System.nanoTime();
        for (String item : items) {
            // This uses iterator internally - O(n) total
        }
        end = System.nanoTime();
        System.out.println("Enhanced for-loop time: " + (end - start) / 1_000 + " μs");

        // For LinkedList:
        // BAD: Indexed loop (get(i)) → O(n²) time complexity
        // GOOD: Iterator or enhanced for-loop → O(n) time complexity

        // Pitfall 2: ConcurrentModificationException
        System.out.println("\nPitfall 2: ConcurrentModificationException");
        try {
            for (String item : items) {
                if (item.equals("C")) {
                    items.remove(item); // This will throw exception
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Exception caught: " + e.getClass().getSimpleName());
        }

        System.out.println("CORRECT: Using Iterator.remove()");
        Iterator<String> iterator = items.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.equals("C")) {
                iterator.remove(); // Safe removal
            }
        }
        System.out.println("After safe removal: " + items);

        // Pitfall 3: Inefficient sorting
        System.out.println("\nPitfall 3: Sorting LinkedList directly (inefficient)");
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 1000; i >= 1; i--) {
            numbers.add(i);
        }

        System.out.println("SLOW: Sorting LinkedList directly");
        start = System.nanoTime();
        Collections.sort(numbers);
        end = System.nanoTime();
        System.out.println("Direct LinkedList sort time: " + (end - start) / 1_000 + " μs (microseconds)");

        // Reset for fair comparison
        numbers.clear();
        for (int i = 1000; i >= 1; i--) {
            numbers.add(i);
        }

        System.out.println("FASTER: Convert to ArrayList, sort, then back to LinkedList");
        start = System.nanoTime();
        ArrayList<Integer> tempList = new ArrayList<>(numbers);
        Collections.sort(tempList);
        numbers = new LinkedList<>(tempList);
        end = System.nanoTime();
        System.out.println("Convert-sort-convert time: " + (end - start) / 1_000 + " μs (microseconds)");

        // Pitfall 4: Memory waste with small lists
        System.out.println("\nPitfall 4: Memory overhead for small lists");
        System.out.println("LinkedList has significant per-element overhead:");
        System.out.println("- Each node stores: data reference + next reference + previous reference");
        System.out.println("- For small lists, ArrayList is more memory efficient");
        System.out.println("- Consider ArrayList for lists with < 100 elements typically");
        System.out.println();
    }

    /**
     * Demonstrates best practices for using LinkedList
     */
    private void bestPractices() {
        System.out.println("8. BEST PRACTICES");
        System.out.println("=================");

        // 1. Use appropriate interface type
        System.out.println("1. Declare with appropriate interface type");
        Deque<String> deque = new LinkedList<>(); // When using as deque (LIFO(stack-like)), addFirst(), removeFirst() etc.
        Queue<String> queue = new LinkedList<>(); // When using as queue (FIFO(queue-like)), add()/offer(), poll()
        List<String> list = new LinkedList<>();   // When using as a Positional list  (linked list), add(), get(i), remove(i)

        deque.addFirst("First");
        queue.offer("Queue element");
        list.add("List element");
        System.out.println("Deque: " + deque + ", Queue: " + queue + ", List: " + list);

        // 2. Use iterator for traversal
        System.out.println("\n2. Always use iterator for traversal, never get(index)");
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        System.out.println("Efficient traversal:");
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 3. Use ListIterator for bidirectional traversal
        System.out.println("\n3. Use ListIterator for bidirectional operations");
        ListIterator<Integer> listIter = numbers.listIterator();
        System.out.print("Forward: ");
        while (listIter.hasNext()) {
            System.out.print(listIter.next() + " ");
        }
        System.out.print("\nBackward: ");
        while (listIter.hasPrevious()) {
            System.out.print(listIter.previous() + " ");
        }
        System.out.println();

        // 4. Leverage head/tail operations
        System.out.println("\n4. Use head/tail operations when possible");
        LinkedList<String> tasks = new LinkedList<>();

        // Adding tasks
        tasks.addLast("Task 1");  // Add to end
        tasks.addFirst("Urgent Task"); // Add to beginning
        tasks.addLast("Task 2");
        System.out.println("Task queue: " + tasks);

        // Processing tasks
        while (!tasks.isEmpty()) {
            String task = tasks.removeFirst(); // Process from beginning
            System.out.println("Processing: " + task);
        }

        // 5. Consider ArrayList for sorting
        System.out.println("\n5. Convert to ArrayList for sorting operations");
        LinkedList<String> names = new LinkedList<>();
        names.addAll(Arrays.asList("Charlie", "Alice", "Bob"));

        // Convert, sort, convert back
        ArrayList<String> tempList = new ArrayList<>(names);
        Collections.sort(tempList);
        names = new LinkedList<>(tempList);
        System.out.println("Sorted names: " + names);

        // 6. Use streams for complex operations
        System.out.println("\n6. Use streams for complex transformations");
        LinkedList<Integer> data = new LinkedList<>();
        data.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        LinkedList<Integer> processed = data.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        System.out.println("Processed data (even squares): " + processed);

        // 7. Handle null elements carefully
        System.out.println("\n7. Handle null elements appropriately");
        LinkedList<String> withNulls = new LinkedList<>();
        withNulls.add("Hello");
        withNulls.add(null);
        withNulls.add("World");

        System.out.println("Processing list with nulls:");
        for (String str : withNulls) {
            if (str != null) {
                System.out.println("Processing: " + str.toUpperCase());
            } else {
                System.out.println("Skipping null element");
            }
        }

        // 8. Use as appropriate data structure
        System.out.println("\n8. Choose LinkedList for appropriate use cases");
        System.out.println("✓ Implement Stack: use addFirst()/removeFirst()");
        System.out.println("✓ Implement Queue: use addLast()/removeFirst()");
        System.out.println("✓ Frequent insertion/deletion at ends");
        System.out.println("✓ Sequential processing");
        System.out.println("✗ Random access by index");
        System.out.println("✗ Frequent sorting");
        System.out.println("✗ Memory-constrained environments");

        // 9. Prefer specific methods over generic ones
        System.out.println("\n9. Use specific methods for better performance");
        LinkedList<String> demo = new LinkedList<>();

        // Better: Use specific methods
        demo.addFirst("First");    // instead of add(0, "First")
        demo.addLast("Last");      // instead of add("Last")
        demo.removeFirst();        // instead of remove(0)
        demo.removeLast();         // instead of remove(size()-1)

        System.out.println("Demo after operations: " + demo);
        System.out.println();
    }

    /**
     * A few demonstrations of efficiency for LinkedList
     * */
    private void demoDiscoveryExamples() {
        System.out.println("9. DEMO DISCOVERY EXAMPLES");
        System.out.println("===========================");

        LinkedList<Integer> list = new LinkedList<>();

        System.out.println("\nAdd first measurement");
        //warm up JVM (optionally)

        for (int i = 0; i < 10_000; i++) {
            list.addFirst(i);
        }
        list.clear();

        long start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.addFirst(i);
        }
        long end = System.nanoTime();
        double elapsed = (end - start) / 1_000_000.0;

        System.out.printf("%d elements in %.3f ms\n", list.size(), elapsed);

        System.out.println("\nSequential access using iterator:");
        if (list.size() >= 1000) {


            start = System.nanoTime();
            Iterator<Integer> iterator = list.iterator();
            for (int i = 0; i < 1000 && iterator.hasNext(); i++) {
                iterator.next();
            }
            end = System.nanoTime();

            System.out.printf("1,000 sequential access operations in: %d μs%n", (end - start) / 1_000);
        } else {
            System.out.println("List does not contain enough elements for the test.");
        }

        System.out.println("\nUsing get(index) for iteration vs iterator.next():");

        LinkedList<Integer> items = new LinkedList<>();
        for (int i = 0; i < 10_000; i++) {
            items.add(i);
        }

        System.out.println("WRONG: Using indexed for-loop (O(n²) complexity)");
        start = System.nanoTime();
        for (int i = 0; i < items.size(); i++) {
            items.get(i); // Each get() is O(n)
        }
        end = System.nanoTime();
        System.out.println("Indexed loop time: " + (end - start) / 1_000 + " μs");

        System.out.println("CORRECT: Using enhanced for-loop or iterator");
        start = System.nanoTime();
        for (Integer item : items) {
            // This uses iterator internally - O(n) total
        }
        end = System.nanoTime();
        System.out.println("Enhanced for-loop time: " + (end - start) / 1_000 + " μs");

        //conclusion:
        //Indexed loop time: 49,908 μs
        //Enhanced for-loop time: 270 μs <-- much more efficient

    }

}
