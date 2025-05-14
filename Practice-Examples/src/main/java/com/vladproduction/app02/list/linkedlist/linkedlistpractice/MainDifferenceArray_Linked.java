package com.vladproduction.app02.list.linkedlist.linkedlistpractice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vladproduction on 23-Mar-24
 */

public class MainDifferenceArray_Linked {
    public static void main(String[] args) {

        List<Integer> listLinked = new LinkedList<>();
        List<Integer> listArray = new ArrayList<>();

        System.out.println("====adding(to end) measure======"); //[0]->[0,1]->[0,1,2]->...
        measureTimeAdd_End(listLinked);
        measureTimeAdd_End(listArray);
        System.out.println("====reading measure======");
        measureTimeReading(listLinked);
        measureTimeReading(listArray);
        System.out.println("====adding(to begin position) measure======"); //[0]->[1,0]->[2,1,0]->...
        measureTimeAdd_OnFirstIndex(listLinked);
        measureTimeAdd_OnFirstIndex(listArray);
    }

    private static void measureTimeAdd_End(List<Integer> list){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            list.add(i); //add to the end of array
        }
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }

    private static void measureTimeReading(List<Integer> list){
        for (int i = 0; i < 100_000; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
             list.get(i);
        }
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }

    private static void measureTimeAdd_OnFirstIndex(List<Integer> list){
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100_000; i++) {
            list.add(0, i);
        }

        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }

}
