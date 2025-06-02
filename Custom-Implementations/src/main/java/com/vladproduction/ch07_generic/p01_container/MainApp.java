package com.vladproduction.ch07_generic.p01_container;

public class MainApp {
    public static void main(String[] args) {

        // Create containers
        Container<String> stringContainer = new Container<>("Hello World");
        Container<Integer> intContainer = new Container<>(42);

        //toString()
        System.out.println("String container: " + stringContainer);
        System.out.println("Integer container: " + intContainer);

        //setItem()
        stringContainer.setItem("Hello Java");
        System.out.println("String container: " + stringContainer);

        intContainer.setItem(100);
        System.out.println("Integer container: " + intContainer);

        //getItem()
        System.out.println(stringContainer.getItem());
        System.out.println(intContainer.getItem());

    }
}
