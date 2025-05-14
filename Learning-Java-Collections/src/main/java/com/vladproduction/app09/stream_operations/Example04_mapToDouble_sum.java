package com.vladproduction.app09.stream_operations;

import com.vladproduction.app01.demo.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static java.lang.System.out;

public class Example04_mapToDouble_sum {
    public static void main(String[] args) {
        Room room1 = new Room("Manchester", "Presentation", 200, 500.0);
        Room room2 = new Room("London", "Hotel", 5, 200.0);
        Room room3 = new Room("Oxford", "Motel", 3, 100.0);
        Room room4 = new Room("Liverpool", "Hotel", 7, 300.0);
        Room room5 = new Room("Newcastle", "Motel", 5, 400.0);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2, room3, room4, room5));
        room1.setBreakfast(true);
        room2.setBreakfast(true);

        //Example04_mapToDouble_sum:
        /**
         * we assign our stream result to a new collection;
         * it possible by using terminal operation 'collect(Collectors.toList()) or toList()';
         * we are mapping rooms in breakfastRooms collection by price;
         * and return sum of thees elements (in our case in double);
         * */

        Collection<Room> breakfastRooms = rooms.stream()
                .filter(Room::isBreakfast)
                .toList();

        //and for each element of breakfastRooms summing prices and getting result:
        double sum = breakfastRooms.stream()
                .mapToDouble(Room::getPrice)
                .sum();
        out.println("sum = " + sum); //as expected room1.getPrice = 500.0 and room2.getPrice = 200.0 (500 + 200 = 700)
        /*sum = 700.0*/



    }
}
