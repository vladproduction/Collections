package com.vladproduction.app05.map.hashmap.hashmap_practice;

import java.util.HashMap;
import java.util.Map;

public class HashFunction_Maps {
    public static void main(String[] args) {

        Map<Player, Team> teammates = new HashMap<>();
        teammates.put(new Player("Kroos",8,74.5), new Team("Real"));
//        teammates.put(new Player("Kazemiro",5,78.5), new Team("Real"));
//        teammates.put(new Player("Modric",10,69.5), new Team("Real"));

        //overridden hashCode() gives same hashCode
        System.out.println(new Player("Kroos",8,74.5).hashCode());
        System.out.println(new Player("Kroos",8,74.5).hashCode());
        //if we have overridden hashCode(), we got a value by this key
        //otherwise null
        System.out.println(teammates.get(new Player("Kroos",8,74.5)));

    }

    record Team(String name){}
}
