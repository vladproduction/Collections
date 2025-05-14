package com.vladproduction.app01.array.excercises_array;

public class ObjectsArrayDemo {
    public static void main(String[] args) {
        Object [] objects = new Object[3];
        objects[0] = 123;
        objects[1] = 'A';
        objects[2] = "JAVA";
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
        System.out.println();
        objects[0] = 17;
        objects[1] = "JDK";
        objects[2] = 1.17;
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
    }
}
