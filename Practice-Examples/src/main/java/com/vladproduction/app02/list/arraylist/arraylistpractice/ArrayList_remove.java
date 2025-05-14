package com.vladproduction.app02.list.arraylist.arraylistpractice;

import java.util.ArrayList;

/**
 * Created by vladproduction on 23-Mar-24
 */

public class ArrayList_remove {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        //add:
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        //remove:
        list.remove(5);
        System.out.println(list);
        /**remove in Array List is not effective*/


    }
}
