package com.vladproduction.app02.list.arraylist.arraylistpractice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vladproduction on 23-Mar-24
 */

public class ArrayListConvention {
    public static void main(String[] args) {

        /**By Convention of Collections Framework we're referring to the Interface during creating the object of Collection
         * for example:
         * ArrayList<Integer> integers = new ArrayList<>();
         * we do:
         * List<Integer> integers = new ArrayList<>();*/

       List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        //remove:
        list.remove(5);
        System.out.println(list);

        //making new type of reference and add some data:
        list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);

        /**so we do not care about implementation, just using object of collection
         * in level of Interface*/

    }
}
