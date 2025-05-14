package com.vladproduction.app02.list.arraylist.arraylistpractice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vladproduction on 24-Mar-24
 */

public class MyPracticeArrayList_2_merging_lists {
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
        list1.stream().forEach(integer -> System.out.print(integer));
        System.out.println();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0)
                list2.add(i);
        }
        list2.stream().forEach(integer -> System.out.print(integer));
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
}
