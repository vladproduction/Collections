package com.vladproduction.app03.set.treeset.app02;

import java.util.TreeSet;

/*
    Exception in thread "main" java.lang.ClassCastException: com.app02.Phone
    cannot be cast to java.lang.Comparable
 */
public class MyApp02 {

    public static void main(String[] args) {
        Phone lg = new Phone();
        lg.price=1;

        Phone htc = new Phone();
        htc.price=2;

        Phone samsung = new Phone();
        samsung.price=3;

        //rules to make compare between 2 Phones
        MyPhoneComparator comparator = new MyPhoneComparator();
        TreeSet set = new TreeSet(comparator);

        set.add(lg);
        set.add(htc);
        set.add(samsung);

        System.out.println(set);

    }

}
