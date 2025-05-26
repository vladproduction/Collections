package com.vladproduction.generic.generic_in_collections;

import java.util.Arrays;

public class Array_problems {
    public static void main(String[] args) {
        Car bmw = new Car("bmw",1000);
        Car audi = new Car("audi",2000);
        Car mercedes = new Car("mercedes",3000);

        Car [] salon = {bmw,audi,mercedes};
        System.out.println(Arrays.toString(salon));

        salon = add(new Car("ford",6500),salon);
        System.out.println(Arrays.toString(salon));

        salon = add(bmw,salon);
        System.out.println(Arrays.toString(salon));

    }

    private static Car [] add(Car car, Car [] array){
        int length = array.length;
        Car [] newArray = Arrays.copyOf(array,length+1);
        newArray[length] = car;
        return newArray;
    }
}
