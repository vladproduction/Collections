package com.vladproduction.app03.set.linkedhashset.demolinkedhashset;

import java.util.LinkedHashSet;

public class Main03 {

    public static void main(String[] args) {

        LinkedHashSet set = new LinkedHashSet();
        Smartphone lg = new Smartphone();
        lg.price=1000;

        Smartphone htc = new Smartphone();
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

    }

}
