package com.vladproduction.generic.producer_consumer;

import java.util.ArrayList;
import java.util.List;

public class Main_ch02_solution {
    public static void main(String[] args) {

        //List<Sport> sports = new ArrayList<? super Sport>(); //no available

                //populateContent(List<? super Sport> list):
        List<? super Sport> sports = new ArrayList<>();
        sports.add(new Sport());
        sports.add(new Football());
        populateContent(sports);

        List<Sport> sports1 = new ArrayList();
        sports1.add(new Sport());
        sports1.add(new Football());
        populateContent(sports1);

                //populateContent2(List<? super Sport> list):
        List<? super Sport> sports22 = new ArrayList<>();
        sports22.add(new Sport());
        sports22.add(new Football());
        //populateContent2(sports22); //Error

        List<Sport> sports33 = new ArrayList();
        sports33.add(new Sport());
        sports33.add(new Football());
        populateContent2(sports33);

    }

         // private static void populateContent(List list){
         //   list.add("Hello"); }

    private static void populateContent(List<? super Sport> list) { //add; producer
        list.add(new Football());
        list.add(new Sport());
        Object value = list.get(0);
    }

    private static void populateContent2(List<? extends Sport> list) { //get; consumer
//        list.add(new Football());  //error
//        list.add(new Sport());  //error
        Sport value = list.get(0);
    }
}

