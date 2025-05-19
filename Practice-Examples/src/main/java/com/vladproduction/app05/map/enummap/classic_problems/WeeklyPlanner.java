package com.vladproduction.app05.map.enummap.classic_problems;


import java.util.EnumMap;
import java.util.List;

public class WeeklyPlanner {
    public static void main(String[] args) {
        EnumMap<Day, List<String>> planner = new EnumMap<>(Day.class);

        planner.put(Day.MONDAY, List.of("Gym", "Meeting"));
        planner.put(Day.TUESDAY, List.of("Work", "Study"));

        planner.forEach((day, tasks) -> {
            System.out.println(day + ": " + tasks);
        });
    }

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    }
}
