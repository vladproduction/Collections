package com.vladproduction.app03.set.hashset.demohashset;

public class Dog {
    public int age;
    public String name = null;

    public String toString(){
        return "(age="+age+"; name="+name+")";
    }

    public int hashCode(){
        int nameHashCode = 0;
        if(name!=null){
            nameHashCode = name.hashCode();
        }
        return age+nameHashCode;
    }

    public boolean equals(Object other){
        if(other==null){
            return false;
        }
        if(other==this){
            return true;
        }
        if(other.getClass()==Dog.class){//other.getClass()==this.getClass()
            Dog otherDog = (Dog) other;
            if(otherDog.age!=age){
                return false;
            }
            if(name==null && otherDog.name==null){
                return true;
            }
            if(name!=null){
                return name.equals(otherDog.name);
            }
            //if(otherDog.name!=null){
              //  return otherDog.name.equals(name);
           // }
            return false;
        }
        return false;
    }
}
