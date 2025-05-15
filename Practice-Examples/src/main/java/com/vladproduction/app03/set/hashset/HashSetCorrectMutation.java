package com.vladproduction.app03.set.hashset;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetCorrectMutation {
    public static void main(String[] args) {

        // Step 1: Initialize and Add Persons
        Set<Person> persons = new HashSet<>();
        Person p1 = new Person("John", 20);
        Person p2 = new Person("John", 20); // logically equal to p1
        Person p3 = new Person("Jane", 21);

        persons.add(p1);
        persons.add(p2); // not added ❌ (equals p1)
        persons.add(p3);

        System.out.println("--- HashCodes ---");
        System.out.println("p1: " + p1.hashCode());
        System.out.println("p2: " + p2.hashCode());
        System.out.println("p3: " + p3.hashCode());

        System.out.println("--- Initial Contains Check ---");
        System.out.println("Contains p1: " + persons.contains(p1));
        System.out.println("Contains p2: " + persons.contains(p2)); // true, because it’s equal to p1
        System.out.println("Contains p3: " + persons.contains(p3));

        System.out.println("--- Initial Set Content ---");
        System.out.println("Persons: " + persons);

        // Step 2: Mutate p1 safely
        System.out.println("--- Safe Mutation of p1 ---");
        if (persons.contains(p1)) {
            persons.remove(p1); // remove using original hash
            System.out.println("p1 removed successfully.");
        } else {
            System.out.println("WARNING: p1 not found for removal!");
        }

        // Safe to mutate after removal
        p1.setName("Johnny");
        System.out.println("p1 mutated to: " + p1);

        // Re-add mutated object
        persons.add(p1);
        System.out.println("p1 re-added to the set.");

        System.out.println("--- Final Set Content ---");
        System.out.println("Persons: " + persons);

        System.out.println("--- Final Contains Check ---");
        System.out.println("Contains p1: " + persons.contains(p1)); // true
        System.out.println("Contains p2: " + persons.contains(p2)); // false — p1 changed
        System.out.println("Contains p3: " + persons.contains(p3)); // true


    }

    static class Person{
        private  String name;
        private  int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person() {
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        //standard implementation of equals and hashCode
        /*@Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }*/

        //custom implementation of equals and hashCode
        public int hashCode(){
            int result = 17;
            result = 31 * result + (name == null ? 0 : name.hashCode());
            result = 31 * result + age;
            return result;
        }

        public boolean equals(Object obj){
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person other = (Person) obj;
            return age == other.age && Objects.equals(name, other.name);
        }

        @Override
        public String toString() {
            return String.format("(%s, age: %d)", name, age);
        }
    }

}
