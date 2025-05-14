package com.vladproduction.app02.list.arraylist;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.sort;

public class SortArrayList {
    public static void main(String[] args) {

        //Ascending sorting - от меньшего к большему (по возрастанию)
            //<String>
        ArrayList<String> stringArrayList =  new ArrayList<String>();
        stringArrayList.add("Volkswagen");
        stringArrayList.add("Toyota");
        stringArrayList.add("Porsche");
        stringArrayList.add("Ferrari");
        stringArrayList.add("Mercedes-Benz");
        stringArrayList.add("Audi");
        stringArrayList.add("Rolls-Royce");
        stringArrayList.add("BMW");
                // printing the unsorted ArrayList
        System.out.println("Before Sort: "+ stringArrayList);

        sort(stringArrayList);
                // printing the sorted ArrayList (ascending Order)
        System.out.println("After Sort: "+stringArrayList);

        System.out.println("------------------");


        //<Integer>
        ArrayList<Integer> numbersList = new ArrayList<Integer>();
        numbersList.add(10);
        numbersList.add(2);
        numbersList.add(68);
        numbersList.add(102);
        numbersList.add(-88);
        numbersList.add(5);
        numbersList.add(140);
        numbersList.add(21);
        System.out.println("<Integer> before sort: "+numbersList);
        for(int items: numbersList){
            System.out.println(items);
        }
        sort(numbersList);
        System.out.println("<Integer> after sort: "+numbersList);
        for(int items: numbersList){
            System.out.println(items);
        }

        System.out.println("------------");

        //Descending order - от большего к меньшему (по убыванию)
        //<String>
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("Data Science");
        stringList.add("Testing");
        stringList.add("C#");
        stringList.add("Basic Language");
        stringList.add("UML");
        stringList.add("Algorithms ");
        stringList.add("Computer Networks");
        stringList.add("Python");
        System.out.println("before:"+stringList);
        Collections.sort(stringList,Collections.reverseOrder());
        System.out.println("after:"+stringList);

        //<Integer>
        ArrayList <Integer> integerList = new ArrayList<Integer>();
        integerList.add(5);
        integerList.add(33);
        integerList.add(-9);
        integerList.add(0);
        integerList.add(15);
        integerList.add(20);
        integerList.add(105);
        System.out.println("before: "+integerList);
        Collections.sort(integerList,Collections.reverseOrder());
        System.out.println("after: "+integerList);
    }


}
