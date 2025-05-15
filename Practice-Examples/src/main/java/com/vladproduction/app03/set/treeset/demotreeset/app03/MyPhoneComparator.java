package com.vladproduction.app03.set.treeset.demotreeset.app03;



import java.util.Comparator;

public class MyPhoneComparator implements Comparator {

    /*
    any negative --> obj1<obj2
    0 --> obj1==obj2
    any positive --> obj1>obj2

     */
    @Override
    public int compare(Object obj1, Object obj2) {
        if(obj1.getClass()!=obj2.getClass()){
            throw new RuntimeException("obj1.class!=obj2.class");
        }
        if(obj1.getClass()== Phone.class){
            Phone p1 = (Phone)obj1;
            Phone p2 = (Phone)obj2;
            return p1.price-p2.price;
        }

        return 1;
    }
}

