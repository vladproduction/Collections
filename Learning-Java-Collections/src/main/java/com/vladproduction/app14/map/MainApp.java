package com.vladproduction.app14.map;

import com.vladproduction.app01.demo.Room;
import com.vladproduction.app11.list.Guest;

import java.util.HashMap;
import java.util.Map;

public class MainApp {
    public static void main(String[] args) {
        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125.00);
        Room oxford = new Room("Oxford", "Suite", 5, 225.0);

        Guest john = new Guest("John", "Doe", false);
        Guest maria = new Guest("Maria", "Doe", true);

        Map<Room, Guest> assignments = new HashMap<>();
        assignments.put(oxford, maria);
        assignments.put(piccadilly, john);

        System.out.println("Oxford: " + assignments.get(new Room("Oxford", "Suite", 5, 225.0)));
        System.out.println("Piccadilly: " + assignments.get(piccadilly));

        /*  equals() and hashCode() is overridden
        Oxford: Maria Doe
        Piccadilly: John Doe*/

        //in case not override, we got (Oxford: null)
    }
}
