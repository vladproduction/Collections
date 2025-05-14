package com.vladproduction.app01.array.arrays_and_objects;

public class ArraysAndObjects {
    public static void main(String[] args) {

        /*  fields as array, fields as objects, 
            and creating array of objects*/

        // amount of objects in array:
        int count = 5;
        // creating array of objects variable:
        Second[] objArr = new Second[count];
        // relating objects variable with elements of array
        // by class Second creates objects:
        for (int i = 0; i < objArr.length; i++) {
            objArr[i] = new Second(3 * i + 4);
        }
    }
}

