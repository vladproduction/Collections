package com.vladproduction.app11.generics.basics;

public class GenericClassExample {

    public static void main(String[] args) {

        Box<String> box = new Box<String>();
        box.setValue("Hello"); //box can hold Strings only
        String value = box.getValue(); // No casting needed
        System.out.println(value);
    }

    static class Box<T>{
        private T value;

        private void setValue(T value){
            this.value = value;
        }

        public T getValue(){
            return value;
        }

    }
}
