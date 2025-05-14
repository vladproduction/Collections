package com.vladproduction.app01.demo;

import java.util.ArrayList;
import java.util.List;

public class EffectiveApp {
    public static void main(String[] args) {
        //create a couple of rooms:
        Room londonPresentation = new Room("London", "Presentation  hall", 200, 500.0);
        Room manchesterHotel = new Room("Manchester", "Living room", 5, 200.0);
        Room chesterfieldHotel = new Room("Chesterfield", "Living room", 7, 250.0);

        //add them into collection for  example (List<Rooms>):
        List<Room> roomList = new ArrayList<>();
        roomList.add(londonPresentation);
        roomList.add(manchesterHotel);
        roomList.add(chesterfieldHotel);

        //now we can only add instance to the list, and no any crucial refactoring needed
        //only creating room and add it to the list


        double total  = getPotentialRevenue(roomList);
        System.out.println("total = " + total);

    }

    private static double getPotentialRevenue(List<Room> roomList) {
        double result = 0;
        for (Room room : roomList) {
            result += room.getPrice();
        }
        return result;
    }
}
