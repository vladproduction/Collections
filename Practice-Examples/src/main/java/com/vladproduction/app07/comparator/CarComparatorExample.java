package com.vladproduction.app07.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarComparatorExample {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(2010, "Dodge"));
        cars.add(new Car(2001, "Audi"));
        cars.add(new Car(2005, "BMW"));
        cars.add(new Car(2020, "Mercedes"));
        cars.add(new Car(2024, "Nissan"));

        System.out.println("sorting cars by year desc:");
        Collections.sort(cars, ((o1, o2) -> {
            if(o1.getYear() > o2.getYear()) return 1;
            if(o1.getYear() < o2.getYear()) return -1;
            else return 0;
        }));
        System.out.println(cars);
    }

    static class Car{
        private int year;
        private String brand;

        public Car(int year, String brand) {
            this.year = year;
            this.brand = brand;
        }

        public int getYear() {
            return year;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "year=" + year +
                    ", brand='" + brand + '\'' +
                    '}';
        }
    }
}
