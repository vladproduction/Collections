package com.vladproduction.app11.generics.basics;

public class BoundedUpper {

    public static void main(String[] args) {
        Calculator<Integer> calcIntegers = new Calculator<>();
        double squareInt = calcIntegers.square(10);
        System.out.println(squareInt);

        System.out.println("======");

        Calculator<Double> calcDouble = new Calculator<>();
        double squareDouble = calcDouble.square(20.0);
        System.out.println(squareDouble);

        System.out.println("======");

        Calculator<Float> calcFloat = new Calculator<>();
        double squareFloat = calcFloat.square(30.0f);
        System.out.println(squareFloat);

    }


    static class Calculator<T extends Number> {

        public double square(T x) {
            return x.doubleValue() * x.doubleValue();
        }

    }
}
