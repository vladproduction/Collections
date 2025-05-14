package com.vladproduction.iterator.my_iterator03;

public class Main_ListImpl3 {
    public static void main(String[] args) {
        MyListIterator3<String> list = new MyListIteratorImpl3<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

//        Iterator<String> iteratorDefault = list.iterator();
//        while (iteratorDefault.hasNext()){
//            String value = iteratorDefault.next();
//            System.out.println(value);
//        }
        System.out.println("-------------------");
        for (String item: list) {
            if("c".equals(item)){
                list.remove(2); //it`s ConcurrentModificationException
                //versions are not same; iteratorVersion= 4 ;version=5
                System.out.println(item);
            }
        }
    }
}
