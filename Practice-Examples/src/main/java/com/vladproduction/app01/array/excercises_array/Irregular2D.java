package com.vladproduction.app01.array.excercises_array;

public class Irregular2D {
    public static void main(String[] args) {
        int [] n = {2,5,1,4,7,9};
        int value = 1;
        int [][] a = new int[n.length][];
        for (int i = 0; i < a.length; i++) {
            a[i] = new int[n[i]];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = value++;
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
