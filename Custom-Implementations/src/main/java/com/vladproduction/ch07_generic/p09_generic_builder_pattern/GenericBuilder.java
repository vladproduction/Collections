package com.vladproduction.ch07_generic.p09_generic_builder_pattern;


import java.util.HashMap;
import java.util.Map;

/**
 * Generic Builder class that can build any type of object
 * */
public class GenericBuilder<T> {

    private final Class<T> clazz;
    private final Map<String, Object> properties = new HashMap<>();

    public GenericBuilder(Class<T> clazz) {
        this.clazz = clazz;
    }

    public GenericBuilder<T> with(String property, Object value){
        if(property == null || value == null){
            throw new IllegalArgumentException("Property and value cannot be null");
        }
        properties.put(property, value);
        return this;
    }

    public T build(){
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                clazz.getDeclaredMethod("set" + entry.getKey(), entry.getValue().getClass()).invoke(instance, entry.getValue());
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Error creating object of type " + clazz.getName(), e);
        }
    }

    public static <T>GenericBuilder<T> of(Class<T> clazz){
        return new GenericBuilder<>(clazz);
    }

}

























