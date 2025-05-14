package com.vladproduction.app03.generics;

import com.vladproduction.app01.demo.Room;

import java.util.ArrayList;
import java.util.Collection;

public class GenericTypeCollection {
    public static void main(String[] args) {
        //with generic, we aren`t allowed to add elements to the collection any type
        //just because we define a type that collection can contain:
        Collection<Room> c = new ArrayList<>();
//        c.add("Cambridge"); //String cannot be converted to com.vladproduction.demo.Room
//        c.add(1L); //long cannot be converted to com.vladproduction.demo.Room
        c.add(new Room("Cambridge", "Suite", 5, 125));

        //casting is not needed as long as we have same types in collection:
//        c.stream()
//                .forEach(elem -> System.out.println(((Room)elem).getCity()));

        //as we have the same type we can access to elements methods directly
        c.forEach(elem -> System.out.println(elem.getCity())); //Cambridge


    }
}
