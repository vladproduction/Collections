package com.vladproduction.app05.map.hashmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Main03 {

    public static void main(String[] args) {

        Map hashtable = new Hashtable();
        //hashtable.put("a", null);


        Map map = new HashMap();
        map.put("a", "A");
        map.put("b", "B");
        map.put("c", "C");
        map.put("d", null);

        Object value = map.get("b");
        System.out.println("value="+value);

        Object value2 = map.get("b1");
        System.out.println("value2="+value2);

        System.out.println("--------------------------");
        Set keys = map.keySet();
        for(Object key: keys){
            Object val = map.get(key);
            System.out.println(key+"="+val);
        }
        System.out.println("--------------------------");
        for(Object key: map.keySet()){
            System.out.println(key+"="+map.get(key));
        }
    }

}
