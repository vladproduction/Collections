package com.vladproduction.app03.set.treeset.demotreeset.app02;

import java.util.Comparator;

public class MyPhoneComparator implements Comparator {

    /*
    any negative --> obj1<obj2
    0 --> obj1==obj2
    any positive --> obj1>obj2

     */
    @Override
    public int compare(Object obj1, Object obj2) {
        return 1;
    }
}
