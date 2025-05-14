package com.vladproduction.app01.array.excercises_array;

public class SimpleArray {
    public static void main(String[] args) {

        int i, size;
        size = 10;
        String oddNumbers = " ";
        int [] numOdd = new int[size];
        for ( i = 0; i < size; i++) {
            numOdd[i] = (2*i)+1;
            oddNumbers += numOdd[i] + " ";
        }
        System.out.println(oddNumbers); //out [] of odd (not even) numbers
    }
}
