package com.vladproduction.app02.foundations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class CreatingCollections {
    public static void main(String[] args) {
        //can be declared and instantiated like any other Object
        Collection<String> collection = new ArrayList<>();
        collection.add("football");
        collection.add("java");
        collection.add("book");
        collection.add("football");

        System.out.println("collection = " + collection); //[football, java, book, football]

        //converting collection as a parameter into new type of collection:
        Collection<String> favourite = new HashSet<>(collection);
        System.out.println("favouriteHasSet = " + favourite); //[java, book, football]
        //we have lost one element - set not allowed duplicates

        //by using the most abstract type of collection
        //allow to switch the implementation easily way
        Collection<String> hobbiesDeque = new ArrayDeque<>();
        hobbiesDeque.add("football");
        hobbiesDeque.add("java");
        hobbiesDeque.add("book");
        hobbiesDeque.add("football");
        System.out.println("hobbiesDeque = " + hobbiesDeque.size());
        System.out.println("hobbiesDeque = " + hobbiesDeque);

        //but this type of collection isn`t such abstract (more specific actually):
        ArrayList<String> hobbiesArray = new ArrayList<>();
        hobbiesArray.add("football");
        hobbiesArray.add("java");
        hobbiesArray.add("book");
        hobbiesArray.add("football");
        System.out.println("hobbiesArray = " + hobbiesArray.isEmpty() + ", size = " + hobbiesArray.size());
        System.out.println("hobbiesArray = " + hobbiesArray);
    }
}
