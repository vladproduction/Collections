package com.vladproduction.app05.map.hashmap.hashmap_practice;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    public static void main(String[] args) {

        Map<Integer, Player> playerMap = new HashMap<>();
        playerMap.put(1, new Player("Stankovic", 5, 74));
        playerMap.put(2, new Player("Ronaldo", 9, 65));
        playerMap.put(3, new Player("Recoba", 20, 63));
        playerMap.put(3, new Player("Recoba", 20, 65)); //latest key override
        System.out.println(playerMap);
        System.out.println("----some methods---------------");
        System.out.println(playerMap.size());
        System.out.println(playerMap.get(1));
        System.out.println(playerMap.containsKey(4));
        System.out.println(playerMap.keySet());
        System.out.println(playerMap.entrySet());
        System.out.println("------remove---------");
        playerMap.remove(3);
        System.out.println("----looping through---------------");
        playerMap.entrySet().forEach(System.out::println);
        System.out.println("----looping through using lambda---------------");
        playerMap.entrySet()
                .forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
        System.out.println("----looping through using lambda but not by using entrySet---------------");
        playerMap.forEach(((key, player) -> {
            //System.out.println(key + "-" + player); //all fields
            System.out.println(key + "-" + player.getName());//concrete value (name for example)
        }));
        System.out.println("-------specifying if key is not present----------");
        System.out.println(playerMap.getOrDefault(3, new Player("Default", 100, 0.001)));
        System.out.println("-------show just a values---------------------");
        System.out.println(playerMap.values());


    }
}
