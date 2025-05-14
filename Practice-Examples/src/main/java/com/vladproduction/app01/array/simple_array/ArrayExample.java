package com.vladproduction.app01.array.simple_array;

import java.util.Arrays;

public class ArrayExample {
    public static void main(String[] args) {
        String[] animals = new String[5];
        animals[0] = "dog";
        animals[1] = "cat";
        animals[2] = "bird";

        System.out.println(Arrays.toString(animals));
        System.out.println("-------------------");

        System.out.println(animals[0]);
        System.out.println(animals[1]);
        System.out.println(animals[2]);
        System.out.println(animals[3]);
        System.out.println(animals[4]);
        //System.out.println(animals[5]); //Exception: ArrayIndexOutOfBoundsException

        animals[2] = "fish";
        System.out.println(Arrays.toString(animals));
        System.out.println("-------------------");

        int[] numbersDemo = new int[2];
        numbersDemo[0] = 100;
        numbersDemo[1] = 200;
        System.out.println(Arrays.toString(numbersDemo));
        System.out.println("-------------------");
        //or
        int[] numbers = {100, 200};
        System.out.println(Arrays.toString(numbers));

        System.out.println("--------for iteration---------------");
        for (int i = 0; i < animals.length; i++){
            System.out.println(animals[i]);
        }
        System.out.println("-------for iteration from end----------------");
        //or from the end
        for (int i = animals.length - 1; i >= 0; i--){
            System.out.println(animals[i]);
        }
        System.out.println("-------length----------------");
        System.out.println(animals.length); //to know length of th array(size)
        System.out.println("----for-------------------");
        //looping
        for (String animal: animals) {
            System.out.println(animal);
        }
        System.out.println("------as stream-----------------");
        //or stream:
        Arrays.stream(animals).forEach(System.out::println);

        //utility:
        //Arrays. --> lots of methods form java.util

    }
}
