package com.vladproduction.app10.set;

import com.vladproduction.app01.demo.Room;

import java.util.LinkedHashSet;
import java.util.Set;

public class DemoSet {
    public static void main(String[] args) {
        Room manchester = new Room("Manchester", "Presentation", 100, 850.0);
        Room london = new Room("London", "Guest", 10, 250.0);
        Room londonDuplicate = new Room("London", "Guest", 10, 250.0);
        Room oxford = new Room("Oxford", "Community", 5, 250.0);

//        Set<Room> roomSet = new HashSet<>(); //no **order**
        Set<Room> roomSet = new LinkedHashSet<>(); // **ordering** is available for LinkedHasSet()
        roomSet.add(manchester);
        roomSet.add(london);
        roomSet.add(londonDuplicate);
        roomSet.add(oxford);

        //1) not duplicates as expected
        //2) no order by HashSet()
        roomSet.stream()
                .map(Room::getCity)
                .forEach(System.out::println);
        /* HashSet:
        London
        Manchester
        Oxford*/

        /* LinkedHashSet:
        Manchester
        London
        Oxford*/
    }
}
