package com.vladproduction.app03.set.linkedhashset.demolinkedhashset;

import java.util.LinkedHashSet;

public class Main01 {

    /*
    Set:
    1) no same items (unique)//
    question - what is unique ? (based on equals and hashcode)
    2) no index access // set.get(i)
    we need to use iterator
    3) no order
    no guarantee that order of added data is not same as we get it
    it may be same, but just a case

    LinkedHashSet:
    3) override default Set behaviour
    And guarantees order

     */

    public static void main(String[] args) {

        LinkedHashSet set = new LinkedHashSet();
        set.add("D");
        set.add("t");
        set.add("D");
        set.add("h");
        for(Object item: set){
            System.out.println(item);
        }
        //set.get(0);
        System.out.println(set.getClass());

    }
}
