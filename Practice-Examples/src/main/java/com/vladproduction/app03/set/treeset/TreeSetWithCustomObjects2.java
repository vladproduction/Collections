package com.vladproduction.app03.set.treeset;

import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

public class TreeSetWithCustomObjects2 {
    public static void main(String[] args) {

        // 1. TreeSet using Comparable (natural order: by age ascending)
        TreeSet<Person> peopleByAge = new TreeSet<>(Comparator.comparing(Person::getAge).thenComparing(Person::getName));
        peopleByAge.add(new Person("Alice", 30));
        peopleByAge.add(new Person("Bob", 25));
        peopleByAge.add(new Person("Charlie", 35));
        peopleByAge.add(new Person("Bob", 25)); // Duplicate by age — will be ignored

        System.out.println("People sorted by age (natural order):");
        for (Person p : peopleByAge) {
            System.out.println(p);
        }

        /// //////////////////////////////////
        TreeSet treeSet = new TreeSet();
        treeSet.add("3");
        treeSet.add(1);
        treeSet.add(2);

        System.out.println(treeSet);

        /// /////////////////////////////////

//        // 2. TreeSet using Comparator (order by name)
//        TreeSet<Person> peopleByName = new TreeSet<>(Comparator.comparing(Person::getName));
//        peopleByName.add(new Person("Alice", 30));
//        peopleByName.add(new Person("Bob", 25));
//        peopleByName.add(new Person("Charlie", 35));
//        peopleByName.add(new Person("Bob", 40)); // "Bob" already exists by name → treated as duplicate
//
//        System.out.println("\nPeople sorted by name (custom Comparator):");
//        for (Person p : peopleByName) {
//            System.out.println(p);
//        }

    }

    static class Person {
        String name;
        int age;

        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return String.format("(%s, age: %d)", name, age);
        }

//        // Used by TreeSet if no Comparator is provided
//        @Override
//        public int compareTo(Person o) {
//            return Integer.compare(age, o.age); //sort by age
//        }
    }

}
