package com.vladproduction.app01.array.excercises_array;

public class ForEachDemo {
    public static void main(String[] args) {
        ForEachDemo forEachDemo = new ForEachDemo();
        forEachDemo.run();
    }

    private void run() {
        //array wanted to be print by for each iterating:
        int [] a = new int [] {1,2,6,4,55,78,10,100,7};
        for (int items: a){
            System.out.print(items+" ");
        }
        System.out.println();
    }
}
