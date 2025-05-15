package com.vladproduction.app03.set.hashset;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetCorrectMutationP2 {
    public static void main(String[] args) {

        Set<Person> persons = new HashSet<>();

        Person p1 = new Person("John", 20);
        Person p2 = new Person("John", 20); // logically equal to p1
        Person p3 = new Person("Jane", 21);

        // Step 1: Initial add
        persons.add(p1);
        persons.add(p2); // not added (equals p1)
        persons.add(p3);

        System.out.println("--- Initial Set Content ---");
        System.out.println("Persons: " + persons);

        // Step 2: Mutate p2 to make it unique
        System.out.println("--- Mutate p2 ---");
        p2.setName("Johnny");
        System.out.println("New hashCode of p2: " + p2.hashCode());
        System.out.println("p2 equals p1? " + p2.equals(p1)); // false now

        // Step 3: Add mutated p2
        persons.add(p2); // accepted now
        System.out.println("--- Final Set Content ---");
        System.out.println("Persons: " + persons);
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public void setName(String name) { this.name = name; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person other = (Person) obj;
            return age == other.age && Objects.equals(name, other.name);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + (name == null ? 0 : name.hashCode());
            result = 31 * result + age;
            return result;
        }

        @Override
        public String toString() {
            return String.format("(%s, age: %d)", name, age);
        }
    }


}
