package com.vladproduction.generic.extending;

import com.vladproduction.generic.MyListGeneric;
import com.vladproduction.generic.MyListGenericImpl;

public class Main_ch01 {
    public static void main(String[] args) {
        MyListGeneric<Parent> generic1 = new MyListGenericImpl<>();
        generic1.add(new Parent());
        generic1.add(new Child1());
        generic1.add(new Child2());
        for (int i = 0; i < generic1.size(); i++) {
            Parent item = generic1.get(i);
            System.out.println(item);
        }
        Parent [] array = new Parent[5];
        Parent [] array1 = new Child1[3];
        Parent [] array2 = new Child2[3];

        System.out.println("array");
        array[0] = new Parent();
        array[1] = new Child1();
        array[2] = new Child2();

        System.out.println("array1");
         //array1[0] = new Parent();// Error// --> мы не можем в массив child добавить parent.
        //на этапе компеляции мы считаем что array1 это массив из parent
        //поэтому в него можно добавлять parent, child`s, но ошибка будет во время работы программы
        //потому что во время работы программа смотрит на правую часть выражения:
        // Parent [] array1 = new Child1[3];
        //и понимает что array1 это массив из child, и выбрасывает ArrayStoreException (RuntimeException)
        array1[1] = new Child1();
        //array1[2] = new Child2();// Error

        System.out.println("array2");
        //array2[0] = new Parent();// Error
        //array2[1] = new Child1();// Error
        array2[2] = new Child2();

    }

    private static void addContent(MyListGeneric myList, String item){
        myList.add(item);
    }
}
