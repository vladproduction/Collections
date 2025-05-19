package com.vladproduction.app10.cloning;

public class AddressShallowCopy {
    String city;

    public AddressShallowCopy(String city) {
        this.city = city;
    }


    static class Person implements Cloneable {
        String name;
        AddressShallowCopy addressShallowCopy;

        public Person(String name, AddressShallowCopy addressShallowCopy) {
            this.name = name;
            this.addressShallowCopy = addressShallowCopy;
        }

        @Override
        public Person clone() {
            try {
                return (Person) super.clone(); // SHALLOW COPY
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("Alice", new AddressShallowCopy("NY"));
        Person p2 = p1.clone();

        p2.addressShallowCopy.city = "LA"; // Affects p1 too!

        System.out.println(p1.addressShallowCopy.city); // LA
    }

}


