package com.vladproduction.app10.cloning;

public class AddressDeepCopy {

    String city;

    public AddressDeepCopy(String city) {
        this.city = city;
    }


    static class Person implements Cloneable {
        String name;
        AddressDeepCopy addressDeepCopy;

        public Person(String name, AddressDeepCopy addressDeepCopy) {
            this.name = name;
            this.addressDeepCopy = addressDeepCopy;
        }

        @Override
        public Person clone() {
            try {
                Person cloned = (Person) super.clone();
                cloned.addressDeepCopy = new AddressDeepCopy(this.addressDeepCopy.city); // DEEP COPY
                return cloned;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("Alice", new AddressDeepCopy("NY"));
        Person p2 = p1.clone();

        p2.addressDeepCopy.city = "LA"; // Only affects p2

        System.out.println(p1.addressDeepCopy.city); // NY
    }


}
