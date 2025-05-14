package com.vladproduction.app09.equals_hashcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Demo_EqualsHashCode {
    public static void main(String[] args) {

        Map<Person, String> map = new HashMap<>();
        Set<Person> set = new HashSet<>();

        Person person1 = new Person(1, "Tom");
        Person person2 = new Person(1, "Tom");
//        Person person2 = new Person(2, "Bob");

        map.put(person1, "Person1");
        map.put(person2, "Person2");

        set.add(person1);
        set.add(person2);

        /*
        equals and hashCode is not overridden yet:
        comparing objects is going by memory location
        */
        System.out.println(map); //{Person{id=1, name='Tom'}=Person1, Person{id=2, name='Bob'}=Person2}
        System.out.println(set); //[Person{id=1, name='Tom'}, Person{id=2, name='Bob'}]

        /**
         * after overridden:
         * equals and hashCode return comparing by behaviour (fields are equal and hashCode is the same):
         * {Person{id=1, name='Tom'}=Person2}
         * [Person{id=1, name='Tom'}]
         * */
    }
}
