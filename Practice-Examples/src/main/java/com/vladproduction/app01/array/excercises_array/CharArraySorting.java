package com.vladproduction.app01.array.excercises_array;

public class CharArraySorting {
    public static void main(String[] args) {
        CharArraySorting sorting = new CharArraySorting();
        sorting.bubbleSort();
    }

    private void bubbleSort() {
        System.out.println("Bubble Sorting start:");
        String text = "Unsorted:\n";
        //size of [] and index variable:
        int size, i, j;
        size = 26;
        char [] symbols = new char[size];
        char s = 'a';
        // fill [] randomly:
        for (int k = 0; k < size; k++) {
            symbols[k] = (char)(s+(byte)(Math.random()*26));
            text += symbols[k] + " ";
        }
        text += "\nSorted:\n";
        //sorting starting here:
        for (int k = 0; k < size; k++) {
            for (int l = 0; l < size-k-1; l++) {
                if (symbols[l]>symbols[l+1]){
                    s = symbols[l+1];
                    symbols[l+1] = symbols[l];
                    symbols[l] = s;
                }
            }
        }
        //printing after sorting:
        for (int k = 0; k < size; k++) {
            text += symbols[k] + " ";
        }
        System.out.println(text);
    }
}
