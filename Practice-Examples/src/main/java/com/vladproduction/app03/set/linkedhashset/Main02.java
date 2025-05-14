package com.vladproduction.app03.set.linkedhashset;

import java.util.LinkedHashSet;

public class Main02 {

    public static void main(String[] args) {
        LinkedHashSet set = new LinkedHashSet();
        Phone lg = new Phone();
        lg.price=1000;

        Phone htc = new Phone();
        htc.price=1000;

        System.out.println("lg.hashCode="+lg.hashCode());
        System.out.println("htc.hashCode="+htc.hashCode());
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
    }
}
