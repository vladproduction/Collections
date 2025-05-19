package com.vladproduction.app06.iterable_iterator_listIterator.iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PitfallsWhileRemovingByIterator {
    public static void main(String[] args) {

        //Using remove() twice in a row without calling next():
        //iterator.remove(); // only once is correct for current next value
        //iterator.remove(); // AGAIN → BOOM(!!!): IllegalStateException

        List<Integer> integerList = new LinkedList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);

        System.out.println("Before removing: " + integerList);

        Iterator<Integer> integerIterator = integerList.iterator();
        while (integerIterator.hasNext()){
            Integer element1 = integerIterator.next();
            if(element1.equals(2)){
                integerIterator.remove();
                //integerIterator.remove(); // IllegalStateException
            }
        }

        System.out.println("After removing: " + integerList);

    }
}
