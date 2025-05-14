package com.vladproduction.generic.generic_in_collections;

import java.util.Objects;

public class Car {
    private String model;
    private int price;

    public Car(String model, int price) {
        Objects.nonNull(model);
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return price == car.price && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        int rez = model.hashCode();
        rez = 31 * rez + price;
        return rez;
    }

    @Override
    public String toString() {
        return "Car: " + model +", "+ price;
    }
}
