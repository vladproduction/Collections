package com.vladproduction.app02.list.arraylist.arraylistpractice2;

import java.util.ArrayList;
import java.util.List;

public class WorkingWithLists {
    public static void main(String[] args) {
        System.out.println("--undefine List-----");
//        List colors = new ArrayList();
//        colors.add("red");
//        colors.add("black");
//        colors.add("white");
//        colors.add(1);
//        colors.add(new Object());
//        System.out.println(colors);

        System.out.println("-----List<String>-----");
        //List<String> colorsString = new LinkedList<>();
        //or
        List<String> colorsString = new ArrayList<>();
        colorsString.add("red");
        colorsString.add("black");
        colorsString.add("white");
        System.out.println(colorsString);
        System.out.println("-------------------");
        System.out.println(colorsString.size());
        System.out.println("-------------------");
        System.out.println(colorsString.contains("red") + ": 'red' contains?");
        System.out.println(colorsString.contains("yellow") + ": 'yellow' contains?");
        //looping
        System.out.println("------looping for each-------------");
        for (String item: colorsString) {
            System.out.println(item);
        }
        System.out.println("------looping for each stream-------------");
        colorsString.forEach(System.out::println);
        System.out.println("------looping for each traditional-------------");
        for (int i = 0; i < colorsString.size(); i++) {
            System.out.println(colorsString.get(i));
        }
        System.out.println("---to construct our List.of()-unmodifiable---");
        System.out.println("ImmutableCollections");
        List<String> colorsUnmodifiable = List.of(
                "red",
                "gray",
                "green"
        );
        //colorsUnmodifiable.add("blue"); //UnsupportedOperationException

    }
}
