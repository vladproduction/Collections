package com.vladproduction.app09.stream_operations;

import com.vladproduction.app01.demo.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static java.lang.System.*;

public class Example01_external_object {
    public static void main(String[] args) {
        Room room1 = new Room("Manchester", "Presentation", 200, 500.0);
        Room room2 = new Room("London", "Hotel", 5, 200.0);
        Room room3 = new Room("Oxford", "Motel", 3, 100.0);
        Room room4 = new Room("Liverpool", "Hotel", 7, 300.0);
        Room room5 = new Room("Newcastle", "Motel", 5, 400.0);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2, room3, room4, room5));
        room1.setBreakfast(true);
        room2.setBreakfast(true);

        //Example01_external_object:
        /**
         * we pass in external object (breakfastRooms) and modifying it after;
         * NOTE: it`s ok to work with external object inside stream and
         * it`s ok to modify object inside stream, but it doesn`t allow to do both;
         * */

        Collection<Room> breakfastRooms = new ArrayList<>();
        //add rooms with breakfast into a new collection 'breakfastRooms'
        /*rooms.stream()
                .filter(Room::isBreakfast)
                .forEach(room -> breakfastRooms.add(room)); //lambda here*/
        rooms.stream()
                .filter(Room::isBreakfast)
                .forEach(breakfastRooms::add); //method reference

        //and for each element of breakfastRooms printing city name:
        breakfastRooms.stream()
                .forEach(room -> out.println(room.getCity()));
        /*Manchester
          London*/

        //or shorter writing is here
        breakfastRooms.forEach(room -> out.println(room.getCity()));
        /*Manchester
          London*/



    }
}
