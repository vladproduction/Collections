package com.vladproduction.generic;

public class Main3 {
    public static void main(String[] args) {
        MyListGeneric<Integer> generic1 = new MyListGenericImpl();
        generic1.add(10);
        //generic1.add("aaa");

        MyListGenericImpl<String> generic2 = new MyListGenericImpl();
        MyListGenericImpl<String> generic3 = new MyListGenericImpl<String>();
        MyListGenericImpl<String> generic4 = new MyListGenericImpl<>();


    }
}
