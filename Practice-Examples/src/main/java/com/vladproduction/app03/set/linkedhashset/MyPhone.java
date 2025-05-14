package com.vladproduction.app03.set.linkedhashset;

public class MyPhone extends Object {

    public int price;

    public String toString(){
        return "MyPhone="+price;
    }

    public int hashCode(){
        return price;
    }

    public boolean equals(Object other){
        if(other==null){
            return false;
        }
        if(other==this){
            return true;
        }
        if(other.getClass() == MyPhone.class){
            //Object has no field price
            //other.price++;
            MyPhone otherMyPhone = (MyPhone) other;
            boolean isEqual = (otherMyPhone.price == this.price);
            return isEqual;
        }
        return false;
    }
}
