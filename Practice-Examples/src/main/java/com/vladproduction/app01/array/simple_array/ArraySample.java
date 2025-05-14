package com.vladproduction.app01.array.simple_array;

import java.util.Arrays;

public class ArraySample {
    public static void main(String[] args) {
        int [] data = new int[5];
        data [0] = 1;
        data [1] = 2;
        data [2] = 3;
        data [3] = 4;
        data [4] = 5;

        System.out.println(Arrays.toString(data));
        System.out.println("--------------");

        String [] data2 = new String[5];
        data2 [0] = "one";
        data2 [1] = "two";
        data2 [2] = "three";
        data2 [3] = "four";
        data2 [4] = "five";

        System.out.println(Arrays.toString(data2));
        System.out.println("--------------");

        System.out.println("все элементы массива: ");
        for (int i = 0; i < data2.length; i++) {
            System.out.println(data2[i] + " ");
        }
        System.out.println();
        // сумма элементов
        int total = 0;
        for (int i = 0; i < data.length; i++) {
            total += data[i];
        }
        System.out.println("сумма элементов массива = " + total);
        System.out.println();
        //наибольшее значение
        int maxValue = data[0];
        for (int i = 1; i < data.length; i++) {
            if(data[i]>maxValue){
                maxValue = data[i];
            }
        }
        System.out.println("максимальный элемент = " + maxValue);

        System.out.println("-------------");
        System.out.println("new foreach ");
        double [] data3 = {1.2, 1.3,1.4,1.5,2.2};
        for (double element: data3) {
            System.out.println(element);
        }



    }

}
