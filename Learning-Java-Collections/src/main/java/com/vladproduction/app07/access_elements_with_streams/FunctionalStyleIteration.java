package com.vladproduction.app07.access_elements_with_streams;

import com.vladproduction.app01.demo.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionalStyleIteration {
    public static void main(String[] args) {

        Room room1 = new Room("Manchester", "Presentation", 200, 500.0);
        Room room2 = new Room("London", "Hotel", 5, 200.0);
        Room room3 = new Room("Oxford", "Motel", 3, 100.0);
        Room room4 = new Room("Liverpool", "Hotel", 7, 300.0);
        Room room5 = new Room("Newcastle", "Motel", 5, 400.0);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2, room3, room4, room5));
        room1.setBreakfast(true);
        room2.setBreakfast(true);

        //functional style iteration:
        rooms.stream()
                .filter(new Predicate<Room>() {
                    @Override
                    public boolean test(Room room) {
                        System.out.format("Testing %s with result %b%n", room.getCity(), room.isBreakfast());
                        return room.isBreakfast();
                    }
                })
                .forEach(new Consumer<Room>() {
                    @Override
                    public void accept(Room room) {
                        System.out.println(room.getCity());
                    }
                });

        /*Testing Manchester with result true
        Manchester
        Testing London with result true
        London
        Testing Oxford with result false
        Testing Liverpool with result false
        Testing Newcastle with result false*/

    }
}
