package com.vladproduction.map;

public class Main_Impl_2 {
    public static void main(String[] args) {
        MyMap map = new MyMapImpl2();
        map.put("player1",1);
        map.put("player2",2);
        map.put("player3",3);
        map.put("player4",4);
        map.put("player5",5);
        map.put("player6",6);
        map.put("player7",7);
        Object oldValue = map.put("player7",77);


    }
}
