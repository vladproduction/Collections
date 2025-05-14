package com.vladproduction.app03.set.hashset.app03;

import java.util.Objects;

public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //standard implementation of equals and hashCode:
//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//        Person person = (Person) object;
//        return age == person.age && Objects.equals(name, person.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(age, name);
//    }

        public int hashCode(){
        int hash = 0;
        if(name!=null){
            hash=hash+name.hashCode();
        }
        hash = hash+age;
        return hash;
    }

    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj==this){
            return true;
        }
        if(obj.getClass()== Person.class){
            Person otherPerson = (Person) obj;
            if(age!=otherPerson.age){
                return false;
            }
            if(name==null && otherPerson.name==null){
                return true;
            }
            if(name!=null){
                return name.equals(otherPerson.name);
            }
            if(otherPerson.name!=null){
                return otherPerson.name.equals(name);
            }

        }
        return false;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
