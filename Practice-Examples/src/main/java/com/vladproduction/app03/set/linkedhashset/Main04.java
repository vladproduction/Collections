package com.vladproduction.app03.set.linkedhashset;

import java.util.LinkedHashSet;

public class Main04 {

    public static void main(String[] args) {
        LinkedHashSet set = new LinkedHashSet();
        MyPhone lg = new MyPhone();
        lg.price=1000;

        MyPhone htc = new MyPhone();
        htc.price=1000;

        System.out.println("lg.hashCode="+lg.hashCode());
        System.out.println("htc.hashCode="+htc.hashCode());
        boolean isEqual = lg.equals(htc);
        System.out.println("isEqual="+isEqual);
        System.out.println("--------add--------------");

        set.add(lg);
        set.add(htc);

        System.out.println("set.size="+set.size());
        System.out.println("set.isEmpty()="+set.isEmpty());
        System.out.println("----------iterator for each--------");
        int step = 0;
        for(Object item : set){
            //System.out.println(item);
            System.out.println(step+"="+item.toString());
            step++;
        }

        System.out.println("---------add item, but with other price--------------");
        MyPhone samsung = new MyPhone();
        samsung.price=2000;
        set.add(samsung);
        step = 0;
        for(Object item: set){
            System.out.println(step+"="+item.toString());
            step++;
        }
        System.out.println("set.size()="+set.size());


    }

}
