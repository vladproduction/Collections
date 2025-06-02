package com.vladproduction.ch07_generic.p07_generic_event_system;

public class MainApp {
    public static void main(String[] args) {

        EventManager<String> stringEventManager = new EventManager<>();
        EventManager<Integer> intEventManager = new EventManager<>();

        // String event listeners
        stringEventManager.addListener(event -> System.out.println("String event received: " + event));
        stringEventManager.addListener(event -> System.out.println("String event length: " + event.length()));

        // Integer event listener
        intEventManager.addListener(event -> System.out.println("Integer event received: " + event));

        // Fire events
        stringEventManager.fireEvent("Hello Generic Events!");
        intEventManager.fireEvent(42);

    }
}
