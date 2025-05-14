package com.vladproduction.app01.demo;

public class RoomMain {
    public static void main(String[] args) {
        Room room1 = new Room("NY", "Suite", 5, 200.0);
        Room room2 = new Room("NY", "Suite", 5, 200.0);
        System.out.println(room1);
        System.out.println(room2);

        //equals:
        System.out.println(room1.equals(room2)); //expected true

        //==
        System.out.println(room1 == room2); //expected false

    }
}
