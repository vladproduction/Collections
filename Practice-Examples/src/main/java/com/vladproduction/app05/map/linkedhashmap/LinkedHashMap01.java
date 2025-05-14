package com.vladproduction.app05.map.linkedhashmap;


import java.util.LinkedHashMap;
import java.util.Set;

public class LinkedHashMap01 {

    public static void main(String[] args) {
        //(String, int)
        LinkedHashMap map = new LinkedHashMap();

        map.put("sheva", 7);
        map.put("lewa", 7);
        map.put("messi", 10);

        System.out.println(map);
        Object oldValue = map.put("Rebrov", 11);
        System.out.println("oldValue for key Rebrov = "+oldValue);
        System.out.println(map);
        oldValue = map.put("sheva", 10);
        System.out.println("oldValue for key sheva = "+oldValue);
        System.out.println(map);
        System.out.println("-----get value by key-----");
        Object value = map.get("lewa");
        System.out.println("get(lewa)="+value);
        int size=map.size();
        boolean isEmpty = map.isEmpty();
        System.out.println("size="+size);
        System.out.println("isEmpty="+isEmpty);
        System.out.println("-------remove sheva-----");
        map.remove("sheva");
        size=map.size();
        System.out.println("size="+size);
        System.out.println(map);
        System.out.println("----------check all items of map-----------");
        Set keys = map.keySet();
        for(Object key: keys){
            Object valueByKey = map.get(key);
            System.out.println(key+"="+valueByKey);
        }
        System.out.println("----------get by non existing key-------------");
        Object zinaNumber = map.get("zina");
        System.out.println("get by zina="+zinaNumber);
        System.out.println("----------remove all-------------");
        map.clear();
        System.out.println(map);

    }

}
