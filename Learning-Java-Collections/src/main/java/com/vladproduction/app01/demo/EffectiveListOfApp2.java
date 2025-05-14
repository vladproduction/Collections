package com.vladproduction.app01.demo;

import java.util.Collection;
import java.util.List;

public class EffectiveListOfApp2 {
    public static void main(String[] args) {
        //create a couple of rooms:
        Room londonPresentation = new Room("London", "Presentation  hall", 200, 500.0);
        Room manchesterHotel = new Room("Manchester", "Living room", 5, 200.0);
        Room chesterfieldHotel = new Room("Chesterfield", "Living room", 7, 250.0);
        Room oxfordDining = new Room("oxford", "Dining room", 17, 350.0); //create a new one

        //add them into collection for  example (Collection<Room>):
        Collection<Room> roomList = List.of(londonPresentation, manchesterHotel, chesterfieldHotel, oxfordDining);// add here

        //now we can only add instance to the list, and no any crucial refactoring needed
        //only creating room and add it to the collection


        double total  = getPotentialRevenue(roomList);
        System.out.println("total = " + total);

    }

    private static double getPotentialRevenue(Collection<Room> roomList) {
        //lambda expression:
        return roomList.stream()
                .mapToDouble(r->r.getPrice())
                .sum();
        //or method reference:
        /*return roomList.stream()
                .mapToDouble(Room::getPrice)
                .sum();*/
    }
}
