package com.vladproduction.app05.map.enummap;

import java.util.EnumMap;

public class EnumMapBasics {
    public static void main(String[] args) {
        EnumMap<Day, String> schedule = new EnumMap<>(Day.class);

        schedule.put(Day.MONDAY, "Math");
        schedule.put(Day.TUESDAY, "Physics");
        schedule.put(Day.WEDNESDAY, "Chemistry");

        System.out.println("Schedule for Monday: " + schedule.get(Day.MONDAY));
        System.out.println("Contains WEDNESDAY? " + schedule.containsKey(Day.WEDNESDAY));
    }

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    }

}
