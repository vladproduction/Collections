package com.vladproduction.ch07_generic.p04_bounds;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        System.out.println("\nUpper Bounds Example:");
        UpperBoundedContainer<Integer> intNum = new UpperBoundedContainer<>(100);
        UpperBoundedContainer<Double> doubleNum = new UpperBoundedContainer<>(99.5);

        System.out.println("Integer value: " + intNum.getValue());
        System.out.println("Double value: " + doubleNum.getDoubleValue());
        System.out.println("Integer > Double: " + intNum.isGreaterThan(doubleNum));

        System.out.println("\nLower Bounds Example:");
        List<Number> numberList = new ArrayList<>();
        //List<Double> numberList = new ArrayList<>();      // Double is not a superclass of Number
        LowerBoundedContainer.addNumber(numberList);        //this method accepts Integer + its superclass Number + its superclass Object
        LowerBoundedContainer.printList(numberList);


        System.out.println("\nMultiple Bounds Example:");
        MultiBoundedContainer.NamedNumber namedNumber = new MultiBoundedContainer.NamedNumber(123);
        MultiBoundedContainer<MultiBoundedContainer.NamedNumber> multiBound = new MultiBoundedContainer<>(namedNumber);
        multiBound.show();
        System.out.println("NamedNumber as double: " + multiBound.getValue().doubleValue());
        System.out.println("NamedNumber as int: " + multiBound.getValue().intValue());
        System.out.println("NamedNumber as long: " + multiBound.getValue().longValue());
        System.out.println("NamedNumber as float: " + multiBound.getValue().floatValue());
        System.out.println("NamedNumber as short: " + multiBound.getValue().shortValue());

    }
}
