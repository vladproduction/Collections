package com.vladproduction.app02.list.arraylist.compare_example;

import java.util.ArrayList;
import java.util.List;

public class MainCompare {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Adel", 21));
        students.add(new Student("Garry", 20));
        students.add(new Student("Shelly", 25));
        students.add(new Student("Shelly", 19));

        students.sort(null);
        students.forEach(System.out::println);

    }
}
