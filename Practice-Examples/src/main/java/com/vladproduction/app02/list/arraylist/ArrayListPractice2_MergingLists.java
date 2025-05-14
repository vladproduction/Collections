package com.vladproduction.app02.list.arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListPractice2_MergingLists {
    public static void main(String[] args) {

        System.out.println(" 7)========");
        /**Merge two sorted lists:

         Create two sorted ArrayList<Integer> named list1 and list2.
         Write a function to merge these lists into a new sorted ArrayList<Integer>.
         Use a two-pointer approach comparing elements from both lists and adding the smaller element to the result list.*/
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            if(i % 2 != 0)
            list1.add(i);
        }
        list1.forEach(i -> System.out.print(i + " "));
        System.out.println();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0)
                list2.add(i);
        }
        list2.forEach(i -> System.out.print(i + " "));
        System.out.println();

        List<Integer> mergeSortedLists = mergeSortedLists(list1, list2);
        String resultList = mergeSortedLists.stream().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(resultList);


    }

    private static List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2){

        List<Integer> mergedList = new ArrayList<>();
        int i = 0;
        int j = 0;

        //iterate while there are elements in both lists:
        while (i < list1.size() && j < list2.size()){
            int element1 = list1.get(i);
            int element2 = list2.get(j);
            //add smaller to the merged:
            if(element1 <= element2){
                mergedList.add(element1);
                i++;
            }else {
                mergedList.add(element2);
                j++;
            }
        }
        mergedList.addAll(list1.subList(i, list1.size()));
        mergedList.addAll(list2.subList(j, list2.size()));

        return mergedList;
    }

    /**
     * Created by vladproduction on 24-Mar-24
     */

    public static class ArrayListPractice4_FindMinMax {
        public static void main(String[] args) {

            System.out.println(" 9)========");
            /**Find the minimum and maximum elements:

             Write code to find the minimum and maximum elements in the numbers list without sorting.
             Iterate through the list and keep track of the current minimum and maximum values encountered.*/
            List<Integer> list = new ArrayList<>();
            list.add(5);
            list.add(50);
            list.add(-5);
            list.add(2);
            list.add(105);
            find_Min_Max(list);
        }

        private static void find_Min_Max(List<Integer> list) {
            int currentMin = list.get(0);
            int currentMax = list.get(0);

            for (Integer number : list) {
                if(number < currentMin){
                    currentMin = number;
                }
                if (number > currentMax){
                    currentMax = number;
                }
            }
            System.out.println("Min: " + currentMin);
            System.out.println("Max: " + currentMax);
        }
    }
}
