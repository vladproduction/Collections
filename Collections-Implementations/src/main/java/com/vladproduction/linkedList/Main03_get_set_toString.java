package com.vladproduction.linkedList;

public class Main03_get_set_toString {
    public static void main(String[] args) {
        MyList list = new MyLinkedListImpl();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println("get(int index)");
        System.out.println(list.get(0)); // "a"
        System.out.println(list.get(2)); // "c"
        System.out.println(list.get(4)); // "e"
        System.out.println();
        System.out.println("-------set(int index, Object newObject):-------");
        System.out.println(list);
        System.out.println(list.size());
        list.set(0,"A0");
        list.set(2,"C2");
        list.set(4,"E4");
        System.out.println("After set(): ");
        System.out.println(list);
        System.out.println(list.size());

    }
}
