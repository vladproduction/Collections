package com.vladproduction.app11.generics.demo;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);

        addElements(list);

        System.out.println(list);

        for (Object i : list) {
            System.out.println(i.getClass().getName());
        }

        System.out.println("---------");

        List<ModelA> listA = new ArrayList<>();

        listA.add(new ModelA());
        listA.add(new ModelB());

//        addElements(listA);

        addModel(listA);
//        addModel(list);

        for (ModelA modelA : listA) {
            System.out.println(modelA.getClass().getName());
        }

        List<? super ModelA> listB = new ArrayList<>();

        //addModel(listB);

        //addModel2(listB);

//        takeModel(listB);

        addModel(listB);






    }

    private static void addElements(List list){
        list.add("1");
    }


    private static void addModel(List<? super ModelA> list){

//        list.add("String"); //error
        list.add(new ModelA());
        list.add(new ModelB());
    }

    private static void takeModel(List<? extends ModelA> list){

//        list.add(new ModelA());
        //list.add(null);  //

        ModelA modelA = list.get(0);
        ModelB modelB = (ModelB) list.get(0);

    }

    private static void addModel2(List<ModelA> list){

        list.add(new ModelA());
        list.add(new ModelB());

        ModelA modelA = list.get(0);
//        ModelB model = list.get(0);


    }




}
