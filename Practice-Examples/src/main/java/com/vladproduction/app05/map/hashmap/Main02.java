package com.vladproduction.app05.map.hashmap;

import java.util.HashMap;
import java.util.Map;

public class Main02 {

    public static void main(String[] args) {

        Map map = new HashMap();
        Person p1 = new Person("TestName", 10);
        Person p2 = new Person("TestName", 10);

        System.out.println("p1.hashCode="+p1.hashCode());
        System.out.println("p2.hashCode="+p2.hashCode());

        Object oldValue = map.put("a", p1);
        System.out.println("oldValue="+oldValue);
        Object currentValue = map.get("a");
        System.out.println("currentValue.hash="+currentValue.hashCode());
        System.out.println("--------------------");
        oldValue = map.put("a", p2);
        System.out.println("oldValue.hash="+oldValue.hashCode());
        currentValue = map.get("a");
        System.out.println("currentValue.hash="+currentValue.hashCode());

    }

}
