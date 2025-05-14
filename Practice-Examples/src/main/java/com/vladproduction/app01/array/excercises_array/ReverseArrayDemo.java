package com.vladproduction.app01.array.excercises_array;

import java.util.Random;

public class ReverseArrayDemo {
    public static void main(String[] args) {
        int [] numbers = new int[10];
        Random random = new Random();
        for (int i = 0; i< numbers.length;i++){
            numbers[i] = random.nextInt(20);
            System.out.print(numbers[i]+" ");
        }
        System.out.println();
        //reverse:
        for (int i = 0, s ; i < numbers.length/2 ; i++) {
            s = numbers[numbers.length-1-i];
            numbers[numbers.length-1-i] = numbers[i];
            numbers[i] = s;
        }
        //printing reverse:
        for (int i = 0; i< numbers.length;i++){
            System.out.print(numbers[i]+" ");
        }
        System.out.println();
    }
}
