package com.vladproduction.generic;

public class Main {
    public static void main(String[] args) {
        MyListGeneric<Integer> generic1 = new MyListGenericImpl();
        generic1.add(10);
        //generic1.add("aaa");

        MyListGeneric<String> generic2 = new MyListGenericImpl();
        //generic2.add(10);
        generic2.add("aaa");

        MyListGeneric<Object> generic3 = new MyListGenericImpl();
        generic3.add(10);
        generic3.add("aaa");


        MyListGeneric generic4 = new MyListGenericImpl();
        generic4.add(10);
        generic4.add("aaa");


    }
}
