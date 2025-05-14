package com.vladproduction.app07.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExampleComparator {
    public static void main(String[] args) {

        System.out.println("-----fruits-----");
        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("mango");
        fruits.add("pineapple");
        fruits.add("mellon");
        fruits.add("banana");
        System.out.println(fruits); //[apple, mango, pineapple, mellon, banana]

        Collections.sort(fruits, new StringLengthComparator());
        System.out.println(fruits); //[apple, mango, mellon, banana, pineapple]

        System.out.println("-----numbers-----");
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(34);
        numbers.add(50);
        numbers.add(1);
        numbers.add(100);
        System.out.println(numbers); //[5, 34, 50, 1, 100]

        Collections.sort(numbers, new DescIntegerComparator());
        System.out.println(numbers); //[100, 50, 34, 5, 1]

        List<Integer> numbers2 = new ArrayList<>();
        numbers2.add(0);
        numbers2.add(304);
        numbers2.add(51);
        numbers2.add(100);
        numbers2.add(10);
        System.out.println("before sorting: " + numbers2);

        //anonymous comparator as ASC:
        Collections.sort(numbers2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 > o2) return 1;
                else if(o1 < o2) return -1;
                else return 0;
            }
        });
        System.out.println("anonymous comparator as ASC: " + numbers2);

        //or lambda expression comparator as DESC:
        Collections.sort(numbers2, (o1, o2) -> {
            if(o1 > o2) return -1;
            else if(o1 < o2) return 1;
            else return 0;
        });
        System.out.println("lambda expression comparator as DESC: " + numbers2);

    }

    static class StringLengthComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            /*
            o1 > o2  = 1;
            o1 < o2  = -1;
            o1 == o2  = 0;
            */
            if(o1.length() > o2.length()){
                return 1;
            } else if (o1.length() < o2.length()){
                return -1;
            } else {
                return 0;
            }
        }
    }

    static class DescIntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1 < o2){
                return 1;
            } else if (o1 > o2){
                return -1;
            } else {
                return 0;
            }
        }
    }
}
