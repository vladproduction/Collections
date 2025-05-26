package com.vladproduction.generic;

public class Main2 {
    public static void main(String[] args) {
        MyListGeneric<Integer> generic1 = new MyListGenericImpl();
        generic1.add(10);
        //generic1.add("aaa");
        addContent(generic1,"aaa");
        System.out.println(generic1);
        for (int i = 0; i < generic1.size(); i++) {
            int unit = generic1.get(i);
            System.out.println(unit);
        }
    }

    private static void addContent(MyListGeneric myList, String item){
        myList.add(item);
    }
}
