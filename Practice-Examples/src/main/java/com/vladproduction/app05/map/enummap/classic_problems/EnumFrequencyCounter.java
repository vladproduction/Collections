package com.vladproduction.app05.map.enummap.classic_problems;

import java.util.EnumMap;



public class EnumFrequencyCounter {
    public static void main(String[] args) {
        Status[] results = {
            Status.SUCCESS, Status.FAILURE, Status.SUCCESS, Status.PENDING, Status.SUCCESS
        };

        EnumMap<Status, Integer> counter = new EnumMap<>(Status.class);
        for (Status status : results) {
            counter.put(status, counter.getOrDefault(status, 0) + 1);
        }

        System.out.println("Status counts: " + counter);
    }

    enum Status {
        SUCCESS, FAILURE, PENDING
    }

}
