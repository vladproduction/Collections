package com.vladproduction.app01.array.excercises_array;

public class CopyArrayDemo {
    public static void main(String[] args) {
        int [] a = {1,3,5,7,9};
        int [] b, c;
        b=a;
        c = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        a[0] = 8;
        b[b.length-1] = 0;
        System.out.println("a:\t b:\t c:");
        for(int k=0;k<a.length;k++){
            System.out.println(a[k]+"\t "+b[k]+"\t "+c[k]);
        }
    }
}
