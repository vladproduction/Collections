package com.vladproduction.app15.utilities;

import com.vladproduction.app01.demo.Room;

import java.util.ArrayList;
import java.util.List;

public class MainComparatorBuildInRoom {
    public static void main(String[] args) {

        Room piccadilly = new Room("Piccadilly", "Guest Room", 3, 175.00);
        Room piccadilly2 = new Room("Piccadilly", "Guest Room", 3, 175.00);
        Room oxford = new Room("Oxford", "Suite", 5, 725.0);
        Room oxfordGuest = new Room("Oxford", "Guest Room", 5, 225.0);
        Room oxfordGuest2 = new Room("Oxford", "Guest Room", 5, 225.0);
        Room manchester = new Room("Manchester", "Premiere", 7, 1125.0);
        Room liverpool = new Room("Liverpool", "Suite", 9, 525.0);

        List<Room> rooms = new ArrayList<>(List.of(piccadilly, piccadilly2, oxford, oxfordGuest, oxfordGuest2, manchester, liverpool));

        //sorting by prices, city, type
//        rooms.sort(Room.PRICE_RATE);

        //by the way we can sort collection in reversed order
        rooms.sort(Room.PRICE_RATE.reversed());

        rooms.forEach(r -> System.out.format("%-15s %-15s %-10f %n", r.getCity(), r.getType(), r.getPrice()));

        //before sorting:
        /*  Piccadilly      Guest Room      175.000000
            Piccadilly      Guest Room      175.000000
            Oxford          Suite           725.000000
            Oxford          Guest Room      225.000000
            Oxford          Guest Room      225.000000
            Manchester      Premiere        1125.000000
            Liverpool       Suite           525.000000 */

        //after sorting (reversed):
        /*  Manchester      Premiere        1125.000000
            Oxford          Suite           725.000000
            Liverpool       Suite           525.000000
            Oxford          Guest Room      225.000000
            Oxford          Guest Room      225.000000
            Piccadilly      Guest Room      175.000000
            Piccadilly      Guest Room      175.00000 */

    }
}
