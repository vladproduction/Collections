package com.vladproduction.app03.set.treeset.app03;



import java.util.TreeSet;

/*
    Exception in thread "main" java.lang.ClassCastException: com.app02.Phone
    cannot be cast to java.lang.Comparable
 */
public class MyApp03 {

    public static void main(String[] args) {
        Phone a = new Phone();
        a.price=500;

        Phone b = new Phone();
        b.price=2000;

        Phone c = new Phone();
        c.price=1000;

        //rules to make compare between 2 Phones
        MyPhoneComparator comparator = new MyPhoneComparator();
        TreeSet set = new TreeSet(comparator);

        set.add(a);
        set.add(b);
        set.add(c);

        System.out.println(set);
        Phone d = new Phone();
        d.price=500;
        System.out.println(set);

    }

}
