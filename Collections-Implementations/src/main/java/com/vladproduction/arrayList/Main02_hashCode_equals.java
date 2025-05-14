package com.vladproduction.arrayList;


import java.util.Arrays;

/*
* working with methods:
    hashCode overridden;
    equals overridden;
 */
public class Main02_hashCode_equals {
    public static void main(String[] args) {
        MyList list = new MyListImpl();
        list.add("Apple");
        list.add("Banana");
        list.add("Citrus");
        list.add("Grapefruit");
        list.add("Pamela");

        MyList list1 = new MyListImpl();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        list1.add("E");

        System.out.println(list.hashCode());//2107663819
        System.out.println(list1.hashCode());//90690786
        System.out.println(list.equals(list1));//false

        System.out.println("----------");
        //different objects
        Object[] data1 = new Object[]{"a","b","c"};
        Object[] data2 = new Object[]{"a","b","c"};
        System.out.println(data1.hashCode());//460141958
        System.out.println(data2.hashCode());//1163157884
        System.out.println(data1.equals(data2));//false

        System.out.println("----------");
        //objects has the same fullness, but they are still different as objects
        System.out.println(Arrays.hashCode(data1));//126145
        System.out.println(Arrays.hashCode(data2));//126145
        System.out.println(Arrays.equals(data2,data1));//true

        System.out.println("----inside different items---");
        Object[] data3 = new Object[]{"A","B","C"};
        Object[] data4 = new Object[]{"a","b","c"};
        System.out.println(data3.hashCode());//1956725890
        System.out.println(Arrays.toString(data3));//[A, B, C]
        System.out.println(data4.hashCode());//356573597
        System.out.println(Arrays.toString(data4));//[a, b, c]
        System.out.println(data3.equals(data4));//false
        System.out.println(Arrays.equals(data3,data4));//false


    }
}
