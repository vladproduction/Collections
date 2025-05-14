package com.vladproduction.app10.set;

import com.vladproduction.app01.demo.Room;

import java.util.LinkedHashSet;
import java.util.Set;

public class UnmodifiableSet {
    public static void main(String[] args) {
        Room manchester = new Room("Manchester", "Presentation", 100, 850.0);
        Room london = new Room("London", "Guest", 10, 250.0);
        Room londonDuplicate = new Room("London", "Guest", 10, 250.0);
        Room oxford = new Room("Oxford", "Community", 5, 250.0);
        Room liverpool = new Room("Liverpool", "Community", 5, 250.0); //new element created

        Set<Room> roomSet = new LinkedHashSet<>();
        roomSet.add(manchester);
        roomSet.add(london);
        roomSet.add(londonDuplicate);
        roomSet.add(oxford);
        roomSet.add(liverpool); //added new element

        /** Set.of(...) - set is not modified (not allowed to add elements):*/
        Set<Room> rooms = Set.of(manchester, london);
//        rooms.add(oxford);  //UnsupportedOperationException

        Set<Room> moreRooms = Set.copyOf(roomSet); //set is backed of all elements from source collection
        //but in that case (after copy and streaming) **order** does not support

        moreRooms.stream()
                .map(Room::getCity)
                .forEach(System.out::println);
        /* before adding liverpool
        Manchester
        Oxford
        London*/

        /* after adding liverpool
        Manchester
        Oxford
        London
        Liverpool*/
    }
}
