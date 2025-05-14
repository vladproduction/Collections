package com.vladproduction.app01.array.excercises_array;

import java.util.Scanner;

public class SortArrayBubble {
    public static void main(String[] args) {
        SortArrayBubble sortArrayBubble = new SortArrayBubble();
        sortArrayBubble.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unsorted array:");
        String [] numbs = scanner.nextLine().split(" ");

        System.out.println("Sorted array:");
        sortBubble(numbs);

    }

    private int [] sortBubble(String [] numbs) {
        int array[] = new int[numbs.length];
        for (int i = 0; i < numbs.length; i++) {
            array[i] = Integer.parseInt(numbs[i]);
        }
        int s;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length-i; j++) {
                if(array[j] > array[j+1]){
                    s = array[j+1];
                    array[j+1] = array[j];
                    array[j] = s;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        return array;
    }
}
