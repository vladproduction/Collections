package com.vladproduction.app02.list.arraylist;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayList01 {
    public static void main(String[] args) {
        //Collections.List:
        //1) ArrayList
        //2) LinkedList
        //add(object) - add to the end of [...i]
        //Object item = get(i) - return i item
        //Object old = set(i, Object) - replaces  value on i position; new Value is Object;
               //Also return Object with oldValue in this position
        //remove(i) - removes item in position i
        //clear() - remove all items
        //boolean isEmpty() --> true is size==0
        //int size()--> ~array.length --> returns current amount/count size of items in Collections.List
        //------------------------------------------------------------------------------------

        ArrayList arrayData = new ArrayList();
        LinkedList linkedData = new LinkedList();
        System.out.println("isEmpty="+arrayData.isEmpty());
        System.out.println("isEmpty="+linkedData.isEmpty());
        System.out.println("----------------");

        List data = new ArrayList();
        data.add(0);
        data.add(1);
        data.add(2);
        System.out.println(data);
        int size = data.size();
        System.out.println("size="+size);
        boolean isEmpty = data.isEmpty();
        System.out.println("isEmpty="+isEmpty);
        data.add(3);
        System.out.println(data);
        size = data.size();
        System.out.println("size="+size);

        System.out.println("------------Get data from list------------");
        System.out.println();
        System.out.println(data);
        Object item = data.get(0);
        System.out.println("item get(0)="+item);
        item = data.get(1);
        System.out.println("item get(1)="+item);
             // how to get last item
        int arraySize = data.size();
        item = data.get(arraySize-1);
        System.out.println("last item="+item);
        System.out.println();

        System.out.println("-----update/ replace---------------------");
        System.out.println(data); //[0, 1, 2, 3]
        Object oldValue = data.set(0, 20);
        System.out.println("oldValue="+oldValue);  //possible to get an old value were before set
        System.out.println(data);  //[20, 1, 2, 3]
        oldValue = data.set(0, 50);                //updating value
        System.out.println(data);
        System.out.println("oldValue="+oldValue);  // oldValue=20
        System.out.println();

        System.out.println("-----remove---------------------");
        data.remove(0);
        System.out.println(data);
        System.out.println("-----remove all---------------------");
        data.clear();
        System.out.println(data);
        System.out.println();

        System.out.println("------how to get current item from data list as ArrayList-------------");
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");
        data.add("e");
        data.add("f");
        data.add("g");
        data.add("h");
        data.add("i");

        for(int i = 0; i<data.size(); i++){ //from 0 element to the end of size by 1(one) step
            Object currentItem = data.get(i); //create a new ref as getting element (i)
            System.out.println(i+"="+currentItem); //print each (i) by laying position in list /data/
                                                   //all item we add to the ArrayList - stands to the end
        }
    }
}
