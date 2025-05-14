package com.vladproduction.app02.list.arraylist;

import java.util.ArrayList;
import java.util.Iterator;

/*   ArrayList:
•Implementation of Collections.List
•Collections of elements in heap
•Multiple data types
•Type safety through generics
•Variable size
•Non-thread safe (Vector)
•No order
       methods:
   •add () •remove() •set() •isEmpty () •size () •clear () •contains ()
*/



public class ArrayList02 {
    public static void main(String args[]) {
        ArrayList<String> list = new ArrayList<String>();//Creating arraylist
        list.add("Mango");//Adding object in arraylist
        list.add("Apple");
        list.add("Banana");
        list.add("Grapes");
        //Printing the arraylist object
        System.out.println(list);  //[Mango, Apple, Banana, Grapes]

        //Traversing list through Iterator
        Iterator itr = list.iterator();//getting the Iterator
        while(itr.hasNext()){//check if iterator has the elements
            System.out.println(itr.next());//printing the element and move to next

            //Mango
            //Apple
            //Banana
            //Grapes
        }
    }
}
