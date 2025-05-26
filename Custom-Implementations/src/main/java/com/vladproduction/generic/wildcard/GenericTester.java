package com.vladproduction.generic.wildcard;

import java.util.ArrayList;
import java.util.List;

public class GenericTester {
    public static void main(String[] args) {

        List<Animal> animalList= new ArrayList<Animal>();
        List<RedCat> redCatList= new ArrayList<RedCat>();

        //add list of super class Animal of Cat class
        addCat(redCatList);
        //add list of Cat class
        addCat(redCatList);
        addCat(redCatList);

        //print all animals
        printAll(animalList);
        printAll(redCatList);

        Cat cat = redCatList.get(0);
        //delete cat
        deleteCat(redCatList, cat);
        printAll(redCatList);

    }

    public static void deleteCat(List<? extends Cat> catList, Cat cat){
        catList.remove(cat);
        System.out.println("Cat removed");
    }

    private static void addCat(List<? super RedCat> catListRed) {
        catListRed.add(new RedCat("red"));
        System.out.println("Cat Added");
    }

    public static void printAll(List<?> list) {
        for (Object item : list)
            System.out.println(item + " ");
    }

}
