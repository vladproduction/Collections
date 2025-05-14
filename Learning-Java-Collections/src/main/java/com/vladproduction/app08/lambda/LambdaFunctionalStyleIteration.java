package com.vladproduction.app08.lambda;

import com.vladproduction.app01.demo.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class LambdaFunctionalStyleIteration {
    public static void main(String[] args) {

        Room room1 = new Room("Manchester", "Presentation", 200, 500.0);
        Room room2 = new Room("London", "Hotel", 5, 200.0);
        Room room3 = new Room("Oxford", "Motel", 3, 100.0);
        Room room4 = new Room("Liverpool", "Hotel", 7, 300.0);
        Room room5 = new Room("Newcastle", "Motel", 5, 400.0);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2, room3, room4, room5));
        room1.setBreakfast(true);
        room2.setBreakfast(true);

        System.out.println("\tlambda:");
        //lambda functional style iteration:
        rooms.stream()
                .filter(room -> room.isBreakfast())
                .forEach(room -> System.out.println(room.getCity()));

        System.out.println("\tmethod reference:");
        //or method reference functional style iteration:
        rooms.stream()
                .filter(Room::isBreakfast)
                .forEach(room -> System.out.println(room.getCity()));

        /*Testing Manchester with result true
        Manchester
        Testing London with result true
        London
        Testing Oxford with result false
        Testing Liverpool with result false
        Testing Newcastle with result false*/

    }
}
