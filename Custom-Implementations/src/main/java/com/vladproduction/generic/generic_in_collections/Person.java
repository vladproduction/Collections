package com.vladproduction.generic.generic_in_collections;

import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        Objects.nonNull(name);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        int rez = name.hashCode();
        rez = 31 * rez + age;
        return rez;
    }

    @Override
    public String toString() {
        return "Person: " + name +", "+ age;
    }
}
