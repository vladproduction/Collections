package com.vladproduction.ch07_generic.p04_bounds;

public class MultiBoundedContainer<T extends Number & MultiBoundedContainer.Displayable> {

    private final T value;

    public MultiBoundedContainer(T value){
        this.value = value;
    }

    public void show(){
        value.display();
    }

    public T getValue(){
        return value;
    }


    interface Displayable{
        void display();
    }

    static class NamedNumber extends Number implements Displayable{

        private final int value;

        public NamedNumber(int value) {
            this.value = value;
        }

        @Override
        public int intValue() {
            return value;
        }

        @Override
        public double doubleValue() {
            return value;
        }

        @Override
        public long longValue() {
            return value;
        }

        @Override
        public float floatValue() {
            return value;
        }

        @Override
        public short shortValue() {
            return (short) value;
        }

        @Override
        public void display(){
            System.out.println("NamedNumber: " + value);
        }

    }

}
