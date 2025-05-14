package com.vladproduction.app01.array.simple_array;



public class ArraySimple2 {
    public static void main(String[] args) {
        double [] data = {1.2,1.3,1.4,1.5,2.5};
        printArray(data);


        int [] data2 = {1,2,3,4,5};
        for (int i : reverse(data2)) {
            System.out.println(i);
        }
        System.out.println("--------");
    }

    public static int[] reverse(int[] d) {
        int[] result = new int[d.length];
        for (int i = 0, j = result.length - 1; i < d.length; i++, j--) {
            result[j] = d[i];
        }
        return result;
    }

    private static void printArray(double[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i] + " ");
        }
    }
}
