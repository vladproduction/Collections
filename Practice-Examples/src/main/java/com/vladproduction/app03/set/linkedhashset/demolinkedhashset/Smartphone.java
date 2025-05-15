package com.vladproduction.app03.set.linkedhashset.demolinkedhashset;

public class Smartphone {
    public int price;

    public String toString(){
        return "Smartphone="+price;
    }

    public int hashCode(){
        return price;
    }
}
