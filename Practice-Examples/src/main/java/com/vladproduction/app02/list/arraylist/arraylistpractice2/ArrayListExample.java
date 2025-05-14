package com.vladproduction.app02.list.arraylist.arraylistpractice2;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
  public static void main(String[] args) {

      ArrayList<String> list = new ArrayList<String>();

      list.add("Bananas");

      Scanner scan = new Scanner(System.in);
      System.out.println("Input 'EXIT' when you have finished");
      String item = "";
      do{
        item = scan.next();
        if(!list.contains(item) && !item.equals("EXIT")){
          list.add(item);
        }
      }
      while(!item.equals("EXIT"));

      System.out.println(list);
  }
}