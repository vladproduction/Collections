package com.vladproduction.app01.demo;

public class BrutalApp {
    public static void main(String[] args) {
        //create a couple of rooms:
        Room londonPresentation = new Room("London", "Presentation  hall", 200, 500.0);
        Room manchesterHotel = new Room("Manchester", "Living room", 5, 200.0);
        //by increasing amount of rooms we need to refactor a huge parts of code:
        Room chesterfieldHotel = new Room("Chesterfield", "Living room", 7, 250.0);


        double total  = getPotentialRevenue(londonPresentation, manchesterHotel, chesterfieldHotel); // need to add here as well
        System.out.println("total = " + total);

    }

    private static double getPotentialRevenue(Room room1, Room room2, Room room3) { //and refactoring here
        return room1.getPrice() + room2.getPrice() + room3.getPrice();
    }
}
