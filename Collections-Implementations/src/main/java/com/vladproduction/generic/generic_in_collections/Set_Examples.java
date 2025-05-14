package com.vladproduction.generic.generic_in_collections;

import java.util.HashSet;
import java.util.Set;

public class Set_Examples
{
    public static void main(String[] args)
    {
        Car bmw = new Car("bmw", 1000);
        Car audi = new Car("audi", 2000);
        Car mercedes = new Car("mercedes", 3000);


        Set<Car> carSet = new HashSet<>();

        // add elements in the same way as a List
        carSet.add(bmw);
        carSet.add(audi);
        carSet.add(mercedes);
        System.out.println(carSet);

        System.out.println("bmw in set? " + carSet.contains(bmw));
        System.out.println("audi in set? " + carSet.contains(audi));
        System.out.println("mercedes in set? " + carSet.contains(mercedes));

        // Only stores unique members
        carSet.add(mercedes);
        System.out.println(carSet);
        System.out.println(carSet.size());

        // NB: Hashcode/Equals

        // Other collections API stuff applies
        for (Car items : carSet){
            System.out.println(items);
        }

        // No order
        //carSet.get(0);
    }
}
