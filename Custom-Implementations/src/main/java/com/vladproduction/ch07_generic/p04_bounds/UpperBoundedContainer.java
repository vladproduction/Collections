package com.vladproduction.ch07_generic.p04_bounds;

/**
 * Generic class that accepts only Number type and its subclasses
 * Upper bound: T extends Number
 * */
public class UpperBoundedContainer<T extends Number> {

    private final T value;

    public UpperBoundedContainer(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    //can use Number methods because T extends Number
    public double getDoubleValue(){
        return value.doubleValue();
    }

    //acceptable Integer/Double/Long/Float/ because there is extending Number
    public boolean isGreaterThan(UpperBoundedContainer<? extends Number> other){
        return this.getDoubleValue() > other.getDoubleValue();
    }



}
