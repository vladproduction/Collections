package com.vladproduction.generic.generic_in_collections;

import java.util.ArrayList;
import java.util.List;

public class List_Example {
    public static void main(String[] args) {
        Car bmw = new Car("bmw",1000);
        Car audi = new Car("audi",2000);
        Car mercedes = new Car("mercedes",3000);

        List<Car> carList = new ArrayList<>();
        carList.add(bmw);
        carList.add(audi);
        carList.add(mercedes);

        System.out.println(carList);

        // Lists auto-resize, with generic add method:
        carList.add(new Car("toyota",4000));

        // Compile Error:
        // carList.add(new Object());

        System.out.println(carList);
        System.out.println(carList.size());

        // Lists have order, we retrieve elements by index:
        for (int i = 0; i < carList.size(); i++) {
            // Generic get method
            Car item = carList.get(i);
            System.out.println(item);
        }

        // can loop over them with a for loop:
        for (Car item: carList){
            System.out.println(item);
        }

        // Can still add duplicates though:
        carList.add(bmw);
        System.out.println(carList);
        System.out.println(carList.size());


    }
}
