package com.vladproduction.app01.array.simple_array;

import java.util.Arrays;

        //  список

public class Array {
    public static void main(String[] args) {

        int[] array = new int[3];
        array[0]=1;
        System.out.println(array.length);  // --> 3

        System.out.println("-----------");
        String[] javaLanguage = {" java", " ceylon ", " kotlin "};
        System.out.println(javaLanguage.length); // 3
        System.out.println(javaLanguage[0]);// java
        System.out.println(javaLanguage[1]);// ceylon
        System.out.println(javaLanguage[2]);// kotlin

        System.out.println("-----------");
        String [] javaLang = {" java", " ceylon ", " kotlin "};
        for( int i = 0; i< javaLang. length ; i++ )
            System . out. println (i+" ="+ javaLang [i]);

        System.out.println("-----------");
        String [] javaLanguages = {" java ", " ceylon ", " kotlin "};
        for( String lang : javaLanguages)
            System . out. println ( lang );
//                                    java
//                                    ceylon
//                                    kotlin

        System.out.println("-----------");
        String [] languages = {" java ", " ceylon ", " kotlin "};
        System . out. println ( Arrays. toString ( languages )); //[java, ceylon, kotlin]

        System.out.println("-----------");
        int[] numbers = { 6 , 9 , 1 };
        Arrays. sort( numbers );
        System.out.println(Arrays.toString(numbers));  //1, 6, 9   //Arrays are mutable

        System.out.println("-----------");
        int[] numbers2 = {2 ,7 ,10 ,16};
        Arrays.sort(numbers2);
        System . out. println ( Arrays. binarySearch(numbers2 , 16)); // 3, as position
        //•Sort before search
        //•Otherwise results are unpredictable

    }
}
