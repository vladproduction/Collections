package com.vladproduction.app02.list.arraylist.compare_example;

public class Student implements Comparable<Student>{

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        int k = name.compareTo(o.name); //compare by name
        if (k != 0) return k; //not equal return
        return Integer.compare(age, o.age); //in other cases compare by age
    }
}
