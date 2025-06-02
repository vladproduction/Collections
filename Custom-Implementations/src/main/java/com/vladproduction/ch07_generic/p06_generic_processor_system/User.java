package com.vladproduction.ch07_generic.p06_generic_processor_system;

/**
 * Immutable User class with builder pattern
 */
public class User {

    private final String name;
    private final int age;
    private final String email;

    private User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }

    public User withName(String newName) {
        return new User(newName, this.age, this.email);
    }

    public User withAge(int newAge) {
        return new User(this.name, newAge, this.email);
    }

    @Override
    public String toString() {
        return String.format("User{name='%s', age=%d, email='%s'}", name, age, email);
    }

    public static class Builder {

        private String name;
        private int age;
        private String email;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {

            return new User(name, age, email);
        }
    }
}
