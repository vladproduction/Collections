package com.vladproduction.app05.map.hashmap;

/*•Implementation of Collections.Map (AbstractMap)
•Collections of elements and keys in heap
•Multiple data types
•Type safety through generics
•Variable size
•Non-thread safe (Vector)
•No order

Methods:
•put()•putAll ()•get()•remove()•keySet()•entrySet()•isEmpty ()

•size ()•clear ()•containsKey ()•containsValue()

*/

import java.util.Arrays;
import java.util.HashMap;

public class HashMap02 {
    public static void main(String[] args) {
        HashMap< Integer , String > hashMap = new HashMap < Integer , String >();
        hashMap. put (1995 , " Java");
        hashMap. put (2011 , " Ceylon ");
        hashMap. put (2012 , "kotlin ");

        HashMap < Integer , String > map = new HashMap < Integer , String >();
        map. putAll( hashMap );

        String JavaLang = map. get (1995); // Java
        System.out.println(JavaLang);

        map. remove (1995);// Java
        System . out. println ( Arrays. asList( map )); //[{2011= Ceylon , 2012= Kotlin }]






    }
}
