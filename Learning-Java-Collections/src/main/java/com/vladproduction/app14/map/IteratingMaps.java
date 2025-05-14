package com.vladproduction.app14.map;

import com.vladproduction.app01.demo.Room;
import com.vladproduction.app11.list.Guest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IteratingMaps {
    public static void main(String[] args) {
        
        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 125.00);
        Room oxford = new Room("Oxford", "Suite", 5, 225.0);
        Room manchester = new Room("Manchester", "Suite", 7, 425.0);
        Room liverpool = new Room("Liverpool", "Suite", 9, 525.0);

        Guest john = new Guest("John", "Doe", false);
        Guest maria = new Guest("Maria", "Doe", true);
        Guest bob = new Guest("Bob", "Doe", false);
        Guest frank = new Guest("Frank", "Doe", true);

        Map<Room, Guest> assignments = new HashMap<>();
        assignments.put(oxford, maria);
        assignments.put(piccadilly, john);
        assignments.put(manchester, bob);
        assignments.put(liverpool, frank);

        Set<Map.Entry<Room, Guest>> entries = assignments.entrySet();
        for (Map.Entry<Room, Guest> entry : entries) {
            Room key = entry.getKey();
            Guest value = entry.getValue();
            System.out.format("%s : %s%n", key.getCity(), value.getFirstName());
        }

    }
}
