package com.vladproduction.app06.iterable_iterator_listIterator.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PitfallsWhileModifyingDuringIteration {
    public static void main(String[] args) {

        // Modifying list directly during iteration:
        /*for (String fruit : fruits) {
            if (fruit.equals("banana")) {
                fruits.remove(fruit); // ConcurrentModificationException
            }
        }*/

        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");
        fruits.add("orange");
        fruits.add("pineapple");
        fruits.add("watermelon");

        System.out.println("Before removing: " + fruits);

        System.out.println("\nExpected Pitfall: ConcurrentModificationException");
        System.out.println("uncomment to see the exception");
        /*for (String fruit : fruits) {
            if(fruit.equals("orange")){
                fruits.remove(fruit);  // ConcurrentModificationException
            }
        }*/

        System.out.println("\nSolution 1: Correct way to remove while using iterator");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()){
            String fruit = iterator.next();
            if(fruit.equals("orange")){
                iterator.remove();
            }
        }

        System.out.println("\nSolution 2: Collection.removeIf is used");
        fruits.removeIf(fruit -> fruit.equals("pineapple"));


        System.out.println("\nAfter removing: " + fruits);

    }
}
