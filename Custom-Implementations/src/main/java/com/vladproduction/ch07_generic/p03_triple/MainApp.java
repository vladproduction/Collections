package com.vladproduction.ch07_generic.p03_triple;

public class MainApp {
    public static void main(String[] args) {

        // Create a triple
        Triple<String, Integer, Double> studentRecord = new Triple<>("Bob", 20, 85.5);

        //toString()
        System.out.println("Student record: " + studentRecord);

        // Get values
        System.out.println("Name: " + studentRecord.getFirst());
        System.out.println("Age: " + studentRecord.getSecond());
        System.out.println("GPA: " + studentRecord.getThird());

    }
}
