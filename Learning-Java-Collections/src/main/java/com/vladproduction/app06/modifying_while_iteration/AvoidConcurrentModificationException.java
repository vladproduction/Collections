package com.vladproduction.app06.modifying_while_iteration;

import com.vladproduction.app01.demo.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class AvoidConcurrentModificationException {
    public static void main(String[] args) {

        Room room1 = new Room("Manchester", "Presentation", 200, 500.0);
        Room room2 = new Room("London", "Hotel", 5, 200.0);
        Room room3 = new Room("Oxford", "Motel", 3, 100.0);
        Room room4 = new Room("Liverpool", "Hotel", 7, 300.0);
        Room room5 = new Room("Newcastle", "Motel", 5, 400.0);

        Collection<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2, room3, room4, room5));
        room1.setBreakfast(true);
        room2.setBreakfast(true);

        //1)create a new collection where we are going to store room to be removed
        Collection<Room> removedRooms = new ArrayList<>();

        //2)add to this collection rooms wanted to be deleted:
        for (Room room : rooms) { //ConcurrentModificationException
            if(room.isBreakfast()){
                removedRooms.add(room);
            }
        }

        //3) removing using bulk operation:
        rooms.removeAll(removedRooms);

        /*//protect collection from modifying by failing fast
        for (Room room : rooms) { //ConcurrentModificationException
            if(room.isBreakfast()){
                rooms.remove(room);
            }
        }*/

        //iterating by streaming and check which rooms left in collection after removing:
        rooms.forEach(room -> System.out.println(room.getCity()));
        //as expected room1 and room2 were removed
        /*Oxford
          Liverpool
          Newcastle*/


    }
}
