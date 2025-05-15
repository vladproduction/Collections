package com.vladproduction.app03.set.linkedhashset.demolinkedhashset;

public class Main04Sample {

    public static void main(String[] args) {
        MyPhone htc = new MyPhone();
        htc.price=1000;

        //IS-A; MyPhone IS-A Object
        //left side - parent
        //right side - child
        Object lg = new MyPhone();

        MyPhone lgPhone = (MyPhone) lg;
        lgPhone.price=1000;



    }

}
